
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;


import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import org.slf4j.Logger;


public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";
    public static String token = "";
    private long expirationTimeInSecond = 1800;

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary( APP_SECRET );
        return new SecretKeySpec( bytes, signatureAlgorithm.getJcaName() );
    }

    /**
     * 构建jwt
     * param jwtInfo
     * param expire
     * return
     */
    private static String buildJwtToken(JwtInfo jwtInfo, int expire) {
        return Jwts.builder()
                .setHeaderParam( "typ", "JWT" )
                .setHeaderParam( "alg", "HS256" )
                .setSubject( "zj-seal-platform" )//主题
                .setIssuedAt( new Date() )//颁发时间
                .setExpiration( Date.from( Instant.now().plusSeconds( expire ) ) )//过期时间
                .claim( "accountUuid", jwtInfo.getAccountUuid() )//用户id
                .claim( "name", jwtInfo.getName() )//用户名称
                .claim( "moziUserCode", jwtInfo.getMoziUserCode() )//用户dingId
                .claim( "dingAccountId", jwtInfo.getDingAccountId() )//用户dingId
                .signWith( SignatureAlgorithm.HS256, getKeyInstance() )
                .compact();
    }

    /**
     * 从token中获取claim
     * @param token token
     * @return claim
     */
    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey( APP_SECRET.getBytes() )
                    .parseClaimsJws( token )
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            logger.error( "token解析错误", e );
            throw new IllegalArgumentException( "Token invalided." );
        }
    }

//    /**
//     * 解析token
//     * param jwtToken
//     * return
//     */
//    public static JwtInfo parseJwtToken(String jwtToken) {
//        Jws<Claims> claimsJws = Jwts.parser().setSigningKey( getKeyInstance() ).parseClaimsJws( jwtToken );
//        Claims claims = claimsJws.getBody();
//        String accountUuid = (String) claims.get( "accountUuid" );
//        String name = (String) claims.get( "name" );
//        String dingAccountId = (String) claims.get( "dingAccountId" );
//        String moziUserCode = (String) claims.get( "moziUserCode" );
//        return JwtInfo.builder().name( name ).dingAccountId( dingAccountId ).accountUuid( accountUuid ).moziUserCode( moziUserCode ).build();
//    }

    /**
     * 获取token的过期时间
     * @param token token
     * @return 过期时间
     */
    private Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken( token )
                .getExpiration();
    }

    /**
     * 判断token是否过期
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken( token );
        return expiration.before( new Date() );
    }

    /**
     * 计算token的过期时间
     * @return 过期时间
     */
    public Date getExpirationTime() {
        return new Date( System.currentTimeMillis() + this.expirationTimeInSecond * 1000 );
    }

    /**
     * 判断token是否非法
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired( token );
    }

    /**
     * 判断token是否存在与有效
     * param jwtToken
     * return
     */
    private static boolean checkJwtTToken(String jwtToken) {
        if (StringUtils.isEmpty( jwtToken )) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey( getKeyInstance() ).parseClaimsJws( jwtToken );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * param request
     * return
     */
    public static boolean checkJwtTToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader( "token" );
            if (StringUtils.isEmpty( jwtToken )) {
                return false;
            }
            Jwts.parser().setSigningKey( getKeyInstance() ).parseClaimsJws( jwtToken );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getToken(String name){
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setName(name);
        String accountUuid =TokenAccountEnum.getAcccountUUId(name);
        jwtInfo.setMoziUserCode(TokenAccountEnum.getMoziUserCode(name));
        jwtInfo.setDingAccountId(TokenAccountEnum.getDingaccountid(name));
        jwtInfo.setAccountUuid(accountUuid);
        logger.info("token "+ buildJwtToken(jwtInfo,15*60*1000));
        return buildJwtToken(jwtInfo,90*60*1000);
    }


    public static void main(String[] args) {
/*        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setName("樊丽鹃");
        jwtInfo.setDingAccountId("76450408");
        jwtInfo.setAccountUuid("68bbcc22-b145-4914-8809-fae39856e46a");
        jwtInfo.setMoziUserCode("GE_2d41d9e698ff4b3b9c4cbad77269f9a3");*/
        String token = getToken("金婵媛");
        System.out.println(token);
    }
}

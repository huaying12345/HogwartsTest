package wechat.apiobject;

import static io.restassured.RestAssured.given;

public class TokenHelper {
    public static String getAccessToken(){
        String accessToken=given()
//                    .log().all()
                .when()
                .param("corpid","wwf5b2f052d0c286c4")
                .param("corpsecret","pk9zr9yPMy-_2YBCQiuTWoafuoMn2lj7vSbZ9p-T88k")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
//                    .log().body()
                .extract()
                .response()
                .path("access_token");
        return accessToken;
    }


}

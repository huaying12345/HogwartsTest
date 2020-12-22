package demotest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DemoTest {

    public static String access_token;

    @BeforeAll
    public static void getMetgod(){
        access_token = given()
                .params("corpid","wwf5b2f052d0c286c4","corpsecret","6cL-Ur7OSNUYJkglFYl5xlwRyMeeorMy6TmU-5NmFr4")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().all()
                .extract().response().path("access_token");

    }

    @Test
    void postMethod(){
        given().contentType("application/json;charset=utf-8")
                .body("{\n" +
                "   \"touser\" : \"@all\",\n" +
                "   \"msgtype\" : \"text\",\n" +
                "   \"agentid\" : 1000002,\n" +
                "   \"text\" : {\n" +
                "       \"content\" : \"Hello World!你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                "   },\n" +
                "}")
                .queryParam("access_token",access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log().all();


    }
}

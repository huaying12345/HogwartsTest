package wechat.restassured;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoBase01 {

    public static String access_token;
    public static String departmentId;

    @BeforeAll
    public static void getAccessToken() {
        access_token = given()
                .params("corpid", "wwf5b2f052d0c286c4", "corpsecret", "pk9zr9yPMy-_2YBCQiuTWoafuoMn2lj7vSbZ9p-T88k")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().all()
                .extract().response().path("access_token");

    }


    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment() {
        String body = "{\n" +
                "   \"name\": \"广州研发中心\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";

        Response response = given().log().all()
                .contentType("application/json;charset=utf-8")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + access_token + "")
                .then().log().all()
                .extract()
                .response();
                departmentId = response.path("id").toString();

    }


    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment() {
        String body = "{\n" +
                "   \"id\": "+ departmentId+ ",\n" +
                "   \"name\": \"广州研发中心01\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": 1\n" +
                "}";

              Response response = given().log().all()
                      .contentType("application/json;charset=utf-8")
                      .body(body)
                      .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+access_token + "")
                      .then().log().all()
                      .extract()
                      .response();

    }


    @DisplayName("查询部门")
    @Test
    @Order(3)
    void queryDepartment() {

        Response response = given().log().all()
                .contentType("application/json;charset=utf-8")
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+access_token + "&id="+ departmentId)
                .then().log().all()
                .extract()
                .response();

    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment() {

        Response response = given().log().all()
                .contentType("application/json;charset=utf-8")
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token="+access_token + "&id="+ departmentId)
                .then().log().all()
                .extract()
                .response();

    }
}
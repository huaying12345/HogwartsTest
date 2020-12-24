/**
 * FileName: sendRequest
 * Author:   huaying
 * Date:     2020-12-23 19:48
 * Description:
 * version: IT2020
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


package wechat.requestutil;


import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

/**
 * @author：huaying
 * Date: 2020-12-23 19:48
 * @Description：
 */

public class RequestUtil {

    public static Response doPost(Map body, String url){
        Response response=given().log().all()
                .contentType(JSON)
                .body(body)
                .post(url)
                .then()
                .log().body()
                .extract()
                .response()
                ;
        return response;
    }

    public static Response doGet(String key,String value,String url){
        Response response=given().log().all()
                .contentType("application/json")
                .param(key,value)
                .get(url)
                .then()
                .log().body()
                .extract()
                .response()
                ;
        return response;
    }



}
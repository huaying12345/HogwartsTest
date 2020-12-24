package wechat.apiobject;

import io.restassured.response.Response;
import wechat.requestutil.RequestUtil;
import wechat.util.FakerUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DepartMentObject{
    public static Response creatDepartMent(String creatName, String creatEnName, String accessToken){
        String creatBody ="{\n" +
                "   \"name\": \""+creatName+"\",\n" +
                "   \"name_en\": \""+creatEnName+"\",\n" +
                "   \"parentid\": 1}";

        Map<String, Object>  jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", creatName);
        jsonAsMap.put("name_en", creatEnName);
        jsonAsMap.put("parentid", 1 );

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("name",  creatName);
//        jsonObject.put("name_en", creatEnName );
//        jsonObject.put("parentid", 1 );
//        String creatBody = jsonObject.toString();


        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken+"";
        Response creatResponse= RequestUtil.doPost( jsonAsMap,url );
        return creatResponse;
    }

    public static String creatDepartMent(String accessToken){
        String creatName= "name"+ FakerUtils.getTimeStamp();
        String creatEnName="en_name"+ FakerUtils.getTimeStamp();
        Response creatResponse = creatDepartMent(creatName,creatEnName,accessToken);
        String departmentId= creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;
        return departmentId;
    }
//
//    public static String creatDepartMentByRandomInt(String accessToken){
//        String creatName= "name"+ FakerUtils.getRandomInt(1000);
//        String creatEnName="en_name"+ FakerUtils.getRandomInt(1000);
//        String creatBody ="{\n" +
//                "   \"name\": \""+creatName+"\",\n" +
//                "   \"name_en\": \""+creatEnName+"\",\n" +
//                "   \"parentid\": 1}";
//        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken+"";
////        Response creatResponse= RequestUtil.doPost( creatBody,url );
////        String departmentId= creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;
//
////        return departmentId;
//    }
//    public static Response updateDepartMent(String updateName,String updateEnName,String departmentId,String accessToken){
//        String updateBody ="{\n" +
//                "   \"id\": "+departmentId+",\n" +
//                "   \"name\": \""+updateName+"\",\n" +
//                "   \"name_en\": \""+updateEnName+"\",\n" +
//                "   \"order\": 1\n" +
//                "}\n";
//        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken+"";
//
////        Response updateResponse= RequestUtil.doPost(updateBody, url );
////        return updateResponse;
//    }
    public static Response listDepartMent(String departmentId,String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken + "";

        Response listResponse = RequestUtil.doGet("id",departmentId, url );
        return listResponse;
    }
    public static Response deletDepartMent(String departmentId,String accessToken){
        Response deletResponse = given().log().all()
                .contentType("application/json")
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract().response();
        return deletResponse;
    }
}

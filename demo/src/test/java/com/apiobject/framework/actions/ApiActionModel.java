package com.apiobject.framework.actions;

import com.apiobject.framework.global.GlobalVariables;
import com.apiobject.framework.util.PlaceholderUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiActionModel {
    private String method = "get";
    private String url;
    private String body;
    private String contentType;
    private HashMap<String, String> query;
    private HashMap<String, String> headers;
    private String post;
    private String get;
    private Response response;
    private ArrayList<String> formalParam;//形参
    private HashMap<String, String> actionVariables = new HashMap<>();//实参

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contenType) {
        this.contentType = contenType;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> query) {
        this.query = query;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public ArrayList<String> getFormalParam() {
        return formalParam;
    }

    public void setFormalParam(ArrayList<String> formalParam) {
        this.formalParam = formalParam;
    }

    public Response run(ArrayList<String> actualParam) {
        HashMap<String, String> finalQuery = new HashMap<>(); //存储替换之后的参数
        String runBody = this.body;//运行时的body
        String runUrl = this.url;// 运行时的url


        /**
         1.确定请求方法和URL
         */
        if (post != null) {
            runUrl = post;
            method = "post";
        } else if (get != null) {
            runUrl = get;
            method = "get";
        }

       /*
       2. 请求参数，URL中全局变量替换
       PS：这里需要编写占位符工具类PlaceHolderUtils
        */
        if (query != null) {
            finalQuery.putAll(PlaceholderUtils.resolveMap(query, GlobalVariables.getGlobalVariables()));

        }

        //body全局变量替换
        runBody = PlaceholderUtils.resolveString(runBody, GlobalVariables.getGlobalVariables());
        //url全局 变量替换，有时候参数会写在url中
        runUrl = PlaceholderUtils.resolveString(runUrl, GlobalVariables.getGlobalVariables());

        if (formalParam != null && formalParam.size() > 0 && actualParam != null && actualParam.size() > 0) {
            /* 3.根据形参和实参构建内部变量MAP*/
            for (int i = 0; i < formalParam.size(); i++) {
                actionVariables.put(formalParam.get(i), actualParam.get(i));
            }


            /* 4.请求 url中的内部变量进行一个替换*/
            if (query != null) {
                finalQuery.putAll(PlaceholderUtils.resolveMap(query, actionVariables));
            }
            runBody = PlaceholderUtils.resolveString(runBody, actionVariables);
            runUrl = PlaceholderUtils.resolveString(runUrl, actionVariables);
        }

        /* 5.拿到了上面完成了变量替换的请求数据，接下来进行请求并返回结果*/
        RequestSpecification requestSpecification = given().log().all();
        if (contentType != null) {
            requestSpecification.contentType(contentType);
        }

        if (headers != null) {
            requestSpecification.headers(headers);
        }
        if (finalQuery != null && finalQuery.size() > 0) {
            requestSpecification.formParams(finalQuery);
        } else if (runBody != null) {
            requestSpecification.body(runBody);
        }

        Response response = requestSpecification.request(method,runUrl).then().log().all().extract().response();

        this.response = response;
        return response;
    }

}
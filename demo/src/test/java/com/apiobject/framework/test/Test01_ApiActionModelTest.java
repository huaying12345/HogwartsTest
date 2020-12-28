package com.apiobject.framework.test;

import com.apiobject.framework.actions.ApiActionModel;
import com.apiobject.framework.global.GlobalVariables;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;


import java.util.ArrayList;
import java.util.HashMap;

public class Test01_ApiActionModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test01_ApiActionModelTest.class);

    @Test
    void runTest(){
        ArrayList<String> actualParam = new ArrayList<>();
        actualParam.add("wwf5b2f052d0c286c4");
        actualParam.add("pk9zr9yPMy-_2YBCQiuTWoafuoMn2lj7vSbZ9p-T88k");


        ApiActionModel apiActionModel = new ApiActionModel();
        apiActionModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");

        HashMap<String,String> globalVariables = new HashMap<>();
        globalVariables.put("x","gettoken");
        GlobalVariables.setGlobalVariables(globalVariables);

        ArrayList<String> formalParam = new ArrayList<>();
        formalParam.add("corpid");
        formalParam.add("corpsecret");

        apiActionModel.setFormalParam(formalParam);


        HashMap<String,String> query = new HashMap<>();
        query.put("corpid","${corpid}");
        query.put("corpsecret","${corpsecret}");

        apiActionModel.setQuery(query);

        Response response = apiActionModel.run(actualParam);

    }


}

package com.apiobject.framework.test;

import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.steps.AssertModel;
import com.apiobject.framework.steps.StepModel;
import com.apiobject.framework.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test05_StepCaseTest {
    public static final Logger logger = LoggerFactory.getLogger(Test05_StepCaseTest.class);

    @BeforeAll
    static void loadTest() throws IOException {

        ApiLoader.load("src/test/resources/api");
        logger.info("debugger!");

    }

    @Test
     void apiLoadTest() throws IOException {

        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("/Users/zoe/HogwartsTest/demo/src/test/resources/testcase/creatdepartment.yaml");
        apiTestCaseModel.run();
        logger.info("debugger!");

    }



}

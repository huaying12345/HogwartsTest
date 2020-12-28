//package com.apiobject.framework.test;
//
//import com.apiobject.framework.global.ApiLoader;
//import com.apiobject.framework.steps.AssertModel;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class Test04_StepModelTest {
//    public static final Logger logger = LoggerFactory.getLogger(Test04_StepModelTest.class);
//
//    @BeforeAll
//    static void loadTest() throws IOException {
//
//        ApiLoader.load("src/test/resources/api");
//        logger.info("debugger!");
//
//    }
//
//    @Test
//    void runTest(){
//        ArrayList<String> actualParam = new ArrayList<>();
//        actualParam.add("wwf5b2f052d0c286c4");
//        actualParam.add("pk9zr9yPMy-_2YBCQiuTWoafuoMn2lj7vSbZ9p-T88k");
//
//
//        //断言
//        ArrayList<AssertModel> assertModels = new AssertModel();
//    }
//
//
//}

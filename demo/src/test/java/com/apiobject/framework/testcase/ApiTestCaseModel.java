package com.apiobject.framework.testcase;


import com.apiobject.framework.steps.StepModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.apiobject.framework.steps.StepResult;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wechat.util.FakerUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;



/*用例yaml对应的数据对象*/

public class ApiTestCaseModel {
    public static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModel.class);

    private String name;
    private String description;
    private ArrayList<StepModel> steps;
    private ArrayList<Executable> assertList = new ArrayList<>();
    private HashMap<String,String> testCaseVariables = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return /**/description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getTestCaseVariables() {
        return testCaseVariables;
    }

    public void setTestCaseVariables(HashMap<String, String> testCaseVariables) {
        this.testCaseVariables = testCaseVariables;
    }


    public static ApiTestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        return objectMapper.readValue(new File(path),ApiTestCaseModel.class);
    }


    public void run(){
        /*1.加载用例层关键字变量*/
        this.testCaseVariables.put("getTimeStamp", FakerUtils.getTimeStamp());
        logger.info("用例变量更新："+ this.testCaseVariables);

        /*2.遍历step进行执行*/
        steps.forEach(step ->{
            StepResult stepResult = step.run(testCaseVariables);

            /*3.追加处理step返回的save变量*/
            if(stepResult.getStepVariables().size()>0){
                testCaseVariables.putAll(stepResult.getStepVariables());//putAll：意思把另一个map存储到这个Map上，意味着累加所有save变量
                logger.info("tesecase变量更新："+ testCaseVariables);
            }

            /*4.处理assertList，并进行统一断言*/
            if(stepResult.getAssertList().size()>0){
                assertList.addAll(stepResult.getAssertList());//先存储assert
            }

        });

        /*5.进行统一断言*/

        assertAll("",assertList.stream());


    }


}

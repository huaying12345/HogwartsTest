package com.apiobject.framework.steps;

/*用例中的step对象*/


import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.global.GlobalVariables;
import com.apiobject.framework.util.PlaceholderUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);

     /*1、需要定义AssertModel类*/
     private String api;
     private String action;
     private ArrayList<String> actualParameter;
     private HashMap<String,String> saveGlobal;
     private HashMap<String,String> save;
     private ArrayList<AssertModel> asserts;


     //局部变量，实参也存在占位符的情况，需要替换成实参
    private ArrayList<String> finalActualParamter = new ArrayList<>();
    HashMap<String,String> stepVariables = new HashMap<>();


    /*2、需要定义StepResult类*/
    private StepResult stepResult = new StepResult();
    private ArrayList<Executable> assertList = new ArrayList<>();


    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(ArrayList<String> actualParameter) {
        this.actualParameter = actualParameter;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public ArrayList<String> getFinalActualParamter() {
        return finalActualParamter;
    }

    public void setFinalActualParamter(ArrayList<String> finalActualParamter) {
        this.finalActualParamter = finalActualParamter;
    }

    public HashMap<String, String> getStepVariables() {
        return stepVariables;
    }

    public void setStepVariables(HashMap<String, String> stepVariables) {
        this.stepVariables = stepVariables;
    }

    public StepResult run(HashMap<String,String> testCaseVariables){
        //testCaseVariables存储每个step返回的变量

        /*3、需要定义AssertModel类*/

        if(actualParameter!=null){
            finalActualParamter.addAll(PlaceholderUtils.resolveList(actualParameter,testCaseVariables));
        }

        /*4、需要case中配置的API对象和action信息，取出并执行相应的action*/
        Response response = ApiLoader.getAction(api,action).run(finalActualParamter);


        /*5、存储save*/
        if(save != null){
            save.forEach((variablesName,path)->{
                String value = response.path(path).toString();
                stepVariables.put(variablesName,value);
                logger.debug( "局部变量更新" + stepVariables);
            });
        }

        /*6、存储saveGlobal*/
        if(saveGlobal != null){
            saveGlobal.forEach((variablesName,path)->{
                String value = response.path( path.toString() );
                GlobalVariables.getGlobalVariables().put(variablesName, value );
                logger.debug( "全局变量更新" + GlobalVariables.getGlobalVariables());
            });
        }

        /*7、根据case中的配置对返回结果进行软断言，但不会终结测试将断言结果存入断言结果列表中，在用例最后进行统一结果输出*/
        if(asserts !=null){
            asserts.stream().forEach( assertModel -> {
                assertList.add( ()->{
                    assertThat(assertModel.getReason(), response.path(assertModel.getActual()).toString(), equalTo(assertModel.getExpect()));
                } );
            });
        }

        /**
         * 8、将response和断言结果存储到stepResult对象中并返回
         */
        stepResult.setAssertList(assertList);
        stepResult.setStepVariables(stepVariables);
        stepResult.setResponse(response);

        return stepResult;
    }


}

package com.apiobject.framework.global;

import java.util.HashMap;

/*用来存储全局变量*/



public class GlobalVariables {

    public static HashMap<String,String> globalVariables = new HashMap();

    public static HashMap<String, String> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, String> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }





}

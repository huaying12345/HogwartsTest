package com.ceshiren.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class BasePage {
    static BasePage instance =null;
    HashMap<String,BasePage> pages =new HashMap<>();
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
    }


    //创建一个单粒，单例存储所有对象
    public static BasePage getInstance(){
        if (instance == null){
            instance = new BasePage();
        }
        return instance;
    }


    void click(By by){
        driver.findElement(by).click();

    }

    void sendKeys(By by, String content){
        driver.findElement(by).sendKeys(content);
    }



    //name 注册的名字，className 原来的类名
    void poInit(String name,String className) throws IllegalAccessException, InstantiationException {
        try {

            //动态创建类,并实例化为一个对象
            BasePage pageClass = (BasePage)Class.forName(className).newInstance();
            pages.put(name,pageClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    BasePage getPO(String name){
        return pages.get(name);
    }

    void stepRun(String method) {
        if (method.equals("search")){
//            this.search()
        }

        Method methodJava = Arrays.stream(this.getClass().getMethods())
                .filter(m->m.getName().equals(method))
                .findFirst().get();
        try {
            methodJava.invoke(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


}

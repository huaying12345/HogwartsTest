package com.ceshiren.web;

import com.ceshiren.web.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {

    private By byPartInfo = By.cssSelector(".js_party_info");

    public ContactPage(WebDriver driver) {
        //保存driver到自己到实例
        super(driver);
    }


    public ContactPage addMember(String username, String acctid, String mobile){
        click(By.linkText("添加成员"));
        sendKeys(By.name("username"),username);
        sendKeys(By.name("acctid"), acctid);
        sendKeys(By.name("mobile"),mobile);
        click(By.linkText("保存"));

        return this;
    }

    //po原则6 添加失败返回的页面是不同，需要封装为不同的方法
    public ContactPage addMemberFail(String username, String acctid, String mobile){
        return this;
    }


    public ContactPage searchDepart(String departName) {
        //po原则1 用公共方法代表页面所提供的功能
        //po原则3  通常不要在po方法内添加断言
        sendKeys(By.id("memberSearchInput"), departName);
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
//        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }


    public String getDepartInfo(){
        String content = driver.findElement(byPartInfo).getText();
        System.out.println(content);

        return content;
    }

    //添加部门
    public ContactPage addDepart(String departName){
//        click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),departName);
        click(By.linkText("选择所属部门"));
        driver.findElements(By.linkText("人生有限公司")).get(1).click();
        click(By.linkText("确定"));

        return this;
    }


    public ContactPage deleteMember(String departName){
        searchDepart(departName);
        //todo: 删除所有的成员
        driver .findElements(By.cssSelector(".ww_checkbox")).get(1).click();
        click(By.linkText("删除"));
        click(By.linkText("确认"));

        return this;
    }

    public ContactPage clearAllDeparts(String departName){

        searchDepart(departName);
        //todo: 删除部门

        return this;
    }



    //修改部门
    public ContactPage updateDeaprt(String departName){
        click(By.linkText("修改名称"));
        sendKeys(By.name("name"),departName);
        click(By.linkText("保存"));
        return this;

    }



}


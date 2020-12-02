package com.ceshiren.web;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPOTest {


    private static MainPage mainPage;

    @BeforeAll
    static void before() throws IOException, InterruptedException {
        mainPage = new MainPage();

        //清理数据
//        mainPage.contact().clearAllDeparts("定向班四期");
    }
    //跳转页面
    //
    @Test
    void  testAddMember() throws IOException, InterruptedException {
        ContactPage contact = mainPage.contact();
        String departName = "定向班四期";
        ContactPage depart = contact.searchDepart(departName);
        contact.addMember("huyaing","huaying","16788890009");
    }


    @Test
    void  testSearchDepartChain() throws IOException, InterruptedException {
        mainPage.contact().searchDepart("demo1115").getDepartInfo().contains("无任何成员");
    }



    // todo: 部门新建 部门搜索  部门更新  部门内部添加成员  导入成员
    @Test
    void testDepartAdd(){
       String departName = "depart_1114";
       assertTrue(mainPage.contact().addDepart(departName).searchDepart(departName).getDepartInfo().contains(departName));

        }

    @Test
    void testDepartUpdate(){
        String departName = "depart_1114";
        String departUpdatedName = "depart_1114_修改";
        ContactPage updatedDepart = mainPage.contact().searchDepart(departName).updateDeaprt(departUpdatedName);
        assertTrue(updatedDepart.getDepartInfo().contains(departUpdatedName));

    }

}

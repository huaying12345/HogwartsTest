package com.ceshiren.web;


import com.ceshiren.web.BasePage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {

    private String pathname = "cookies.yaml";


    void initLogin() throws InterruptedException, IOException {

        //扫描登录
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        mapper.writeValue(new File(pathname),cookies);
        System.exit(0);

    }

    void toLogined() throws IOException, InterruptedException {
        File f = new File("cookies.yaml");

        if(f.exists()){
            //利用cookie复用session
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/frame");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference= new TypeReference<List<HashMap<String,Object>>>(){};
            List<HashMap<String,Object>> cookies = mapper.readValue(new File(pathname), typeReference);
//        System.out.println(cookies);


            cookies.forEach(cookieMap->{
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
            });

            driver.navigate().refresh();
        }else{
            initLogin();
        }
    }


    public MainPage() throws IOException, InterruptedException {
        //初始化你的selenium 复用session，打开网站
        this.toLogined();
    }

    public ContactPage contact(){
        //进入通讯录
        click(By.id("menu_contacts"));
        //传递selenium的driver给另外一个PO
        return new ContactPage(driver);
    }


}


package com.ceshiren.com;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassicContactTest {

    private static WebDriver driver ;
    private static String pathname = "cookies.yaml";

    static void initLogin() throws InterruptedException, IOException {

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


    @BeforeAll
    static void toLogined() throws IOException, InterruptedException {
        File f = new File(pathname);

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


    @Test
    void addMember(){
        click(By.linkText("添加成员"));
        sendKeys(By.name("username"),"花楹");
        sendKeys(By.name("acctid"),"huaying");
        sendKeys(By.name("mobile"),"15788909091");
        click(By.linkText("保存"));
    }

    @Test
    void searchDepart(){
        click(By.id("menu_contacts"));
        sendKeys(By.id("memberSearchInput"),"demo1115");
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));
        content = driver.findElement(By.cssSelector(".js_party_info")).getText();

        assertTrue(content.contains("无任何成员"));

    }


    @Test
    void addDepart() throws InterruptedException {
        click(By.id("menu_contacts"));
//        click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),"新建部门");
        click(By.linkText("选择所属部门"));
//        driver.findElements(By.linkText("定向班四期")).get(1).click();
//        click(By.linkText("人生有限公司"));
        driver.findElements(By.linkText("人生有限公司")).get(1).click();
        click(By.linkText("确定"));
    }



    @Test
    void deleteMember(){
        driver.findElement(By.linkText("删除")).click();

    }


    void click(By by){
        driver.findElement(by).click();

    }

    void sendKeys(By by, String content){
        driver.findElement(by).sendKeys(content);
    }
}

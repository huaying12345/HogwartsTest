package com.ceshiren.com;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class testWeb {


    private WebDriver driver = new ChromeDriver() ;

    @Test
    void testSearch(){

//        System.setProperty("webdriver.chrome.driver", "/Users/zoe/web/chromedriver");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://ceshiren.com");
        driver.findElement(By.cssSelector("#search-button")).click();
        driver.findElement(By.cssSelector("#search-term")).sendKeys("selenium");
//        driver.findElement(By.cssSelector(".search-input")).sendKeys("selenium");

    }

    @Test
    void testLogin() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        File f = new File("cookies.yaml");
        if(f.exists()){
            f.delete();
        }
        mapper.writeValue(new File("cookies.yaml"),cookies);

    }


    @Test
    public void testLogined() throws IOException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference= new TypeReference<List<HashMap<String,Object>>>(){};
        List<HashMap<String,Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
//        System.out.println(cookies);


        cookies.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });

        driver.navigate().refresh();

    }


    @Test
    public void addMember() throws IOException, InterruptedException {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference= new TypeReference<List<HashMap<String,Object>>>(){};
        List<HashMap<String,Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
//        System.out.println(cookies);

        cookies.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });

        driver.navigate().refresh();

        driver.findElement(By.xpath("//a[@node-type='addmember']"));
        driver.findElement(By.id("username")).sendKeys("花楹");
        driver.findElement(By.id("memberAdd_acctid")).sendKeys("huaying");
        driver.findElement(By.cssSelector("#qui_btn ww_btn js_btn_save"));



    }






}


//package com.ceshiren.web;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Cookie;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//public class testWeb {
//
//
//    private static WebDriver driver ;
//
//    @Test
//    void testSearch(){
//
////        System.setProperty("webdriver.chrome.driver", "/Users/zoe/web/chromedriver");
//
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("http://ceshiren.com");
//        driver.findElement(By.cssSelector("#search-button")).click();
//        driver.findElement(By.cssSelector("#search-term")).sendKeys("selenium");
////        driver.findElement(By.cssSelector(".search-input")).sendKeys("selenium");
//
//    }
//
//    @Test
//    void testLogin() throws InterruptedException, IOException {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://work.weixin.qq.com/wework_admin/frame");
//
//        Thread.sleep(15000);
//        Set<Cookie> cookies = driver.manage().getCookies();
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//
//
////        mapper.writeValue(new File(packagthname),cookies);
//
//    }
//
//
//    @BeforeAll
//    static void initLogined() throws IOException, InterruptedException {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://work.weixin.qq.com/wework_admin/frame");
//
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        TypeReference typeReference= new TypeReference<List<HashMap<String,Object>>>(){};
////        List<HashMap<String,Object>> cookies = mapper.readValue(new File(pathname), typeReference);
////        System.out.println(cookies);
//
//
////        cookies.forEach(cookieMap->{
////            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
////        });
//
//        driver.navigate().refresh();
//
//    }
//
//
//    @Test
//    public void addMember() throws IOException, InterruptedException {
//
//        driver.findElement(By.cssSelector("[node-type=addmember]")).click();
//        driver.findElement(By.cssSelector("[id=username]")).sendKeys("花楹");
//        driver.findElement(By.cssSelector("[id=memberAdd_acctid]")).sendKeys("huaying");
//        driver.findElement(By.cssSelector("[id=memberAdd_mail")).sendKeys("AAA@qq.com");
//        driver.findElement(By.linkText("保存")).click();
//
//
//    }
//
//
//
//
//
//
//}
//

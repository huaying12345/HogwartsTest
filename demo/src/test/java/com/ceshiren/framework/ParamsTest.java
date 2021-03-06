package com.ceshiren.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParamsTest {

//    private WebDriver driver;

//    @ParameterizedTest
//    @MethodSource("stringProvider")
//    void testWithExplicitLocalMethodSource(String argument) {
//        //todo：测试步骤
//        //todo：测试数据
//        //todo：断言
//        assertNotNull(argument);
//    }
//
//    static Stream<String> stringProvider() {
//        return Stream.of("apple", "banana");
//    }


//    @ParameterizedTest
//    @ValueSource(strings = {"demo1","demo2"})
//    void search(String keyword){
//        ChromeDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("http://ceshiren.com");
//        driver.findElement(By.cssSelector("#search-button")).click();
//        driver.findElement(By.cssSelector("#search-term")).sendKeys(keyword);
//    }


//    @ParameterizedTest
//    @MethodSource("search")
//    void search(String keyword){
//        ChromeDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("http://ceshiren.com");
//        driver.findElement(By.cssSelector("#search-button")).click();
//        driver.findElement(By.cssSelector("#search-term")).sendKeys(keyword);
//    }

//    static List<String> search2() throws IOException {
////        return Stream.of("apple", "banana");
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        TypeReference typeReference= new TypeReference<List<String>>(){};
//
//        List<String> keywords = mapper.readValue
//                (ParamsTest.class.getResourceAsStream("/framework/search.yaml"), typeReference);
//
//        return keywords;
//    }


    @ParameterizedTest
    @MethodSource()
    void search(TestCase testCase){
        System.out.println(testCase);
        //done:测试步骤的数据驱动
        //done: runner引擎
        testCase.run();
    }


    static List<TestCase> search() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        TestCase testCase = mapper.readValue(
                ParamsTest.class.getResourceAsStream("/framework/search_po_test.yaml"),
                SeleniumTestCase.class);

//        return Stream.of(testCase);
        return testCase.testcaseGenerate();
    }
}

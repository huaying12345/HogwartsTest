package com.ceshiren.framework;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class JavaTest {

    @Test
    void reflect() throws ClassNotFoundException {
        Class<?> str = Class.forName("java.lang.String");//动态生成类，动态找方法
        System.out.println(str.getMethods());

        Arrays.stream(str.getMethods()).forEach(m -> System.out.println(m));

    }
}

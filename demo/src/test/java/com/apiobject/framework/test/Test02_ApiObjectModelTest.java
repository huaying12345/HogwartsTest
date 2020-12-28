package com.apiobject.framework.test;

import com.apiobject.framework.api.ApiObjectModel;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Test02_ApiObjectModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test02_ApiObjectModelTest.class);

    @Test
    void loadTest() throws IOException {
        ArrayList<String> actualParam = new ArrayList<>();
        actualParam.add("wwf5b2f052d0c286c4");
        actualParam.add("pk9zr9yPMy-_2YBCQiuTWoafuoMn2lj7vSbZ9p-T88k");

        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/api/tokenhelper.yaml");
        apiObjectModel.getActions().get("getToken").run(actualParam);

    }


}

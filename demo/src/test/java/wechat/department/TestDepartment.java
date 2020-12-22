package wechat.department;



import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wechat.apiobject.DepartMentObject;
import wechat.apiobject.TokenHelper;
import wechat.util.FakerUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDepartment {
    /**
     * 1、基础脚本，分别执行了，创建、修改、查询、删除接口并进行了校验
     * 2、进行了优化，方法间进行解耦，每个方法可以独立行
     * 3、进行了优化，使用时间戳命名法避免入参重复造成的报错。
     * 4、进行了优化，每次方法执行前后都对历史数据进行清理，确保每次执行脚本数据环境一致。
     * 5、进行了优化，对脚本进行了分层，减少了重复代码，提高了代码复用率，并减少了维护成本。
     * 6、进行了优化，因为要覆盖不同的入参组合，以数据驱动的方式将入参从代码剥离。
     * 7、进行了优化，使用Junit5提供的Java 8 lambdas的断言方法，增加了脚本的容错性。
     */

    private static final Logger logger = getLogger(TestDepartment.class);
    static String accessToken;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken= TokenHelper.getAccessToken();
        logger.info(accessToken);
    }
    //    @AfterEach
//    @BeforeEach
    void clearDepartment(){
        Response listResponse =DepartMentObject.listDepartMent("1",accessToken);
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for(int departmentId : departmentIdList){
            if(1==departmentId){
                continue;
            }
            Response DelResponse = DepartMentObject.deletDepartMent(departmentId+"",accessToken);
        }
    }
    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv", numLinesToSkip = 1)
    void createDepartment(String creatName, String creatEnName, String returnCode) {
        Response response=DepartMentObject.creatDepartMent(creatName,creatEnName,accessToken);
        assertEquals(returnCode,response.path("errcode").toString());
    }
    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment() {
        String updateName = "name"+ FakerUtils.getTimeStamp();
        String updateEnName = "en_name"+FakerUtils.getTimeStamp();

        String departmentId = DepartMentObject.creatDepartMent(accessToken);

        Response updateResponse = DepartMentObject.updateDepartMent(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());

    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void listDepartment() {
        String creatName= "name"+FakerUtils.getTimeStamp();
        String creatEnName="en_name"+ FakerUtils.getTimeStamp();
        Response creatResponse = DepartMentObject.creatDepartMent(creatName,creatEnName,accessToken);
        String departmentId= creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;

        Response listResponse =DepartMentObject.listDepartMent(departmentId,accessToken);

//        assertEquals("1",listResponse.path("errcode").toString());
//        assertEquals(departmentId+"x",listResponse.path("department.id[0]").toString());
//        assertEquals(creatName+"x",listResponse.path("department.name[0]").toString());
//        assertEquals(creatEnName+"x",listResponse.path("department.name_en[0]").toString());
//
//        assertAll("查询返回值校验！",
//                ()->assertEquals("1",listResponse.path("errcode").toString()),
//                ()->assertEquals(departmentId+"x",listResponse.path("department.id[0]").toString()),
//                ()->assertEquals(creatName+"x",listResponse.path("department.name[0]").toString()),
//                ()->assertEquals(creatEnName+"x",listResponse.path("department.name_en[0]").toString())
//        );

    }
    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment() {
        String departmentId = DepartMentObject.creatDepartMent(accessToken);

        Response response = DepartMentObject.deletDepartMent(departmentId,accessToken);
        assertEquals("0",response.path("errcode").toString());

    }
}


/**
 * FileName: BaseResult
 * Author:   huaying
 * Date:     2020-12-29 11:51
 * Description:
 * version: IT2020
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


package com.apiobject.framework.global;


import io.restassured.response.Response;

/**
 * @author：huaying
 * Date: 2020-12-29 11:51
 * @Description：
 */

public class BaseResult {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
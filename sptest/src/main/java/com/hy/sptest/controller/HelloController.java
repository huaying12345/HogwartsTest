/**
 * FileName: HelloController
 * Author:   huaying
 * Date:     2021-2-23 15:49
 * Description:
 * version: IT2021
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


package com.hy.sptest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：huaying
 * Date: 2021-2-23 15:49
 * @Description：
 */

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String getHello(){
        return "hello";
    }
}
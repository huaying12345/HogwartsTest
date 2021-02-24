/**
 * FileName: LoginControll
 * Author:   huaying
 * Date:     2021-2-23 17:46
 * Description:
 * version: IT2021
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


package com.hy.sptest.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：huaying
 * Date: 2021-2-23 17:46
 * @Description：
 */

@RestController
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
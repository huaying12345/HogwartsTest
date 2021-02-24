/**
 * FileName: UserService
 * Author:   huaying
 * Date:     2021-2-24 13:52
 * Description:
 * version: IT2021
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


package com.hy.sptest.service;

import com.hy.sptest.eneity.User;

/**
 * @author：huaying
 * Date: 2021-2-24 13:52
 * @Description：定义接口
 */

public interface UserService {
    User save(User user);

    void delete(int id);

    User get(Integer id);
}
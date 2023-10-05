package com.itguigu.service;

import com.itguigu.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itguigu.utils.Result;

/**
* @author Natalia_Lai
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-09-14 18:24:14
*/
public interface UserService extends IService<User> {
    /**
     * 登录业务->记住要传参
     * @return
     */
    Result login(User user);


    /**
     * 根据token获取用户数据
     * @param token
     * @return
     */
    Result getUserInfo(String token);


    /**
     * 检查注册时的用户名是否可用
     * @param username
     * @return
     */
    Result checkUserName(String username);


    /**
     * 用户注册
     * @param user
     * @return
     */
    Result regist(User user);
}

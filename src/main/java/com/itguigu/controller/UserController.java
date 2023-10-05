package com.itguigu.controller;

import com.itguigu.pojo.User;
import com.itguigu.service.UserService;
import com.itguigu.utils.JwtHelper;
import com.itguigu.utils.Result;
import com.itguigu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin //解决跨域
public class UserController {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired //注入业务层对象
    private UserService userService;

    //登录功能实现
    @PostMapping ("login") //根据请求方法写注释
    public Result login(@RequestBody User user){ //返回值都是Result类 根据功能命名方法

        Result result =  userService.login(user); //业务层对象调用具体方法->要把user传进来
        return result;
    }

    //根据token获取用户数据
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){

        Result result = userService.getUserInfo(token);
        return result;
    }

    //注册用户名检查
    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result = userService.checkUserName(username);
        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result result = userService.regist(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration){
            //已经过期了
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}

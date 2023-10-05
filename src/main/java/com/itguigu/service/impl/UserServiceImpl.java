package com.itguigu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itguigu.pojo.User;
import com.itguigu.service.UserService;
import com.itguigu.mapper.UserMapper;
import com.itguigu.utils.JwtHelper;
import com.itguigu.utils.MD5Util;
import com.itguigu.utils.Result;
import com.itguigu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Natalia_Lai
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-09-14 18:24:14
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private UserMapper userMapper; //注入数据层对象

    @Autowired
    private JwtHelper jwtHelper;


    /**
     * 登录业务 ->本质:数据查询
     * 1.根据账户,查询用户对象->LoginUser
     * 2.如果用户对象为null->则查询失败,账号错误->返回异常501
     * 3.如果用户对象不为null,->对比密码->若失败->返回异常503
     * 4.若密码也正确->根据用户id生成一个token, token->result返回
     *
     * @return
     */
    @Override
    public Result login(User user) {
        //根据账号查询数据库
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>(); //泛型要传实体类
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);

        //账号为空
        if (loginUser == null) {//返回Result,build传参设置(null表示data为空,再根据错误传状态码)
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        //对比密码
        if (!StringUtils.isEmpty(user.getUserPwd()) //密码不为空
                && MD5Util.encrypt(user.getUserPwd()) //给密码加密
                .equals(loginUser.getUserPwd())) {//传入的用户对象密码加密后与数据库中的密码相等

            //登录成功

            //根据用户id生成token
            //将token封装到result返回
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map data = new HashMap<>();
            data.put("token",token);

            return Result.ok(data);
        }

        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    /**
     * 根据token获取用户数据
     *  1.token是否在有效期
     *  2.根据token解析userId
     *  3.根据用户id查询用户数据
     *  4.去掉密码,封装result结果返回即可
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
        //判断是否过期,返回值为true->则为过期
        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration){
            //失效,当作未登录看待
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        int userId = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(userId);

        Map data = new HashMap<>();
        data.put("loginUser",user);//已登录用户

        return Result.ok(data);
    }

    /**
     * 注册用户名检查
     *  1.根据账号进行count查询
     *  2.count==0->账号名可用
     *  3.count>0->账号名不可用
     * @param username
     * @return
     */
    @Override
    public Result checkUserName(String username) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);
        Long count = userMapper.selectCount(lambdaQueryWrapper);

        if (count==0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 注册业务
     *  1.依然检查账户是否已经被注册
     *  2.密码加密处理
     *  3.账号数据保存
     *  4.返回结果
     * @param user
     * @return
     */
    @Override
    public Result regist(User user) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(lambdaQueryWrapper);

        if (count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));//给用户密码加密
        userMapper.insert(user);//将用户对象数据插入到数据库中

        return Result.ok(null);
    }
}



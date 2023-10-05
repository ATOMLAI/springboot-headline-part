package com.itguigu.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itguigu.utils.JwtHelper;
import com.itguigu.utils.Result;
import com.itguigu.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录包含拦截器,检查请求头中是否包含有效token
 *  有且有效->放行
 *  没有或者无效->返回504
 *
 */
@Component //要放到ioc容器中
public class LoginProtectedInterceptor implements HandlerInterceptor {
    @Autowired //->爆红说明少加@Component
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String token = request.getHeader("token");
        //检查是否有效
        boolean expiration = jwtHelper.isExpiration(token);
        //有效放行
        if (!expiration){//没有过期
            return  true;
        }
        //无效返回504的状态json
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        //->自己写respond返回响应json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);

        return false;
    }
}

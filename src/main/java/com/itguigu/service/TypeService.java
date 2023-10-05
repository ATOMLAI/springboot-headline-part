package com.itguigu.service;

import com.itguigu.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itguigu.utils.Result;

/**
* @author Natalia_Lai
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-09-14 18:24:14
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询所有类别数据
     * @return
     */
    Result findAllTypes();
}

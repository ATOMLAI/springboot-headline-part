package com.itguigu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itguigu.pojo.Type;
import com.itguigu.service.TypeService;
import com.itguigu.mapper.TypeMapper;
import com.itguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Natalia_Lai
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-09-14 18:24:14
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;


    /**
     * 查询所有类别的数据
     * @return
     */
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }
}





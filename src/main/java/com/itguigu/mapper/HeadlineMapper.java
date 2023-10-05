package com.itguigu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itguigu.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itguigu.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Natalia_Lai
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-09-14 18:24:14
* @Entity com.itguigu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
        IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

        /**
         * 根据Id查询详细数据
         * @param hid
         * @return
         */
        Map queryDetailMap(Integer hid);
}





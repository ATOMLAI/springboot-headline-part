package com.itguigu.service;

import com.itguigu.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itguigu.pojo.vo.PortalVo;
import com.itguigu.utils.Result;

/**
* @author Natalia_Lai
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-09-14 18:24:14
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 首页数据查询
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 根据id查询详情
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     * 发布头条方法->数据库插入
     * @param headline
     * @return
     */
    Result publish(Headline headline,String token);

    /**
     * 修改头条数据
     * @param headline
     * @return
     */
    Result updateData(Headline headline);
}

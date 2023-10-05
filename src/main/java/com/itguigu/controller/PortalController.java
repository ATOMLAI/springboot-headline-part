package com.itguigu.controller;

import com.itguigu.pojo.vo.PortalVo;
import com.itguigu.service.HeadlineService;
import com.itguigu.service.TypeService;
import com.itguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 首页的控制层
 */

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){//创建一个实体类来接收参数->传入的是json数据
        Result result =  headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){

        Result result = headlineService.showHeadlineDetail(hid);

        return result;
    }
}

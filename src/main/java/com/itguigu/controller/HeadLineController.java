package com.itguigu.controller;

import com.itguigu.pojo.Headline;
import com.itguigu.service.HeadlineService;
import com.itguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadLineController {

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){
        Result result = headlineService.publish(headline,token);//请求头中对应的token->通过token拿到对应的用户id
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        Headline headline = headlineService.getById(hid);//->不需要去mapper层,直接在service层调用MP方法即可
        Map data = new HashMap<>();
        data.put("headline",headline);
        return Result.ok(data);
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result = headlineService.updateData(headline);//注意方法命名不要跟MybatisPlus中已有的方法名重名
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}

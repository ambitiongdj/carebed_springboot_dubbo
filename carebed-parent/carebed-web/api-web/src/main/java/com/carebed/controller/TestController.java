package com.carebed.controller;

import com.carebed.annotation.ClearAnnotation;
import com.carebed.common.core.domain.AjaxResult;
import com.carebed.interceptor.OnlineVerifyInterceptor;
import com.carebed.interceptor.RequestInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author GDJ
 * @Date 2020/06/22
 * @Version 1.0
 */
@RequestMapping("/test")
@Controller
public class TestController {

    @RequestMapping("/list")
    @ResponseBody
    @ClearAnnotation({OnlineVerifyInterceptor.class, RequestInterceptor.class})
    public AjaxResult list(){
        Map<String,Object> param = new HashMap<>();
        param.put("id",0);
        param.put("name","gdj");
        Map<String,Object> param1 = new HashMap<>();
        param1.put("id",1);
        param1.put("name","zy");
        Map<String,Object> param2 = new HashMap<>();
        param2.put("id",2);
        param2.put("name","zyn");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(param);
        list.add(param1);
        list.add(param2);
        return AjaxResult.success(list);
    }
}

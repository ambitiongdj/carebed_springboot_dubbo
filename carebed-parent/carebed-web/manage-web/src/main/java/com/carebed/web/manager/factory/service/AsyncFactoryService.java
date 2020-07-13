package com.carebed.web.manager.factory.service;

import com.carebed.manage.entity.SysLogininfor;
import com.carebed.manage.entity.SysOperLog;
import com.carebed.manage.service.ISysLogininforService;
import com.carebed.manage.service.ISysOperLogService;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service("AsyncFactoryService")
public class AsyncFactoryService {


    @Reference
    private ISysOperLogService sysOperLogService;

    @Reference
    private ISysLogininforService sysLogininforService;


    public void insertOperlog(SysOperLog operLog){
        sysOperLogService.insertOperlog(operLog);
    }

    public void insertLogininfor(SysLogininfor logininfor){
        sysLogininforService.insertLogininfor(logininfor);
    }

}

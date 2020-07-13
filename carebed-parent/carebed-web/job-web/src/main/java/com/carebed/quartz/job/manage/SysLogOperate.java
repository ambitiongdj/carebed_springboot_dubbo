package com.carebed.quartz.job.manage;


import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.manage.service.ISysJobLogService;
import com.carebed.quartz.entity.SysJobLog;
import org.springframework.stereotype.Component;

@Component
public class SysLogOperate {

    @Reference
    private ISysJobLogService logService;

    public void addJobLog(SysJobLog sysJobLog){
        logService.addJobLog(sysJobLog);
    }
}

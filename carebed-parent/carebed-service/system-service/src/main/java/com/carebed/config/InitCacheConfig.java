package com.carebed.config;

import com.carebed.manage.service.ISysConfigService;
import com.carebed.manage.service.ISysDictTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitCacheConfig {

    private static final Logger log = LoggerFactory.getLogger(InitCacheConfig.class);

    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private ISysDictTypeService sysDictTypeService;


    @PostConstruct
    public void init()
    {
        log.info("初始化字典和配置到redis--开始");
        sysConfigService.initSysConfig();
        sysDictTypeService.initSysDictType();
        log.info("初始化字典和配置到redis--完成");
    }
}

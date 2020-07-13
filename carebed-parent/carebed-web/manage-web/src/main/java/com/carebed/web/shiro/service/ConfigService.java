package com.carebed.web.shiro.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carebed.manage.service.ISysConfigService;
import org.springframework.stereotype.Service;

/**
 * RuoYi首创 html调用 thymeleaf 实现参数管理
 * 
 * @author iysk
 */
@Service("config")
public class ConfigService
{
    @Reference
    private ISysConfigService configService;

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String getKey(String configKey)
    {
        return configService.selectConfigByKey(configKey);
    }
}

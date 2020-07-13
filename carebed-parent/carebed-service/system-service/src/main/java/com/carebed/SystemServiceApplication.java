package com.carebed;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author iysk
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableDubbo
public class SystemServiceApplication
{
    private static final Logger log = LoggerFactory.getLogger(SystemServiceApplication.class);

    public static void main(String[] args)
    {
        SpringApplication.run(SystemServiceApplication.class, args);
        log.info("-------------system-service-dubbo启动成功-------------------");
    }
}
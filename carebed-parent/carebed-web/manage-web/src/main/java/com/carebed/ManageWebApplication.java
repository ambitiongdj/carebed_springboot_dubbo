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
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDubbo
public class ManageWebApplication
{
    private static final Logger log = LoggerFactory.getLogger(ManageWebApplication.class);

    public static void main(String[] args)
    {
        SpringApplication.run(ManageWebApplication.class, args);
        log.info("------------------manage-web-启动成功----------------------");
    }
}
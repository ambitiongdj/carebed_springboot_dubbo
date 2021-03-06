package com.carebed;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDubbo
public class ApiWebApplication {

    private static final Logger log = LoggerFactory.getLogger(ApiWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiWebApplication.class, args);
        log.info("-------------------api-web-启动成功---------------------");
    }
}

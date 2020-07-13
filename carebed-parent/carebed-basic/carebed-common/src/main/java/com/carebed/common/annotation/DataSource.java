package com.carebed.common.annotation;

/**
 * 自定义多数据源切换注解
 * 使用sharding-jdbc-spring-boot-starter 注释掉这个类
 * @author ruoyi
 */
/*@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited*/
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    /*public DataSourceType value() default DataSourceType.MASTER;*/
}

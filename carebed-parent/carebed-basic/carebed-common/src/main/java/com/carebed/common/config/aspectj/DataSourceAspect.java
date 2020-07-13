package com.carebed.common.config.aspectj;

/**
 * 多数据源处理
 * 使用sharding-jdbc-spring-boot-starter 注释掉这个类
 * @author iysk
 */
/*@Aspect
@Order(1)
@Component*/
public class DataSourceAspect
{
    /*protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(DataSource)"
            + "|| @within(DataSource)")
    public void dsPointCut()
    {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        DataSource dataSource = getDataSource(point);

        if (StringUtils.isNotNull(dataSource))
        {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }

        try
        {
            return point.proceed();
        }
        finally
        {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    *//**
     * 获取需要切换的数据源
     *//*
    public DataSource getDataSource(ProceedingJoinPoint point)
    {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
        if (StringUtils.isNotNull(targetDataSource))
        {
            return targetDataSource;
        }
        else
        {
            Method method = signature.getMethod();
            DataSource dataSource = method.getAnnotation(DataSource.class);
            return dataSource;
        }
    }*/
}

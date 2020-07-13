package com.carebed.config;

import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
@ConditionalOnProperty(name = "spring.redis.cluster.enabled", havingValue = "true")
public class RedisClusterConfig {
    @Autowired
    RedisProperties redisProperties;
    // 在构建LettuceConnectionFactory时，如果不使用内置的destroyMethod，可能会导致Redis连接早于其它Bean被销毁
    @Bean(destroyMethod = "destroy")
    public RedisConnectionFactory newLettuceConnectionFactory() {
        // 配置用于开启自适应刷新和定时刷新。如自适应刷新不开启，Redis集群变更时将会导致连接异常
        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .enablePeriodicRefresh(Duration.ofSeconds(3))// 开启周期刷新(默认60秒)
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(100)) // 自适应刷新超时时间(默认30秒)
                .enableAllAdaptiveRefreshTriggers()// 开启所有自适应刷新，MOVED，ASK，PERSISTENT都会触发
                .build();
        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder().validateClusterNodeMembership(false)// 取消校验集群节点的成员关系
                .topologyRefreshOptions(clusterTopologyRefreshOptions).build();
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .clientOptions(clusterClientOptions).build();
        return new LettuceConnectionFactory(getClusterConfiguration(), clientConfig);
    }
     
    private  RedisClusterConfiguration getClusterConfiguration() {
        RedisProperties.Cluster clusterProperties = redisProperties.getCluster();
        RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperties.getNodes());
        if (clusterProperties.getMaxRedirects() != null) {
            config.setMaxRedirects(clusterProperties.getMaxRedirects());
        }
        if (redisProperties.getPassword() != null) {
            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        return config;
    }
}


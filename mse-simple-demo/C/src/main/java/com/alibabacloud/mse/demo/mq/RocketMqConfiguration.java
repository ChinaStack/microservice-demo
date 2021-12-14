package com.alibabacloud.mse.demo.mq;

/**
 * @author wangbb
 * @version 1.0
 * @description: rocketmq容器加载
 * @date 2021/8/10 10:10 上午
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhujy
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RocketMqConfiguration {

    @Value("${middleware.mq.address}")
    private String nameSrvAddr;

    @Value("${rocketmq.consumer.group}")
    private String groupName;

    static {
        System.setProperty("rocketmq.client.log.loadconfig", "false");
    }

    @Bean(destroyMethod = "shutdown")
    public DefaultMQProducer getBaseProducer() throws Exception {
        log.info("正在启动rocketMq的producer");

        DefaultMQProducer baseProducer = new DefaultMQProducer();
        baseProducer.setProducerGroup(groupName);
        baseProducer.setNamesrvAddr(nameSrvAddr);
        baseProducer.start();
        log.info("完成启动rocketMq的producer");
        return baseProducer;
    }


}


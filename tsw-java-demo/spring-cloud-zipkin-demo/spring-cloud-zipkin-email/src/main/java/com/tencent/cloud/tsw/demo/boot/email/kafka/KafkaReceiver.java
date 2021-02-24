package com.tencent.cloud.tsw.demo.boot.email.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @KafkaListener(topics = "tsw-zipkin-java-gz")
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        try{
            Thread.sleep(10);
        } catch (Exception e){

        }

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            Object value = ops.get("tsw");
            LOG.info("Redis value of key[tsw] is [{}] when orderId is [{}].", value, message);
            LOG.info("Email of orderId [{}] is sent.", message);
        }

    }
}

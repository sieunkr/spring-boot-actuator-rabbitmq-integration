package spring.boot.actuator.microservice.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 *
 * MQ 채널 정의
 * 이렇게 정의하지 않으면 채널명이 기본으로 output 으로 설정 됨, 멀티 바인딩을 위해서 이렇게 정의 필요
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
public interface CustomSource {

    @Output("SOURCE-HEALTH")
    MessageChannel output();
}

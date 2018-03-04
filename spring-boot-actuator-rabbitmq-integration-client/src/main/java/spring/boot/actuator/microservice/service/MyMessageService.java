package spring.boot.actuator.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import spring.boot.actuator.microservice.model.StatusInfo;

@Service
@EnableBinding(CustomSource.class)
public class MyMessageService {

    //TODO:실제로 주입이 잘 되는데, 디버깅에서는 null로 표시, 추후에 확인 필요
    @Autowired
    CustomSource customSource;

    public void sendMessage(Health health) {
        customSource.output().send(MessageBuilder.withPayload(health).build());
    }

    public void sendMessagePojo(StatusInfo statusInfo) {
        customSource.output().send(MessageBuilder.withPayload(statusInfo).build());
    }

    public void sendMessageByte(byte[] statusInfo) {
        customSource.output().send(MessageBuilder.withPayload(statusInfo).build());
    }

    public void sendMessageString(String msg) {
        customSource.output().send(MessageBuilder.withPayload(msg).build());
    }

}

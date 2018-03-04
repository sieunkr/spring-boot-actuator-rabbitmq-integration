package spring.boot.actuator.admin.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.util.SerializationUtils;
import spring.boot.actuator.microservice.model.StatusInfo;

/**
 *
 * Spring Cloud Stream 리스너 클래스
 * Health Check 정보를 전달 받는다.
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
@EnableBinding(adminListener.Sink.class)
public class adminListener {

    //TODO:Health 데이터는 직렬화가 안되기 때문에 메시지 전달이 안됨
    /*
    @StreamListener("sink-merge")
    public void subscribe(Health health) {
    }
    */

    //TODO:StatusInfo가 직렬화가 가능한 데이터임에도 불구하고, details 정보가 있을 경우 메시지 수신이 안됨
    /*
    @StreamListener("sink-merge")
    public void subscribe2(@Payload StatusInfo statusInfo) {
    }
    */

    //TODO:직렬화 된 메시지를 받아서 역직렬화 하는 로직인데, 추후에 Pojo 에 매핑되는 구조로 개선 필요
    @StreamListener("sink-merge")
    public void subscribe2(Message<?> message) {

        StatusInfo statusInfo = (StatusInfo) SerializationUtils.deserialize((byte[])message.getPayload());

        System.out.println("수신 완료");
    }

    //채널 연동
    public interface Sink {
        @Input("sink-merge")
        SubscribableChannel HealthListener();
    }
}

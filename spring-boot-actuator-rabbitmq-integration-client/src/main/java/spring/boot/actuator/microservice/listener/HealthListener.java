package spring.boot.actuator.microservice.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 * Spring Cloud Stream 기반 메시지 리스너
 * (아직 연동 전)
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
@EnableBinding(HealthListener.Sink.class)
public class HealthListener {

    //TODO:@Async 활용하여 멀티스레드 기반 병렬화 구현 예정
    @StreamListener(Sink.inboundTest)
    public void subscribe() {

        try{
            //TODO:메시지 수신 시, Health Check 로직 수행
        }
        catch(Exception e){
        }
    }

    public interface Sink {
        String inboundTest = "SINK-HEALTH";
        @Input(Sink.inboundTest)
        SubscribableChannel HealthListener();
    }
}

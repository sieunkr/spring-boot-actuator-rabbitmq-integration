package spring.boot.actuator.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.actuator.microservice.health.CustomHealthCheck;
import spring.boot.actuator.microservice.health.CustomimplHealthCheck;
import spring.boot.actuator.microservice.model.CustomHealth;
import spring.boot.actuator.microservice.model.StatusInfo;
import spring.boot.actuator.microservice.service.MyMessageService;
import spring.boot.actuator.microservice.util.metricUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 컨트롤러 정의, Health Check 를 엔드포인트(url 끝점)에 의해서 실행가능하도록 설정
 * 해당 방법 외에, Custom endpoint 를 설정 가능(CustomEndpoint 참고)
 * 그리고, Health Check 는 엔드포인트에 의한 실행 외에, MQ 연동을 통해서 메시지 수신시에도 실행할 수 있도록 작업
 *
 * @author Eddy.Kim
 * @since
 *
 */
@RestController
public class HomeController {

    @Autowired
    private CustomHealthCheck customHealthCheck;

    @Autowired
    private CustomimplHealthCheck customimplHealthCheck;

    @Autowired
    private MyMessageService myMessageService;

    @GetMapping("/")
    public CustomHealth home(HttpServletRequest request, HttpServletResponse response){

        return null;
    }

    @GetMapping("/health/metric")
    public Health checkHealthMetric(HttpServletRequest request, HttpServletResponse response){

        myMessageService.sendMessageString(metricUtil.getMetric());

        return customHealthCheck.health();
    }

    @GetMapping("/health/custom")
    public StatusInfo checkHealthCustom(HttpServletRequest request, HttpServletResponse response){

        //TODO:메시지 전송은 잘 되지만, 메시지를 받는 쪽에서 Spring Cloud Stream 의 listener 에서 Pojo 에 맞게 매핑을 못함
        //myMessageProducer.sendMessagePojo(customHealthCheck.healthStatusInfo());

        /*
        일단, 아래와 같이 직렬화를 하여 메시지 전송한 다음에, Spring Cloud Stream 의 listener 에서는 byte[]를 다시 역직렬화해서 매핑
        직렬화,역직렬화 과정을 거치지 않고 자동으로 Pojo 에 매핑이 되는 방식으로 원하지만, 기술 검토 중
        기본적으로 Pojo 연동은 되는데, Serializable 에서 뭔가 오류가 있는 듯
        */
        myMessageService.sendMessageByte(SerializationUtils.serialize(customHealthCheck.healthStatusInfo()));

        return customHealthCheck.healthStatusInfo();
    }


    @GetMapping("/health/default")
    public Health checkHealthDefault(HttpServletRequest request, HttpServletResponse response){

        //TODO:메시지 전송 안됨, 직렬화가 안되어서??
        myMessageService.sendMessage(Health.up().build());

        return Health.up().build();
    }
}

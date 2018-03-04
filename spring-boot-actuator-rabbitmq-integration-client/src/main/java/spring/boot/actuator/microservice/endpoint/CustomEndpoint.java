package spring.boot.actuator.microservice.endpoint;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 커스텀 엔드포인트 정의
 * (actuator에서 제공해주는 끝점 외에)추가로 커스텀하게 설정해서 호출할 수 있다.
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
@Component
public class CustomEndpoint implements Endpoint<List<String>> {

    @Override
    public String getId() {
        return "customEndpoint";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return true;
    }

    @Override
    public List<String> invoke() {
        List<String> messages = new ArrayList<String>();
        messages.add("메시지 1");
        messages.add("메시지 2");

        return messages;
    }
}

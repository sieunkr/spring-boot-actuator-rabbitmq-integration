package spring.boot.actuator.microservice.health;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 커스텀 HealthCheck 방법 2
 * HealthIndicator 인터페이스 구현
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
@Component
public class CustomimplHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        //int errorCode = check(); // perform some specific health check

        int errorCode = 0;
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();

            //RabbitMQ 연동, 에러 메시지 전송

        }
        return Health.up().build();
    }

    public String check() {

        //private final List<PublicMetrics> publicMetrics;

        SystemPublicMetrics systemPublicMetrics = new SystemPublicMetrics();

        Map<String, Object> result = new LinkedHashMap<String, Object>();

        for (Metric<?> metric : systemPublicMetrics.metrics()) {
            result.put(metric.getName(), metric.getValue());
        }

        try{
            String json = new ObjectMapper().writeValueAsString(result);
            return json;
        }
        catch (Exception e){

        }

        return null;

        // Our logic to check health
        //return 0;
    }
}

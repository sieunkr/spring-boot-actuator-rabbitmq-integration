package spring.boot.actuator.microservice.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 *
 * 커스텀 HealthCheck 방법 3
 * AbstractHealthIndicator 추상클래스 상속
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
@Component
public class CustomExtendsHealthCheck extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up();
    }
}

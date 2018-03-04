package spring.boot.actuator.microservice.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import spring.boot.actuator.microservice.model.DetailsHealth;
import spring.boot.actuator.microservice.model.StatusInfo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 커스텀 HealthCheck 방법 1
 * 신규 클래스 작성
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
@Component
public class CustomHealthCheck{

    /**
     * Health Check 데이터를 신규로 정의한 StatusInfo 클래스에 매핑
     *
     * @param
     * @return StatusInfo
     */
    public StatusInfo healthStatusInfo() {
        int errorCode = check();
        if (errorCode != 0) {
            return StatusInfo.ofDown();
        }

        Map<String, DetailsHealth> details = new HashMap<>();
        DetailsHealth detailsHealth = new DetailsHealth();
        detailsHealth.setName("");
        details.put("name", detailsHealth);

        return StatusInfo.ofUp(details);
    }

    /**
     * Health Check 데이터를 actuator의 Health 클래스에 매핑
     *
     * @param
     * @return StatusInfo
     */
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
        }

        return Health.up().withDetail("application-name","msa01").build();
    }

    public int check() {
        // Health Check 로직 수행 및 리턴, return 0 일 경우 정상
        return 0;
    }
}

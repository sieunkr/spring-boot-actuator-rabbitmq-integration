package spring.boot.actuator.microservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * Metrics 정보 조회
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
public class metricUtil {

    public static String getMetric() {

        SystemPublicMetrics systemPublicMetrics = new SystemPublicMetrics();
        Map<String, Object> result = new LinkedHashMap<String, Object>();

        for (Metric<?> metric : systemPublicMetrics.metrics()) {
            result.put(metric.getName(), metric.getValue());
        }

        try {
            String metrics = new ObjectMapper().writeValueAsString(result);
            return metrics;
        } catch (Exception e) {

        }
        return "";
    }
}

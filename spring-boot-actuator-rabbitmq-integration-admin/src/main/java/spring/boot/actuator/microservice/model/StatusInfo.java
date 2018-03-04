package spring.boot.actuator.microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Health Check 정보를 매핑하기 위해 만든 클래스
 * 원래 의도는 라이브러리의 Health 에 매핑시킬려고 했는데 실패, 그 이후 StatusInfo 클래스 방법으로 일단 빠르게 개발
 * 그리고 원래 해당 패키지 경로가 아닌데,
 * Spring Cloud Stream 리스너에서 연동시에, 해당 Pojo 패키지 경로를 메시지를 보내는 곳에서의 패키지 경로와 맞춰야 매핑이 됨(이유 아직 모름)
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
public class StatusInfo implements Serializable {
    private static final long serialVersionUID = 2L;

    private final String status;
    private final long timestamp;
    private final Map<String, Serializable> details;

    protected StatusInfo(String status, long timestamp,
                         Map<String, ? extends Serializable> details) {
        this.status = status.toUpperCase();
        this.timestamp = timestamp;
        this.details = details != null ? Collections.unmodifiableMap(new HashMap<>(details))
                : Collections.<String, Serializable>emptyMap();
    }

    public static StatusInfo valueOf(String statusCode,
                                     Map<String, ? extends Serializable> details) {
        return new StatusInfo(statusCode, System.currentTimeMillis(), details);
    }

    public static StatusInfo valueOf(String statusCode) {
        return valueOf(statusCode, null);
    }

    public static StatusInfo ofUnknown() {
        return valueOf("UNKNOWN", null);
    }

    public static StatusInfo ofUp() {
        return ofUp(null);
    }

    public static StatusInfo ofDown() {
        return ofDown(null);
    }

    public static StatusInfo ofOffline() {
        return ofOffline(null);
    }

    public static StatusInfo ofUp(Map<String, ? extends Serializable> details) {
        return valueOf("UP", details);
    }

    public static StatusInfo ofDown(Map<String, ? extends Serializable> details) {
        return valueOf("DOWN", details);
    }

    public static StatusInfo ofOffline(Map<String, ? extends Serializable> details) {
        return valueOf("OFFLINE", details);
    }

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, Serializable> getDetails() {
        return details;
    }

    @JsonIgnore
    public boolean isUp() {
        return "UP".equals(status);
    }

    @JsonIgnore
    public boolean isOffline() {
        return "OFFLINE".equals(status);
    }

    @JsonIgnore
    public boolean isDown() {
        return "DOWN".equals(status);
    }

    @JsonIgnore
    public boolean isUnknown() {
        return "UNKNOWN".equals(status);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StatusInfo other = (StatusInfo) obj;
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StatusInfo [status=" + status + ", timestamp=" + timestamp + "]";
    }

}

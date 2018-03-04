package spring.boot.actuator.microservice.model;

import java.io.Serializable;

/**
 *
 * Details 정보를 매핑하기 위해 만든 클래스
 *
 * @author Eddy.Kim
 * @since 04 MARCH 2018
 *
 */
public class DetailsHealth implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package org.spring.springboot;


import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * CustomEndpoint
 *
 * @Date 2024-09-04 10:06
 * @Author 杨海波
 **/
@Component
@Endpoint(id = "customEndpoint")
public class CustomEndpoint {

    @ReadOperation
    public String customEndpoint() {

        return "This is a custom endpoint response";
    }
}
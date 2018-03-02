package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomEndpoint implements Endpoint<List<String>> {


    @Autowired
    //private SieunHealthIndicator sieunHealthIndicator;

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
    //public List<String> invoke() {
    public List<String> invoke() {
        // Custom logic to build the output
        List<String> messages = new ArrayList<String>();
        messages.add("This is message 1");
        messages.add("This is message 2");


        //Health health = sieunHealthIndicator.health();

        //MQ 연동
        // ???


        return messages;
    }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HomeController {

    @Autowired
    private HealthCheck healthCheck;

    @Autowired
    private NewHealthCheck newHealthCheck;


    @GetMapping("/sample")
    public Health check(HttpServletRequest request, HttpServletResponse response){

        return healthCheck.health();
    }

    @GetMapping("/sample02")
    public Health newcheck(HttpServletRequest request, HttpServletResponse response){
        return newHealthCheck.health();
    }
}

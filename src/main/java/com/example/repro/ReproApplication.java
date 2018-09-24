package com.example.repro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.module.SomeService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class ReproApplication {

    @Autowired
    private SomeService someService;

    @GetMapping("/test")
    public String test() {
        log.info("Test Endpoint called, check TraceId/SpanId/ParentSpanId in log");
        return someService.decorate("hello world") + "\n";
    }

    public static void main(String[] args) {
        SpringApplication.run(ReproApplication.class, args);
    }

}

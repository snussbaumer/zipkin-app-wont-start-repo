package com.example.module.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.module.SomeService;

import lombok.extern.slf4j.Slf4j;

import brave.Tracer;

/**
 * @author snussbaumer
 */
@Configuration
@AutoConfigureAfter(name = "org.springframework.cloud.sleuth.autoconfig.TraceAutoConfiguration")
@Slf4j
public class TestAutoConfiguration {

    @ConditionalOnBean(type = "brave.Tracer")
    @Configuration
    public static class TracedConfiguration {

        @Bean
        public SomeService someService(Tracer tracer) {
            log.info("Create traced SomeService");
            return value -> value + " [" + tracer.currentSpan().toString() + "]";
        }
    }

    @ConditionalOnMissingBean(type = "brave.Tracer")
    @Configuration
    public static class NoTracedConfiguration {

        @Bean
        public SomeService someService() {
            log.info("Create not traced SomeService");
            return value -> value + " [not-traced]";
        }
    }

}

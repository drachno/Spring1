package com.example.spring.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.example.spring.demo.person", "com.example.spring.demo.group"})
public class JPAConfig {
}

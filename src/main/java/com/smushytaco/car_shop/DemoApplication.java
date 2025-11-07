package com.smushytaco.car_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public final class DemoApplication { static void main(final String[] args) { SpringApplication.run(DemoApplication.class, args); } }
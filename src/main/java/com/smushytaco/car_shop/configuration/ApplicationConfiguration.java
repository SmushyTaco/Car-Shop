package com.smushytaco.car_shop.configuration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class ApplicationConfiguration {}
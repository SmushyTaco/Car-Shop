package com.smushytaco.car_shop.configuration;
import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
@Configuration
class DatabaseConfiguration {
    @Bean
    public MariaDB4jSpringService mariaDB4jSpringService() { return new MariaDB4jSpringService(); }
    @Bean
    public DataSource dataSource(final MariaDB4jSpringService mariaDB4jSpringService, @Value("${app.mariaDB4j.databaseName}") final String databaseName, @Value("${spring.datasource.username}") final String datasourceUsername, @Value("${spring.datasource.password}") final String datasourcePassword, @Value("${spring.datasource.driver-class-name}") final String datasourceDriver, @Value("${spring.datasource.url}") final String datasourceUrl) throws ManagedProcessException {
        if (!mariaDB4jSpringService.isRunning()) mariaDB4jSpringService.start();
        mariaDB4jSpringService.getDB().createDB(databaseName);
        return DataSourceBuilder.create().username(datasourceUsername).password(datasourcePassword).url(datasourceUrl + databaseName).driverClassName(datasourceDriver).build();
    }
}
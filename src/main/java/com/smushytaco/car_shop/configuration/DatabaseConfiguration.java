package com.smushytaco.car_shop.configuration;

import com.smushytaco.postgres.embedded.EmbeddedPostgres;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
class DatabaseConfiguration {
    @Bean(destroyMethod = "close")
    public EmbeddedPostgres embeddedPostgres(
            @Value("${app.postgres.port:0}") final int port,
            @Value("${app.postgres.dataDir:}") final String dataDir,
            @Value("${app.postgres.cleanDataDir:true}") final boolean cleanDataDir
    ) throws IOException {
        final EmbeddedPostgres.Builder builder = EmbeddedPostgres.builder().setCleanDataDirectory(cleanDataDir);
        if (port > 0) builder.setPort(port);
        if (!dataDir.isBlank()) builder.setDataDirectory(Path.of(dataDir));
        return builder.start();
    }
    @Bean
    public DataSource dataSource(
            final EmbeddedPostgres embeddedPostgres,
            @Value("${app.postgres.databaseName:car-shop}") final String databaseName,
            @Value("${spring.datasource.username:postgres}") final String user,
            @Value("${spring.datasource.password:postgres}") final String password
    ) throws SQLException {
        try (final Connection admin = embeddedPostgres.getPostgresDatabase().getConnection()) {
            final DSLContext context = DSL.using(admin);
            if (!context.fetchExists(DSL.selectOne().from("pg_database").where(DSL.field("datname").eq(databaseName))))
                context.query("create database {0}", DSL.name(databaseName)).execute();
        }
        final PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setPortNumbers(new int[]{embeddedPostgres.getPort()});
        ds.setDatabaseName(databaseName);
        ds.setUser(user);
        ds.setPassword(password);
        return ds;
    }
}
package com.helpdeskeditor.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

public class DriverManagerDataSource {
    @Autowired
    private static Environment env;

    @Bean(name ="SQL")
    @Primary
    public static DataSource SQLDataSource()
    {

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url("jdbc:sqlserver://127.0.0.1;databaseName=Biomedica;integratedSecurity=false;encrypt=true;trustServerCertificate=true");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("zitiz");
        return dataSourceBuilder.build();
    }

    //@Override
    public void setEnvironment( final Environment environment) {
        env=environment;
    }
}

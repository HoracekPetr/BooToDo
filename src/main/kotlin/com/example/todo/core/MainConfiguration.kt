package com.example.todo.core

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
class MainConfiguration{

    @Bean
    fun provideConnectionInitializer(): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        val connectionFactory = PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .username("postgres")
                        .password("postgres")
                        .database("postgres")
                        .build()
        )
        return initializer.apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
        }
    }
}

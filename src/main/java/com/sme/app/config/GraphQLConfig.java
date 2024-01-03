package com.sme.app.config;


import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLScalarType extendedScalarLong() {
        return ExtendedScalars.GraphQLLong;
    }

    @Bean
    public GraphQLScalarType extendedScalarBigDecimal() {
        return ExtendedScalars.GraphQLBigDecimal;
    }

    @Bean
    public GraphQLScalarType extendedScalarDateTime() {
        return ExtendedScalars.DateTime;
    }

}

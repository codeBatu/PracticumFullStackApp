package com.practicum.fttech.backendgateway;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class BackendGatewayApplication {

        public static void main(String[] args) {
            SpringApplication.run(BackendGatewayApplication.class, args);
        }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                .info(new Info().title("FTTeknoloji Practicum")
                        .description("FTTeknoloji Practicum").summary("FTTeknoloji Practicum")
                );
    }



}
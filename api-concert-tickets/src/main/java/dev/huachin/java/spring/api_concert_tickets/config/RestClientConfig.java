package dev.huachin.java.spring.api_concert_tickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient restClient(RestClient.Builder restClientBuilder) {
        // Configura el cliente REST para conectarse a la API de gestión de pedidos
        return restClientBuilder
                .baseUrl("https://dummyjson.com")
                .build();
    }
}

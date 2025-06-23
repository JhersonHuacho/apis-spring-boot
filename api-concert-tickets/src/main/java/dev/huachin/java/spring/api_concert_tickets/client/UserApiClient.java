package dev.huachin.java.spring.api_concert_tickets.client;

import dev.huachin.java.spring.api_concert_tickets.client.dto.ApiUserDto;
import dev.huachin.java.spring.api_concert_tickets.client.dto.ApiUserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class UserApiClient {
    private static final Logger LOG = LoggerFactory.getLogger(UserApiClient.class);

    @Autowired
    RestClient restClient;

    public List<ApiUserDto> getUsers() {
        try {
            LOG.info("Fetching users from external API");

            ApiUserResponseDto response = restClient
                .get()
                .uri("/users")
                .retrieve()
                .body(ApiUserResponseDto.class);

            if (response == null || response.users().length == 0) {
                LOG.warn("No users found in external API");
                return List.of();
            }
            return Arrays.asList(response.users());
        } catch (Exception e) {
            LOG.error("Error fetching users from external API", e);
            throw new RuntimeException("Failed to fetch users from external API", e);
        }
    }
}

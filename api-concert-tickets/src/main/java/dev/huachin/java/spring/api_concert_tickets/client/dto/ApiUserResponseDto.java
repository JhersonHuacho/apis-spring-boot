package dev.huachin.java.spring.api_concert_tickets.client.dto;

import java.util.List;

public record ApiUserResponseDto(
    ApiUserDto[] users
) {
}

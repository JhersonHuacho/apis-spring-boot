package dev.huachin.java.spring.api_concert_tickets.client.dto;

public record ApiUserDto(
    Long id,
    String username,
    String email,
    String firstName,
    String lastName,
    String phone
) {
}

package dev.huachin.java.spring.api_concert_tickets.dto.response;

public record UserResponseDto(
    Long id,
    String username,
    String email,
    String firstName,
    String lastName,
    String phone
) {
}

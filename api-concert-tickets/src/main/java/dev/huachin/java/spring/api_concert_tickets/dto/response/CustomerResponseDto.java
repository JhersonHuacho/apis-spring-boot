package dev.huachin.java.spring.api_concert_tickets.dto.response;

public record CustomerResponseDto(
    Long id,
    String firstName,
    String lastName,
    String email,
    String address,
    String phoneNumber) {
}

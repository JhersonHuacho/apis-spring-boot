package dev.huachin.java.spring.api_concert_tickets.dto.response;

public record PaymentResponseDto(
    String paymentMethod,
    String paymentStatus,
    String transactionCode
) {
}

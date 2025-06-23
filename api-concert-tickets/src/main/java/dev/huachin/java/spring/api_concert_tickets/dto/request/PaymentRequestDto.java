package dev.huachin.java.spring.api_concert_tickets.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentRequestDto(
    BigDecimal amount,
    String paymentMethod) {
}

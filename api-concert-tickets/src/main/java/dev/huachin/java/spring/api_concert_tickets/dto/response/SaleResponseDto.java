package dev.huachin.java.spring.api_concert_tickets.dto.response;

import java.math.BigDecimal;

public record SaleResponseDto(
    Long saleId,
    String operationNumber,
    BigDecimal totalAmount,
    String status,
    PaymentResponseDto payment
) {
}

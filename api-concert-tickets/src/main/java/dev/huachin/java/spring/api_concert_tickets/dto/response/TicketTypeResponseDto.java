package dev.huachin.java.spring.api_concert_tickets.dto.response;

import java.math.BigDecimal;

public record TicketTypeResponseDto(
    Long id,
    ConcertResponseDto concert,
    String name,
    BigDecimal unitPrice,
    Integer stock,
    String status
) {
}

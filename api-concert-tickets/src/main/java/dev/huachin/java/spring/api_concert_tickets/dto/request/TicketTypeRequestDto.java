package dev.huachin.java.spring.api_concert_tickets.dto.request;

import java.math.BigDecimal;

public record TicketTypeRequestDto(
    Long concertId,
    String name,
    BigDecimal unitPrice,
    Integer stock
) {
}

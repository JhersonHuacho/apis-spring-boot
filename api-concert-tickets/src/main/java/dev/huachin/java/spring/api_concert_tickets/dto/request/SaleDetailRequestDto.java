package dev.huachin.java.spring.api_concert_tickets.dto.request;

import java.math.BigDecimal;

public record SaleDetailRequestDto(
    Long ticketTypeId,
    Integer quantity
) {
}

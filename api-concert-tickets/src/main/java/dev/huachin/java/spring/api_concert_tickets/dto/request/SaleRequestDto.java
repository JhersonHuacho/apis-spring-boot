package dev.huachin.java.spring.api_concert_tickets.dto.request;

import java.util.List;

public record SaleRequestDto (
    Long customerId,
    List<SaleDetailRequestDto> items,
    PaymentRequestDto payment
) {
}

package dev.huachin.java.spring.api_concert_tickets.dto.response;

import java.time.LocalDateTime;

public record ReservationResponseDto(
    Long id,
    CustomerResponseDto customer,
    TicketTypeResponseDto ticketType,
    int numberOfTickets,
    LocalDateTime reservationDateTime,
    LocalDateTime expirationDateTime,
    String status
) {
}

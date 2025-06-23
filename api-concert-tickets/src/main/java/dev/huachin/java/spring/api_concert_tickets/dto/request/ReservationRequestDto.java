package dev.huachin.java.spring.api_concert_tickets.dto.request;

public record ReservationRequestDto(
    Long customerId,
    Long ticketTypeId,
    int numberOfTickets,
    String reservationDateTime
) {
}

package dev.huachin.java.spring.api_concert_tickets.dto.response;

import java.time.LocalDateTime;

public record ConcertResponseDto(
    Long id,
    String title,
    String description,
    String place,
    GenreResponseDto genre,
    LocalDateTime dateEvent,
    String imageUrl,
    Boolean finalized,
    Boolean status
) {
}

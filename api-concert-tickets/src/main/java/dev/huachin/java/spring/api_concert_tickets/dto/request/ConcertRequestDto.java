package dev.huachin.java.spring.api_concert_tickets.dto.request;

import dev.huachin.java.spring.api_concert_tickets.dto.response.GenreResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConcertRequestDto(
    String title,
    String description,
    String place,
    Long genreId,
    String dateEvent,
    String imageUrl
) {
}

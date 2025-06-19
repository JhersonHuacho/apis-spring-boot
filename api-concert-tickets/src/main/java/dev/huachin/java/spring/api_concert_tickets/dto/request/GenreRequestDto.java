package dev.huachin.java.spring.api_concert_tickets.dto.request;

import dev.huachin.java.spring.api_concert_tickets.dto.validators.AlphaNumericWithSpaces;
import jakarta.validation.constraints.NotEmpty;

public record GenreRequestDto(
    @AlphaNumericWithSpaces(message = "Solo número, espacios y letras")
    @NotEmpty(message = "El campo name es obligatorio")
    String name
) {
}

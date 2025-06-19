package dev.huachin.java.spring.api_concert_tickets.dto.request;

import dev.huachin.java.spring.api_concert_tickets.dto.validators.AlphaNumericWithSpaces;
import jakarta.validation.constraints.NotEmpty;

public record CustomerRequestDto(
    @AlphaNumericWithSpaces(message = "Solo número, espacios y letras")
    @NotEmpty(message = "El campo firstName es obligatorio")
    String firstName,

    @NotEmpty(message = "El campo lastName es obligatorio")
    String lastName,

    @NotEmpty(message = "El campo email es obligatorio")
    String email,

    @NotEmpty(message = "El campo address es obligatorio")
    String address,

    @NotEmpty(message = "El campo phoneNumber es obligatorio")
    String phoneNumber) {
}

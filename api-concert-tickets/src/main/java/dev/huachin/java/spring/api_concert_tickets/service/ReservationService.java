package dev.huachin.java.spring.api_concert_tickets.service;

import dev.huachin.java.spring.api_concert_tickets.dto.request.ReservationRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.ReservationResponseDto;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<ReservationResponseDto> getAll();
    Optional<ReservationResponseDto> getById(Long id);
    ReservationResponseDto create(ReservationRequestDto reservation);
    ReservationResponseDto update(Long id, ReservationRequestDto reservation);
    void delete(Long id);
}

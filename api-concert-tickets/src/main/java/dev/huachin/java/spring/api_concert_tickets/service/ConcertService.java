package dev.huachin.java.spring.api_concert_tickets.service;

import dev.huachin.java.spring.api_concert_tickets.dto.request.ConcertRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.ConcertResponseDto;

import java.util.List;
import java.util.Optional;

public interface ConcertService {
    List<ConcertResponseDto> getAll();
    Optional<ConcertResponseDto> getById(Long id);
    ConcertResponseDto create(ConcertRequestDto concertRequestDto);
    ConcertResponseDto update(Long id, ConcertRequestDto concertRequestDto);
    void delete(Long id);
}

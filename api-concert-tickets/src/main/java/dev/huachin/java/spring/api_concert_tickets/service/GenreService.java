package dev.huachin.java.spring.api_concert_tickets.service;

import dev.huachin.java.spring.api_concert_tickets.dto.request.GenreRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.GenreResponseDto;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreResponseDto> getAll();
    Optional<GenreResponseDto> getById(Long id);
    GenreResponseDto create(GenreRequestDto genre);
    GenreResponseDto update(Long id, GenreRequestDto genre);
    void delete(Long id);
}

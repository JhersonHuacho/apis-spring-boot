package dev.huachin.java.spring.api_concert_tickets.service;

import dev.huachin.java.spring.api_concert_tickets.dto.request.TicketTypeRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.TicketTypeResponseDto;

import java.util.List;
import java.util.Optional;

public interface TicketTypeService {
    List<TicketTypeResponseDto> getAll();
    Optional<TicketTypeResponseDto> getById(Long id);
    TicketTypeResponseDto create(TicketTypeRequestDto ticketTypeRequestDto);
    TicketTypeResponseDto update(Long id, TicketTypeRequestDto ticketTypeRequestDto);
    void delete(Long id);
}

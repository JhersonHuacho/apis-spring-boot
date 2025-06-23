package dev.huachin.java.spring.api_concert_tickets.service.impl;

import dev.huachin.java.spring.api_concert_tickets.dto.request.TicketTypeRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.ConcertResponseDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.GenreResponseDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.TicketTypeResponseDto;
import dev.huachin.java.spring.api_concert_tickets.entity.ConcertEntity;
import dev.huachin.java.spring.api_concert_tickets.entity.TicketTypeEntity;
import dev.huachin.java.spring.api_concert_tickets.repository.ConcertRepository;
import dev.huachin.java.spring.api_concert_tickets.repository.GenreRepository;
import dev.huachin.java.spring.api_concert_tickets.repository.TicketTypeRepository;
import dev.huachin.java.spring.api_concert_tickets.service.TicketTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TicketTypeServiceImpl implements TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final ConcertRepository concertRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<TicketTypeResponseDto> getAll() {
        return ticketTypeRepository.findAll()
            .stream()
            .map(ticketType -> {
                GenreResponseDto genreResponseDto = new GenreResponseDto(
                    ticketType.getConcertEntity().getGenreEntity().getId(),
                    ticketType.getConcertEntity().getGenreEntity().getName()
                );

                ConcertResponseDto concertResponseDto = new ConcertResponseDto(
                    ticketType.getConcertEntity().getId(),
                    ticketType.getConcertEntity().getTitle(),
                    ticketType.getConcertEntity().getDescription(),
                    ticketType.getConcertEntity().getPlace(),
                    genreResponseDto,
                    ticketType.getConcertEntity().getDateEvent(),
                    ticketType.getConcertEntity().getImageUrl(),
                    ticketType.getConcertEntity().getFinalized(),
                    ticketType.getConcertEntity().getStatus()
                );

                TicketTypeResponseDto ticketTypeResponseDto = new TicketTypeResponseDto(
                    ticketType.getId(),
                    concertResponseDto,
                    ticketType.getName(),
                    ticketType.getUnitPrice(),
                    ticketType.getStock(),
                    ticketType.getStatus()
                );

                return ticketTypeResponseDto;
            })
            .toList();
    }
    // Un Optional es un contenedor que puede o no contener un valor.
    @Override
    public Optional<TicketTypeResponseDto> getById(Long id) {
        return ticketTypeRepository.findById(id)
            .map(ticketType -> {
                GenreResponseDto genreResponseDto = new GenreResponseDto(
                    ticketType.getConcertEntity().getGenreEntity().getId(),
                    ticketType.getConcertEntity().getGenreEntity().getName()
                );

                ConcertResponseDto concertResponseDto = new ConcertResponseDto(
                    ticketType.getConcertEntity().getId(),
                    ticketType.getConcertEntity().getTitle(),
                    ticketType.getConcertEntity().getDescription(),
                    ticketType.getConcertEntity().getPlace(),
                    genreResponseDto,
                    ticketType.getConcertEntity().getDateEvent(),
                    ticketType.getConcertEntity().getImageUrl(),
                    ticketType.getConcertEntity().getFinalized(),
                    ticketType.getConcertEntity().getStatus()
                );

                return new TicketTypeResponseDto(
                    ticketType.getId(),
                    concertResponseDto,
                    ticketType.getName(),
                    ticketType.getUnitPrice(),
                    ticketType.getStock(),
                    ticketType.getStatus()
                );
            });
    }

    @Override
    public TicketTypeResponseDto create(TicketTypeRequestDto ticketTypeRequestDto) {
        ConcertEntity concertEntity = concertRepository
            .findById(ticketTypeRequestDto.concertId())
            .orElseThrow(() -> new RuntimeException("Concierto no encontrado con ID: " + ticketTypeRequestDto.concertId()));


        TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setConcertEntity(concertEntity);
        ticketTypeEntity.setName(ticketTypeRequestDto.name());
        ticketTypeEntity.setUnitPrice(ticketTypeRequestDto.unitPrice());
        ticketTypeEntity.setStock(ticketTypeRequestDto.stock());

        TicketTypeEntity savedTicketType = ticketTypeRepository.save(ticketTypeEntity);
        TicketTypeResponseDto ticketTypeResponseDto = new TicketTypeResponseDto(
            savedTicketType.getId(),
            new ConcertResponseDto(
                savedTicketType.getConcertEntity().getId(),
                savedTicketType.getConcertEntity().getTitle(),
                savedTicketType.getConcertEntity().getDescription(),
                savedTicketType.getConcertEntity().getPlace(),
                new GenreResponseDto(
                    savedTicketType.getConcertEntity().getGenreEntity().getId(),
                    savedTicketType.getConcertEntity().getGenreEntity().getName()
                ),
                savedTicketType.getConcertEntity().getDateEvent(),
                savedTicketType.getConcertEntity().getImageUrl(),
                savedTicketType.getConcertEntity().getFinalized(),
                savedTicketType.getConcertEntity().getStatus()
            ),
            savedTicketType.getName(),
            savedTicketType.getUnitPrice(),
            savedTicketType.getStock(),
            savedTicketType.getStatus()
        );

        return ticketTypeResponseDto;
    }

    @Override
    public TicketTypeResponseDto update(Long id, TicketTypeRequestDto ticketTypeRequestDto) {
        ConcertEntity concertEntity = concertRepository
            .findById(ticketTypeRequestDto.concertId())
            .orElseThrow(() -> new RuntimeException("Concierto no encontrado con ID: " + ticketTypeRequestDto.concertId()));

        TicketTypeEntity existingTicketType = ticketTypeRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de ticket no encontrado con ID: " + id));

        existingTicketType.setConcertEntity(concertEntity);
        existingTicketType.setName(ticketTypeRequestDto.name());
        existingTicketType.setUnitPrice(ticketTypeRequestDto.unitPrice());
        existingTicketType.setStock(ticketTypeRequestDto.stock());

        TicketTypeEntity updatedTicketType = ticketTypeRepository.save(existingTicketType);

        TicketTypeResponseDto ticketTypeResponseDto = new TicketTypeResponseDto(
            updatedTicketType.getId(),
            new ConcertResponseDto(
                updatedTicketType.getConcertEntity().getId(),
                updatedTicketType.getConcertEntity().getTitle(),
                updatedTicketType.getConcertEntity().getDescription(),
                updatedTicketType.getConcertEntity().getPlace(),
                new GenreResponseDto(
                    updatedTicketType.getConcertEntity().getGenreEntity().getId(),
                    updatedTicketType.getConcertEntity().getGenreEntity().getName()
                ),
                updatedTicketType.getConcertEntity().getDateEvent(),
                updatedTicketType.getConcertEntity().getImageUrl(),
                updatedTicketType.getConcertEntity().getFinalized(),
                updatedTicketType.getConcertEntity().getStatus()
            ),
            updatedTicketType.getName(),
            updatedTicketType.getUnitPrice(),
            updatedTicketType.getStock(),
            updatedTicketType.getStatus()
        );

        return ticketTypeResponseDto;
    }

    @Override
    public void delete(Long id) {
        TicketTypeEntity existingTicketType = ticketTypeRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de ticket no encontrado con ID: " + id));

        ticketTypeRepository.delete(existingTicketType);
    }
}

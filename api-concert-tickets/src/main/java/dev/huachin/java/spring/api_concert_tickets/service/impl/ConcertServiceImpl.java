package dev.huachin.java.spring.api_concert_tickets.service.impl;

import dev.huachin.java.spring.api_concert_tickets.dto.request.ConcertRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.ConcertResponseDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.GenreResponseDto;
import dev.huachin.java.spring.api_concert_tickets.entity.ConcertEntity;
import dev.huachin.java.spring.api_concert_tickets.entity.GenreEntity;
import dev.huachin.java.spring.api_concert_tickets.repository.ConcertRepository;
import dev.huachin.java.spring.api_concert_tickets.repository.GenreRepository;
import dev.huachin.java.spring.api_concert_tickets.service.ConcertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository concertRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<ConcertResponseDto> getAll() {
        return concertRepository.findAll()
            .stream()
            .map(concert -> {
                GenreResponseDto genreResponseDto = new GenreResponseDto(
                    concert.getGenreEntity().getId(),
                    concert.getGenreEntity().getName()
                );
                return new ConcertResponseDto(
                    concert.getId(),
                    concert.getTitle(),
                    concert.getDescription(),
                    concert.getPlace(),
                    genreResponseDto,
                    concert.getDateEvent(),
                    concert.getImageUrl(),
                    concert.getFinalized(),
                    concert.getStatus()
                );
            })
            .toList();
    }

    @Override
    public Optional<ConcertResponseDto> getById(Long id) {
        return concertRepository.findById(id)
            .map(concert -> {
                GenreResponseDto genreResponseDto = new GenreResponseDto(
                    concert.getGenreEntity().getId(),
                    concert.getGenreEntity().getName()
                );
                return new ConcertResponseDto(
                    concert.getId(),
                    concert.getTitle(),
                    concert.getDescription(),
                    concert.getPlace(),
                    genreResponseDto,
                    concert.getDateEvent(),
                    concert.getImageUrl(),
                    concert.getFinalized(),
                    concert.getStatus()
                );
            })
            .or(() -> {;
                throw new RuntimeException("Concert not found with ID: " + id);
            });
    }

    @Override
    public ConcertResponseDto create(ConcertRequestDto concertRequestDto) {
        var genreEntity = genreRepository.findById(concertRequestDto.genreId())
            .orElseThrow(() -> new RuntimeException("Genre not found with ID: " + concertRequestDto.genreId()));

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // 28-05-2025
        LocalDate dateEvent = LocalDate.parse(concertRequestDto.dateEvent(), formatter);

        var concertEntity = new ConcertEntity();
        concertEntity.setTitle(concertRequestDto.title());
        concertEntity.setDescription(concertRequestDto.description());
        concertEntity.setPlace(concertRequestDto.place());
        concertEntity.setGenreEntity(genreEntity);
        concertEntity.setDateEvent(dateEvent.atStartOfDay());
        concertEntity.setImageUrl(concertRequestDto.imageUrl());

        ConcertEntity savedConcert = concertRepository.save(concertEntity);
        ConcertResponseDto concertResponseDto = new ConcertResponseDto(
            savedConcert.getId(),
            savedConcert.getTitle(),
            savedConcert.getDescription(),
            savedConcert.getPlace(),
            new GenreResponseDto(
                savedConcert.getGenreEntity().getId(),
                savedConcert.getGenreEntity().getName()
            ),
            savedConcert.getDateEvent(),
            savedConcert.getImageUrl(),
            savedConcert.getFinalized(),
            savedConcert.getStatus()
        );

        return concertResponseDto;
    }

    @Override
    public ConcertResponseDto update(Long id, ConcertRequestDto concertRequestDto) {
        ConcertEntity existingConcert = concertRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Concert not found with ID: " + id));

        GenreEntity genreEntity = genreRepository.findById(concertRequestDto.genreId())
            .orElseThrow(() -> new RuntimeException("Genre not found with ID: " + concertRequestDto.genreId()));

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // 28-05-2025
        LocalDate dateEvent = LocalDate.parse(concertRequestDto.dateEvent(), formatter);

        existingConcert.setTitle(concertRequestDto.title());
        existingConcert.setDescription(concertRequestDto.description());
        existingConcert.setPlace(concertRequestDto.place());
        existingConcert.setGenreEntity(genreEntity);
        existingConcert.setDateEvent(dateEvent.atStartOfDay());
        existingConcert.setImageUrl(concertRequestDto.imageUrl());

        ConcertEntity updatedConcert = concertRepository.save(existingConcert);
        ConcertResponseDto concertResponseDto = new ConcertResponseDto(
            updatedConcert.getId(),
            updatedConcert.getTitle(),
            updatedConcert.getDescription(),
            updatedConcert.getPlace(),
            new GenreResponseDto(
                updatedConcert.getGenreEntity().getId(),
                updatedConcert.getGenreEntity().getName()
            ),
            updatedConcert.getDateEvent(),
            updatedConcert.getImageUrl(),
            updatedConcert.getFinalized(),
            updatedConcert.getStatus()
        );

        return concertResponseDto;
    }

    @Override
    public void delete(Long id) {
        if (!concertRepository.existsById(id)) {
            throw new RuntimeException("Concert not found with ID: " + id);
        }
        concertRepository.deleteById(id);
    }
}

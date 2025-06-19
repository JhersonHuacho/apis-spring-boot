package dev.huachin.java.spring.api_concert_tickets.service.impl;

import dev.huachin.java.spring.api_concert_tickets.dto.request.GenreRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.GenreResponseDto;
import dev.huachin.java.spring.api_concert_tickets.entity.GenreEntity;
import dev.huachin.java.spring.api_concert_tickets.mapper.GenreMapper;
import dev.huachin.java.spring.api_concert_tickets.repository.GenreRepository;
import dev.huachin.java.spring.api_concert_tickets.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreResponseDto> getAll() {
        return genreRepository.findAll()
            .stream()
            .map(genreMapper::toResponseDto)
            .toList();
    }

    @Override
    public Optional<GenreResponseDto> getById(Long id) {
        return Optional.ofNullable(
            genreRepository.findById(id)
                .map(genreMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id))
        );
    }

    @Override
    public GenreResponseDto create(GenreRequestDto genre) {
        GenreEntity genreEntity = genreMapper.toEntity(genre);
        GenreEntity savedGenre = genreRepository.save(genreEntity);

        GenreResponseDto genreResponseDto = genreMapper.toResponseDto(savedGenre);
        return genreResponseDto;
    }

    @Override
    public GenreResponseDto update(Long id, GenreRequestDto genreRequestDto) {
        GenreEntity existingGenre = genreRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));

        genreMapper.updateEntityFromRequestDto(genreRequestDto, existingGenre);

        GenreEntity savedGenre = genreRepository.save(existingGenre);
        GenreResponseDto genreResponseDto = genreMapper.toResponseDto(savedGenre);
        return genreResponseDto;
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}

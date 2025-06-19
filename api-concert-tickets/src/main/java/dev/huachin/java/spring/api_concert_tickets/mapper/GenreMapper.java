package dev.huachin.java.spring.api_concert_tickets.mapper;

import dev.huachin.java.spring.api_concert_tickets.dto.request.GenreRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.GenreResponseDto;
import dev.huachin.java.spring.api_concert_tickets.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreResponseDto toResponseDto(GenreEntity entity);
    GenreEntity toEntity(GenreRequestDto requestDto);
    @Mapping(target = "id", ignore = true) // Ignora el campo id al actualizar
    void updateEntityFromRequestDto(GenreRequestDto requestDto, @MappingTarget GenreEntity entity);
}

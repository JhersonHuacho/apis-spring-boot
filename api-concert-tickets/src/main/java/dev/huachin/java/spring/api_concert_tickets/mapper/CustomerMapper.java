package dev.huachin.java.spring.api_concert_tickets.mapper;

import dev.huachin.java.spring.api_concert_tickets.dto.request.CustomerRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.CustomerResponseDto;
import dev.huachin.java.spring.api_concert_tickets.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDto toResponseDto(CustomerEntity entity);
    CustomerEntity toEntity(CustomerRequestDto dto);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CustomerRequestDto dto, @MappingTarget CustomerEntity entity);
}

package dev.huachin.java.spring.api_concert_tickets.service;

import dev.huachin.java.spring.api_concert_tickets.dto.request.CustomerRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.CustomerResponseDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerResponseDto> getAll();
    Optional<CustomerResponseDto> getById(Long id);
    CustomerResponseDto create(CustomerRequestDto customerRequestDto);
    CustomerResponseDto update(Long id, CustomerRequestDto customerRequestDto);
    void delete(Long id);
}

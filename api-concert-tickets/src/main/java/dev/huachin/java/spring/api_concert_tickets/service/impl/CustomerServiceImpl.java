package dev.huachin.java.spring.api_concert_tickets.service.impl;

import dev.huachin.java.spring.api_concert_tickets.dto.request.CustomerRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.CustomerResponseDto;
import dev.huachin.java.spring.api_concert_tickets.entity.CustomerEntity;
import dev.huachin.java.spring.api_concert_tickets.mapper.CustomerMapper;
import dev.huachin.java.spring.api_concert_tickets.repository.CustomerRepository;
import dev.huachin.java.spring.api_concert_tickets.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    // @Autowired es un componente de Spring que permite inyectar dependencias automáticamente.

    @Override
    public List<CustomerResponseDto> getAll() {
        return customerRepository.findAll()
            .stream()
            .map(customerMapper::toResponseDto)
            .toList();
    }

    @Override
    public Optional<CustomerResponseDto> getById(Long id) {
        return customerRepository
            .findById(id)
            .map(customerMapper::toResponseDto)
            .map(Optional::of)
            .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

    @Override
    public CustomerResponseDto create(CustomerRequestDto customerRequestDto) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerRequestDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        CustomerResponseDto customerResponseDto = customerMapper.toResponseDto(savedCustomer);

        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerRequestDto customerRequestDto) {
        CustomerEntity existingCustomer = customerRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        customerMapper.updateEntityFromDto(customerRequestDto, existingCustomer);
        CustomerEntity updatedCustomer = customerRepository.save(existingCustomer);
        CustomerResponseDto customerResponseDto = customerMapper.toResponseDto(updatedCustomer);
        return customerResponseDto;
    }

    @Override
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }
}

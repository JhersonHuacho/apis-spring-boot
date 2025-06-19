package dev.huachin.java.spring.api_concert_tickets.controller;

import dev.huachin.java.spring.api_concert_tickets.dto.request.CustomerRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.CustomerResponseDto;
import dev.huachin.java.spring.api_concert_tickets.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation (
        summary = "Get all customers",
        description = "Retorna una lista de todos los clientes registrados",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "List of customers",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto[].class)
                )
            ),
            @ApiResponse(
                responseCode = "204",
                description = "No customers found"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "getAllCustomers"
    )
    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        try {
            List<CustomerResponseDto> customers = customerService.getAll();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            log.error("Error retrieving customers", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation (
        summary = "Get customer by ID",
        description = "Retorna un cliente específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Customer found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Customer not found"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "getCustomerById"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        try {
            return customerService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error("Error retrieving customer with id: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation (
        summary = "Create a new customer",
        description = "Permite crear un nuevo cliente en el sistema",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Customer created successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid request data"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "createCustomer"
    )
    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(
        @Valid @RequestBody CustomerRequestDto customerRequest) {
        try {
            CustomerResponseDto createdCustomer = customerService.create(customerRequest);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating customer", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation (
        summary = "Update an existing customer",
        description = "Permite actualizar un cliente existente por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Customer updated successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Customer not found"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "updateCustomer"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(
        @PathVariable("id") Long id,
        @Valid @RequestBody CustomerRequestDto customerRequest) {
        try {
            Optional<CustomerResponseDto> existingCustomer = customerService.getById(id);
            if (existingCustomer.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            CustomerResponseDto updatedCustomer = customerService.update(id, customerRequest);
            return ResponseEntity.ok(updatedCustomer);
        } catch (Exception e) {
            log.error("Error updating customer with id: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

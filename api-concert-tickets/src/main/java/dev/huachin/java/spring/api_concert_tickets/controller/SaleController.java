package dev.huachin.java.spring.api_concert_tickets.controller;

import dev.huachin.java.spring.api_concert_tickets.dto.request.SaleRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.SaleResponseDto;
import dev.huachin.java.spring.api_concert_tickets.service.SaleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDto> createSale(
        @RequestBody SaleRequestDto saleRequestDto) {
        try {
            SaleResponseDto saleResponseDto = saleService.createSale(saleRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saleResponseDto);
        } catch (Exception e) {
            log.error("Error al crear la venta: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

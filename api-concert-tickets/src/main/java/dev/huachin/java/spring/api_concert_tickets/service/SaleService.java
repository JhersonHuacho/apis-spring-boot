package dev.huachin.java.spring.api_concert_tickets.service;

import dev.huachin.java.spring.api_concert_tickets.dto.request.SaleRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.SaleResponseDto;

public interface SaleService {
    SaleResponseDto createSale(SaleRequestDto saleRequestDto);
    SaleResponseDto createSaleV2(SaleRequestDto saleRequestDto);
}

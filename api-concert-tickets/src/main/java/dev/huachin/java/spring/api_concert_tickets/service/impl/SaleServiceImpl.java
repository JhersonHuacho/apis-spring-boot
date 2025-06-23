package dev.huachin.java.spring.api_concert_tickets.service.impl;

import dev.huachin.java.spring.api_concert_tickets.dto.request.SaleRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.PaymentResponseDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.SaleResponseDto;
import dev.huachin.java.spring.api_concert_tickets.entity.*;
import dev.huachin.java.spring.api_concert_tickets.repository.CustomerRepository;
import dev.huachin.java.spring.api_concert_tickets.repository.PaymentRepository;
import dev.huachin.java.spring.api_concert_tickets.repository.SaleRepository;
import dev.huachin.java.spring.api_concert_tickets.repository.TicketTypeRepository;
import dev.huachin.java.spring.api_concert_tickets.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {
    private final TicketTypeRepository ticketTypeRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public SaleResponseDto createSale(SaleRequestDto saleRequestDto) {
        SaleEntity saleEntity = new SaleEntity();

        List<SaleDetailEntity> saleDetailEntities = saleRequestDto
            .items()
            .stream()
            .map(saleDetailRequestDto -> {
                TicketTypeEntity ticketTypeEntity = ticketTypeRepository
                    .findById(saleDetailRequestDto.ticketTypeId())
                    .orElseThrow(() -> new RuntimeException("Ticket type not found with ID: " + saleDetailRequestDto.ticketTypeId()));

                var subtotal = ticketTypeEntity.getUnitPrice()
                    .multiply(BigDecimal.valueOf(saleDetailRequestDto.quantity()));

                SaleDetailEntity saleDetailEntity = new SaleDetailEntity();
                saleDetailEntity.setSaleEntity(saleEntity);
                saleDetailEntity.setTicketTypeEntity(ticketTypeEntity);
                saleDetailEntity.setQuantity(saleDetailRequestDto.quantity());
                saleDetailEntity.setUnitPrice(ticketTypeEntity.getUnitPrice());
                saleDetailEntity.setSubtotal(subtotal);
                return saleDetailEntity;
            })
            .toList();

        CustomerEntity customerEntity = customerRepository
            .findById(saleRequestDto.customerId())
            .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + saleRequestDto.customerId()));

        var total = saleDetailEntities
            .stream()
            .map(SaleDetailEntity::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        saleEntity.setCustomerEntity(customerEntity);
        saleEntity.setSaleDate(LocalDateTime.now());
        saleEntity.setTotalAmount(total);
        saleEntity.setStatus("PENDING");
        saleEntity.setSaleDetailEntities(saleDetailEntities);

        Random random = new Random();
        int operationNumber = 10000 + random.nextInt(90000);
        saleEntity.setOperationNumber(String.valueOf(operationNumber));

        SaleEntity savedSaleEntity = saleRepository.save(saleEntity);

        var transactionCode = UUID.randomUUID().toString();
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setSaleEntity(savedSaleEntity);
        paymentEntity.setAmount(saleRequestDto.payment().amount());
        paymentEntity.setPaymentDate(LocalDateTime.now());
        paymentEntity.setPaymentStatus("SUCCESS");
        paymentEntity.setPaymentMethod(saleRequestDto.payment().paymentMethod());
        paymentEntity.setTransactionCode(transactionCode);

        PaymentEntity savedPaymentEntity = paymentRepository.save(paymentEntity);

        //savedSaleEntity.setStatus("SUCCESS");
        //saleRepository.save(savedSaleEntity);

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto(
            savedPaymentEntity.getPaymentMethod(),
            savedPaymentEntity.getPaymentStatus(),
            savedPaymentEntity.getTransactionCode()
        );

        SaleResponseDto saleResponseDto = new SaleResponseDto(
            savedSaleEntity.getId(),
            savedSaleEntity.getOperationNumber(),
            savedSaleEntity.getTotalAmount(),
            savedSaleEntity.getStatus(),
            paymentResponseDto
        );
        return saleResponseDto;
    }

    @Override
    @Transactional
    public SaleResponseDto createSaleV2(SaleRequestDto saleRequestDto) {
        CustomerEntity customerEntity = customerRepository
            .findById(saleRequestDto.customerId())
            .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + saleRequestDto.customerId()));

        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setCustomerEntity(customerEntity);
        saleEntity.setSaleDate(LocalDateTime.now());
        saleEntity.setStatus("PENDING");

        SaleEntity savedSaleEntity = saleRepository.save(saleEntity);

        List<SaleDetailEntity> saleDetailEntities = saleRequestDto
            .items()
            .stream()
            .map(saleDetailRequestDto -> {
                TicketTypeEntity ticketTypeEntity = ticketTypeRepository
                    .findById(saleDetailRequestDto.ticketTypeId())
                    .orElseThrow(() -> new RuntimeException("Ticket type not found with ID: " + saleDetailRequestDto.ticketTypeId()));

                var subtotal = ticketTypeEntity.getUnitPrice()
                    .multiply(BigDecimal.valueOf(saleDetailRequestDto.quantity()));

                SaleDetailEntity saleDetailEntity = new SaleDetailEntity();
                saleDetailEntity.setSaleEntity(savedSaleEntity);
                saleDetailEntity.setTicketTypeEntity(ticketTypeEntity);
                saleDetailEntity.setQuantity(saleDetailRequestDto.quantity());
                saleDetailEntity.setUnitPrice(ticketTypeEntity.getUnitPrice());
                saleDetailEntity.setSubtotal(subtotal);
                return saleDetailEntity;
            })
            .toList();



        return null;
    }
}

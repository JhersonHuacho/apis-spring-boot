package dev.huachin.java.spring.api_concert_tickets.service.impl;

import dev.huachin.java.spring.api_concert_tickets.dto.request.ReservationRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.*;
import dev.huachin.java.spring.api_concert_tickets.entity.CustomerEntity;
import dev.huachin.java.spring.api_concert_tickets.entity.ReservationEntity;
import dev.huachin.java.spring.api_concert_tickets.entity.TicketTypeEntity;
import dev.huachin.java.spring.api_concert_tickets.repository.*;
import dev.huachin.java.spring.api_concert_tickets.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final ConcertRepository concertRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<ReservationResponseDto> getAll() {
        return reservationRepository.findAll()
            .stream()
            .map(reservation -> {
                CustomerResponseDto customerResponseDto = new CustomerResponseDto(
                    reservation.getCustomerEntity().getId(),
                    reservation.getCustomerEntity().getFirstName(),
                    reservation.getCustomerEntity().getLastName(),
                    reservation.getCustomerEntity().getEmail(),
                    reservation.getCustomerEntity().getAddress(),
                    reservation.getCustomerEntity().getPhoneNumber()
                );
                var genreResponseDto = new GenreResponseDto(
                    reservation.getTicketTypeEntity().getConcertEntity().getGenreEntity().getId(),
                    reservation.getTicketTypeEntity().getConcertEntity().getGenreEntity().getName()
                );

                var concertResponseDto = new ConcertResponseDto(
                    reservation.getTicketTypeEntity().getConcertEntity().getId(),
                    reservation.getTicketTypeEntity().getConcertEntity().getTitle(),
                    reservation.getTicketTypeEntity().getConcertEntity().getDescription(),
                    reservation.getTicketTypeEntity().getConcertEntity().getPlace(),
                    genreResponseDto,
                    reservation.getTicketTypeEntity().getConcertEntity().getDateEvent(),
                    reservation.getTicketTypeEntity().getConcertEntity().getImageUrl(),
                    reservation.getTicketTypeEntity().getConcertEntity().getFinalized(),
                    reservation.getTicketTypeEntity().getConcertEntity().getStatus()
                );

                var ticketTypeResponseDto = new TicketTypeResponseDto(
                    reservation.getTicketTypeEntity().getId(),
                    concertResponseDto,
                    reservation.getTicketTypeEntity().getName(),
                    reservation.getTicketTypeEntity().getUnitPrice(),
                    reservation.getTicketTypeEntity().getStock(),
                    reservation.getTicketTypeEntity().getStatus()
                );
                return new ReservationResponseDto(
                    reservation.getId(),
                    customerResponseDto,
                    ticketTypeResponseDto,
                    reservation.getQuantity(),
                    reservation.getReservedAt(),
                    reservation.getExpirationAt(),
                    reservation.getStatus()
                );
            })
            .toList();
    }

    @Override
    public Optional<ReservationResponseDto> getById(Long id) {
        return reservationRepository.findById(id)
            .map(reservation -> {
                CustomerResponseDto customerResponseDto = new CustomerResponseDto(
                    reservation.getCustomerEntity().getId(),
                    reservation.getCustomerEntity().getFirstName(),
                    reservation.getCustomerEntity().getLastName(),
                    reservation.getCustomerEntity().getEmail(),
                    reservation.getCustomerEntity().getAddress(),
                    reservation.getCustomerEntity().getPhoneNumber()
                );
                var genreResponseDto = new GenreResponseDto(
                    reservation.getTicketTypeEntity().getConcertEntity().getGenreEntity().getId(),
                    reservation.getTicketTypeEntity().getConcertEntity().getGenreEntity().getName()
                );

                var concertResponseDto = new ConcertResponseDto(
                    reservation.getTicketTypeEntity().getConcertEntity().getId(),
                    reservation.getTicketTypeEntity().getConcertEntity().getTitle(),
                    reservation.getTicketTypeEntity().getConcertEntity().getDescription(),
                    reservation.getTicketTypeEntity().getConcertEntity().getPlace(),
                    genreResponseDto,
                    reservation.getTicketTypeEntity().getConcertEntity().getDateEvent(),
                    reservation.getTicketTypeEntity().getConcertEntity().getImageUrl(),
                    reservation.getTicketTypeEntity().getConcertEntity().getFinalized(),
                    reservation.getTicketTypeEntity().getConcertEntity().getStatus()
                );

                var ticketTypeResponseDto = new TicketTypeResponseDto(
                    reservation.getTicketTypeEntity().getId(),
                    concertResponseDto,
                    reservation.getTicketTypeEntity().getName(),
                    reservation.getTicketTypeEntity().getUnitPrice(),
                    reservation.getTicketTypeEntity().getStock(),
                    reservation.getTicketTypeEntity().getStatus()
                );
                return new ReservationResponseDto(
                    reservation.getId(),
                    customerResponseDto,
                    ticketTypeResponseDto,
                    reservation.getQuantity(),
                    reservation.getReservedAt(),
                    reservation.getExpirationAt(),
                    reservation.getStatus()
                );
            });
    }

    @Override
    public ReservationResponseDto create(ReservationRequestDto reservation) {
        CustomerEntity customerEntity = customerRepository
            .findById(reservation.customerId())
            .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + reservation.customerId()));

        TicketTypeEntity ticketTypeEntity = ticketTypeRepository
            .findById(reservation.ticketTypeId())
            .orElseThrow(() -> new RuntimeException("Ticket type not found with ID: " + reservation.ticketTypeId()));

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCustomerEntity(customerEntity);
        reservationEntity.setTicketTypeEntity(ticketTypeEntity);
        reservationEntity.setQuantity(reservation.numberOfTickets());
        reservationEntity.setReservedAt(LocalDateTime.parse(reservation.reservationDateTime()));

        ReservationEntity savedReservationEntity = reservationRepository.save(reservationEntity);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto(
            savedReservationEntity.getCustomerEntity().getId(),
            savedReservationEntity.getCustomerEntity().getFirstName(),
            savedReservationEntity.getCustomerEntity().getLastName(),
            savedReservationEntity.getCustomerEntity().getEmail(),
            savedReservationEntity.getCustomerEntity().getAddress(),
            savedReservationEntity.getCustomerEntity().getPhoneNumber()
        );

        var genreResponseDto = new GenreResponseDto(
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getGenreEntity().getId(),
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getGenreEntity().getName()
        );

        var concertResponseDto = new ConcertResponseDto(
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getId(),
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getTitle(),
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getDescription(),
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getPlace(),
            genreResponseDto,
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getDateEvent(),
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getImageUrl(),
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getFinalized(),
            savedReservationEntity.getTicketTypeEntity().getConcertEntity().getStatus()
        );

        var ticketTypeResponseDto = new TicketTypeResponseDto(
            savedReservationEntity.getTicketTypeEntity().getId(),
            concertResponseDto,
            savedReservationEntity.getTicketTypeEntity().getName(),
            savedReservationEntity.getTicketTypeEntity().getUnitPrice(),
            savedReservationEntity.getTicketTypeEntity().getStock(),
            savedReservationEntity.getTicketTypeEntity().getStatus()
        );

        return new ReservationResponseDto(
            savedReservationEntity.getId(),
            customerResponseDto,
            ticketTypeResponseDto,
            savedReservationEntity.getQuantity(),
            savedReservationEntity.getReservedAt(),
            savedReservationEntity.getExpirationAt(),
            savedReservationEntity.getStatus()
        );
    }

    @Override
    public ReservationResponseDto update(Long id, ReservationRequestDto reservation) {
        CustomerEntity customerEntity = customerRepository
            .findById(reservation.customerId())
            .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + reservation.customerId()));

        TicketTypeEntity ticketTypeEntity = ticketTypeRepository
            .findById(reservation.ticketTypeId())
            .orElseThrow(() -> new RuntimeException("Ticket type not found with ID: " + reservation.ticketTypeId()));

        ReservationEntity reservationEntity = reservationRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));

        reservationEntity.setQuantity(reservation.numberOfTickets());
        reservationEntity.setReservedAt(LocalDateTime.parse(reservation.reservationDateTime()));

        ReservationEntity updatedReservationEntity = reservationRepository.save(reservationEntity);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto(
            updatedReservationEntity.getCustomerEntity().getId(),
            updatedReservationEntity.getCustomerEntity().getFirstName(),
            updatedReservationEntity.getCustomerEntity().getLastName(),
            updatedReservationEntity.getCustomerEntity().getEmail(),
            updatedReservationEntity.getCustomerEntity().getAddress(),
            updatedReservationEntity.getCustomerEntity().getPhoneNumber()
        );

        var genreResponseDto = new GenreResponseDto(
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getGenreEntity().getId(),
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getGenreEntity().getName()
        );

        var concertResponseDto = new ConcertResponseDto(
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getId(),
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getTitle(),
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getDescription(),
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getPlace(),
            genreResponseDto,
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getDateEvent(),
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getImageUrl(),
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getFinalized(),
            updatedReservationEntity.getTicketTypeEntity().getConcertEntity().getStatus()
        );

        var ticketTypeResponseDto = new TicketTypeResponseDto(
            updatedReservationEntity.getTicketTypeEntity().getId(),
            concertResponseDto,
            updatedReservationEntity.getTicketTypeEntity().getName(),
            updatedReservationEntity.getTicketTypeEntity().getUnitPrice(),
            updatedReservationEntity.getTicketTypeEntity().getStock(),
            updatedReservationEntity.getTicketTypeEntity().getStatus()
        );

        return new ReservationResponseDto(
            updatedReservationEntity.getId(),
            customerResponseDto,
            ticketTypeResponseDto,
            updatedReservationEntity.getQuantity(),
            updatedReservationEntity.getReservedAt(),
            updatedReservationEntity.getExpirationAt(),
            updatedReservationEntity.getStatus()
        );
    }

    @Override
    public void delete(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Reservation not found with ID: " + id);
        }
        reservationRepository.deleteById(id);
    }
}

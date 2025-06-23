package dev.huachin.java.spring.api_concert_tickets.controller;

import dev.huachin.java.spring.api_concert_tickets.dto.request.ReservationRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.ReservationResponseDto;
import dev.huachin.java.spring.api_concert_tickets.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    public final ReservationService reservationsService;

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        try {
            List<ReservationResponseDto> reservations = reservationsService.getAll();
            if (reservations.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            log.error("Error al obtener reservas: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservationById(Long id) {
        try {
            Optional<ReservationResponseDto> reservation = reservationsService.getById(id);
            return reservation
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
        } catch (Exception e) {
            log.error("Error al obtener reserva por ID: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(
        @RequestBody ReservationRequestDto reservationRequestDto) {
        try {
            ReservationResponseDto createdReservation = reservationsService.create(reservationRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
        } catch (Exception e) {
            log.error("Error al crear reserva: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> updateReservation(
        @PathVariable Long id,
        @RequestBody ReservationRequestDto reservationRequestDto) {
        try {
            ReservationResponseDto updatedReservation = reservationsService.update(id, reservationRequestDto);
            return ResponseEntity.ok(updatedReservation);
        } catch (Exception e) {
            log.error("Error al actualizar reserva: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        try {
            reservationsService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar reserva: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

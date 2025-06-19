package dev.huachin.java.spring.api_concert_tickets.controller;

import dev.huachin.java.spring.api_concert_tickets.dto.request.ConcertRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.ConcertResponseDto;
import dev.huachin.java.spring.api_concert_tickets.service.ConcertService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/concerts")
@AllArgsConstructor
public class ConcertController {
    private final ConcertService concertService;

    @GetMapping
    public ResponseEntity<List<ConcertResponseDto>> getAll() {
        try {
            List<ConcertResponseDto> concerts = concertService.getAll();
            if (concerts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(concerts);
        } catch (Exception e) {
            log.error("Error al obtener conciertos: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcertResponseDto> getById(Long id) {
        try {
            Optional<ConcertResponseDto> concert = concertService.getById(id);
            if (concert.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(concert.get());
        } catch (Exception e) {
            log.error("Error al obtener concierto por ID: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ConcertResponseDto> createConcert(
        @RequestBody ConcertRequestDto concertRequestDto) {
        try {
            ConcertResponseDto createdConcert = concertService.create(concertRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdConcert);
        } catch (Exception e) {
            log.error("Error al crear concierto: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConcertResponseDto> updateConcert(
        @PathVariable Long id,
        @RequestBody ConcertRequestDto concertRequestDto) {
        try {
            ConcertResponseDto updatedConcert = concertService.update(id, concertRequestDto);
            return ResponseEntity.ok(updatedConcert);
        } catch (Exception e) {
            log.error("Error al actualizar concierto: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConcert(@PathVariable Long id) {
        try {
            concertService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar concierto: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

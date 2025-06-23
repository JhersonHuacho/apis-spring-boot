package dev.huachin.java.spring.api_concert_tickets.controller;

import dev.huachin.java.spring.api_concert_tickets.dto.request.TicketTypeRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.TicketTypeResponseDto;
import dev.huachin.java.spring.api_concert_tickets.service.TicketTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ticket-types")
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;

    @GetMapping
    public ResponseEntity<List<TicketTypeResponseDto>> getAllTicketTypes() {
        try {
            List<TicketTypeResponseDto> ticketTypes = ticketTypeService.getAll();
            if (ticketTypes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(ticketTypes);
        } catch (Exception e) {
            log.error("Error al obtener tipos de entradas: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketTypeResponseDto> getTicketTypeById(Long id) {
        try {
            Optional<TicketTypeResponseDto> ticketType = ticketTypeService.getById(id);
            if (ticketType.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(ticketType.get());
        } catch (Exception e) {
            log.error("Error al obtener tipo de entrada por ID: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<TicketTypeResponseDto> createTicketType(
        @RequestBody TicketTypeRequestDto ticketTypeDto) {
        try {
            TicketTypeResponseDto createdTicketType = ticketTypeService.create(ticketTypeDto);
            return ResponseEntity.status(201).body(createdTicketType);
        } catch (Exception e) {
            log.error("Error al crear tipo de entrada: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketTypeResponseDto> updateTicketType(
        @PathVariable Long id,
        @RequestBody TicketTypeRequestDto ticketTypeDto) {
        try {
            TicketTypeResponseDto updatedTicketType = ticketTypeService.update(id, ticketTypeDto);
            return ResponseEntity.ok(updatedTicketType);
        } catch (Exception e) {
            log.error("Error al actualizar tipo de entrada: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketType(@PathVariable Long id) {
        try {
            ticketTypeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar tipo de entrada: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

}

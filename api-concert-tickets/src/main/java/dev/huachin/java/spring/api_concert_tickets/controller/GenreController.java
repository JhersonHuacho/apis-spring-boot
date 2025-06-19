package dev.huachin.java.spring.api_concert_tickets.controller;

import dev.huachin.java.spring.api_concert_tickets.dto.request.GenreRequestDto;
import dev.huachin.java.spring.api_concert_tickets.dto.response.GenreResponseDto;
import dev.huachin.java.spring.api_concert_tickets.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Operation(
        summary = "Get all genres",
        description = "Retorna una lista de todos los géneros disponibles",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "List of genres",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenreResponseDto[].class)
                )
            ),
            @ApiResponse(
                responseCode = "204",
                description = "No genres found"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "getAllGenres"
    )
    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAllGenres() {
        try {
            List<GenreResponseDto> genres = genreService.getAll();
            if (genres.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(genres);
        } catch (Exception e) {
            log.error("Error fetching genres: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation (
        summary = "Get genre by ID",
        description = "Retorna un género específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Genre found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenreResponseDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Genre not found"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "getGenreById"
    )
    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> getGenreById(Long id) {
        try {
            return genreService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error("Error fetching genre with id: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation (
        summary = "Create a new genre",
        description = "Crea un nuevo género en el sistema",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Genre created successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenreResponseDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input data"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "createGenre"
    )
    @PostMapping
    public ResponseEntity<GenreResponseDto> createGenre(@Valid @RequestBody GenreRequestDto genreRequestDto) {
        try {
            GenreResponseDto createdGenre = genreService.create(genreRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
        } catch (Exception e) {
            log.error("Error creating genre: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation (
        summary = "Update an existing genre",
        description = "Actualiza un género existente por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Genre updated successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenreResponseDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Genre not found"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input data"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "updateGenre"
    )
    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDto> updateGenre(
        @PathVariable Long id,
        @Valid @RequestBody GenreRequestDto genreRequestDto) {
        try {
            GenreResponseDto updatedGenre = genreService.update(id, genreRequestDto);
            return ResponseEntity.ok(updatedGenre);
        } catch (Exception e) {
            log.error("Error updating genre with id: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation (
        summary = "Delete a genre",
        description = "Elimina un género por su ID",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Genre deleted successfully"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Genre not found"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            )
        },
        operationId = "deleteGenre"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        try {
            Optional<GenreResponseDto> genre = genreService.getById(id);
            if (genre.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            genreService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting genre with id: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

package dev.huachin.java.spring.api_concert_tickets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "concerts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConcertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String place;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genreEntity;
    @Column(name = "date_event")
    private LocalDateTime dateEvent;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(insertable = false)
    private Boolean finalized;
    @Column(insertable = false)
    private Boolean status;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

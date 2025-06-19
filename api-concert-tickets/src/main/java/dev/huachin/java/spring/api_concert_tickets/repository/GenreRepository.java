package dev.huachin.java.spring.api_concert_tickets.repository;

import dev.huachin.java.spring.api_concert_tickets.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}

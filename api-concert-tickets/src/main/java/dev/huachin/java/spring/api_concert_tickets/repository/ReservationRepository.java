package dev.huachin.java.spring.api_concert_tickets.repository;

import dev.huachin.java.spring.api_concert_tickets.dto.response.ReservationResponseDto;
import dev.huachin.java.spring.api_concert_tickets.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}

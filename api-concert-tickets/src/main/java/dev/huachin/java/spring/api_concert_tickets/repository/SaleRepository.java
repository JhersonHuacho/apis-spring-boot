package dev.huachin.java.spring.api_concert_tickets.repository;

import dev.huachin.java.spring.api_concert_tickets.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {
}

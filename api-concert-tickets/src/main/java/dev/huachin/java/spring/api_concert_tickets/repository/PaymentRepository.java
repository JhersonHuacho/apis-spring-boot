package dev.huachin.java.spring.api_concert_tickets.repository;

import dev.huachin.java.spring.api_concert_tickets.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}

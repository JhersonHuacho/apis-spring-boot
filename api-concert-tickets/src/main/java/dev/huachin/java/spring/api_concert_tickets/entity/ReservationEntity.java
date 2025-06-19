package dev.huachin.java.spring.api_concert_tickets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "concert_id")
    private CustomerEntity customerEntity;
    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketTypeEntity ticketTypeEntity;
    private Integer quantity;
    @Column(name = "reservation_at")
    private LocalDateTime reservedAt;
    @Column(name = "expiration_at")
    private LocalDateTime expirationAt;
    private String status;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

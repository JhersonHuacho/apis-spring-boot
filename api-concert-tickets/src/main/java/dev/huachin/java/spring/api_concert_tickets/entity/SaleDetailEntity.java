package dev.huachin.java.spring.api_concert_tickets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleEntity saleEntity;
    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketTypeEntity ticketTypeEntity;
    private Integer quantity;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private Boolean status;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

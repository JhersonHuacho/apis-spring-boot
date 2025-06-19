package dev.huachin.java.spring.api_concert_tickets.repository;

import dev.huachin.java.spring.api_concert_tickets.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository es una anotación de Spring que indica que esta interfaz es un repositorio.
// y lo registra en el contexto de la aplicación para que pueda ser inyectado en otros componentes.
// y lo que hace es extender la funcionalidad de JpaRepository para la entidad ConcertEntity.
@Repository
public interface ConcertRepository extends JpaRepository<ConcertEntity, Long> {
}

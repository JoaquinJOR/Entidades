package edu.unimagdalena.microg2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.unimagdalena.microg2.entities.Sugerencia;
import java.time.LocalDateTime;
import java.util.List; 

public interface SugerenciaRepository extends JpaRepository<Sugerencia, Long> {
    List<Sugerencia> findByDate(LocalDateTime createdAt);
    @Query("SELECT s FROM Sugerencia s WHERE s.createdAt LIKE %?1")
    List<Sugerencia> buscarPorFecha(LocalDateTime createdAt);
}

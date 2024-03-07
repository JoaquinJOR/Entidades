package edu.unimagdalena.microg2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.unimagdalena.microg2.entities.Partida;
import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByCityAndProvince(String ciudad, String provincia);
    @Query("SELECT p FROM Partida p WHERE p.ciudad = ?1 AND p.provincia = ?2")
    List<Partida> buscarPorCiudadYProvicia(String ciudad, String provincia);

    List<Partida> findBySport(String deporte);
    @Query("SELECT p FROM Partida p WHERE p.deporte LIKE %?1")
    List<Partida> buscarPorDeporte(String deporte);
}

package edu.unimagdalena.microg2.repository;

import edu.unimagdalena.microg2.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByCreatorLike(String creador);
    @Query("SELECT m FROM Mensaje m WHERE m.creador LIKE %?1")
    List<Mensaje> buscarPorCreador(String creador);

    List<Mensaje> findByaddresseeLike(String destinatario);
    @Query("SELECT m FROM Mensaje m WHERE m.destinatario LIKE %?1")
    List<Mensaje> buscarPorDestinatario(String destinatario);

    List<Mensaje> findByContentLike(String contenido);
    @Query("SELECT m FROM Mensaje m WHERE m.contenido LIKE %?1")
    List<Mensaje> buscarPorContenido(String contenido);
}

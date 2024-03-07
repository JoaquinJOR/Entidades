package edu.unimagdalena.microg2.repository;

import edu.unimagdalena.microg2.AbstractIntegrationDBTest;
import edu.unimagdalena.microg2.entities.Partida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PartidaRepositoryTest extends AbstractIntegrationDBTest {
    @Autowired
    private PartidaRepository partidaRepository;

    @BeforeEach
    void setUp() {
        partidaRepository.deleteAll();
    }

    @Test
    void givenAPartida_whenSave_thenPartidaWithId() {
        // given
        Partida partida = new Partida();
        partida.setCiudad("Bogotá");
        partida.setProvincia("Cundinamarca");
        partida.setDeporte("Fútbol");

        // when
        Partida savedPartida = partidaRepository.save(partida);

        // then
        assertThat(savedPartida.getId()).isNotNull();
    }

    @Test
    @DisplayName("given a set of games when searching by city, province, and sport then get the list of games in the database")
    void shouldGetPartidasByCityProvinceAndSport() {
        // given
        Partida partida1 = new Partida();
        partida1.setCiudad("Bogotá");
        partida1.setProvincia("Cundinamarca");
        partida1.setDeporte("Fútbol");
        partidaRepository.save(partida1);

        Partida partida2 = new Partida();
        partida2.setCiudad("Medellín");
        partida2.setProvincia("Antioquia");
        partida2.setDeporte("Baloncesto");
        partidaRepository.save(partida2);

        Partida partida3 = new Partida();
        partida3.setCiudad("Cali");
        partida3.setProvincia("Valle del Cauca");
        partida3.setDeporte("Tenis");
        partidaRepository.save(partida3);

        // when
        List<Partida> partidas = partidaRepository.findByCityAndProvince("Bogotá", "Cundinamarca");
        List<Partida> partidas1 = partidaRepository.findBySport("Fútbol");
        // then
        assertThat(partidas).hasSize(1);
        assertThat(partidas.get(0).getDeporte()).isEqualTo("Fútbol");
    }
}

package edu.unimagdalena.microg2.repository;

import edu.unimagdalena.microg2.AbstractIntegrationDBTest;
import edu.unimagdalena.microg2.entities.Sugerencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SugerenciaRepositoryTest extends AbstractIntegrationDBTest {

    @Autowired
    private SugerenciaRepository sugerenciaRepository;

    @BeforeEach
    void setUp() {
        sugerenciaRepository.deleteAll();
    }

    @Test
    void givenSugerenciaWithDate_whenFindByFecha_thenGetSugerenciaList() {
        // Given
        LocalDate fechaPrueba = LocalDate.of(2024, 3, 7);
        Sugerencia sugerencia = Sugerencia.builder()
                .contenido("Hacer algo interesante")
                .fecha(fechaPrueba)
                .build();
        sugerenciaRepository.save(sugerencia);

        // When
        List<Sugerencia> sugerenciasEnFecha = sugerenciaRepository.findByDate(fechaPrueba);

        // Then
        assertThat(sugerenciasEnFecha).isNotEmpty();
        assertThat(sugerenciasEnFecha).hasSize(1);
        assertThat(sugerenciasEnFecha.get(0).getCreatedAt()).isEqualTo(fechaPrueba);
    }
}

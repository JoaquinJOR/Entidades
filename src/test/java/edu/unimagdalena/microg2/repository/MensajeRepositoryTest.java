package edu.unimagdalena.microg2.repository;

import edu.unimagdalena.microg2.AbstractIntegrationDBTest;
import edu.unimagdalena.microg2.entities.Mensaje;
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
class MensajeRepositoryTest extends AbstractIntegrationDBTest {
    @Autowired
    private MensajeRepository mensajeRepository;

    @BeforeEach
    void setUp() {
        mensajeRepository.deleteAll();
    }

    @Test
    void givenAMessage_whenSave_thenMessageWithId() {
        // given
        Mensaje mensaje = new Mensaje();
        mensaje.setCreador("Usuario1");
        mensaje.setDestinatario("Usuario2");
        mensaje.setContenido("Hola, ¿cómo estás?");

        // when
        Mensaje savedMessage = mensajeRepository.save(mensaje);

        // then
        assertThat(savedMessage.getId()).isNotNull();
    }

    @Test
    @DisplayName("given a set of messages when searching by creator and recipient then get the list of messages in the database")
    void shouldGetMessagesByCreatorAndRecipient() {
        // given
        Mensaje mensaje1 = new Mensaje();
        mensaje1.setCreador("Usuario1");
        mensaje1.setDestinatario("Usuario2");
        mensaje1.setContenido("Mensaje 1");
        mensajeRepository.save(mensaje1);

        Mensaje mensaje2 = new Mensaje();
        mensaje2.setCreador("Usuario2");
        mensaje2.setDestinatario("Usuario1");
        mensaje2.setContenido("Mensaje 2");
        mensajeRepository.save(mensaje2);

        Mensaje mensaje3 = new Mensaje();
        mensaje3.setCreador("Usuario1");
        mensaje3.setDestinatario("Usuario3");
        mensaje3.setContenido("Mensaje 3");
        mensajeRepository.save(mensaje3);

        // when
        List<Mensaje> messages = mensajeRepository.findByContentLike("Usuario1");

        // then
        assertThat(messages).hasSize(1);
        assertThat(messages.get(0).getContenido()).isEqualTo("Mensaje 1");
    }
}

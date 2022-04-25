package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.controlador.ConsultaControladorUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorUsuario.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorReservaTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar reservas")
    void deberiaListarReservas() throws Exception {

        mocMvc.perform(get("/reservas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreUsuario", is("Hiko")))
                .andExpect(jsonPath("$[0].id", is(1)));

    }

    @Test
    @DisplayName("Deberia listar Reserva por nombre de usuario")
    void deberiaListarReservaPorNombreDeUsuario() throws Exception {

        String nombreUsuario = "Hiko";
        Long idCancha = 1L;

        mocMvc.perform(get("/reservas/{nombreUsuario}/{idCancha}", nombreUsuario, idCancha)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

}

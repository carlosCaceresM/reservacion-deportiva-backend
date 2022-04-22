package com.ceiba.cancha.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.cancha.comando.ComandoCancha;
import com.ceiba.cancha.servicio.testdatabuilder.ComandoCanchaTestDataBuilder;
import com.ceiba.usuario.controlador.ComandoControladorUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorUsuario.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorCanchaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear una cancha")
    void deberiaCrearUnUsuario() throws Exception {

        ComandoCancha cancha = new ComandoCanchaTestDataBuilder().build();

        mocMvc.perform(post("/canchas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cancha)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 7}"));
    }

    @Test
    @DisplayName("Deberia actualizar los datos de una cancha")
    void deberiaActualizarUnaCancha() throws Exception {

        Long id = 1L;
        ComandoCancha cancha = new ComandoCanchaTestDataBuilder().build();

        mocMvc.perform(put("/canchas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cancha)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia eliminar una Cancha")
    void deberiaEliminarUnaCancha() throws Exception {

        Long id = 1L;
        mocMvc.perform(delete("/canchas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/canchas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

}

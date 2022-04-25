package com.ceiba.configuracion;

import com.ceiba.cancha.puerto.repositorio.RepositorioCancha;
import com.ceiba.cancha.servicio.ServicioActualizarCancha;
import com.ceiba.cancha.servicio.ServicioCrearCancha;
import com.ceiba.cancha.servicio.ServicioEliminarCancha;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.reserva.servicio.ServicioEliminarReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioCrearCancha servicioCrearCancha(RepositorioCancha repositorioCancha) {
        return new ServicioCrearCancha(repositorioCancha);
    }

    @Bean
    public ServicioEliminarCancha servicioEliminarCancha(RepositorioCancha repositorioCancha) {
        return new ServicioEliminarCancha(repositorioCancha);
    }

    @Bean
    public ServicioActualizarCancha servicioActualizarCancha(RepositorioCancha repositorioCancha) {
        return new ServicioActualizarCancha(repositorioCancha);
    }

    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva) {
        return new ServicioCrearReserva(repositorioReserva);
    }

    @Bean
    public ServicioActualizarReserva servicioActualizarReserva(RepositorioReserva repositorioReserva) {
        return new ServicioActualizarReserva(repositorioReserva);
    }

    @Bean
    public ServicioEliminarReserva servicioActualizarCancha(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        return new ServicioEliminarReserva(repositorioReserva, daoReserva);
    }

}

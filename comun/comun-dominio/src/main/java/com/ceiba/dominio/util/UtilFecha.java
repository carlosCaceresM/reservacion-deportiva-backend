package com.ceiba.dominio.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class UtilFecha {

    public static boolean esDiadeMantenimiento(LocalDateTime fecha) {
        boolean respuesta = false;
        DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();

        if (diaDeLaSemana == DayOfWeek.MONDAY) {
            respuesta = true;
        }
        return respuesta;
    }

    public static boolean esDiaHabilDeServicio(LocalDateTime fecha) {
        boolean respuesta = false;

        if (fecha.isAfter(LocalDateTime.now()) || fecha.equals(LocalDateTime.now())) {
            respuesta = true;
        }
        return respuesta;
    }

    public static boolean esDiaDeDescuento(LocalDateTime fecha) {
        boolean respuesta = false;
        DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();

        if (diaDeLaSemana == DayOfWeek.TUESDAY
                || diaDeLaSemana == DayOfWeek.WEDNESDAY
                || diaDeLaSemana == DayOfWeek.THURSDAY
        ) {
            respuesta = true;
        }
        return respuesta;
    }

    public static boolean esHoraHabilDeServicio(LocalDateTime fecha) {
        boolean respuesta = false;
        int hora = fecha.getHour();

        if (hora >= 17 && hora <= 22) {
            respuesta = true;
        }
        return respuesta;
    }

    public static boolean esHoraHabilParaAnularReserva(LocalDateTime fecha, int horaMinimaCancelacion) {
        boolean respuesta = false;
        int horaCancelacion = LocalDateTime.now().getHour() - (fecha.getHour() - horaMinimaCancelacion);

        if (horaCancelacion < horaMinimaCancelacion) {
            respuesta = true;
        }
        return respuesta;
    }

}

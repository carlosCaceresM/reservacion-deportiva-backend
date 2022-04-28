package com.ceiba.reserva.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre del Usuario";
    private static final String SE_DEBE_INGRESAR_LA_HORA_DE_INICIO = "Se debe ingresar la Hora de Inicio";
    private static final String SE_DEBE_INGRESAR_LA_HORA_FINAL = "Se debe ingresar la Hora Fin";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_A_PAGAR = "Se debe ingresar el Valor a Pagar";
    private static final String SE_DEBE_INGRESAR_EL_ESTADO = "Se debe ingresar el estado";
    public static final String LA_FECHA_NO_PUEDE_SER_IGUAL_AL_DIA_DE_DESCANSO = "La fecha no puede ser igual al dia de descanso";
    public static final String LA_HORA_DEBE_SER_UNA_HORA_HABIL_DE_SERVICIO = "La hora debe ser una hora habil de servicio";

    public static final int HORA_APERTURA_DEL_ESTABLECIMIENTO = 17;
    public static final int HORA_CIERRE_DEL_ESTABLECIMIENTO = 22;
    public static final int DESCUENTO = 20;

    private Long id;
    private String nombreUsuario;
    private LocalDateTime fecha;
    private int horasReservadas;
    private double valorPagar;
    private boolean estado;
    private Long idCancha;

    public Reserva(Long id, String nombreUsuario,
                   LocalDateTime fecha, int horasReservadas,
                   double valorPagar, boolean estado, Long idCancha
    ) {
        validarObligatorio(nombreUsuario, SE_DEBE_INGRESAR_NOMBRE_DE_USUARIO);
        validarObligatorio(fecha, SE_DEBE_INGRESAR_LA_HORA_DE_INICIO);
        validarObligatorio(horasReservadas, SE_DEBE_INGRESAR_LA_HORA_FINAL);
        validarObligatorio(valorPagar, SE_DEBE_INGRESAR_EL_VALOR_A_PAGAR);
        validarObligatorio(estado, SE_DEBE_INGRESAR_EL_ESTADO);

        validarFechaNoPuedeSerIgualAlDiaDeDescanso(fecha, LA_FECHA_NO_PUEDE_SER_IGUAL_AL_DIA_DE_DESCANSO);
        validarHoraDebeSerUnaHoraHabilDeServicio(
                fecha, HORA_APERTURA_DEL_ESTABLECIMIENTO,
                HORA_CIERRE_DEL_ESTABLECIMIENTO, LA_HORA_DEBE_SER_UNA_HORA_HABIL_DE_SERVICIO
        );

        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.horasReservadas = horasReservadas;
        this.valorPagar = calcularValorAPagar(fecha, valorPagar, horasReservadas);
        this.estado = estado;
        this.idCancha = idCancha;

    }

    private static double calcularValorAPagar(LocalDateTime fecha, double valorPagar, int horasReservadas) {
        DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();
        double valorTotal = valorPagar * horasReservadas;

        if (diaDeLaSemana == DayOfWeek.THURSDAY || diaDeLaSemana == DayOfWeek.TUESDAY
                || diaDeLaSemana == DayOfWeek.WEDNESDAY) {
            valorTotal -= (valorTotal * DESCUENTO) / 100;
        }
        return valorTotal;
    }

    public static void validarFechaNoPuedeSerIgualAlDiaDeDescanso(LocalDateTime fecha, String mensaje) {
        DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();
        if (diaDeLaSemana == DayOfWeek.MONDAY) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public static void validarHoraDebeSerUnaHoraHabilDeServicio(LocalDateTime fecha, int horaInicioServicio,
                                                                int horaFinServicio, String mensaje) {
        if (fecha.getHour() < horaInicioServicio || fecha.getHour() > horaFinServicio) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }
}
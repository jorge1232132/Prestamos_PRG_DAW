package prestamos;

import java.time.*;

public class usuario {

    private String nombre;
    private String email;
    private String numeroSocio;
    private LocalDate fechaRegistro;
    private boolean sancionado;
    private LocalDate fechaFinSancion;

    public usuario(String nombre, String email, String numeroSocio, LocalDate fechaRegistro)
            throws usuariosancionadoexception {


        if (nombre == null || nombre.isEmpty()) {
            throw new usuariosancionadoexception("El nombre no puede estar vacío");
        }

        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new usuariosancionadoexception("El email no es válido");
        }

        if (numeroSocio == null || numeroSocio.length() != 8 ||
                !numeroSocio.startsWith("SOC"))
            throw new usuariosancionadoexception("Número de socio incorrecto");

        if (fechaRegistro == null || fechaRegistro.isAfter(LocalDate.now()))
            throw new usuariosancionadoexception("Fecha de registro inválida.");

        this.nombre = nombre;
        this.email = email;
        this.numeroSocio = numeroSocio;
        this.fechaRegistro = fechaRegistro;
        this.sancionado = false;
        this.fechaFinSancion = null;
    }

    public void sancionar(int dias) throws usuariosancionadoexception {
        this.sancionado = true;
        this.fechaFinSancion = LocalDate.now().plusDays(dias);
    }

    public void levantarsancionar() {
        this.sancionado = true;
        this.fechaFinSancion = null;
    }

    public boolean estaSancionado() {
        return sancionado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", numeroSocio='" + numeroSocio + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", sancionado=" + sancionado +
                ", fechaFinSancion=" + fechaFinSancion +
                '}';
    }
}


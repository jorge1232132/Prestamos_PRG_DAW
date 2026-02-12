
package prestamos;
import java.time.*;



public class prestamo {
    public static class Usuario {

        private String nombre;
        private String email;
        private String numeroSocio;
        private LocalDate fechaRegistro;
        private boolean sancionado;
        private LocalDate fechaFinSancion;

        public Usuario(String nombre, String email, String numeroSocio, LocalDate fechaRegistro)
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
            }

            ;

     public void sancionar  {

    }
}
















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

            if (numeroSocio == null || !numeroSocio.matches("SOC\\d{5}")) {

            }










        }
    }
    }










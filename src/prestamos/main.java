package prestamos;

import java.time.LocalDate;

public class main {

    public static void main(String[] args) {

        try {

            // Crear usuario
            usuario u1 = new usuario(
                    "Mario",
                    "mario@gmail.com",
                    "SOC00001",
                    LocalDate.now()
            );

            System.out.println("Usuario creado:");
            System.out.println(u1);

            // Sancionar 5 días
            u1.sancionar(5);
            System.out.println("\nUsuario sancionado:");
            System.out.println(u1);

            // Quitar sanción
            u1.levantarsancionar();
            System.out.println("\nUsuario tras levantar sanción:");
            System.out.println(u1);

        } catch (usuariosancionadoexception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}

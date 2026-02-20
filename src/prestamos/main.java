package prestamos;

import java.time.LocalDate;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        gestorbiblioteca gestor = new gestorbiblioteca();

        int op = 0;

        while (op != 8) {

            System.out.println("\n==== MENU BIBLIOTECA ====");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Realizar préstamo del libro");
            System.out.println("3. Devolver el  libro");
            System.out.println("4. Consultar estado de usuario");
            System.out.println("5. Mostrar préstamos activos");
            System.out.println("6. Mostrar usuarios sancionados");
            System.out.println("7. Actualizar sanciones");
            System.out.println("8. Salir");
            System.out.print("Opción: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            // 1) Registrar nuevo  usuario
            if (op == 1) {
                try {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Número socio (SOC00001): ");
                    String numSocio = sc.nextLine();

                    usuario u = new usuario(nombre, email, numSocio, LocalDate.now());
                    gestor.registrarUsuario(u);

                    System.out.println(" Usuario registrado.");
                } catch (Exception e) {
                    System.out.println(" Error: " + e.getMessage());
                }
            }









        }


    }

}

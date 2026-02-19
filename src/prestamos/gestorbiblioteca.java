package prestamos;

public class gestorbiblioteca {


    private static final int MAX_USUARIOS = 50;
    private static final int MAX_PRESTAMOS = 200;


    private usuario[] usuarios = new usuario[MAX_USUARIOS];
    private prestamo[] prestamos = new prestamo[MAX_PRESTAMOS];

    private int numeroUsuarios = 0;
    private int numeroPrestamos = 0;


    public void añadirUsuario(usuario u) {
        if (numeroUsuarios < MAX_USUARIOS) {
            usuarios[numeroUsuarios] = u;
            numeroUsuarios++;
        }
    }

    public void añadirPrestamo(prestamo p) {
        if (numeroPrestamos < MAX_PRESTAMOS) {
            prestamos[numeroPrestamos] = p;
            numeroPrestamos++;
        }
    }

    public int getNumeroUsuarios() {
        return numeroUsuarios;
    }

    public int getNumeroPrestamos() {
        return numeroPrestamos;
    }
}


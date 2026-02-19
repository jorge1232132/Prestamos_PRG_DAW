package prestamos;

import java.time.LocalDate;

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

    public void registrarUsuario(usuario u) throws usuariotepetidoexception {
        if (u == null) return;

        for (int i = 0; i < numeroUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].toString().equals(u.toString())) {
                throw new usuariotepetidoexception("Usuario ya registrado.");
            }
        }

        añadirUsuario(u);
    }

    public usuario buscarUsuario(String codigoSocio) {
        if (codigoSocio == null) return null;

        for (int i = 0; i < numeroUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].toString().contains(codigoSocio)) {
                return usuarios[i];
            }
        }
        return null;
    }

    public prestamo realizarPrestamo(String codigoLibro, String titulo, LocalDate fechaPrestamo, usuario socio)
            throws prestamoinvalidoexception, usuariosancionadoexception, libronodisponibleexception {

        if (socio == null) throw new prestamoinvalidoexception("Socio null.");

        if (socio.estaSancionado())
            throw new usuariosancionadoexception("Usuario sancionado, no puede hacer préstamos.");


        for (int i = 0; i < numeroPrestamos; i++) {
            if (prestamos[i] != null &&
                    prestamos[i].getCodigoLibro().equals(codigoLibro) &&
                    prestamos[i].getFechaDevolucionReal() == null) {

                throw new libronodisponibleexception("Libro no disponible (ya prestado).");
            }
        }

        prestamo p = new prestamo(codigoLibro, socio, titulo, fechaPrestamo);
        añadirPrestamo(p);
        return p;
    }

    public boolean devolverLibro(String codigoLibro, LocalDate fechaDevolucion)
            throws prestamoinvalidoexception {

        for (int i = 0; i < numeroPrestamos; i++) {
            if (prestamos[i] != null &&
                    prestamos[i].getCodigoLibro().equals(codigoLibro) &&
                    prestamos[i].getFechaDevolucionReal() == null) {

                // marca devuelto
                prestamos[i].registrarDevolucion(fechaDevolucion);

                // sanciona 1 día por cada día de retraso
                int retraso = prestamos[i].calculaDiasRetraso();
                if (retraso > 0) {
                    try {
                        prestamos[i].getSocio().sancionar(retraso);
                    } catch (Exception e) {

                    }
                }
                return true;
            }
        }

        return false;
    }

    public prestamo[] getPrestamos() {
        return prestamos;
    }

    public usuario[] getUsuarios() {
        return usuarios;
    }

    @Override
    public String toString() {
        String txt = "=== USUARIOS (" + numeroUsuarios + ") ===\n";
        for (int i = 0; i < numeroUsuarios; i++) {
            txt += usuarios[i] + "\n";
        }

        txt += "\n=== PRESTAMOS (" + numeroPrestamos + ") ===\n";
        for (int i = 0; i < numeroPrestamos; i++) {
            txt += prestamos[i] + "\n";
        }

        return txt;
    }
}

package prestamos;

import java.time.LocalDate;

public class prestamo {

    private String codigoLibro; // 3 mayúsculas + 4 dígitos (LIB0001)
    private String tituloLibro;
    private usuario socio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal; // null si no devuelto

    // Getters (los necesita GestorBiblioteca)
    public String getCodigoLibro() { return codigoLibro; }
    public usuario getSocio() { return socio; }
    public LocalDate getFechaDevolucionReal() { return fechaDevolucionReal; }

    // Constructor
    public prestamo(String codigoLibro, usuario socio, String tituloLibro, LocalDate fechaPrestamo)
            throws prestamoinvalidoexception {

        if (codigoLibro == null || codigoLibro.trim().isEmpty())
            throw new prestamoinvalidoexception("Código libro vacío.");

        codigoLibro = codigoLibro.trim();

        if (codigoLibro.length() != 7)
            throw new prestamoinvalidoexception("Código inválido: debe tener 7 caracteres (LIB0001).");

        // 3 letras mayúsculas
        for (int i = 0; i < 3; i++) {
            char c = codigoLibro.charAt(i);
            if (c < 'A' || c > 'Z')
                throw new prestamoinvalidoexception("Código inválido: las 3 primeras deben ser mayúsculas.");
        }

        // 4 dígitos
        for (int i = 3; i < 7; i++) {
            if (!Character.isDigit(codigoLibro.charAt(i)))
                throw new prestamoinvalidoexception("Código inválido: los 4 últimos deben ser dígitos.");
        }

        if (socio == null)
            throw new prestamoinvalidoexception("Socio null.");

        if (tituloLibro == null || tituloLibro.trim().isEmpty())
            throw new prestamoinvalidoexception("Título vacío.");

        if (fechaPrestamo == null)
            throw new prestamoinvalidoexception("Fecha préstamo null.");

        if (fechaPrestamo.isAfter(LocalDate.now()))
            throw new prestamoinvalidoexception("Fecha préstamo no puede ser futura.");

        this.codigoLibro = codigoLibro;
        this.socio = socio;
        this.tituloLibro = tituloLibro.trim();
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaPrestamo.plusDays(14);
        this.fechaDevolucionReal = null;
    }

    public void registrarDevolucion(LocalDate fecha) throws prestamoinvalidoexception {
        if (fecha == null)
            throw new prestamoinvalidoexception("Fecha devolución null.");

        if (fecha.isBefore(fechaPrestamo))
            throw new prestamoinvalidoexception("Devolución no puede ser antes del préstamo.");

        this.fechaDevolucionReal = fecha;
    }

    public int calculaDiasRetraso() {
        LocalDate comparo = (fechaDevolucionReal != null) ? fechaDevolucionReal : LocalDate.now();

        if (!comparo.isAfter(fechaDevolucionPrevista)) return 0;

        return (int) (comparo.toEpochDay() - fechaDevolucionPrevista.toEpochDay());
    }

    public boolean estaRetrasado() {
        return fechaDevolucionReal == null && LocalDate.now().isAfter(fechaDevolucionPrevista);
    }

    @Override
    public String toString() {
        return "prestamo{" +
                "codigoLibro='" + codigoLibro + '\'' +
                ", tituloLibro='" + tituloLibro + '\'' +
                ", socio=" + socio +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucionPrevista=" + fechaDevolucionPrevista +
                ", fechaDevolucionReal=" + fechaDevolucionReal +
                '}';
    }
}

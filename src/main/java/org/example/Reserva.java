package org.example;

import java.util.ArrayList;

public class Reserva {
    // Declaraciones
    private int idSocio;
    private int idlibro;
    private String fecha;
    private ArrayList<Libro> listaReservaLibro;

    // Constructor

    public Reserva(int idSocio, int idlibro, String fecha) {
        this.idSocio = idSocio;
        this.idlibro = idlibro;
        this.fecha = fecha;
        this.listaReservaLibro = new ArrayList<>();

    }

    // Getter

    public String getFecha() {
        return fecha;
    }


    public void agregarLibroReserva(Libro libro) {
        listaReservaLibro.add(libro);
    }
}

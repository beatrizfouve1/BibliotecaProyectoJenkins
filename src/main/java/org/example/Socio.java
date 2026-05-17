package org.example;

import java.util.ArrayList;

public class Socio {
    private int idSocio;
    private String nombre;
    private String apellidos;
    public int numPrestamo;
    private static int numSocios;
    public static final int PRESTAMOS_MAX = 3;
    private ArrayList<Libro> librosPrestados;

    public Socio(String nombre, String apellidos) {
        numSocios++;
        this.idSocio = numSocios;
        this.nombre = nombre;
        this.apellidos = apellidos;
        numPrestamo = 0;
        this.librosPrestados = new ArrayList<>();

    }

    //Getter y Setter

    public ArrayList<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public int getIdSocio() {
        return idSocio;
    }

    public void agregarLibroPrestado(Libro libro) {
        librosPrestados.add(libro);
    }


}








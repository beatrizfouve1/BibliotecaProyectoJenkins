package org.example;



public class Libro {
    // Declaraciones
    private int isLibro;
    private String autor;
    private String titulo;
    private boolean isPrestado;
    private static int numTotalLibros;
    private boolean disponible;


    // Constructor
    public Libro(String autor, String titulo) {
        numTotalLibros++;
        this.isLibro = numTotalLibros;
        this.autor = autor;
        this.titulo = titulo;
        this.isPrestado = false;
        this.disponible = true;
    }

    // Getter and setter
    public int getIsLibro() {
        return isLibro;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setPrestado(boolean prestado) {
        isPrestado = prestado;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}




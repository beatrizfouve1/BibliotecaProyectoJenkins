package org.example;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        // Declaraciones
        Scanner scanner = new Scanner(System.in);

        final int PRESTAR_LIBRO = 1, DEVOLVER_LIBRO = 2, RESERVA_LIBRO = 3, SALIR = 4;
        ArrayList<Socio> listaSocios = new ArrayList<>();
        ArrayList<Libro> listaLibros = new ArrayList<>();
        ArrayList<Reserva> listaReserva = new ArrayList<>();
        int opcion = 0;

        cargarSocio(listaSocios);
        cargarLibro(listaLibros);


        // Programa
        while (opcion != SALIR) {
            opcion = cargarMenu(scanner);
            scanner.nextLine();
            switch (opcion) {
                case PRESTAR_LIBRO -> {
                    try {
                        Socio socio = buscarSocio(listaSocios, scanner);
                        Libro libro = buscarLibro(listaLibros, scanner);

                        if (libro.isDisponible() && socio.getLibrosPrestados().size() < Socio.PRESTAMOS_MAX) {
                            socio.agregarLibroPrestado(libro);
                            libro.setDisponible(false);
                            libro.setPrestado(true);
                            socio.numPrestamo++;
                            System.out.println("Libro prestado: " + libro.getTitulo() + " al socio " + socio.getNombre() + socio.getApellidos());
                        } else if (!libro.isDisponible()) {
                            System.out.println("El libro no esta disponible");
                        } else {
                            System.out.println("El socio llego al limite de prestamos");
                        }
                    } catch (Exception exception) {
                        System.out.println("Error " + exception.getMessage());
                    }
                }

                case DEVOLVER_LIBRO -> {
                    try {
                        devolverLibro(listaLibros, listaSocios, scanner);
                    } catch (Exception exception) {
                        System.out.println("Error " + exception.getMessage());
                    }

                }

                case RESERVA_LIBRO -> {
                    try {
                        reservarLibro(listaLibros, listaSocios, listaReserva, scanner);
                    }catch (Exception exception){
                        System.out.println("Error" + exception.getMessage());
                    }
                }

                case SALIR -> System.out.println("Fin del programa");
                default -> System.out.println("Opción no contemplada");
            }
        }
    }
    // Funciones
    private static void reservarLibro(ArrayList<Libro> listaLibros, ArrayList<Socio> listaSocios, ArrayList<Reserva> listaReserva, Scanner scanner) throws Exception{
        Socio socio = buscarSocio(listaSocios, scanner);
        System.out.println("Introduce título del libro a reservar: ");
        String nombreLibro = scanner.nextLine();
        System.out.println("Introduce el nombre del autor");
        String nombreAutor = scanner.nextLine();

        Libro libroReservar = null;
        for (Libro libro : listaLibros){
            if (libro.getTitulo().equals(nombreLibro) && libro.getAutor().equals(nombreAutor)){
                libroReservar = libro;
            }
        }
        if (libroReservar == null){
            throw new Exception("Libro no encontrado");
        }
        if (libroReservar.isDisponible()){
            System.out.println("El libro está disponible");

        }

        Reserva reserva = new Reserva(socio.getIdSocio(), libroReservar.getIsLibro(), LocalDate.now().toString());
        reserva.agregarLibroReserva(libroReservar);
        listaReserva.add(reserva);

        System.out.println("Reserva realizada");
        System.out.println("Socio " + socio.getNombre() + " " + socio.getApellidos());
        System.out.println("Libro reservado: " + libroReservar.getTitulo());
        System.out.println("Fecha de reserva: " + reserva.getFecha());
    }

    private static Socio devolverLibro(ArrayList<Libro> listaLibros, ArrayList<Socio> listaSocios, Scanner scanner) throws Exception {
        System.out.println("Introduce el nombre del socio");
        String nombreSocio = scanner.nextLine();
        System.out.println("Introduce el apellido del socio");
        String apellidos = scanner.nextLine();
        System.out.println("Introduce título del libro");
        String nombreLibro = scanner.nextLine();
        System.out.println("Introcuce nómbre del Autor");
        String nombreAutor = scanner.nextLine();

        Socio socioEncontrado = null;
        for (Socio socio : listaSocios) {
            if (socio.getNombre().equals(nombreSocio) && socio.getApellidos().equals(apellidos)) {
                socioEncontrado = socio;

            }
            if (socioEncontrado == null) {
                throw new Exception("Socio no encontrado");
            }
        }
        Libro libroDevolver = null;
        for (Libro libro : socioEncontrado.getLibrosPrestados()) {
            if (libro.getTitulo().equals(nombreLibro) && libro.getAutor().equals(nombreAutor)) {
                libroDevolver = libro;
            }
        }
        if (libroDevolver == null) {
            throw new Exception("No tiene prestado libro" + nombreLibro);
        }
        socioEncontrado.getLibrosPrestados().remove(libroDevolver);
        libroDevolver.setDisponible(true);
        libroDevolver.setPrestado(false);
        socioEncontrado.numPrestamo--;

        System.out.println(socioEncontrado.getNombre() + " devuelve " + libroDevolver.getTitulo());

        return socioEncontrado;
    }


    private static Socio buscarSocio(ArrayList<Socio> listaSocios, Scanner scanner) throws Exception {
        System.out.println("Introduce nombre del socio");
        String nombreSocio = scanner.nextLine();
        System.out.println("Introduce apellido del socio");
        String apellidos = scanner.nextLine();

        Socio socioEncontrado = null;
        for (Socio socio : listaSocios) {
            if (socio.getNombre().equals(nombreSocio) && socio.getApellidos().equals(apellidos)) {
                socioEncontrado = socio;
            }
        }
        if(socioEncontrado == null){
            throw new Exception("Socio no encontrado");
        }

        if (socioEncontrado.getLibrosPrestados().size() >= Socio.PRESTAMOS_MAX) {
            throw new Exception("Socio tiene el limite de 3 libros prestados ");
        }
        return socioEncontrado;
    }

    private static Libro buscarLibro(ArrayList<Libro> listaLibros, Scanner scanner) throws Exception {
        System.out.println("Introduce título del libro");
        String nombreLibro = scanner.nextLine();
        System.out.println("Introcuce nómbre del Autor");
        String nombreAutor = scanner.nextLine();
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equals(nombreLibro) && libro.getAutor().equals(nombreAutor)) {
                return libro;
            }
        }
        throw new Exception("Libro no encontrado");
    }


    private static int cargarMenu(Scanner scanner) {
        System.out.println("1. Prestar libro");
        System.out.println("2. Devolver libro");
        System.out.println("3. Reservar libro");
        System.out.println("4. Salir");
        System.out.println("Introduce opción");
        return scanner.nextInt();
    }

    private static void cargarLibro(ArrayList<Libro> listaLibros) {
        listaLibros.add(new Libro("Miguel de Cervantes", "Don Quijote de la Mancha"));
        listaLibros.add(new Libro("Gabriel García Márquez", "Cien años de soledad"));
        listaLibros.add(new Libro("George Orwell", "1984"));
        listaLibros.add(new Libro("Jane Austen", "Orgullo y prejuicio"));
        listaLibros.add(new Libro("J.K. Rowling", "Harry Potter y la piedra filosofal"));
        listaLibros.add(new Libro("J.R.R. Tolkien", "El Señor de los Anillos"));
        listaLibros.add(new Libro("Fiodor Dostoievski", "Crimen y castigo"));
        listaLibros.add(new Libro("Isabel Allende", "La casa de los espíritus"));
        listaLibros.add(new Libro("Julio Cortázar", "Rayuela"));
        listaLibros.add(new Libro("Harper Lee", "Matar a un ruiseñor"));

    }

    private static void cargarSocio(ArrayList<Socio> listaSocios) {

        listaSocios.add(new Socio("Ana", "Alvarez"));
        listaSocios.add(new Socio("Carlos", "Martínez"));
        listaSocios.add(new Socio("María", "González"));
        listaSocios.add(new Socio("Javier", "López"));
        listaSocios.add(new Socio("Laura", "Rodríguez"));
        listaSocios.add(new Socio("David", "Fernández"));
        listaSocios.add(new Socio("Elena", "Pérez"));
        listaSocios.add(new Socio("Miguel", "Sánchez"));
        listaSocios.add(new Socio("Isabel", "Ramírez"));
        listaSocios.add(new Socio("Pablo", "Torres"));

    }
}


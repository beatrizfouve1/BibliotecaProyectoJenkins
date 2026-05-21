package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class LibroTest {
    
    private Libro libro;
    
    @BeforeEach
    void setUp() {
        libro = new Libro("Gabriel García Márquez", "Cien años de soledad");
    }
    
    @Test
    void testCrearLibro() {
        assertEquals("Gabriel García Márquez", libro.getAutor());
        assertEquals("Cien años de soledad", libro.getTitulo());
        assertTrue(libro.isDisponible());
    }
    
    @Test
    void testPrestarLibro() {
        assertTrue(libro.isDisponible());
        libro.setDisponible(false);
        assertFalse(libro.isDisponible());
    }
    
    @Test
    void testIsbnGenerado() {
        assertTrue(libro.getIsLibro() > 0);
    }
}

package com.fitnesstracker.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RutinaTest {


    //Se prueba que el constructor con ID funciona correctamente
    @Test
    void testConstructorConId() {
        LocalDate fecha = LocalDate.of(2025, 6, 4);
        Rutina rutina = new Rutina(10, 5, fecha);
        assertEquals(10, rutina.getId());
        assertEquals(5, rutina.getUsuarioId());
        assertEquals(fecha, rutina.getFecha());
        assertTrue(rutina.getEjercicios().isEmpty());
    }

    //Prueba que se agregue y almacene correctamente un ejercicio
    @Test
    void agregarEjercicio() {
        Rutina rutina = new Rutina(3, LocalDate.now());
        EjercicioRutina ejercicio = new EjercicioRutina(1, 3, "Fondos", 8, 3, "Espalda");
        rutina.agregarEjercicio(ejercicio);

        assertEquals(1, rutina.getEjercicios().size());
        assertEquals("Fondos", rutina.getEjercicios().get(0).getNombre());
    }
}
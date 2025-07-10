package com.fitnesstracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private int id;
    private int usuarioId;
    private LocalDate fecha;
    private List<EjercicioRutina> ejercicios;

    public Rutina(int id, int usuarioId, LocalDate fecha) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.ejercicios = new ArrayList<>();
    }

    public Rutina(int usuarioId, LocalDate fecha) {
        this(-1, usuarioId, fecha);
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<EjercicioRutina> getEjercicios() {
        return ejercicios;
    }

    public void agregarEjercicio(EjercicioRutina ejercicio) {
        ejercicios.add(ejercicio);
    }
}

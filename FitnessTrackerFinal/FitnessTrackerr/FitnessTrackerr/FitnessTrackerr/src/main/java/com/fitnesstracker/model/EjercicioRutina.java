package com.fitnesstracker.model;

public class EjercicioRutina {
    private int id;
    private int rutinaId;
    private String nombre;
    private int repeticiones;
    private int series;
    private String grupoMuscular;

    public EjercicioRutina(int id, int rutinaId, String nombre, int repeticiones, int series, String grupoMuscular) {
        this.id = id;
        this.rutinaId = rutinaId;
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.series = series;
        this.grupoMuscular = grupoMuscular;
    }

    public EjercicioRutina(int rutinaId, String nombre, int repeticiones, int series, String grupoMuscular) {
        this(-1, rutinaId, nombre, repeticiones, series, grupoMuscular);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRutinaId() {
        return rutinaId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }
}

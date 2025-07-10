package com.fitnesstracker.model;

public class Usuario {
    private int id;
    private String nombre;
    private double peso;
    private double altura;
    private double pesoObjetivo;
    private double imc;

    public Usuario(int id, String nombre, double peso, double altura, double pesoObjetivo, double imc) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
        this.pesoObjetivo = pesoObjetivo;
        this.imc = imc;
    }

    public Usuario(String nombre, double peso, double altura, double pesoObjetivo, double imc) {
        this(-1, nombre, peso, altura, pesoObjetivo, imc);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public double getPesoObjetivo() {
        return pesoObjetivo;
    }

    public double getImc() {
        return imc;
    }
}

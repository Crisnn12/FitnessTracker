package com.fitnesstracker.utilidades;

public class CalculoIMC {
    public static double calcularIMC(double pesoKg, double alturaM) {
        if (alturaM <= 0) return 0;
        return pesoKg / (alturaM * alturaM);
    }
}

package com.fitnesstracker.utilidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculoIMCTest {

    @Test
    //Un caso normal con peso y altura válidos
    void testIMCNormal() {
        double peso = 70.0;
        double altura = 1.75;
        double resultadoEsperado = peso / (altura * altura);
        double resultado = CalculoIMC.calcularIMC(peso, altura);
        assertEquals(resultadoEsperado, resultado, 0.0001);
    }

    @Test
    //Manejo de división por cero (altura = 0)
    void testAlturaCero() {
        double resultado = CalculoIMC.calcularIMC(70.0, 0.0);
        assertEquals(0.0, resultado);
    }

    @Test
    //Altura negativa (no válida)
    void testAlturaNegativa() {
        double resultado = CalculoIMC.calcularIMC(70.0, -1.75);
        assertEquals(0.0, resultado);
    }

    @Test
    //Que pasa si el peso es 0
    void testPesoCero() {
        double resultado = CalculoIMC.calcularIMC(0.0, 1.75);
        assertEquals(0.0, resultado);
    }

    @Test
    //Precisión del cálculo usando delta
    void testPrecision() {
        double resultado = CalculoIMC.calcularIMC(60.0, 1.60);
        assertEquals(23.4375, resultado, 0.0001);
    }
}
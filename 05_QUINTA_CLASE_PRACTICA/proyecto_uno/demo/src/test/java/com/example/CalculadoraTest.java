package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;

public class CalculadoraTest {

    @Test
    public void testsumar() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.sumar(2, 3);
        assertEquals(5, resultado, "La suma de 2 + 3 debe ser 5");
    }

    @Test
    public void testConNumeroNegativo() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.sumar(-2, 3);
        assertEquals(1, resultado, "La suma de -2 + 3 es igual a 1");
    }

    @Test
    public void testSumaNumerosGrandes() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.sumar(100000, 100000);
        assertEquals(200000, resultado, "La suma debe dar 200.000");
    }

    @Test
    public void testSumaNumerosNegativos() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.sumar(-5, 5);
        assertEquals(0, resultado, "La suma de dos números iguales con distinto signo debe ser 0");
    }

    @Test
    public void testConResultadoNegativo() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.sumar(-35, -54);
        assertEquals(-89, resultado, "La suma de -35 + -54 es igual a -89");
    }
}
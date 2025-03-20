package com.example;

public class App {
    public static void main(String[] args) {
        CalculadoraComision calculadora = new CalculadoraComision(5000, 0.1);
        double comision = calculadora.calcularComision();
        System.out.println("La comisión de esta venta es: " + comision);
    }
}

class CalculadoraComision {
    private double montoVentas;
    private double tasaComision;

    public CalculadoraComision(double montoVentas, double tasaComision) {
        this.montoVentas = montoVentas;
        this.tasaComision = tasaComision;
    }

    public double calcularComision() {
        return montoVentas * tasaComision;
    }
}

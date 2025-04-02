package com.example;

public class Division {

    public static int dividir(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {

            System.err.println("Error: División por cero no es valido");
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(dividir(10, 0));
    }
}

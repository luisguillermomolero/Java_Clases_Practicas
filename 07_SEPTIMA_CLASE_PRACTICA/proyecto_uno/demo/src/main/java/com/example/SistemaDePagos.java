package com.example;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Excepción personalizada para los pagos invalidos
class PagoInvalidoException extends Exception {
    public PagoInvalidoException(String mensaje) {
        super(mensaje);
    }

}

public class SistemaDePagos {

    private Scanner scanner = new Scanner(System.in);

    // Método para capturar y validar el monto ingresado por teclado
    public double solicitarMonto() throws PagoInvalidoException {

        System.out.println("Ingrese el monto a pagar: ");

        try {
            double monto = scanner.nextDouble();
            if (monto <= 0) {
                throw new PagoInvalidoException("El monto debe ser mayor a cero...");
            }
            return monto;
        } catch (InputMismatchException e) {
            scanner.next();
            throw new PagoInvalidoException("Entrada invalida. Debe ingresar un número válido");
        }
    }

    public void ejecutar() {

        try {

            double monto = solicitarMonto();

            // Simulación de error en la conexión - 30% de probabilidad de ocurrencia de un
            // erro
            if (Math.random() < 0.3) {
                throw new IOException("Error en la conexión con la pasarela de pago...");
            }

            System.out.println("Pago de $ " + monto + " realizado con éxito...");

        } catch (PagoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Operación de pago finaliada...");
            scanner.close();
        }
    }

    public static void main(String[] args) {
        SistemaDePagos sistema = new SistemaDePagos();
        sistema.ejecutar();
    }
}
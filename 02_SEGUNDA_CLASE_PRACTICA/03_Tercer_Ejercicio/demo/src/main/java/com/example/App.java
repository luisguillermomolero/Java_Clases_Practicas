package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public void procesarPago(String usuario, double monto) {

        if (monto <= 0) {
            logger.warn("Intento de pago con monto inválid - Usuario: {} - Monto: {}", usuario, monto);
        }

        try {
            // Simular algún procesamiento de pago
            if (Math.random() > 0.8) {
                throw new Exception("Error en el sistema de pago");
            }
            // Resgistra un mensaje informativo si el pago es procesado con éxito
            logger.info("Pago exitoso - Usuario - {} - Monto ${}", usuario, monto);
        } catch (Exception e) {
            // Registrar un error si ocurre una excepción durante el proceso de pago
            logger.error("Error en el proceso de pago - Usuario: {} - Monto: {} - Detalles: {}", usuario, monto,
                    e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Crear una instancia (objeto) del servicio de pago
        App servicio = new App();

        // Intento para procesar un pago exitoso
        servicio.procesarPago("Cliente001", 300.50);

        // Intento para procesar un pago exitoso
        servicio.procesarPago("Cliente020", -500.00);

        // Intento para procesar un pago exitoso
        servicio.procesarPago("Cliente223", 100.50);
    }
}

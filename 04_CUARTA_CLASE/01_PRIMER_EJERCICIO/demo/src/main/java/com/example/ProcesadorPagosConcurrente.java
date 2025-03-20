package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcesadorPagosConcurrente {

    private static final Logger LOGGER = Logger.getLogger(ProcesadorPagosConcurrente.class.getName());

    private static final AtomicInteger saldo = new AtomicInteger(1000);

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<?>> futures = new ArrayList<>();

        try {
            for (int i = 0; i < 5; i++) {
                futures.add(executor.submit(() -> {
                    try {
                        realizarPago(300);
                    } catch (SaldoInsuficienteException e) {
                        LOGGER.log(Level.WARNING, "Pago fallido por saldo insuficiente: " + e.getMessage());
                    } catch (OperacionFallidaException e) {
                        LOGGER.log(Level.SEVERE, "Error critico en la operacion de pago", e);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Excepcion inesperada durante el pago, e");
                    }
                }));
            }

            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error en la ejecucion de una tarea", e);
                }
            }
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    LOGGER.log(Level.WARNING, "Forzando el cierre de tareas pendientes...");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar el pool de hilos", e);
                Thread.currentThread().interrupt();
                ;
            }
        }
    }

    public static void realizarPago(int monto) throws SaldoInsuficienteException, OperacionFallidaException {
        int saldoPrevio = saldo.get();

        if (saldoPrevio < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente para el pago de: " + monto);
        }

        if (Math.random() < 0.05) {
            throw new OperacionFallidaException("Falla inesperada en la trnsacción");
        }

        boolean pagoExitoso = false;
        int saldoFinal;

        try {

            Thread.sleep(100);

            do {
                saldoPrevio = saldo.get();
                saldoFinal = saldoPrevio - monto;
                pagoExitoso = saldo.compareAndSet(saldoPrevio, saldoFinal);
            } while (!pagoExitoso);

            LOGGER.log(Level.INFO, "Pago realizado con exito. Saldo restante: " + saldoFinal);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.SEVERE, "La transaccion fue interrumpida", e);
            throw new OperacionFallidaException("Transaccion interrupida");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error desconociddo: " + e.getMessage());
        }
    }

    static class SaldoInsuficienteException extends Exception {

        public SaldoInsuficienteException(String message) {
            super(message);
        }
    }

    static class OperacionFallidaException extends Exception {

        public OperacionFallidaException(String message) {
            super(message);
        }
    }
}
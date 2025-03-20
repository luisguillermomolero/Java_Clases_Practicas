package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Inicio del programa...");

        logger.debug("Intentando la conexión contra la BD");

        logger.error("Error: No es posible la conexión contra la Base de datos");
    }
}

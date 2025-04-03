package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

class AutenticacionException extends Exception {
    public AutenticacionException(String mensaje) {
        super(mensaje);
    }
}

class Usuario {

    private String nombre;
    private String contrasena;

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    // Método para validar si la contraseña ingresada es correcta
    public boolean validarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena.trim());
    }

}

public class GestionUsuarios {

    private static final Logger logger = Logger.getLogger(GestionUsuarios.class.getName());
    private List<Usuario> usuarios = new ArrayList<>();

    static {
        try {
            FileHandler fileHandler = new FileHandler("usuarios.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "No se pudo configurar el archivo para log", e);
        }
    }

    // Constructor que inicializa un registro de 2 usuarios
    public GestionUsuarios() {
        usuarios.add(new Usuario("Luis", "123456789"));
        usuarios.add(new Usuario("Carlos", "ñlkjhgfgds"));
    }

    public void autenticarUsuario(String nombre, String contrasena) throws AutenticacionException {
        logger.info("Intento de autenticación del usuario: " + nombre);

        // For para recorre la lista de usuarios para verificar SI el usuario existe
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                if (usuario.validarContrasena(contrasena)) {
                    logger.info("Usuario autenticado con éxito: " + nombre);
                    return;
                }
            }
        }

        logger.warning("Usuario no encontrado: " + nombre);
        throw new AutenticacionException("Usuario no registrado");

    }

    public static void main(String[] args) {
        GestionUsuarios gestion = new GestionUsuarios();

        try {
            // Intento de autenticación exitosa
            gestion.autenticarUsuario("Luis", "123456789");

            // Intento de autenticación fallido
            gestion.autenticarUsuario("Carlos", "789456");

        } catch (AutenticacionException e) {
            logger.log(Level.SEVERE, "Error de autenticación", e);
        }
    }
}
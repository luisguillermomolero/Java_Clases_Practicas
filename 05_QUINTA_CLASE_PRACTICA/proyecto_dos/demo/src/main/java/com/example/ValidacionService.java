package com.example;

public class ValidacionService {

    public boolean validarPedido(Pedido pedido) {
        return pedido.getDescripcion() != null && !pedido.getDescripcion().isEmpty();
    }

}

package com.example;

public class PedidoService {

    private ValidacionService validacionService;

    public PedidoService(ValidacionService validacionService) {
        this.validacionService = validacionService;
    }

    public boolean procesarPedido(Pedido pedido) {
        if (validacionService.validarPedido(pedido)) {
            System.out.println("Pedido procesado: " + pedido.getDescripcion());
            return true;
        }
        System.out.println("Pedido rechazado");
        return false;
    }

}

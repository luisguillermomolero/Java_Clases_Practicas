package com.example;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PedidoServiceTest {

    @Test
    public void testProcesarPedido_Exitoso() {
        ValidacionService mockValidacionService = mock(ValidacionService.class);
        PedidoService pedidoService = new PedidoService(mockValidacionService);
        Pedido pedido = new Pedido("123", "Portatil");

        when(mockValidacionService.validarPedido(pedido)).thenReturn(true);

        boolean resultado = pedidoService.procesarPedido(pedido);
        assertTrue(resultado);

        verify(mockValidacionService).validarPedido(pedido);
    }

    @Test
    public void testProcesarPedido_Fallido() {
        ValidacionService mockValidacionService = mock(ValidacionService.class);

        PedidoService pedidoService = new PedidoService(mockValidacionService);
        Pedido pedido = new Pedido("123", "");

        when(mockValidacionService.validarPedido(pedido)).thenReturn(false);
        boolean resultado = pedidoService.procesarPedido(pedido);
        assertFalse(resultado);
        verify(mockValidacionService).validarPedido(pedido);
    }
}

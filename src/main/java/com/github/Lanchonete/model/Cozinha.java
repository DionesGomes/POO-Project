package main.java.com.github.Lanchonete.model;


import java.util.ArrayList;
import java.util.List;
import main.java.com.github.Lanchonete.controller.GerenciaMesa;

public class Cozinha {

    /*Atender os Pedidos*/
    private List<Pedido> pedidos;

    /*Construtor*/
    public Cozinha() {
        pedidos = new ArrayList<>();
    }

    /*Método para adicionar um pedido*/
    public boolean adicionarPedido(Pedido p) {
        return pedidos.add(p);
    }

    /*Método para remover um pedido feito.*/
    public boolean removePedido(int numeroPedido) {
        return pedidos.remove(pedidos.get(buscar(numeroPedido)));
    }

    /*Método para buscar pedido pelo número*/
    int buscar(int numeroPedido) {
        if (!pedidos.isEmpty()) {
            for (int i = 0; i < pedidos.size(); i++) {
                if (pedidos.get(i).getNumeroPedido() == numeroPedido) {
                    return i;
                }
            }
        }
        return -1;
    }
    /*Método para atender os pedidos*/
    public boolean atenderPedido(int numeroPedido, GerenciaMesa gerencia) {
        if (buscar(numeroPedido) == -1) {
            return false;
        }

        int mesa = pedidos.get(buscar(numeroPedido)).getMesa();/*Identifica um determinado pedido*/
        gerencia.getComanda(mesa).getPedido(numeroPedido).alterarStatus(); /*Muda o status do pedido para atendido*/
        return pedidos.remove(pedidos.get(buscar(numeroPedido))); /*Remove o pedidi diretamente na cozinha*/
    }
    
    public String visualizar(){
        String s = "";
        for (Pedido p : pedidos) {
            s+= "Mesa:" + p.getMesa() + "Número:" + p.getNumeroPedido() + "\n" + p.toString();
        }
        return s;
    }    
  }


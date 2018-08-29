package main.java.com.github.Lanchonete.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Comanda {

    private List<Pedido> comanda;
    private final LocalDate data;
    private int numeroComanda;
    private static int id;
    private int mesa;

    /*construtror*/
    public Comanda(int mesa) {
        comanda = new ArrayList<>();
        numeroComanda = ++id;
        this.mesa = mesa;
        data = LocalDate.now();
    }

    int getTamanho() {
        return comanda.size();
    }

    public LocalDate getData() {
        return data;
    }

    public int getMesa() {
        return mesa;
    }

    /*Recupera um pedido pelo seu respectivo número na comanda e retorna o indice na lista de comandas*/
    public int buscarPedido(int numeroPedido) {
        if (!comanda.isEmpty()) {
            for (int i = 0; i < comanda.size(); i++) {
                if (comanda.get(i).getNumeroPedido() == numeroPedido) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean adicionaPedido(Pedido p) {
        p.setMesa(mesa);//define o numero da mesa do pedido(que é o mesmo da comanda) 
        return comanda.add(p);
    }

    public boolean removePedido(int numeroPedido) {
        if (comanda.remove(buscarPedido(numeroPedido)) != null) {
            return true;
        }
        return false;
    }

    public float valorTotal() {//valor total da comanda
        float total = 0;
        for (Pedido p : comanda) {
            total += p.getValorTotal();
        }
        return total;
    }

    /* retorna um pedido específico da comanda. Esse método está relacionada a GerenciaComanda */
    public Pedido getPedido(int numeroPedido) {
        return comanda.get(buscarPedido(numeroPedido));
    }
    /* retorna uma lista de todos os pedidos da comanda. Esse método está relacionado com GerenciaComanda*/
    public List<Pedido> getListarPedidos() {
        return comanda;
    }
    
    /*ToString()*/
}

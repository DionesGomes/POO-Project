package main.java.com.github.Lanchonete.model;

import java.util.ArrayList;
import java.util.List;
import main.java.com.github.Lanchonete.controller.GerenciaMesa;

/**
 * Esta classe modela os dados referentes a entidade Cozinha.
 *
 * @sse main.java.com.github.model.Pedido.
 * @since 1.8.
 * @version 1.0.
 * @author Diones Gomes
 */
public class Cozinha {

    private List<Pedido> pedidos;

    /**
     * Inicializa o ArrayList sem passar parâmetros.
     */
    public Cozinha() {
        pedidos = new ArrayList<>();
    }

    /**
     * Método para adicionar um pedido na lista de Pedidos.
     *
     * @param p Referente ao pedido adicionado a lista de Pedidos.
     * @return true ou false.
     */
    public boolean adicionarPedido(Pedido p) {
        return pedidos.add(p);
    }

    /**
     * Métedo para remover um pedido da lista de Pedidos com base no número do
     * mesmo.
     *
     * @param numeroPedido Refere-se ao número do pedido.
     * @return true ou false.
     */
    public boolean removePedido(int numeroPedido) {
        /*Adicionar exceção posteriomente.*/
        return pedidos.remove(pedidos.get(buscar(numeroPedido)));
    }

    /**
     * Método para buscar um Pedido.
     *
     * @param numeroPedido Refere-se ao número do Pedido.
     * @return o número do pedido se presente, se não returna um inteiro
     * negativo.
     */
    public int buscar(int numeroPedido) {
        if (!pedidos.isEmpty()) {
            for (int i = 0; i < pedidos.size(); i++) {
                if (pedidos.get(i).getNumeroPedido() == numeroPedido) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Método para efetuar o atendimento de um pedido realizado.
     *
     * @param numeroPedido Refere-se ao número do pedido.
     * @param gerencia Refere-se ao objeto relacionado a mesa que efetua o
     * pedido.
     * @return true ou false.
     */
    public boolean atenderPedido(int numeroPedido, GerenciaMesa gerencia) {
        if (buscar(numeroPedido) == -1) {
            return false;
        }

        int mesa = pedidos.get(buscar(numeroPedido)).getMesa();/*Identifica um determinado pedido*/
        gerencia.getComanda(mesa).getPedido(numeroPedido).alterarStatus();
        /*Muda o status do pedido para atendido*/
        return pedidos.remove(pedidos.get(buscar(numeroPedido)));
        /*Remove o pedidi diretamente na cozinha*/
    }

    /**
     *
     * @return Uma String contendo todas as informações referentes a um pedido.
     */
    public String visualizar() {
        String s = "";
        for (Pedido p : pedidos) {
            s += "Mesa:" + p.getMesa() + "Número:" + p.getNumeroPedido() + "\n" + p.toString();
        }
        return s;
    }
}

package main.java.com.github.Lanchonete.model;

import java.util.ArrayList;
import java.util.List;
import main.java.com.github.Lanchonete.controller.GerenciaMesa;

/**
 * Esta classe modela os dados referentes a entidade Cozinha.
 *
 * @see main.java.com.github.Lanchonete.model.Pedido
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
     * 
     * @param numeroPedido corresponde ao número do pedido feito para uma mesa.
     * @param gm referente a uma mesa cadastrada.
     * @return muda o estatus do pedido para atendido e remove o pedido da cozinha.
     */
    public boolean atenderPedido(int numeroPedido, GerenciaMesa gm) {
        if (buscar(numeroPedido) == -1) {
            return false;
        }
        int mesa = pedidos.get(buscar(numeroPedido)).getMesa();//descobre a mesa de um determinado pedido
        //gm.getComanda(mesa).getPedido(numeroPedido).mudarStatus();// muda o status de não atendido para atendido
        return pedidos.remove(pedidos.get(buscar(numeroPedido)));//remove da cozinha
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

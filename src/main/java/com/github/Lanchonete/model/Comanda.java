package main.java.com.github.Lanchonete.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta Classe modela os dados da entidade Comanda.
 * @see main.java.com.github.Lanchonete.controller.Menu
 *
 * @author Diones Gomes
 */
public class Comanda {

    private List<Pedido> comanda;
    private final LocalDate data;
    private int numeroComanda;
    private static int id;
    private int mesa;

    /**
     * Inicializa o valor do atributo mesa.
     *
     * @param mesa Refere-se ao valor único, do novo pedido que será criado para
     * uma mesa.
     */
    public Comanda(int mesa) {
        comanda = new ArrayList<>();
        numeroComanda = ++id;
        this.mesa = mesa;
        data = LocalDate.now();
    }

    public LocalDate getData() {
        return data;
    }

    public int getMesa() {
        return mesa;
    }

    int getTamanho() {
        return comanda.size();
    }
    /**
     * Recupera o pedido atráves do número do pedido da mesa.
     *
     * @param numeroPedido Refere-se ao número do pedido que é unico para cada
     * pedido.
     * @return o indíce do pedido realizado, retorna um inteiro positivo se
     * presente caso contrário retorna -1.
     */
    public int buscarPedido(int numeroPedido) {
        /*Recupera um pedido pelo seu respectivo número na comanda e retorna o indice na lista de comandas*/
        if (!comanda.isEmpty()) {
            for (int i = 0; i < comanda.size(); i++) {
                if (comanda.get(i).getNumeroPedido() == numeroPedido) {
                    return i;
                }
            }
        }
        return -1;
    }
    /**
     * Método para adicionar um pedido a mesa.
     *
     * @param p Refere-se ao número do pedido que será adicionado a mesa.
     * @return true ou false.
     */
    public boolean adicionaPedido(Pedido p) {
        p.setMesa(mesa);
        return comanda.add(p);
    }
    /**
     * Método para remover um pedido.
     *
     * @param numeroPedido Refere-se ao número do pedido na mesa cujo pedido irá
     * ser removido.
     * @return true para removido ou false se não removido.
     */
    public boolean removePedido(int numeroPedido) {
        if (comanda.remove(buscarPedido(numeroPedido)) != null) {
            return true;
        }
        return false;
    }
    /**
     * Método para calcular o valor tatal da comanda.
     *
     * @return O valor total do somatória da comanda.
     */
    public float valorTotal() {//valor total da comanda
        float total = 0;
        for (Pedido p : comanda) {
            total += p.getValorTotal();
        }
        return total;
    }
    /**
     * Método para retornar o número do pedido.
     *
     * @param numeroPedido Fefere-se o número do pedido que será removido.
     * @return O pedido se encontrado, retorna NULL se não encontrado.
     */
    public Pedido getPedido(int numeroPedido) {
        return comanda.get(buscarPedido(numeroPedido));
    }
    /**
     * Retorna uma lista contando todos os pedidos feitos.
     *
     * @return Uma lista com os pedidos.
     */
    public List<Pedido> getListarPedidos() {
        return comanda;
    }
    /*ToString()*/
    /**
     * Retorna o toString da classe pedido.
     *
     * @return String contendo todas as informações dos pedidos realizado.
     */
    public String toString() {
        String s = data.toString() + " |Mesa:" + mesa + " |N°:" + numeroComanda + "\n==================================\n";
        for (Pedido p : comanda) {
            s += p.toString();
        }
        return s;
    }
}

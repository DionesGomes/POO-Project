package main.java.com.github.Lanchonete.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Esta Classe modela os dados da entidade Pedido.
 *
 * @see main.java.com.github.Lanchonete.controller.Menu
 * @author Diones Gomes
 */
public class Pedido implements Serializable {

    private int numeroPedido;
    private Produto produto;
    private int quantidade;
    private boolean status;
    private final LocalDate data;
    private final LocalTime hora;
    private static int id;
    private int mesa;

    /**
     * Inicializando apenas os atributos Produto e Quantidade.
     *
     * @param produto Referencia o produto neste pedido.
     * @param quantidade Referente a quantidade do produto neste pedido.
     */
    public Pedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        data = LocalDate.now();
        hora = LocalTime.now();
        numeroPedido = ++id;
        /*Incrementa o número do pedido*/
        status = false;
    }
    /*Getters e Setters*/
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int i) { //Refere-se a um número de um  pedido.
        numeroPedido = i;
    }

    public static int getContPedidos() {
        return id;
    }

    public static void setContPedidos(int i) { //Refere-se ao contador dos pedidos.
        id = i;
    }

    public boolean isStatus() {
        return status;
    }

    public void alterarStatus() {
        status = true;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }
    /**
     *
     * Esse método retorna o valor total do pedido realizado. A formula é dada
     * atráves da multiplicação do preço pela unidade, multiplicada pela
     * quantidade do produto.
     * @return Um float com o valor tatal.
     */
    public float getValorTotal() {
        return produto.getPreco() * quantidade;
    }
    /**
     * Visualiza todos pedidos que ainda não foram atendidos na cozinha.
     *
     * @return Uma String contendo todas as informações referentes a um pedido.
     */
    @Override
    public String toString() {
        String s = isStatus() ? " Pedido atendido! " : " Pedido não atendido! ";
        return quantidade + " <-> " + produto.getNome() + " -> Subtotal: " + getValorTotal()
                + " ~ N°:" + getNumeroPedido() + " ~ " + s + "\n";
    }
}

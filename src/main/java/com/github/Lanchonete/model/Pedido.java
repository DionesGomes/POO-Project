package main.java.com.github.Lanchonete.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class Pedido implements Serializable {
    
   
   private int numeroPedido;     /*Número referente ao pedido*/
   private Produto produto;      /*Requisito da Classe*/
   private int quantidade;       /*Requisito da Classe*/
   private boolean status;       /*Atendido ou não Atendido*/
   private LocalDate data;       /*Requisito da Classe*/
   private LocalTime hora;       /*Requisito da Classe*/
   private static int id;        /*Incremento para número do pedido*/
   private int mesa;             /*Requisito da Classe*/

   
    /*Construtor*/
    public Pedido(int numeroPedido, float subTotal, Produto produto, int quantidade, boolean status, LocalDate data, LocalTime hora, int mesa) {
        this.numeroPedido = ++id;     /*Incrementa o número do pedido*/
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = status;
        this.data = data;
        this.hora = hora;
        this.mesa = mesa;
    }

    /*Getters e Setters*/
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Pedido.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.numeroPedido;
        hash = 97 * hash + Objects.hashCode(this.produto);
        hash = 97 * hash + this.quantidade;
        hash = 97 * hash + (this.status ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.data);
        hash = 97 * hash + Objects.hashCode(this.hora);
        hash = 97 * hash + this.mesa;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (this.numeroPedido != other.numeroPedido) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.mesa != other.mesa) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedido{" + "numeroPedido=" + numeroPedido + ", produto=" + produto + ", quantidade=" +
                quantidade + ", status=" + status + ", data=" + data + ", hora=" + hora + ", mesa=" + mesa + '}';
    }
    
    /*Método para retornar o total*/
    public float getValorTotal(){
           return  produto.getPreco() * quantidade;
            
    }
    
}

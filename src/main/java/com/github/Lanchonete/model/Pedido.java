
package main.java.com.github.Lanchonete.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class Pedido {
    
   
   private int numeroPedido; 
   private float valorTotal; /*Sobtotal da comanda*/
   private Produto produto; //típo produto.
   private int quantidade;
   private boolean status; /*Status do pedido, se foi atendido ou não*/
   private LocalDate data; 
   private LocalTime hora;  
   private static int id;
   private int mesa;   

    /*Construtor com apenas Quantidade e Produto*/
   
    public Pedido(int quantidade, Produto produto) { 
        this.valorTotal = produto.getPreco();
        this.quantidade = quantidade;
        this.produto = produto;        
        data = LocalDate.now();
        hora = LocalTime.now();
        numeroPedido = ++id;       /*Incrementa o número do pedido*/
         status = false;
        
    } 

    /*Getters e Setters*/
    
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
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
        int hash = 5;
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
        if (Float.floatToIntBits(this.valorTotal) != Float.floatToIntBits(other.valorTotal)) {
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
        return "Pedido{" + "numeroPedido=" + numeroPedido + ", valorTotal=" + valorTotal + ", produto=" +
                produto + ", quantidade=" + quantidade + ", status=" + status + ", data=" + data + ", hora=" +
                hora + ", mesa=" + mesa + '}';
    }
   
    
}

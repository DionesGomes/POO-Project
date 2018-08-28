package main.java.com.github.Lanchonete.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Pedido implements Serializable {    
   
   private int numeroPedido;     
   private Produto produto;      
   private int quantidade;       
   private boolean status;       
   private final LocalDate data;       
   private final LocalTime hora;    
   private static int id;        
   private int mesa;
   
    /*Construtor*/
    public Pedido(Produto produto, int quantidade) {            
        this.produto = produto;
        this.quantidade = quantidade;        
        data = LocalDate.now();
        hora = LocalTime.now();
        numeroPedido = ++id;  /*Incrementa o número do pedido*/
        status = false;
    }

    /*Getters e Setters*/
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int i) { //Refere-se a um número de um  pedido específico.
        numeroPedido = i;
    }
    
    public static int getContPedidos(){
        return id; 
    }

    public static void setContPedidos(int i){ //Refere-se ao contador dos pedidos.
        id = i;
    }
        
    public boolean isStatus() {
        return status;
    }

    public void AlterarStatus() {
        status = true;
    }
    
    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
    
    public LocalDate getData(){
        return data;
    }
    
    public LocalTime getHora(){
        return hora;
    }
    
    /*Método para retornar o total*/
    /*Efetua a multiplicação do preço unitário dos produtos por cada produto pedido.*/
    public float getValorTotal(){
           return  produto.getPreco() * quantidade;
            
    }
    
    @Override
    public String toString() {
		String s = isStatus()? "Atendido" : "Não Atendido";
		return quantidade + " <-> " + produto.getNome() + " -> Subtotal: " + getValorTotal()+
                " -- n°:" + getNumeroPedido() + " ==> " +s+ "\n";
    }    
}

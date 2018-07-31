
package main.java.com.github.Lanchonete.model;

import java.time.LocalDate;
import java.time.LocalTime;


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

    

    /*Métodos*/   
   
}

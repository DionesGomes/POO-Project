
package main.java.com.github.Lanchonete.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Comanda {  
    
    private List<Pedido> comanda;
    private LocalDate dataComanda;    
    private int numeroMesa;
    private static int id;
    private int mesa;   

    public Comanda(int mesa) {
        numeroMesa = ++id;
        this.mesa = mesa;
        comanda = new ArrayList<>();
        dataComanda = LocalDate.now();       
    }   
    
   
    
    
}

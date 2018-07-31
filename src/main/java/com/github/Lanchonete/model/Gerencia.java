
package main.java.com.github.Lanchonete.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Gerencia {
    
    private List<Comanda> comandasFechadas; /*Todas a comandas fechadas*/    

    public Gerencia(List<Comanda> comandasFechadas) {
        this.comandasFechadas = new ArrayList<>();
        LocalDate d = LocalDate.now();      
    }
    
    
    
    
    
   
}


package main.java.com.github.Lanchonete.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Comanda {  
    
    private List<Pedido> comanda;
    private LocalDate data;    
    private int numeroMesa;
    private static int id;   

    public Comanda(int mesa) {
        numeroMesa = ++id;
        comanda = new ArrayList<>();
    }

    public List<Pedido> getComanda() {
        return comanda;
    }

    public void setComanda(List<Pedido> comanda) {
        this.comanda = comanda;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Comanda.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.comanda);
        hash = 41 * hash + this.numeroMesa;
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
        final Comanda other = (Comanda) obj;
        if (this.numeroMesa != other.numeroMesa) {
            return false;
        }
        if (!Objects.equals(this.comanda, other.comanda)) {
            return false;
        }
        return true;
    }

    

    
      
}

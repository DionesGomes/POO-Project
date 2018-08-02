
package main.java.com.github.Lanchonete.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GerenciaMesa {

    private List<Comanda> comanda; /*Lista com as  Comandas*/

    /*Construtor*/
    public GerenciaMesa(List<Comanda> comanda) {
        this.comanda = new ArrayList<>(); 
  
    }  

    /*Getters e Setters*/
    public List<Comanda> getComanda() {
        return comanda;
    }

    public void setComanda(List<Comanda> comanda) {
        this.comanda = comanda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.comanda);
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
        final GerenciaMesa other = (GerenciaMesa) obj;
        if (!Objects.equals(this.comanda, other.comanda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GerenciaMesa{" + "comanda=" + comanda + '}';
    }
}

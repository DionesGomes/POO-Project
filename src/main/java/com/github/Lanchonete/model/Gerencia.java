
package main.java.com.github.Lanchonete.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Gerencia {
    
    /*Gerenciar as Comandas fechadas*/    
    private List<Comanda> comandasFechadas;     

    public Gerencia(List<Comanda> comandasFechadas) {
        this.comandasFechadas = new ArrayList<>();/*inicializar*/      
    }    
    
    /*Getters e Setters*/
    public List<Comanda> getComandasFechadas() {
        return comandasFechadas;
    }

    public void setComandasFechadas(List<Comanda> comandasFechadas) {
        this.comandasFechadas = comandasFechadas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.comandasFechadas);
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
        final Gerencia other = (Gerencia) obj;
        if (!Objects.equals(this.comandasFechadas, other.comandasFechadas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gerencia{" + "comandasFechadas=" + comandasFechadas + '}';
    }  

}

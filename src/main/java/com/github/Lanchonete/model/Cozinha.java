
package main.java.com.github.Lanchonete.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Cozinha {
    
    private List<Pedido> pedidos;

    public Cozinha(List<Pedido> pedidos) {
        this.pedidos = new ArrayList<>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.pedidos);
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
        final Cozinha other = (Cozinha) obj;
        if (!Objects.equals(this.pedidos, other.pedidos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cozinha{" + "pedidos=" + pedidos + '}';
    }
    
    
    
    
}

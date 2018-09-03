package main.java.com.github.Lanchonete.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.com.github.Lanchonete.model.Comanda;
import main.java.com.github.Lanchonete.model.Pedido;

/**
 * Esta classe faz o gerenciamento das mesas.
 *
 * @see Comanda
 * @see Gerencia
 * @see Pedido
 * @author Diones Gomes
 */
public class GerenciaMesa {

    private List<Comanda> mesas;

    /**
     * Iniciando o ArrayList, sem passar parâmentros.
     */
    public GerenciaMesa() {
        mesas = new ArrayList<>();
    }
    /**
     * Método para adicionar uma nova comanda para uma mesa.
     *
     * @param mesa Refere-se ao número da mesa que abriu uma comanda.
     * @return true ou false.
     */
    public boolean novaComanda(int mesa) {
        if (buscar(mesa) != -1) {// verifica a existência da mesa, pois não pode haver mais de uma comanda para uma mesma mesa. 
            return false;
        }
        return mesas.add(new Comanda(mesa));
    }
    /**
     * Método para buscar uma mesa na lista de mesas.s
     *
     * @param mesa Refere-se ao número da mesa que será recuperado.
     * @return 1 para mesa encontrada ou -1 caso a mesa não exista.
     */
    int buscar(int mesa) {
        if (!(mesas.isEmpty())) {// Verifica a existência de uma comanda para uma mesa.
            for (int i = 0; i < mesas.size(); i++) {
                if (mesas.get(i).getMesa() == mesa) {
                    return i;
                }
            }
        }
        return -1;
    }    

}

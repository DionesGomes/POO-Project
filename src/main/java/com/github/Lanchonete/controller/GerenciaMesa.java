package main.java.com.github.Lanchonete.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.com.github.Lanchonete.model.Comanda;
import main.java.com.github.Lanchonete.model.Cozinha;
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
     * Método para fechar a comenada e adicionando ela na lista de comandas
     * fechadas.
     *
     * @param mesa Refere-se ao número da mesa que que abriu a comanda.
     * @return true ou false.
     */
    public boolean fecharComanda(int mesa) {
        if (buscar(mesa) == -1) {// verifica a existência da mesa, pois não pode-se encerrar uma comanda que não existe.
            return false;
        }
        for (Pedido p : getComanda(mesa).getListarPedidos()) {
            if (p.isStatus() == false) {
                return false;
            }
        }
        return Gerencia.adicionarComanda(mesas.remove(buscar(mesa)));
    }

    /**
     * Método para fazer um novo pedido para uma comanda que já está em aberto.
     *
     * @param mesa Refere-se ao número da mesa onde a comanda faz refência.
     * @param pedido Refere-se ao pedido da comanda.
     * @param cozinha Refer-se a cozinha para adicionar um novo pedido.
     * @return true para pedido adicionado, ou false para pedido não adicionado.
     */
    public boolean fazerPedido(int mesa, Pedido pedido, Cozinha cozinha) {
        if (buscar(mesa) != -1 && pedido.getProduto() != null) {
            cozinha.adicionarPedido(pedido); // adiciona à cozinha                            
            return mesas.get(buscar(mesa)).adicionaPedido(pedido);
        }
        Pedido.setContPedidos(Pedido.getContPedidos() - 1);
        return false;
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

    /**
     * Recupera a mesa na lista de mesas.
     *
     * @param mesa Refere-se ao número da mesa a ser consultada.
     * @return a mesa caso exista, ou null se não existe.
     */
    public Comanda getComanda(int mesa) {
        return mesas.get(buscar(mesa));
    }

}

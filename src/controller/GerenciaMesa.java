package controller;

import java.util.ArrayList;
import java.util.List;

import model.Comanda;
import model.Pedido;
import model.Cozinha;

/**
 * Esta classe faz o gerenciamento das mesas.
 *
 * @see Comanda
 * @see Gerencia
 * @see Pedido
 * @author Diones Gomes
 */
public class GerenciaMesa {

    /**
     * Inicia a lista de mesas da classe GerenciaMesa.
     *
     */
    private static List<Comanda> mesas = new ArrayList<>();

    /**
     * Cria uma nova mesa na lista de mesas da classe.
     *
     * @param mesa recebe o valor <b>único</b> da nova mesa que será adicionada
     * @return true se a mesa for inserida na lista ou false se a mesa não for
     * inserida.
     *
     */
    public static boolean novaComanda(int mesa) {
        if (buscar(mesa) != -1) {// verifica se a mesa existe ou não 
            return false; // não pode ser aberta duas ou mais comandas na mesma mesa
        }
        return mesas.add(new Comanda(mesa));
    }

    /**
     * Busca a mesa na lista de mesas e retorna seu indice.
     *
     * @param mesa recebe o valor <b>único</b> da mesa que será buscada.
     * @return número >= 0 caso a mesa seja encontrasa, sendo o número o indice
     * da mesa, ou -1 caso a mesa não seja encontrada.
     *
     */
    static int buscar(int mesa) {
        if (!(mesas.isEmpty())) {// não se pode buscar comandas se elas não existem
            for (int i = 0; i < mesas.size(); i++) {
                if (mesas.get(i).getMesa() == mesa) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Busca a mesa na lista de mesas e a retorna.
     *
     * @param mesa recebe o valor <b>único</b> da mesa que será buscada.
     * @return mesa caso seja encontrada ou null caso não exista;
     *
     */
    public static Comanda getComanda(int mesa) {//retorna a (tudo da)comanda de uma mesa especifica (relaciona esta classe com Cozinha) /////não confundir com getListaPedido() que retorna a lista de todos os pedidos da comanda mas não retorna data, numero e mesa 
        if (buscar(mesa) != -1) {
            return mesas.get(buscar(mesa));
        }
        return null;
    }

    /**
     * Método para listar as comandas armazenadas.
     *
     * @return array contendo as comandas.
     */
    public static List<Comanda> Listar() {
        return mesas;
    }
}

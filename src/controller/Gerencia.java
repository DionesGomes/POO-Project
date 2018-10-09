package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Comanda;

/**
 * Esta classe contém as funções referentes ao gerenciamento das mesas.
 *
 * @author Diones Gomes
 */
public class Gerencia {

    /**
     * Contém a lista de mesas relacionadas as comandas.
     */
    private static List<Comanda> mesas = new ArrayList<>();

    /**
     * Método para adicionar uma nova comanda para uma determinada mesa.
     *
     */
    static boolean adicionarComanda(Comanda c) {
        return mesas.add(c);
    }

}

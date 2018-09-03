package main.java.com.github.Lanchonete.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.java.com.github.Lanchonete.model.Comanda;

/**
 * Esta classe contém as funções referentes ao gerenciamento das mesas.
 *
 * @author Diones Gomes
 */
public class Gerencia {

    /**
     * Contém a lista de mesas relacionadas as comandas.
     */
    private static List<Comanda> gerenciaComanda = new ArrayList<>();

    /**
     * Método para adicionar uma mesa que já foi encerrada.
     */
    public static boolean adicionarComanda(Comanda c) {//esta função é usada somente por GerenciaMesa 
        return gerenciaComanda.add(c);
    }

    /**
     * Método para listar todas as mesas que foram abertas dentro de um período
     * de tempo específico.
     *
     * @param inicio Refere-se a data de início.
     * @param fim Refere-se a data final.
     * @return As mesas cujas datas informadas no período de tempo especificado.
     */
    public static String listarComandas(LocalDate inicio, LocalDate fim) {
        String s = "";
        for (Comanda comanda : gerenciaComanda) {
            if (comanda.getData().isAfter(inicio.plusDays(-1)) && comanda.getData().isBefore(fim.plusDays(1))) {// se a data de uma comanda c qualquer for depois de "inicio-1" e antes de "fim+1" ela é concatenada como String
                s += comanda.toString();
            }
        }
        return s;
    }

    /**
     * Método para calcular o lucro total de todas as mesas, dentro de um
     * período de tempo específico.
     *
     * @param inicio Refere-se a data de início.
     * @param fim Refere-se a data final.
     * @return O valor total do somátório das comandas.
     */
    public static float CalculaLucroTotal(LocalDate inicio, LocalDate fim) {
        float lucro = 0f;
        for (Comanda comanda : gerenciaComanda) {
            if (comanda.getData().isAfter(inicio.plusDays(-1)) && comanda.getData().isBefore(fim.plusDays(1))) {
                lucro += comanda.valorTotal();
            }
        }
        return lucro;
    }
}

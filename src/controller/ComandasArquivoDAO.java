/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Comanda;
import model.Pedido;

/**
 *
 * @author Diones Gomes
 */
public class ComandasArquivoDAO {

    private final File arquivo;

    public ComandasArquivoDAO() {
        arquivo = new File("Comandas.bin");

        if (!arquivo.exists()) {

            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Falha na conexão com arquivo", "Mensagem Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    /**
     * Método para adicionar um pedido.
     *
     * @param com Corresponde a comanda fechada.
     *
     * @return true para pedido feito e false para pedido não feito.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean AddComandasFechadas(Comanda com) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Comanda> comandas;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            comandas = (List<Comanda>) in.readObject();
        } else {
            comandas = new ArrayList<>();
        }

        if (comandas.add(com)) {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(arquivo));

            out.writeObject(comandas);
            out.close();

            return true;

        } else {
            return false;
        }

    }

    /**
     * Método para listar todos os pedidos.
     *
     * @return um array contendo os pedidos realizados.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Comanda> listarComandas() throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            return (List<Comanda>) in.readObject();
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Metodo para criar uma comanda fechada.
     *
     * @param mesa corresponde ao número da mesa que a comanda pertênce.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void montarComanda(int mesa) throws IOException, ClassNotFoundException {

        LocalDate data = null;
        int nComanda = 0;

        List<Pedido> lista = new ArrayList();
        PedidosArquivoDAO dao = new PedidosArquivoDAO();

        for (Comanda c : GerenciaMesa.Listar()) {
            if (c.getMesa() == mesa) {
                data = c.getData();
                nComanda = c.getNumeroComanda();
            }
        }

        for (Pedido p : dao.listar()) {
            if (p.getMesa() == mesa) {
                lista.add(p);
            }
        }
        //Criando a comanda fechada. 
        Comanda comanda = new Comanda(lista, data, nComanda, mesa);
        AddComandasFechadas(comanda);

    }

    /**
     * Método responsável por listar todas as comendas fechadas num determinado
     * periodo de tempo.
     *
     * @param inicio corresponde a data de inicio.
     * @param fim corresponde a data de final.
     * @return returna as comendas no tempo determinado.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Comanda> listarComandasNumPeriodoDeTempo(LocalDate inicio, LocalDate fim) throws IOException, ClassNotFoundException, NullPointerException {
        List<Comanda> listar = new ArrayList<>();

        for (Comanda comanda : listarComandas()) {
            if (comanda.getData().isAfter(inicio.plusDays(-1)) && comanda.getData().isBefore(fim.plusDays(1))) {// se a data de uma comanda c qualquer for depois de "inicio-1" e antes de "fim+1" ela é concatenada como String
                listar.add(comanda);
            }
        }
        return listar;
    }

}

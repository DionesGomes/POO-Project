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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Pedido;
import model.Produto;

/**
 * Essa classe é resposável por fazer a persistência dos dados referentes aos
 * pedidos em um arquivo binário.
 *
 * @author Diones Gomes
 */
public class PedidosArquivoDAO {

    private final File arquivo;

    public PedidosArquivoDAO() {
        arquivo = new File("Pedidos.bin");

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
     * @param pedido corresponde ao pedido feito.
     * @return true para pedido feito e false para pedido não feito.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean Adicionar(Pedido pedido) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Pedido> pedidos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            pedidos = (List<Pedido>) in.readObject();
        } else {
            pedidos = new ArrayList<>();
        }

        if (pedidos.add(pedido)) {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(arquivo));

            out.writeObject(pedidos);
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
    public List<Pedido> listar() throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            return (List<Pedido>) in.readObject();
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Método para atualizar um pedido feito.
     *
     * @param mesa corresponde ao número da mesa onde o pedido foi feito.
     * @param produto corresponde ao novo produto que sera atualizado.
     * @param quantidade corresponde a quantidade do novo produto adicionado.
     * @return true para atualizado ou false para não atualizado.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean atualizar(int mesa, Produto produto, int quantidade, int numero) throws IOException, ClassNotFoundException {

        List<Pedido> pedidos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            pedidos = (List<Pedido>) in.readObject();
        } else {
            pedidos = new ArrayList<>();
        }

        for (int i = 0; i < pedidos.size(); i++) {

            if (pedidos.get(i).getMesa() == mesa && pedidos.get(i).getNumeroPedido() == numero) {

                pedidos.get(i).setProduto(produto);
                pedidos.get(i).setQuantidade(quantidade);

                ObjectOutputStream out = new ObjectOutputStream(
                        new FileOutputStream(arquivo));
                out.writeObject(pedidos);
                out.close();

                return true;

            }

        }
        return false;

    }

    /**
     * Método para deletar um pedido.
     *
     * @param mesa corresponde ao número da mesa onde o pedido pertênce.
     * @param numeroPedido Corresponde ao número do pedido.
     * @return true para pedido deletado ou false para pedido não deletado.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean deletar(int mesa, int numeroPedido) throws IOException, ClassNotFoundException {

        List<Pedido> pedidos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            pedidos = (List<Pedido>) in.readObject();
        } else {
            return false;
        }

        for (int i = 0; i < pedidos.size(); i++) {

            if (pedidos.get(i).getMesa() == mesa && pedidos.get(i).getNumeroPedido() == numeroPedido) {
                pedidos.remove(i);
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
                out.writeObject(pedidos);
                out.close();
                return true;
            }

        }
        return false;

    }

    /**
     * Método para listar todos os pedidos
     *
     * @return Os pedidos ou null
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Pedido> listarStatus() throws IOException, ClassNotFoundException {
        List<Pedido> pedidos = new ArrayList<>();
        for (Pedido pedido : listar()) {
            if (pedido.isStatus() == false) {
                pedidos.add(pedido);

            }
            return pedidos;
        }
        return null;
    }

    /**
     * Método para mudar o estados dos pedidos.
     *
     * @param numero corresponde ao número do pedido.
     * @param mesa corresponde ao número da mesa que o pedido pertênce.
     * @return true para atendido ou false para não atendido.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean mudarStatus(int numero, int mesa) throws IOException, ClassNotFoundException {
        List<Pedido> pedidos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            pedidos = (List<Pedido>) in.readObject();
        } else {
            pedidos = new ArrayList<>();
        }

        for (int i = 0; i < pedidos.size(); i++) {

            if (pedidos.get(i).getMesa() == mesa && pedidos.get(i).getNumeroPedido() == numero) {

                pedidos.get(i).alterarStatus();

                ObjectOutputStream out = new ObjectOutputStream(
                        new FileOutputStream(arquivo));
                out.writeObject(pedidos);
                out.close();

                return true;

            }

        }
        return false;

    }

    /**
     * Método para verificar quais messas foram atendidadas.
     *
     * @param mesa corresponde ao número da mesa.
     * @return true para atendido ou false para não atendido.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean MesasAtendidas(int mesa) throws IOException, ClassNotFoundException {
        int numeroMesa = 0, atendidas = 0;
        for (Pedido pedido : listar()) {
            if (pedido.getMesa() == mesa) {
                numeroMesa++;
            }
            if (pedido.getMesa() == mesa && pedido.isStatus() == true) {
                atendidas++;
            }

        }
        return numeroMesa == atendidas;
    }

    /**
     * Método para calcular o valor total da comanda.
     *
     * @param mesa corresponde ao número da mesa.
     * @return o valor total da comenda.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public float valorTotal(int mesa) throws IOException, ClassNotFoundException {//Calcula o valor total da comanda
        float total = 0;
        for (Pedido p : listar()) {
            if (p.getMesa() == mesa) {
                total += p.getValorTotal();
            }
        }
        return total;
    }

    /**
     * Método para deletar as comandas fechadas da cozinha e armazenar em um
     * array de comandas fechadas.
     *
     * @param mesa corresponde ao número da mesa.
     * @return true para comanda retirada da cozinha ou false para comanda não
     * removida da cozinha.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean DeletaComandasFechadas(int mesa) throws IOException, ClassNotFoundException {

        List<Pedido> pedidos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            pedidos = (List<Pedido>) in.readObject();
        } else {
            return false;
        }

        for (int i = 0; i < pedidos.size(); i++) {

            if (pedidos.get(i).getMesa() == mesa) {
//               
                pedidos.remove(i);
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
                out.writeObject(pedidos);
                out.close();
                return true;
            }

        }
        return false;

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.github.lanchonete.controller;

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
import main.java.com.github.Lanchonete.model.Pedido;
import main.java.com.github.Lanchonete.model.Produto;

/**
 *
 * @author Diones Gomes
 */
public class CadastrarPedidosArquivo {

    private final File arquivo;

    public CadastrarPedidosArquivo() {
        arquivo = new File("Pedidos.bin");

        if (!arquivo.exists()) {

            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Falha na conexão com arquivo", "Mensagem Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

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

    public List<Pedido> listar() throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            return (List<Pedido>) in.readObject();
        } else {
            return new ArrayList<>();
        }

    }

    public boolean atualizar(int mesa, Produto produto, int quantidade) throws IOException, ClassNotFoundException {

        List<Pedido> pedidos;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            pedidos = (List<Pedido>) in.readObject();
        } else {
            pedidos = new ArrayList<>();
        }

        for (int i = 0; i < pedidos.size(); i++) {

            if (pedidos.get(i).getMesa() == mesa) {

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
        if (numeroMesa == atendidas) {
            return true;
        }
        return false;
    }

    public float valorTotal(int mesa) throws IOException, ClassNotFoundException {//valor total da comanda
        float total = 0;
        for (Pedido p : listar()) {
            if (p.getMesa() == mesa) {
                total += p.getValorTotal();
            }
        }
        return total;
    }

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

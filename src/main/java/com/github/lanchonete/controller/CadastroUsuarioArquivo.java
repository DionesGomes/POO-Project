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
import main.java.com.github.Lanchonete.model.Usuario;

/**
 * Esta classe é responsável por salvar os dados em um arquivo binário.
 *
 * @author Diones Gomes
 */
public class CadastroUsuarioArquivo {

    private final File arquivo;

    public CadastroUsuarioArquivo() {
        arquivo = new File("Usuario.bin");

        if (!arquivo.exists()) {

            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Falha na conexão com arquivo", "Mensagem Erro", JOptionPane.ERROR_MESSAGE);
            }

        }

    }
 
    public boolean Adicionar(Usuario u) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Usuario> usuarios;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            usuarios = (List<Usuario>) in.readObject();
        } else {
            usuarios = new ArrayList<>();
        }

        if (usuarios.add(u)) {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(arquivo));

            out.writeObject(usuarios);
            out.close();

            return true;

        } else {
            return false;
        }

    }

    public Usuario buscar(String email) throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            List<Usuario> lista = (List<Usuario>) in.readObject();
            for (Usuario u : lista) {
                if (u.getEmail().equals(email)) {
                    return u;
                }
            }

        }
        return null;

    }

    public List<Usuario> listar() throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            return (List<Usuario>) in.readObject();
        } else {
            return new ArrayList<>();
        }

    }

    public boolean Autenticacao(String email, String senha) throws IOException, ClassNotFoundException {
        try {
            Usuario usuario = buscar(email);
            if (usuario.getSenha().equals(senha)) {
                return true;
            }
        } catch (NullPointerException ex) {
            System.out.println("Login ou Senha inválido!");
        }

        return false;
    }

    public boolean atualizar(String email, Usuario u) throws IOException, ClassNotFoundException {

        List<Usuario> usuario;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            usuario = (List<Usuario>) in.readObject();
        } else {
            usuario = new ArrayList<>();
        }

        for (int i = 0; i < usuario.size(); i++) {

            if (usuario.get(i).getEmail().equals(email)) {

                usuario.get(i).setCpf(u.getCpf());
                usuario.get(i).setEmail(u.getEmail());
                usuario.get(i).setNome(u.getNome());
                usuario.get(i).setSenha(u.getSenha());
                usuario.get(i).setSetor(u.getSetor());
                usuario.get(i).setTelefone(u.getTelefone());
                usuario.get(i).setNascimento(u.getNascimento());

                ObjectOutputStream out = new ObjectOutputStream(
                        new FileOutputStream(arquivo));
                out.writeObject(usuario);
                out.close();

                return true;

            }

        }
        return false;

    }

    public boolean deletar(String email) throws IOException, ClassNotFoundException {

        List<Usuario> usuario;

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            usuario = (List<Usuario>) in.readObject();
        } else {
            return false;
        }

        for (int i = 0; i < usuario.size(); i++) {

            if (usuario.get(i).getEmail().equals(email)) {
                usuario.remove(i);
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
                out.writeObject(usuario);
                out.close();
                return true;
            }

        }
        return false;

    }

}

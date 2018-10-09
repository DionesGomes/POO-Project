/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.CpfExcecption;
import exceptions.TelefoneException;
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
import model.Usuario;
import sun.net.TelnetProtocolException;

/**
 * Esta classe é responsável por persistir os dados em um arquivo binário.
 *
 * @author Diones Gomes
 */
public class UsuariosArquivoDAO {

    private final File arquivo;

    /**
     * Inicializando o construtor sem passar parâmetros.
     *
     * @author Diones Gomes
     *
     */
    public UsuariosArquivoDAO() {
        arquivo = new File("Usuario.bin");

        if (!arquivo.exists()) {

            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Falha na conexão com arquivo", "Mensagem Erro", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    /**
     * Método para adicionar uma nova conta de usuário.
     *
     * @param usuario correponde a um usuário que será adicionado.
     * @return true se o usuário for cadastrado, false se não.
     * @throws exceptions.CpfExcecption
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws exceptions.TelefoneException
     */
    public boolean Adicionar(Usuario usuario) throws CpfExcecption, FileNotFoundException, IOException, ClassNotFoundException, TelefoneException {
        List<Usuario> usuarios;

        //Tratando a exceção para campo CPF vázio!
        if (usuario.getCpf().equals("   .   .   -  ")) {
            throw new CpfExcecption("");
        }

        //Tratando a exceção para campo TELEFONE vázio! 
        if (usuario.getTelefone().equals("(  ) .    -    ")) {
            throw new TelefoneException("");
        }

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            usuarios = (List<Usuario>) in.readObject();
        } else {
            usuarios = new ArrayList<>();
        }

        if (usuarios.add(usuario)) {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(arquivo));

            out.writeObject(usuarios);
            out.close();

            return true;

        } else {
            return false;
        }

    }

    /**
     * Método para fazer a busca de usuário por meio do email.
     *
     * @param email corresponde ao email cadastrado do usuário.
     * @return o usuário correspondente.
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
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

    /**
     * Método para lista todos os usuário cadastrados.
     *
     * @return um array contendo todos os usuários do sistema.
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public List<Usuario> listar() throws IOException, ClassNotFoundException {

        if (arquivo.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivo));

            return (List<Usuario>) in.readObject();
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Método responsával por fazer a autenticação do usuário no sistema.
     *
     * @param email corresponde ao email do usuário cadastrado.
     * @param senha corresponde a senha do usuário.
     * @return true para autendicado com sucesso ou false para falha na
     * autenticação.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean Autenticacao(String email, String senha) throws IOException, ClassNotFoundException {
        try {
            Usuario usuario = buscar(email);
            if (usuario.getSenha().equals(senha) && usuario != null) {
                return true;
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Usuário ou Senha incorreto!");
        }

        return false;
    }

    /**
     * Método para atualizar um usuário cadastrado no sistema.
     *
     * @param email corresponde a um email do usuário a ser atualizado.
     * @param u corresponde ao usuário que vai ter os dados atualizados.
     * @return true para modificado com sucesso ou false para falha na
     * atualização.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws exceptions.CpfExcecption
     * @throws exceptions.TelefoneException
     */
    public boolean atualizar(String email, Usuario u) throws IOException, ClassNotFoundException, CpfExcecption, TelefoneException {

        List<Usuario> usuario;

        //Tratando a exceção para campo CPF vázio!
        if (u.getCpf().equals("   .   .   -  ")) {
            throw new CpfExcecption("");
        }

        //Tratando a exceção para campo TELEFONE vázio! 
        if (u.getTelefone().equals("(  ) .    -    ")) {
            throw new TelefoneException("");
        }

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

    /**
     * Método para deletar uma conta de usuário.
     *
     * @param email corresponde ao email do usuário que será excluído.
     * @return true para usuário deletado ou false para falha na exclussão.a
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

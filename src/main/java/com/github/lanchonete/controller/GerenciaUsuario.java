package main.java.com.github.Lanchonete.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import main.java.com.github.Lanchonete.model.Setor;
import main.java.com.github.Lanchonete.model.Usuario;

/**
 * Esta classe contém métodos para Adicionar, Atualizar e Deletar os dados de um
 * usuário. Aqui será realizada a autenticação dos usuários.
 *
 * @see java.util.HashMap
 * @see main.java.com.github.Lanchonete.model.Usuario
 * @author Diones Gomes
 */
public class GerenciaUsuario {

    private Map<String, Usuario> usuarios;

    /**
     * Inicializando o construtor, sem passar parâmetros.
     */
    public GerenciaUsuario() {
        usuarios = new HashMap<>();

        /*Adicionando o gerente para teste.*/
        addLogin(new Usuario("111.111.111-11", "Administrador", "admin@gmail.com", "admin", "9999-9999", LocalDate.of(1996, Month.AUGUST, 12), Setor.GERENCIA));
    }

    /**
     * Método para encontar um usuário com base em seu e-mail.
     *
     * @param email Refere-se ao e-mail do usuário que é usado como base para a
     * pesquisa.
     * @return null se o usuário não está cadastrado, ou o e-mail se cadastrado.
     */
    public Usuario encontrarUsuario(String email) {
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(email);
    }

    /**
     * Método para adicionar um novo usuário ao sistema.
     *
     * @param usuario Refere-se ao novo usuário que será dastrado no sistema.
     * @return true ou false;
     */
    public boolean addLogin(Usuario usuario) {
        /*Verificando se o usuário já não existe*/
        if (encontrarUsuario(usuario.getEmail()) == null) {
            usuarios.put(usuario.getEmail(), usuario);
            return true;
        }
        return false;
    }

    /**
     * Método para remover um usuário já cadastrado no sistema.
     *
     * @param email Refere-se ao email do usuário que é usado como base para a
     * remoção.
     * @return true ou false.
     */
    public boolean removeLogin(String email) {
        Usuario usuario = encontrarUsuario(email);
        if (usuario != null) {
            return usuarios.remove(email, usuario);
        }
        return false;
    }

    /**
     * Método para atualizar os dados de um usuário ja dastrado.
     *
     * @param email Refere-se ao email que identifica cada usuário para a
     * atualização.
     * @param usuario Referese ao nome de usuário a ser atualizado.
     * @return true ou false.
     */
    public boolean uptadeUsuario(String email, Usuario usuario) {
        if (encontrarUsuario(email) != null) {
            removeLogin(email);
            return addLogin(usuario);
        }
        return false;
    }

    /**
     * Método para verificar se o usuário está ou não cadastrado no sistema.
     *
     * @param email Refere-se a o e-mail que identifica cada usuário cadastrado.
     * @param senha Refere-se a senha do usuário que é critério de autenticação
     * do login.
     * @return true ou false.
     */
    public boolean Autenticacao(String email, String senha) {
        Usuario usuario = encontrarUsuario(email);
        if (usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    /**
     * Esse método faz a listagem dos usuários dastrados.
     *
     */
    public void Listar() {
        for (String map : usuarios.keySet()) {
            System.out.println(usuarios.get(map));
        }
    }
}

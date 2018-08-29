package main.java.com.github.Lanchonete.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import main.java.com.github.Lanchonete.model.Setor;
import main.java.com.github.Lanchonete.model.Usuario;

public class GerenciaUsuario {

    private Map<String, Usuario> usuarios;

    public GerenciaUsuario() {
        usuarios = new HashMap<>();
        
        /*Adicionando o gerente*/
        addLogin(new Usuario("111.111.111-11", "Gerente", "admin@gmail.com.br",
                "9999999999", LocalDate.of(2018, 8, 12), Setor.GERENCIA, "admin"));
    }

    /*Pesquisa por um funcionario com base em seu email.*/
    public Usuario encontrarUsuario(String email) {
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(email);
    }

    /*Método para adicionar um novo usuário*/
    public boolean addLogin(Usuario usuario) {
        /*Verificando se o usuário já não existe*/
        if (encontrarUsuario(usuario.getEmail()) == null) {
            usuarios.put(usuario.getEmail(), usuario);
            return true;
        }
        return false;
    }

    /*Método para remover um usuário já cadastrado*/
    public boolean removeLogin(String email) {
        Usuario usuario = encontrarUsuario(email);
        if (usuario != null) {
            return usuarios.remove(email, usuario);//remove o usuario encontrado a partir da chave passada
        }
        return false;
    }

    //recebe um email por fora porque se for buscar pelo email do usuario(usuario editado) passado
    //pode acontecer do email dele ter sido mudado: Busca pelo email antigo
    public boolean uptadeUsuario(String email, Usuario usuario) {
        if (encontrarUsuario(email) != null) {//verifica se so usuario que vai ser editado existe
            removeLogin(email);//apaga o usuario antigo
            return addLogin(usuario);//adiciona o usuario editado
        }
        return false;
    }

    /*Método para autenticação de usuários*/
    public boolean Autenticacao(String email, String senha) {
        Usuario usuario = encontrarUsuario(email);
        if (usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }
}

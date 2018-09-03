package main.java.com.github.Lanchonete.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A classe Usuario modela a entidade Usuário.
 *
 * @author Diones Gomes
 * @see GerenciaUsuario
 */
public class Usuario implements Serializable {

    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate nascimento;
    private Setor setor;
    private String senha;

    /**
     * Inizializando os atributos do Usuário.
     *
     * @param cpf Referente ao CPF do Usuário.
     * @param nome Referente ao Nome do Usuário.
     * @param email Refente ao E-mail do Usuário.
     * @param senha Referente a Senha do Usuário.
     * @param telefone Referente ao Número de telefone de um Usuário.
     * @param nascimento Referencia a Data de nascimento do Usuário.
     * @param setor Referente ao Setor onde o Usuário trabalha.
     */
    public Usuario(String cpf, String nome, String email, String senha, String telefone, LocalDate nascimento, Setor setor) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.setor = setor;
        this.senha = senha;
    }

    /*Getters e Setters*/
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cpf = " + cpf + ", nome = " + nome + ", email = " + email + ", telefone = "
                + telefone + ", nascimento = " + nascimento + ", setor = " + setor + ", senha = " + senha + '}';
    }

}

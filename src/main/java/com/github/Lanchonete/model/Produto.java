package main.java.com.github.Lanchonete.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Está Classe modela os dados da entidade Produto.
 *
 * @see main.java.com.github.Lanchonete.controller.Menu
 * @see main.java.com.github.Lanchonete.model.Pedido
 * @author Diones Gomes
 */
public class Produto implements Serializable {

    private int codigo;
    private String nome;
    private String descricao;
    private float preco;
    //private static int id;

    /**
     * Inicializando todos os atributos da classe Produto.
     *
     * @param codigo Referente ao Código do produto.
     * @param nome Referente ao Nome do Produto.
     * @param descricao Refere-se a Descrição do Produto.
     * @param preco Referente ao Preço de vanda do Produto.
     */
    public Produto(int codigo, String nome, String descricao, float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        //codigo = ++id;
    }

    /*Getters e Setters*/
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.codigo;
        hash = 31 * hash + Objects.hashCode(this.nome);
        hash = 31 * hash + Objects.hashCode(this.descricao);
        hash = 31 * hash + Float.floatToIntBits(this.preco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (Float.floatToIntBits(this.preco) != Float.floatToIntBits(other.preco)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "codigo = " + codigo + ", nome = " + nome
                + ", descricao = " + descricao + ", preco = " + preco + '}';
    }

}

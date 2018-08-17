package main.java.com.github.Lanchonete.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Produto implements CadastroProduto, Serializable{
    
    private int codigo;
    private String nome;
    private String descricao;
    private float preco;
    
    List<Produto> produtos = new ArrayList<>(); //Array com os produtos.
    
    /*Construtor*/
    public Produto(int codigo, String nome, String descricao, float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
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
        return "Produto{" + "codigo=" + codigo + ", nome=" + nome +
                ", descricao=" + descricao + ", preco=" + preco + '}';
    }

    /*CRUD produtos*/
    
    @Override
    public boolean cadastrarProdutos(Produto produto) {
        return true;
    }

    @Override
    public boolean excluirProdutos(int codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Produto> listarProdutos() {        
       return null;
    }

    @Override
    public boolean alterarProdutos(Produto produto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
        
}

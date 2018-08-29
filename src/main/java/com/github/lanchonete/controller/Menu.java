package main.java.com.github.Lanchonete.controller;

import java.util.ArrayList;
import main.java.com.github.Lanchonete.model.Produto;

public class Menu {

    private ArrayList<Produto> produtos;

    public Menu() {
        produtos = new ArrayList<>();
    }

    //buscar o indice do produto na lista pelo seu codigo
    int recuperarProduto(int codigo) {
        if (produtos.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    /*Lista todos os produtos*/
    public ArrayList<Produto> listarProdutos() {
        if (produtos.isEmpty()) {
            return null;
        }else{
            return produtos;
        }
        
    }

    //buscar e retornar o produto
    public Produto EscolherProduto(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public boolean adicionarProduto(Produto produto) {
        if (recuperarProduto(produto.getCodigo()) >= 0) {
            return false;//Produto com codigo ja cadastrado
        }else{
         return produtos.add(produto);
        }
    }

    public boolean excluirProduto(int codigo) {
        if (recuperarProduto(codigo) < 0)//verifica se o produto existe
        {
            return false;
        }else{
        return produtos.remove(produtos.get(recuperarProduto(codigo)));
        }        
    }

    //recebe o codigo do produto que sera editado e um novo produto com informações novas do produto
    public boolean editarProduto(int codigo, Produto produto) {
        if (recuperarProduto(codigo) < 0)//verifica se o produto existe para ser editado
        {
            return false;
        }
        produto.setCodigo(codigo);//garante que o produto editado(novo produto) tenha o mesmo codigo
        if (excluirProduto(codigo)) {//se excluir o produto antigo sera adicionado o novo(editado)
            adicionarProduto(produto);//adicionando o produto editado
            return true;
        }
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.github.lanchonete.controller;

import java.io.IOException;
import java.util.ArrayList;
import main.java.com.github.Lanchonete.model.Produto;

/**
 *
 * @author Diones Gomes
 */
public class GerenciaMenu {

    /**
     * Inicializa a estrutura ArrayList sem conter valores
     *
     */
    private static ArrayList<Produto> produtos = new ArrayList<>();

    //buscar o indice do produto na lista pelo seu codigo
    private static int buscarProduto(int codigo) {
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

    /**
     * Lista todos os produtos contidos no cardápio.
     *
     * @return Lista de Produtos ou null.
     *
     */
    public static ArrayList<Produto> listarProdutos() {
        if (produtos.isEmpty()) {
            return null;
        }
        return produtos;
    }

    /**
     * Seleciona um produto específico pelo seu código.
     *
     * @param codigo o código para especificar o produto.
     * @return um produto ou null
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     *
     */
    public static Produto EscolherProduto(int codigo) throws IOException, ClassNotFoundException {
        CadastrarProdutoArquivo arquivo = new CadastrarProdutoArquivo();
        return arquivo.buscar(codigo);
        
    }

    /**
     * Adiciona um produto ao menu(cardápio).
     *
     * @param produto o produto que será adicionado ao menu.
     * @return true ou false.
     *
     */
    public static boolean adicionarProduto(Produto produto) {
        if (buscarProduto(produto.getCodigo()) >= 0) {
            return false;//Produto com codigo ja cadastrado
        }
        return produtos.add(produto);
    }

    /**
     * Exclui o produto do menu(cardápio).
     *
     * @param codigo o código para especificar o produto que será excluido.
     * @return true ou false.
     *
     */
    public static boolean excluirProduto(int codigo) {
        if (buscarProduto(codigo) < 0)//verifica se o produto existe
        {
            return false;
        }
        return produtos.remove(produtos.get(buscarProduto(codigo)));
    }

    /**
     * Edita um produto do menu(cardápio).
     *
     * @param codigo o código para especificar o produto que será editado.
     * @param produto o produto que substituirá o atual.
     * @return true ou false.
     *
     */
    public static boolean editarProduto(int codigo, Produto produto) {
        if (buscarProduto(codigo) < 0)//verifica se o produto existe para ser editado
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

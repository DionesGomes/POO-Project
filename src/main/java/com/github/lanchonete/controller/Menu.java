package main.java.com.github.Lanchonete.controller;

import java.util.ArrayList;
import main.java.com.github.Lanchonete.model.Produto;

/**
 * Esta classe contém medos para Adicionar, Consultar, Atualizar e Deletar
 * Produtos.
 *
 * @see java.util.ArrayList
 * @see main.java.com.github.Lanchonete.model.Produto
 * @author Diones Gomes
 */
public class Menu {

    private ArrayList<Produto> produtos;

    /**
     * Iniciando o ArrayList, sem passar parâmentros.
     */
    public Menu() {
        produtos = new ArrayList<>();
    }

    /**
     * Método para consultar os produtos dispiníveis.
     *
     * @param codigo Refere-se ao código do produto usado como base para a
     * pesquisa.
     * @return o número do pedido se encontrado, se não, retorna -1.
     */
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

    /**
     * Mètodo para listar todos os produtos no ArrayList.
     *
     * @return Uma lista de produtos, ou null se não tiver produtos.
     */
    public ArrayList<Produto> listarProdutos() {
        if (produtos.isEmpty()) {
            return null;
        } else {
            return produtos;
        }

    }

    /**
     * Método para selecionar os produtos com base no código.
     *
     * @param codigo Refere-se ao código de identificação do produto.
     * @return O produto se presente, ou null se não estiver.
     */
    public Produto SelecionaProduto(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    /**
     * Método para adicionar um novo produto.
     *
     * @param produto Refere-se ao produto que será adicionado.
     * @return true ou false.
     */
    public boolean adicionarProduto(Produto produto) {
        if (recuperarProduto(produto.getCodigo()) >= 0) {
            return false;//Produto com codigo ja cadastrado
        } else {
            return produtos.add(produto);
        }
    }

    /**
     * Método para exluir um produto.
     *
     * @param codigo Refere-se ao código do produto que é usado como base para a
     * esclusão.
     * @return true ou false.
     */
    public boolean excluirProduto(int codigo) {
        if (recuperarProduto(codigo) < 0)//verifica se o produto existe
        {
            return false;
        } else {
            return produtos.remove(produtos.get(recuperarProduto(codigo)));
        }
    }

    //recebe o codigo do produto que sera editado e um novo produto com informações novas do produto
    /**
     * Método para editar um produto.
     *
     * @param codigo Refere-se ao código do produto,que será editado.
     * @param produto Refere-se ao novo produto que será adiocionado.
     * @return true ou false.
     */
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

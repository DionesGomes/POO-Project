/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import model.Produto;

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

    /**
     * Seleciona um produto específico pelo seu código.
     *
     * @param codigo o código para especificar o produto.
     * @return um produto se o código passado corresponder a algun produto
     * cadastrado no sistema ou null.
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     *
     */
    public static Produto EscolherProduto(int codigo) throws IOException, ClassNotFoundException {
        ProdutosArquivoDAO arquivo = new ProdutosArquivoDAO();
        return arquivo.buscar(codigo);

    }
}

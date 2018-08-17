
package main.java.com.github.Lanchonete.model;

import java.util.List;


public interface CadastroProduto {
    
    /*CRUD de Produtos*/
    boolean cadastrarProdutos(Produto produto);
    boolean excluirProdutos(int codigo);
    List<Produto> listarProdutos();
    boolean alterarProdutos(Produto produto);
    
}
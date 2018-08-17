package main.java.com.github.Lanchonete.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.github.Lanchonete.model.Pedido;
import main.java.com.github.Lanchonete.model.Produto;
import main.java.com.github.Lanchonete.model.Usuario;


/**
 *
 * @author Diones Gomes
 */
public class App {
        
    
    
    public static void main(String[] args){ 
        
      
        List<Produto> produtos = new ArrayList<>(); //Array com os produtos.
        
        Produto produto1 = new Produto(1, "Refrigerante", "Coca-Cola", 6.50f);
        Produto produto2 = new Produto(2, "Carne", "Carne de Hamburguer", 3.50f);
        Produto produto3 = new Produto(3, "Pão", "Pão de Hamburguer", 4.50f);
        Produto produto4 = new Produto(4, "Ovo", "Capoeira", 6.00f);
        Produto produto5 = new Produto(5, "Linguiça", " de Porco", 6.00f);
        
       produtos.add(produto1);
       produtos.add(produto2);
       produtos.add(produto3);
       produtos.add(produto4);
       produtos.add(produto5);
       
       /*Listando todos produtos do ArrayList*/
        for (Produto produto : produtos) {
            System.out.println("Código :" + produto.getCodigo()+ "Nome :" + produto.getNome()+ "Descrição :" + 
                    produto.getDescricao() + "Preço :" + produto.getPreco());
            System.out.println("...............................................");
        }
        
        /*Removendo algum produto*/
        produtos.remove(produto5);
        
        for (Produto produto : produtos) {
            System.out.println("Código :" + produto.getCodigo()+ "Nome :" + produto.getNome()+ "Descrição :" + 
                    produto.getDescricao() + "Preço :" + produto.getPreco());
            System.out.println("................................................");
        }
        
               
    }         
}
    

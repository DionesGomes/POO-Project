package main.java.com.github.Lanchonete.view;

;
import java.util.Scanner;
import javax.swing.JOptionPane;

import main.java.com.github.Lanchonete.controller.GerenciaMesa;
import main.java.com.github.Lanchonete.controller.GerenciaUsuario;
import main.java.com.github.Lanchonete.controller.Menu;
import main.java.com.github.Lanchonete.model.Cozinha;
import main.java.com.github.Lanchonete.model.Produto;

/**
 *
 * @author Diones Gomes
 */


public class App {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        GerenciaMesa gerenciamesa = new GerenciaMesa();
        GerenciaUsuario usuario = new GerenciaUsuario();
        Cozinha cozinha = new Cozinha();
        Menu menu = new Menu();

        /*Adicionando produtos*/
        menu.adicionarProduto(new Produto(01, "Refrigerente", "Coca-cola", 7.76f));
        menu.adicionarProduto(new Produto(02, "Hamburguer", "X-Tudo", 13.00f));
        menu.adicionarProduto(new Produto(03, "Carne", "Carne de hanburguer", 1.80f));
        menu.adicionarProduto(new Produto(04, "Arroz", "Porção de arroz branco", 4.50f));

        String username = null, password = null;
        boolean fechar = true;
        int i, codigoProduto = 0;

        while (fechar) {

            System.out.println("_________________________________________________\n");
            System.out.println(":::::::::::::::::::LANCHONETE::::::::::::::::::::::");
            System.out.println("_________________________________________________\n");

            
            System.out.println(" ||(-1-)LOGIN||                ||(-2-)CADASTRE-SE||");

            i = ler.nextInt();
            if (i == 1) {
                System.out.println("Digite o seu E-mail de usuário:");
                username = ler.next();
                System.out.println("Digite a sua Senha de usuário: ");
                password = ler.next();
            }

            if (i == 1 && usuario.Autenticacao(username, password)) {

                limparTela();
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::: LOGADO COM SUCESSO! :::::::::::::::::::::::::::::::::::::::::::::\n");
     
                while (fechar) {
                    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::: MENU ::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
                    System.out.println("||(-1-)CARDÁPIO||   ||(-2-)MESAS||   ||(-3-)CONTA||  ||(-4-)COZINHA||  ||(-5-)GERÊNCIA||  ||(-0-)PARA SAIR||\n");

                    if (i == 1) {
                        System.out.println(":::::::::::::::::::::::::::::::::::::  PRODUTOS DISPONÍVEIS ::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
                        for (Produto produtos : menu.listarProdutos()) {
                            System.out.println(produtos);
                        }

                        System.out.println("(-1-)SALVAR (-2-)EDITAR (-3-)EXCLUIR (-0-)SAIR\n");

                        i = ler.nextInt();
                        if (i == 3) {
                            System.out.println("DIGITE O CÓDIGO DO PRODUTO");
                            codigoProduto = ler.nextInt();
                        }
                        ler.nextInt();
                        if ((i == 1) || (i == 2)) {
                            System.out.println("DIGITE O NOME DO PRODUTO:");
                            String nomeProduto = ler.nextLine();
                            System.out.println("GIGITE A DESCRIÇÃO DO PRODUTO:");
                            String descricaoProduto = ler.nextLine();
                            System.out.println("DIGITE O PREÇO DO PRODUTO:");
                            float precoProduto = ler.nextFloat();

                            if (i == 1) {
                                System.out.println(menu.adicionarProduto(new Produto(codigoProduto, nomeProduto, descricaoProduto, precoProduto)));
                            } else if (i == 3) {
                                System.out.println(menu.editarProduto(codigoProduto, new Produto(codigoProduto, nomeProduto, descricaoProduto, precoProduto)));
                            }
                        } else if (i == 2) {
                            System.out.println(menu.excluirProduto(codigoProduto));
                        }
                    }
                    fechar = false;
                }
            }

            fechar = false;
        }
    }

    /*GANBIARRA PARA LIMPAR A TELA*/
    public static void limparTela() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }
}

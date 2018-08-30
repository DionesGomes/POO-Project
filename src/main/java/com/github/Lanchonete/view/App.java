package main.java.com.github.Lanchonete.view;

;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import javax.swing.JOptionPane;
import main.java.com.github.Lanchonete.controller.Gerencia;

import main.java.com.github.Lanchonete.controller.GerenciaMesa;
import main.java.com.github.Lanchonete.controller.GerenciaUsuario;
import main.java.com.github.Lanchonete.controller.Menu;
import main.java.com.github.Lanchonete.model.Cozinha;
import main.java.com.github.Lanchonete.model.Produto;
import main.java.com.github.Lanchonete.model.Setor;
import main.java.com.github.Lanchonete.model.Usuario;

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

        String username = null, password = null;
        LocalDate dataInicio = null, dataFim = null;
        int i, mesa, codigoProduto = 0, numeroPedido;
        boolean fechar = true;

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
                    } else if (i == 2) {
                        System.out.println("DIGITE O NUMERO DA MESA:");
                        mesa = ler.nextInt();
                        System.out.println("(-1-)CRIAR COMANDA (-2-)VISUALIZAR PEDIDOS (-3-)NOVO PEDIDO (-4-)FECHAR COMANDA (-0-)SAIR\n");
                        i = ler.nextInt();
                        if (i == 1) {
                            System.out.println(gerenciamesa.novaComanda(mesa));
                        } else if (i == 2) {
                            System.out.println(gerenciamesa.verPedidos(mesa));
                        } else if (i == 3) {
                            /*Implementar*/
                        } else if (i == 4) {
                            System.out.println(gerenciamesa.fecharComanda(mesa));
                        }
                    } else if (i == 3) {
                        System.out.print("||(-1-)ATUALIZAR DADOS DO USUÁRI||   ||(-2-)EXCLUIR USUÁRIO||  ||(-0-)SAIR||");
                        i = ler.nextInt();
                        if ((i == 1) || (i == 2)) {
                            System.out.print(" DIGITE O E-MAIL DO USUÁRIO: ");
                            username = ler.next();
                        }
                        if (i == 1) {
                            System.out.println(usuario.uptadeUsuario(username, cadastrarNovoUsuario(ler)));
                        } else if (i == 2) {
                            System.out.println(usuario.removeLogin(username));
                        }
                    } else if (i == 4) {
                        System.out.print(cozinha.visualizar() + "DIGITE O NÚMERO DO PEDIDO OU (-0-)SAIR :");
                        numeroPedido = ler.nextInt();
                        if (numeroPedido > 0) {
                            System.out.println(cozinha.atenderPedido(numeroPedido, gerenciamesa));
                        }

                    } else if (i == 5) {
                        System.out.println("||(-1-)VISUALIZAR COMANDAS COM BASE NA DATA||     ||(-2-)-VISUALIZAR VALOR TOTAL DE UMA COMANDA COM BASE NA DATA||     ||(-0-)SAIR||");
                        i = ler.nextInt();
                        if ((i == 1) || (i == 2)) {
                            System.out.println("DIGITE A DATA DE INÍCIO:");
                            dataInicio = informeData(ler);
                            System.out.println("DIGITE A DATA DO FIM:");
                            dataFim = informeData(ler);
                        }
                        if (i == 1) {
                            System.out.println(Gerencia.listarComandas(dataInicio, dataFim));
                        } else if (i == 2) {
                            System.out.println(Gerencia.CalculaLucroTotal(dataInicio, dataFim));
                        }
                    } else {
                        fechar = false;
                    }
                }
            } else if (i == 2) {
                usuario.addLogin(cadastrarNovoUsuario(ler));
            }
            fechar = true;
        }
    }

    /*METÓDOS STATICOS*/
 /*GANBIARRA PARA LIMPAR A TELA*/
    
    public static void limparTela() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }
    
     static LocalDate informeData(Scanner ler) {
        System.out.print("DIGITE O ANO :");
        int ano = ler.nextInt();
        System.out.print("DIGITE O MÊS :");
        int mes = ler.nextInt();
        System.out.print("DIGITE O DIA :");
        int dia = ler.nextInt();
        return LocalDate.of(ano, mes, dia);
    }
    
    static Usuario cadastrarNovoUsuario(Scanner ler) {
        System.out.print("Informe o CPF:");
        String cpf = ler.next();
        System.out.print("Informe o nome:");
        ler.nextLine();
        String nome = ler.nextLine();
        System.out.print("Informe o E-mail:");
        String email = ler.next();
        System.out.print("Informe a senha:");
        String senha = ler.next();
        System.out.print("Informe o telefone:");
        ler.nextLine();
        String telefone = ler.nextLine();
        System.out.println("Informe a data de Nascimento:");
        LocalDate nascimento = informeData(ler);
        System.out.print("1-Atendimento\n2-Cozinha\n3-Caixa\n4-Gerencia\nInforme o setor:");
        int setor = ler.nextInt();
        if (setor > 4 || setor < 1) {
            return null;
        }
        Setor s = null;

        switch (setor) {
            case 1:
                s = Setor.GARCOM;
                break;
            case 2:
                s = Setor.COZINHA;
                break;
            case 3:
                s = Setor.CAIXA;
                break;
            case 4:
                s = Setor.GERENCIA;
                break;
        }
        return new Usuario(cpf, nome, email, senha, telefone, nascimento, s);
    }   
}

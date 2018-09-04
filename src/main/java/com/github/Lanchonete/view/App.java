package main.java.com.github.Lanchonete.view;

;
import java.time.LocalDate;
import java.util.Scanner;

import main.java.com.github.Lanchonete.controller.GerenciaUsuario;
import main.java.com.github.Lanchonete.controller.Menu;
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
        GerenciaUsuario usuario = new GerenciaUsuario();
        Menu menu = new Menu();

        /*ADICIONANDO PRODUTOS PARA EFETUAR OS TESTES*/
        menu.adicionarProduto(new Produto(01, "Agua", "500ml", 3.00f));
        menu.adicionarProduto(new Produto(02, "Suco", "MARACUJÁ", 2.80f));
        menu.adicionarProduto(new Produto(03, "Refrigerente", "Coca-cola", 1.80f));
        menu.adicionarProduto(new Produto(04, "X-tudo", "Hanburguer", 1.80f));
        menu.adicionarProduto(new Produto(05, "X-Bacon", "Hanburguer", 1.80f));

        String username = null, password = null;
        int i, codigoProduto = 0;
        boolean fechar = true;

        while (fechar) {
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println(":::::::::::::::::::::LANCHONETE::::::::::::::::::");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.print("       <(1)ENTRAR>        <(2)CADASTRE-SE>  \n ~ ");
            i = ler.nextInt();
            if (i == 1) {
                System.out.print("DIGITE O SEU E-MAIL DE USUÁRIO: ");
                username = ler.next();
                System.out.print("DIGITE A SUA SENHA DE USUÁRIO : ");
                password = ler.next();
            }

            if (i == 1 && usuario.Autenticacao(username, password)) {
                while (fechar) {
                    System.out.println("::::::::::::::::::::::::::::::::::::MENU:::::::::::::::::::::::::::::::::::::::");
                    System.out.print("<(1)PRODUTOS>  <(2)XXXXX>  <(3)USUÁRIOS>  <(4)XXXXX>  <(5)XXXXX>  <(0)SAIR> \n ~ ");
                    i = ler.nextInt();

                    switch (i) {
                        case 1:
                            System.out.print("\n::::::::::::::::::::::::::::::PRODUTOS DISPONÍVEIS:::::::::::::::::::::::::");
                            /*LISTAGEM DOS PRODUTOS DISPONÍVEIS*/
                            for (Produto p : menu.listarProdutos()) {
                                System.out.println(p);
                            }
                            System.out.println(":::::::::::::::::::::::::::::PAINEL DO ADMINISTRADOR:::::::::::::::::::::::");
                            System.out.print("(1)SALVAR     (2)EXCLUIR     (3)EDITAR     (0)SAIR \n ~ ");
                            i = ler.nextInt();
                            if (i >= 1 && i <= 3) {
                                System.out.print("DIGITE O CÓDIGO DO PRODUTO:");
                                codigoProduto = ler.nextInt();
                            }
                            ler.nextLine();
                            if (i == 1 || i == 3) {
                                System.out.print("DIGITE O NOME DO PRODUTO : ");
                                String nomeProduto = ler.nextLine();
                                System.out.print("DIGITE A DESCRIÇÃO DO PRODUTO : ");
                                String descricaoProduto = ler.nextLine();
                                System.out.print("DIGITE O PREÇO DO PRODUTO : ");
                                float precoProduto = ler.nextFloat();
                                if (i == 1) {
                                    System.out.println(menu.adicionarProduto(new Produto(codigoProduto, nomeProduto, descricaoProduto, precoProduto)));
                                } else if (i == 3) {
                                    System.out.println(menu.editarProduto(codigoProduto, new Produto(codigoProduto, nomeProduto, descricaoProduto, precoProduto)));
                                }
                            } else if (i == 2) {
                                System.out.println(menu.excluirProduto(codigoProduto));
                            }
                            break;
                        case 3:
                            System.out.println(":::::::::::::::::::::::::::::::::::::::::USUÁRIOS:::::::::::::::::::::::::::::::");
                            /*LISTAGEM DOS USUÁRIOS JÁ DASTRADOS*/
                            usuario.Listar();
                            System.out.println(":::::::::::::::::::::::::::::::::PAINEL DO ADMINISTRADOR::::::::::::::::::::::::");
                            System.out.print("(1)EDITAR USUÁRIO     (2)EXCLUIR USUÁRIO    (0)SAIR \n ~ ");
                            i = ler.nextInt();
                            if (i == 1 || i == 2) {
                                System.out.print("DIGITE O E-MAIL DE USUÁRIO DO USUÁRIO QUE VOCÊ DESEJA ALTERAR : ");
                                username = ler.next();
                            }
                            if (i == 1) {
                                usuario.uptadeUsuario(username, cadastrarNovoUsuario(ler));
                            } else if (i == 2) {
                                usuario.removeLogin(username);
                            }
                            break;
                        default:
                            fechar = false;
                            break;
                    }
                }
            } else if (i == 2) {
                usuario.addLogin(cadastrarNovoUsuario(ler));
            }

            fechar = true;

        }
    }

    /*Métodos estáticos.*/
    public static void limparTela() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }

    /*Construindo*/
    static LocalDate informeData(Scanner ler) {
        System.out.print("DIGITE O ANO :");
        int ano = ler.nextInt();
        System.out.print("DIGITE O MÊS :");
        int mes = ler.nextInt();
        System.out.print("DIGITE O DIA :");
        int dia = ler.nextInt();
        return LocalDate.of(ano, mes, dia);
    }

    /*Construindo*/
    static Usuario cadastrarNovoUsuario(Scanner ler) {
        System.out.print("DIGITE O SEU CPF:");
        String cpf = ler.next();
        System.out.print("DIGITE O SEU NOME:");
        ler.nextLine();
        String nome = ler.nextLine();
        System.out.print("DIGITE O SEU E-MAIL:");
        String email = ler.next();
        System.out.print("DIGITE A SUA SENHA:");
        String senha = ler.next();
        System.out.print("DIGITE O SEU TELEFONE:");
        ler.nextLine();
        String telefone = ler.nextLine();
        System.out.println("DIGITE A SUA DATA DE NASCIMENTO:");
        LocalDate nascimento = informeData(ler);
        System.out.print("(1)ATENDIMENTO\n (2)COZINHA \n (3)CAIXA \n (4)GERÊNCIA\n DIGITE O SETOR\n\n ~ ");

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

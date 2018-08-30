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

        /*ADICIONANDO PRODUTOS PARA EFETUAR OS TESTES*/
        menu.adicionarProduto(new Produto(01, "Refrigerente", "Coca-cola", 7.76f));
        menu.adicionarProduto(new Produto(02, "Hamburguer", "X-Tudo", 13.00f));
        menu.adicionarProduto(new Produto(03, "Carne", "Carne de hanburguer", 1.80f));

        String username = null, password = null;
        LocalDate dataInicio = null, dataFim = null;
        int i, mesa, codigoProduto = 0, numeroPedido;
        boolean fechar = true;

        while(fechar) {
			System.out.println("----------------TELA INICIAL--------------");
			System.out.print("1-Autenticar        2-Criar nova conta\n>>>>>");
			i = ler.nextInt();
			if(i == 1) {
				System.out.print("Usuário(email):");
				username = ler.next();
				System.out.print("Senha:");
				password = ler.next();
			}

			if(i == 1 && usuario.Autenticacao(username, password)) {
				while(fechar) {
					System.out.print("1-Cardápio     2-Mesas     3-Minha Conta"
							+ "\n4-Cozinha     5-Gerência     0-Sair\n>>>>");
					i = ler.nextInt();
                                        
					if(i == 1) {
						System.out.print("Gerênciar Menu\n\nLista de Produtos disponiveis:\n");
						for(Produto p:menu.listarProdutos()) {
							System.out.println(p);
						}
						System.out.print("1-Salvar     2-Excluir     3-Editar     0-Sair\n>>>>");
						i = ler.nextInt();
						if(i>=1 && i<=3) {
							System.out.print("Informe o codigo do produto:");
							codigoProduto = ler.nextInt();
						}
						ler.nextLine();
						if(i == 1 || i == 3) {
							System.out.print("Informe o nome do produto:");
							String nomeProduto = ler.nextLine();
							System.out.print("Informe a descrição do produto:");
							String descricaoProduto = ler.nextLine();
							System.out.print("Informe o preço do produto:");
							float precoProduto = ler.nextFloat();
							if(i == 1) {
								System.out.println(menu.adicionarProduto(new Produto(codigoProduto, nomeProduto, descricaoProduto, precoProduto)));
							}
							else if(i == 3) {
								System.out.println(menu.editarProduto(codigoProduto, new Produto(codigoProduto, nomeProduto, descricaoProduto, precoProduto)));
							}
						}
						else if(i == 2) {
							System.out.println(menu.excluirProduto(codigoProduto));
						}	
					}

					else if(i == 3) {
						System.out.print("Informe\n1-Editar Usuário     2-Excluir Usuário     0-Sair\n>>>>");
						i = ler.nextInt();
						if(i==1 || i==2) {
							System.out.print("Informe o E-mail que deseja alterar:");
							username = ler.next();
						}
						if(i==1) {
							System.out.println(usuario.uptadeUsuario(username, cadastrarNovoUsuario(ler)));
						}
						else if(i == 2) {
							System.out.println(usuario.removeLogin(username));
						}
					}			
					
					else {
						fechar=false;
					}
				}
			}
	
			else if(i == 2) {
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

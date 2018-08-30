package main.java.com.github.Lanchonete.view;

;
import java.time.LocalDate;
import java.util.Scanner;



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
        GerenciaUsuario usuario = new GerenciaUsuario();      
        Menu menu = new Menu();

        /*ADICIONANDO PRODUTOS PARA EFETUAR OS TESTES*/
        menu.adicionarProduto(new Produto(01, "Refrigerente", "Coca-cola", 7.76f));
        menu.adicionarProduto(new Produto(02, "Hamburguer", "X-Tudo", 13.00f));
        menu.adicionarProduto(new Produto(03, "Carne", "Carne de hanburguer", 1.80f));

        String username = null, password = null;       
        int i, codigoProduto = 0;
        boolean fechar = true;

        while(fechar) {
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::");
			System.out.println(":::::::::::::::::::::LANCHONETE::::::::::::::::::");
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::\n");
			System.out.print("    (1)PARA ENTRAR        (2)PARA CADASTRE-SE \n\n ~ "        );
			i = ler.nextInt();
			if(i == 1) {
				System.out.print("DIGITE O SEU E-MAIL DE USUÁRIO: ");
				username = ler.next();
				System.out.print("DIGITE A SUA SENHA DE USUÁRIO : ");
				password = ler.next();
			}

			if(i == 1 && usuario.Autenticacao(username, password)) {
				while(fechar) {
                                        System.out.println("::::::::::::::::::::::::::::::::::::MENU:::::::::::::::::::::::::::::::::::::::");
					System.out.print("(1)CARDÁPIO     (2)MESAS     (3)CONTAS (4)COZINHA      (5)GERÊNCIA  (0)SAIR \n\n ~ ");
					i = ler.nextInt();                                        
   
					if(i == 1) {
                                                
						System.out.print("\n::::::::::::::::PRODUTOS DISPONÍVEIS:::::::::::\n");
						for(Produto p:menu.listarProdutos()) {
							System.out.println(p);
						}
                                                System.out.println("::::::::::::::::::::::::PRODUTOS:::::::::::::::::::::");
						System.out.print("(1)SALVAR     (2)EXCLUIR     (3)EDITAR     (0)SAIR \n\n ~ ");
						i = ler.nextInt();
						if(i>=1 && i<=3) {
							System.out.print("DIGITE O CÓDIGO DO PRODUTO:");
							codigoProduto = ler.nextInt();
						}
						ler.nextLine();
						if(i == 1 || i == 3) {
							System.out.print("DIGITE O NOME DO PRODUTO : ");
							String nomeProduto = ler.nextLine();
							System.out.print("DIGITE A DESCRIÇÃO DO PRODUTO : ");
							String descricaoProduto = ler.nextLine();
							System.out.print("DIGITE O PREÇO DO PRODUTO : ");
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
                                                System.out.println(":::::::::::::::::::::::::::::USUÁRIOS::::::::::::::::::::::::::::::");
						System.out.print("(1)EDITAR USUÁRIO     (2)EXCLUIR USUÁRIO     (0)SAIR \n\n ~ ");
						i = ler.nextInt();
						if(i==1 || i==2) {
							System.out.print("DIGITE O E-MAIL DE USUÁRIO : ");
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

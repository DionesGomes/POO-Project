package main.java.com.github.Lanchonete.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.com.github.Lanchonete.model.Comanda;
import main.java.com.github.Lanchonete.model.Cozinha;
import main.java.com.github.Lanchonete.model.Pedido;


public class GerenciaMesa {
    
    private List<Comanda> mesas;

    /*Costrutor*/
    public GerenciaMesa() {
        mesas = new ArrayList<>();
    }

    public boolean novaComanda(int mesa) {
		if(buscar(mesa)!= -1) {// verifica se a mesa existe ou não 
			return false; // não pode ser aberta duas ou mais comandas na mesma mesa
		}
		return mesas.add(new Comanda(mesa));		
	}
    
	public boolean fecharComanda(int mesa) {
		if(buscar(mesa)==-1) {// verifica se a mesa existe ou não
			return false;// não se pode encerrar uma comanda que não existe
		}
		for(Pedido p : getComanda(mesa).getListarPedidos()) {
			if(p.isStatus() == false) {// não se pode encerrar uma comanda que não tenha sido atendido todos pedidos
				return false;
			}
		}
		return Gerencia.adicionarComanda(mesas.remove(buscar(mesa)));//  adiciona a comanda para gerenciar 
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	public boolean fazerPedido(int mesa, Pedido p, Cozinha c) {
		if(buscar(mesa)!= -1 && p.getProduto()!=null) {                                              
			c.adicionarPedido(p); // adiciona à cozinha                            
			return mesas.get(buscar(mesa)).adicionaPedido(p);
		}
		Pedido.setContPedidos(Pedido.getContPedidos()-1);// decrementa o contador de pedido pois o pedido p não pode ser feito então ele não sera contado com os demais
		return false;
	}
	public String verPedidos(int mesa) {// retorna uma string com todos os pedidos de uma determinada comanda
		if(buscar(mesa)==-1) {
			return "";
		}
		return mesas.get(buscar(mesa)).toString();
	}
	public boolean modificarPedido(int mesa, Pedido p, int numeroPedido, Cozinha c) {
		if(ehNumeroPedidoValido(mesa, numeroPedido)) {// verifica se o numero do pedido existe (tambem verifica se a mesa existe) 
			if(!(mesas.get(buscar(mesa)).getPedido(numeroPedido).isStatus())) {//verifica se ele já não foi atendido
				Pedido.setContPedidos(Pedido.getContPedidos()-1);//pelo fato de ter sido necessário instanciar um novo pedido, so para modificar um já existente, é decrementado o contador de pedidos
				p.setNumeroPedido(numeroPedido); // o pedido novo, que servirá para modificar o antigo, tem seu numero definido para o do pedido antigo 
				excluirPedido(mesa, numeroPedido, c);//o pedido antigo é excluido da comanda e tambem da cozinha
				fazerPedido(mesa, p, c);//o novo pedido é adicionado a comanda e a cozinha, substituindo o antigo 
				return true;
			}
		}
		return false;
	}
	public boolean excluirPedido(int mesa, int numeroPedido, Cozinha c) {
		if(ehNumeroPedidoValido(mesa, numeroPedido)) {// verifica se o numero do pedido existe (tambem verifica se a mesa existe) 
			if(!(mesas.get(buscar(mesa)).getPedido(numeroPedido).isStatus())) {//verifica se ele já não foi atendido
				c.removePedido(numeroPedido); //remover da lista da cozinha
				return mesas.get(buscar(mesa)).removePedido(numeroPedido);
			}
		}
		return false;
	}
	///////////////////////////////////////////////////////////////////////////////////////
	int buscar(int mesa) {
		if(!(mesas.isEmpty())) {// não se pode buscar comandas se elas não existem
			for(int i = 0;i < mesas.size();i++) {
				if(mesas.get(i).getMesa() == mesa) {
					return i;
				}
			}
		}
		return -1;
	}
	public Comanda getComanda(int mesa){//retorna a (tudo da)comanda de uma mesa especifica (relaciona esta classe com Cozinha) /////não confundir com getListaPedido() que retorna a lista de todos os pedidos da comanda mas não retorna data, numero e mesa 
		return mesas.get(buscar(mesa));
	}
	////////////////////////////////////////////////////////////////////////////////////////
	private boolean ehNumeroPedidoValido(int mesa, int numeroPedido) { // verifica se o numero do pedido e a mesa existem
		if(buscar(mesa)!=-1) {
			return mesas.get(buscar(mesa)).buscarPedido(numeroPedido)!=-1; // retorna o valor lógico da comparação
		}
		return false;
	}
}

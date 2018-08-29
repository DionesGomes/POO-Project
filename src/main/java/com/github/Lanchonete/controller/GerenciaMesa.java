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
        if (buscar(mesa) != -1) {// verifica se a mesa existe ou não 
            return false; // não pode ser aberta duas ou mais comandas na mesma mesa
        }
        return mesas.add(new Comanda(mesa));
    }

    public boolean encerrarComanda(int mesa) {
        if (buscar(mesa) == -1) {// verifica se a mesa existe ou não
            return false;// não se pode encerrar uma comanda que não existe
        }
        for (Pedido p : getComanda(mesa).getListarPedidos()) {
            if (p.isStatus() == false) {// não se pode encerrar uma comanda que não tenha sido atendido todos pedidos
                return false;
            }
        }
        return Gerencia.adicionarParaGerencia(mesas.remove(buscar(mesa)));//  adiciona a comanda para gerenciar 
    }

    //retorna a (tudo da)comanda de uma mesa especifica (relaciona esta classe com Cozinha) 
    //não confundir com getListaPedido() que retorna a lista 
    //de todos os pedidos da comanda mas não retorna data, numero e mesa 
    public Comanda getComanda(int mesa) {
        return mesas.get(buscar(mesa));
    }
}

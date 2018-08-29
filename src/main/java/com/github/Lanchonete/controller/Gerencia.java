
package main.java.com.github.Lanchonete.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.java.com.github.Lanchonete.model.Comanda;


public class Gerencia {    
       
    private static List<Comanda> gerenciaComanda = new ArrayList<>();    
    
        /*Adicioanar comanda para poder gerenciá-la*/
	public static boolean adicionarComanda(Comanda d) {//esta função é usada somente por GerenciaMesa 
		return gerenciaComanda.add(d);
	}
        /*Lista as comandas em um determinado periodo de tempo*/
	public static String listarComandas(LocalDate inicio, LocalDate fim) {
		String s = "";
		for(Comanda comanda : gerenciaComanda) {
			if(comanda.getData().isAfter(inicio.plusDays(-1)) && comanda.getData().isBefore(fim.plusDays(1))) {// se a data de uma comanda c qualquer for depois de "inicio-1" e antes de "fim+1" ela é concatenada como String
				s+=comanda.toString();
			}
		}
		return s;
	}
        /*Calcula o lucro total de uma comanda*/
	public static float CalculaLucroTotal(LocalDate inicio, LocalDate fim) {
		float lucro = 0f;
		for(Comanda comanda : gerenciaComanda) {
			if(comanda.getData().isAfter(inicio.plusDays(-1)) && comanda.getData().isBefore(fim.plusDays(1))) {
				lucro+=comanda.valorTotal();
			}
		}
		return lucro;
	}
}

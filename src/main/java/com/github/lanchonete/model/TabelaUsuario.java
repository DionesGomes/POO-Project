/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.github.lanchonete.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import main.java.com.github.Lanchonete.model.Usuario;

/**
 *
 * 
 */  
public class TabelaUsuario extends AbstractTableModel{

    private List<Usuario> movLista = new ArrayList<>();
    private String [] colunas = {"CPF", "Email", "Nome", "Telefone", "Data de Nascimento", "Setor"};

    @Override
    public String getColumnName(int colums) {
        return colunas[colums];
    }
    
    @Override
    public int getRowCount() {
        
        return movLista.size();
        
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
        
            case 0:
                return movLista.get(linha).getCpf();
            case 1:
                return movLista.get(linha).getEmail();
            case 2:
                return movLista.get(linha).getNome();
            case 3:
                return movLista.get(linha).getTelefone();
            case 4:
                return movLista.get(linha).getNascimento();
            case 5:
                return movLista.get(linha).getSetor();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int lin, int col) {
        
            return false;
        
    }
    
   public void addRow(Usuario u){
   
       this.movLista.add(u);
       this.fireTableDataChanged();
       
   } 
   
   public void addList(List<Usuario> lista){
   
       this.movLista = lista;
       this.fireTableDataChanged();
   }
    
    
}

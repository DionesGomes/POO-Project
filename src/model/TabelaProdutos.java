/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diones Gomes
 */
public class TabelaProdutos extends AbstractTableModel {

    private List<Produto> movLista = new ArrayList<>();
    private String[] colunas = {"Código", "Nome", "Descricão", "Preço"};

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

        switch (coluna) {

            case 0:
                return movLista.get(linha).getCodigo();
            case 1:
                return movLista.get(linha).getNome();
            case 2:
                return movLista.get(linha).getDescricao();
            case 3:
                return movLista.get(linha).getPreco();

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int lin, int col) {

        return false;

    }

    public void addRow(Produto p) {

        this.movLista.add(p);
        this.fireTableDataChanged();

    }

    public void addList(List<Produto> lista) {

        this.movLista = lista;
        this.fireTableDataChanged();
    }

}

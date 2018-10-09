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
public class TabelaComandasFechadas extends AbstractTableModel {

    private List<Comanda> movLista = new ArrayList<>();
    private String[] colunas = {"Data da Comanda", "Numero da Comanda", "Total"};

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
                return movLista.get(linha).getData();
            case 1:
                return movLista.get(linha).getNumeroComanda();
            case 2:
                return movLista.get(linha).valorTotal();

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int lin, int col) {

        return false;

    }

    public void addRow(Comanda comanda) {

        this.movLista.add(comanda);
        this.fireTableDataChanged();

    }

    public void addList(List<Comanda> lista) {

        this.movLista = lista;
        this.fireTableDataChanged();
    }
}

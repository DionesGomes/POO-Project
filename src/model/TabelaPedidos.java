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
public class TabelaPedidos extends AbstractTableModel {

    private List<Pedido> movLista = new ArrayList<>();
    private String[] colunas = {"Produto", "Quantidade", "Mesa", "Numero do Pedido", "Status"};

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
                return movLista.get(linha).getProduto().getNome();
            case 1:
                return movLista.get(linha).getQuantidade();
            case 2:
                return movLista.get(linha).getMesa();
            case 3:
                return movLista.get(linha).getNumeroPedido();
            case 4:
                if (movLista.get(linha).isStatus() == true) {
                    return "Atendido";
                }
                return "Não atendido";

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int lin, int col) {

        return false;

    }

    public void addRow(Pedido pedido) {

        this.movLista.add(pedido);
        this.fireTableDataChanged();

    }

    public void addList(List<Pedido> lista) {

        this.movLista = lista;
        this.fireTableDataChanged();
    }
}

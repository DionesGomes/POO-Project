/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.github.lanchonete.view;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import main.java.com.github.Lanchonete.model.Usuario;
import main.java.com.github.lanchonete.controller.CadastroUsuarioArquivo;
import main.java.com.github.lanchonete.model.TableUser;

/**
 *
 * @author Diones Gomes
 */
public class ListarContas extends javax.swing.JFrame {

    /**
     * Creates new form ListarContas
     */
    public CadastroUsuarioArquivo cad = new CadastroUsuarioArquivo();
    public TableUser tabela = new TableUser();

    public ListarContas() {
        initComponents();
        incializarTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        listarUsuario = new javax.swing.JTable();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listando contas de usuários");
        setResizable(false);

        listarUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Email", "Telefone", "Data de Dascimento", "Setor", "Senha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(listarUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listarUsuario;
    // End of variables declaration//GEN-END:variables

    private void incializarTabela() {

        try {
            List<Usuario> lista = null;

            lista = cad.listar();

            tabela.addList(lista);
            listarUsuario.setModel(tabela);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão com arquivo",
                    "Mensagem Erro", JOptionPane.ERROR_MESSAGE);

        }
    }
}

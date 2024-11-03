/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package formularios;

import clases.PeriodoContable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import clases.Conexion;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author sarsg
 */
public class CerrarPeriodoContable extends javax.swing.JPanel {

    /**
     * Creates new form CerrarPeriodoContable
     */
    Conexion connect = new Conexion();
    public CerrarPeriodoContable() {
        initComponents();
        llenarComboBoxPeriodo();
    }
    private void llenarComboBoxPeriodo() {
        DefaultComboBoxModel value;
        this.cbPeriodoContable.removeAllItems();
        try {
            cbPeriodoContable.removeAllItems();
            String sentencia = "SELECT * FROM periodo_contable ORDER BY id";
            PreparedStatement sentencia1;
            sentencia1 = null;
            sentencia1 = this.connect.getConexion().prepareCall(sentencia);

            ResultSet rs = sentencia1.executeQuery();
            value = new DefaultComboBoxModel();

            cbPeriodoContable.setModel(value);

            while (rs.next()) {
                PeriodoContable periodoC = new PeriodoContable();

                periodoC.setId(rs.getInt("id"));
                periodoC.setFechaInicio(rs.getDate("fecha_inicio"));
                periodoC.setFechaFin(rs.getDate("fecha_fin"));
                value.addElement(periodoC);

            }
        } catch (SQLException ex) {
            ex.printStackTrace(); //Maneja la excepción SQL.
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbPeriodoContable = new javax.swing.JComboBox<>();
        btnFinalizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PERIODO CONTABLE");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Selecciona periodo a finalizar:");

        cbPeriodoContable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFinalizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(cbPeriodoContable, 0, 226, Short.MAX_VALUE))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(48, 48, 48)
                .addComponent(cbPeriodoContable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar)
                    .addComponent(btnCancelar))
                .addContainerGap(85, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        PeriodoContable periodoSeleccionado = (PeriodoContable) cbPeriodoContable.getSelectedItem();
        if (periodoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Error debe ingresar un periodo contable para realizar la acción.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            PreparedStatement sentencia = this.connect.getConexion().prepareStatement("UPDATE periodo_contable SET cerrado = true WHERE id = ?");
            sentencia.setInt(1, periodoSeleccionado.getId());
            sentencia.executeUpdate();

            JOptionPane.showMessageDialog(this, "Periodo contable finalizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            JFrame topFrame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            if (topFrame != null) {
                topFrame.dispose(); // Cierra el JFrame que contiene este JPanel
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al finalizar el periodo contable.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        cbPeriodoContable.removeItem(periodoSeleccionado);
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        JFrame topFrame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (topFrame != null) {
            topFrame.dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JComboBox<String> cbPeriodoContable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
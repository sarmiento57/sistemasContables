/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package formularios;

import com.formdev.flatlaf.FlatClientProperties;
import clases.Conexion;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author sarsg
 */
public class catalogoCuentas extends javax.swing.JPanel {
    

    Conexion connect = new Conexion();
    
    public catalogoCuentas() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        inicializarColumnas();

        actualizarTabla(tbCuentas);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tbCuentas.getColumnCount(); i++) {
            tbCuentas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
     private void inicializarColumnas() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        for (int i = 0; i < 4; i++) {
            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("Clase de cuenta");
                    break;
                case 1:
                    col.setHeaderValue("Subclasificación");
                    break;
                case 2:
                    col.setHeaderValue("Código");
                    break;
                case 3:
                    col.setHeaderValue("Nombre de cuenta");
                    break;
            }
            tColumnModel.addColumn(col);
        }
        this.tbCuentas.setColumnModel(tColumnModel);
    }
    
    public void actualizarTabla(JTable tabla){
        
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); 
        
        try {
            String sentencia = "SELECT tc.tipo_cuenta, sc.nombre_cuenta AS nombre_subcl, " +
                               "c.idcuenta, c.nombre_cuenta " +
                               "FROM public.cuenta c " +
                               "JOIN public.tipocuenta tc ON c.idtipo = tc.idtipo " +
                               "JOIN public.subclasificacion_cuenta sc ON c.idclasificacion = sc.idclasificacion " +
                               "ORDER BY c.idcuenta;";
            
            PreparedStatement sentencia1;
            sentencia1 = null;
            sentencia1 = this.connect.getConexion().prepareCall(sentencia);
            ResultSet rs = sentencia1.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("tipo_cuenta");
                String subtipo = rs.getString("nombre_subcl");
                int idcuenta = rs.getInt("idcuenta");
                String nombre = rs.getString("nombre_cuenta");
                modelo.addRow(new Object[]{tipo, subtipo, idcuenta, nombre});
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

        lb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCuentas = new javax.swing.JTable();
        btnCrearCuenta = new javax.swing.JToggleButton();
        btnEliminar = new javax.swing.JButton();

        lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb.setText("CATÁLOGO DE CUENTAS");

        tbCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Clase de cuenta", "Subclasificación", "Código", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbCuentas);

        btnCrearCuenta.setText("Registrar cuenta");
        btnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCuentaActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar cuneta");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addComponent(lb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnCrearCuenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearCuenta)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCuentaActionPerformed
    JFrame frame = new JFrame("Registrar Cuentas");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().add(new RegistrarCuenta());
    frame.pack();
    frame.setLocationRelativeTo(this);

    
    frame.setResizable(false);

   
    frame.setAlwaysOnTop(true); 
    frame.setVisible(true);
    }//GEN-LAST:event_btnCrearCuentaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         int filaSeleccionada = tbCuentas.getSelectedRow();
    
    if (filaSeleccionada != -1) {
   
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro que quieres eliminar esta cuenta?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Accede a la columna correcta (índice 2)
                int idcuenta = (int) tbCuentas.getValueAt(filaSeleccionada, 2);
                
                String sentencia = "DELETE FROM cuenta WHERE idcuenta = ?";
                PreparedStatement ps = connect.getConexion().prepareStatement(sentencia);
                ps.setInt(1, idcuenta);
                int filasAfectadas = ps.executeUpdate();
                ps.close();
                
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "La cuenta se eliminó correctamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    
                    DefaultTableModel modeloT = (DefaultTableModel) tbCuentas.getModel();
                    modeloT.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la cuenta para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona una cuenta para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCrearCuenta;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tbCuentas;
    // End of variables declaration//GEN-END:variables
}
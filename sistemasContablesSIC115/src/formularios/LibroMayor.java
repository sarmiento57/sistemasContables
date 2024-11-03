package formularios;

import com.formdev.flatlaf.FlatClientProperties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import clases.Conexion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author sarsg
 */
public class LibroMayor extends javax.swing.JPanel {
    
    
    Conexion connect = new Conexion();
    private DefaultTableModel modelo;


    public LibroMayor() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");

        modelo = (DefaultTableModel) tbLibroMayor.getModel();
        actualizarTabla();

     
        CustomCellRenderer centerRenderer = new CustomCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbLibroMayor.getColumnModel().getColumn(1).setCellRenderer(new CustomCellRenderer());
        tbLibroMayor.getColumnModel().getColumn(2).setCellRenderer(new CustomCellRenderer());

        for (int i = 0; i < tbLibroMayor.getColumnCount(); i++) {
            tbLibroMayor.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void actualizarTabla() {
        modelo.setRowCount(0); 

        try {
           
            String sentencia = "SELECT c.nombre_cuenta, t.debe_trans, t.haber_trans "
                    + "FROM public.cuenta c "
                    + "LEFT JOIN public.transaccion t ON c.idcuenta = t.idcuenta "
                    + "ORDER BY c.idcuenta";
            PreparedStatement sentencia1 = this.connect.getConexion().prepareStatement(sentencia);
            ResultSet rs = sentencia1.executeQuery();

            String cuentaActual = "";
            double totalDebe = 0;
            double totalHaber = 0;

            while (rs.next()) {
                String nombreCuenta = rs.getString("nombre_cuenta");
                double debe = rs.getDouble("debe_trans");
                double haber = rs.getDouble("haber_trans");

              
                if (!cuentaActual.equals(nombreCuenta)) {
                    if (!cuentaActual.isEmpty()) {
                     
                        double saldoCuenta;
                        if (isCuentaDeDeber(cuentaActual)) {
                            saldoCuenta = totalDebe - totalHaber; 
                        } else if (isCuentaDeHaber(cuentaActual)) {
                            saldoCuenta = totalHaber - totalDebe; 
                        } else {
                            saldoCuenta = 0; 
                        }
                        modelo.addRow(new Object[]{"", totalDebe, totalHaber, saldoCuenta});
                    }
                    cuentaActual = nombreCuenta;
                    totalDebe = 0; 
                    totalHaber = 0; 
                    
                   
                    modelo.addRow(new Object[]{"", "", "", ""}); 

                    
                    modelo.addRow(new Object[]{nombreCuenta, "Debe", "Haber", "Saldo"});
                }

               
                totalDebe += debe;
                totalHaber += haber;

               
                modelo.addRow(new Object[]{"", debe, haber, ""});
            }

            
            if (!cuentaActual.isEmpty()) {
                double saldoCuenta;
                if (isCuentaDeDeber(cuentaActual)) {
                    saldoCuenta = totalDebe - totalHaber; 
                } else if (isCuentaDeHaber(cuentaActual)) {
                    saldoCuenta = totalHaber - totalDebe; 
                } else {
                    saldoCuenta = 0; 
                }
                modelo.addRow(new Object[]{cuentaActual, totalDebe, totalHaber, saldoCuenta});
            }

            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    //cuentas que crecen en el debe 
    private boolean isCuentaDeDeber(String nombre) {
        String[] cuentasDeDeber = {
            "Caja", "Bancos", "Cuentas por cobrar", "IVA Credito Fiscal",
            "Anticipos a proveedores", "Alquiler pagado por anticipado",
            "Mobiliario y equipo de oficina", "Depreciación acumulada",
            "Gastos de Personal Técnico", "Gastos de Administración",
            "Gastos de Oficina", "Gastos de Tecnología", "Gastos de venta"
        };
        return Arrays.asList(cuentasDeDeber).contains(nombre);
    }
    //listar cuentas que crecen en el haber
    private boolean isCuentaDeHaber(String nombre) {
        String[] cuentasDeHaber = {
            "Ingresos por Servicios", "Proyectos de Software Personalizado",
            "Mantenimiento y soporte", "Impuestos por pagar",
            "IVA Debito Fiscal", "Anticipos de Clientes",
            "Cuentas por pagar", "Documentos por pagar", "Capital social"
        };
        return Arrays.asList(cuentasDeHaber).contains(nombre);
    }
    
    //agregar borde a la tabla
    private static class CustomCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            c.setBorder(BorderFactory.createEmptyBorder());

           
            if (value != null && (value.toString().equals("Debe") || value.toString().equals("Haber"))) {
               
                c.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)); 
            }

           
            if (column == 2 && value != null && !value.toString().isEmpty()) {
        
                Border currentBorder = c.getBorder();
                Border newBorder = BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY);
                c.setBorder(BorderFactory.createCompoundBorder(newBorder, currentBorder)); 
            }

            return c;
        }
    }








    


    



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLibroMayor = new javax.swing.JTable();

        lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("LIBRO MAYOR");

        tbLibroMayor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cuenta", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbLibroMayor);
        if (tbLibroMayor.getColumnModel().getColumnCount() > 0) {
            tbLibroMayor.getColumnModel().getColumn(0).setResizable(false);
            tbLibroMayor.getColumnModel().getColumn(1).setResizable(false);
            tbLibroMayor.getColumnModel().getColumn(2).setResizable(false);
            tbLibroMayor.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibroMayor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibroMayor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibroMayor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibroMayor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibroMayor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tbLibroMayor;
    // End of variables declaration//GEN-END:variables
}

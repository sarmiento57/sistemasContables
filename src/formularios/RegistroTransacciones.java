
package formularios;

import com.formdev.flatlaf.FlatClientProperties;
import clases.*;
import java.sql.PreparedStatement;
import javax.swing.DefaultComboBoxModel;
import clases.Conexion;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import sistemacontable.SubCuenta;

/**
 *
 * @author Carlos Escobar - ES21001
 */
public class RegistroTransacciones extends javax.swing.JPanel {

    /**
     * Creates new form RegistroTransacciones
     */
    Conexion connect = new Conexion();
    
    public RegistroTransacciones() {
        initComponents();
        actualizarIdTransaccion();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        
        llenarComboBox();
        periodoContable();
        actualizarTabla(tbTransaccion);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tbTransaccion.getColumnCount(); i++) {
            tbTransaccion.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void actualizarTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        
        modelo.setRowCount(0); // Limpia la tabla

        try {
            String sentencia = "SELECT * FROM transaccion ORDER BY idtransaccion";
            
            PreparedStatement sentencia1;
            
            sentencia1 = null;
            sentencia1 = this.connect.getConexion().prepareCall(sentencia);
            
            ResultSet rs = sentencia1.executeQuery();
            
            while (rs.next()) {
                int idtransaccion = rs.getInt("idtransaccion");
                String fecha = rs.getString("fecha_transaccion");
                int idcuenta = rs.getInt("idcuenta");
                String nombre = rs.getString("nombre_cuenta");
                String descripcion = rs.getString("descripcion");
                double debe = rs.getDouble("debe_trans");
                double haber = rs.getDouble("haber_trans");
//                modelo.addRow(new Object[]{fecha, idcuenta, nombre, descripcion, debe, haber});
                modelo.addRow(new Object[]{idtransaccion, fecha, nombre, descripcion, debe, haber});
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdTransaccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbCuenta = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jfecha = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDebe = new javax.swing.JTextField();
        txtHaber = new javax.swing.JTextField();
        btnAgregarTransaccion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTransaccion = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFinicio = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtFfin = new javax.swing.JLabel();

        lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("REGISTRO DE TRANSACCIONES");

        jLabel1.setText("Código de Transacción:");

        txtIdTransaccion.setEditable(false);
        txtIdTransaccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdTransaccionKeyTyped(evt);
            }
        });

        jLabel2.setText("Cuenta:");

        cbCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Descripción:");

        jLabel4.setText("Fecha:");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Monto ($):");

        jLabel6.setText("Debe");

        jLabel7.setText("Haber");

        txtDebe.setText("0.0");

        txtHaber.setText("0.0");

        btnAgregarTransaccion.setText("Agregar Transacción");
        btnAgregarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTransaccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarTransaccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(84, 84, 84))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(55, 55, 55))
                            .addComponent(txtDebe))
                        .addGap(98, 98, 98)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(83, 83, 83))
                            .addComponent(txtHaber))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDebe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHaber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarTransaccion)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        tbTransaccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Cuenta", "Descripción", "Debe", "Haber"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbTransaccion);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Ajustar Transacción");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Listado de transacciónes");

        jLabel9.setText("Periodo Contable:");

        jLabel11.setText("Hasta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(127, 127, 127))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(186, 186, 186))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addGap(288, 288, 288))
                            .addComponent(cbCuenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdTransaccion)
                            .addComponent(txtDescripcion))
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jfecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFinicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFfin)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminar)
                        .addComponent(btnLimpiar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtFinicio)
                        .addComponent(jLabel11)
                        .addComponent(txtFfin)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdTransaccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdTransaccionKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdTransaccionKeyTyped
    
    private static final Set<String> CUENTAS_CON_IVA = new HashSet<>(Arrays.asList(
            "Cuentas por cobrar", "Mobiliario y equipo de oficina", "Cuentas por pagar",
            "Documentos por pagar", "Ingresos por Servicios", "Proyectos de Software Personalizado",
            "Mantenimiento y soporte", "Gastos de Administración", "Gastos de Oficina",
            "Gastos de Tecnología", "Gastos de venta"
    ));
    
    
    private void btnAgregarTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTransaccionActionPerformed
        if (txtIdTransaccion.getText().isEmpty() || txtDescripcion.getText().isEmpty() || this.jfecha.getDate() == null
                || (txtDebe.getText().isEmpty() && txtHaber.getText().isEmpty())) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isNumeric(txtDebe.getText()) || !isNumeric(txtHaber.getText())) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos en los campos 'Debe' y 'Haber'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int valorId = Integer.parseInt(txtIdTransaccion.getText());
        String valorCb1 = cbCuenta.getSelectedItem().toString();
        int valorNumericoCb1 = Integer.parseInt(valorCb1.split("-")[0].trim());
        String valorTxt0 = valorCb1.split("-")[1].trim();
        String valorTxt1 = txtDescripcion.getText();
        double valorTxt2 = Double.parseDouble(txtDebe.getText());
        double valorTxt3 = Double.parseDouble(txtHaber.getText());

        try {
            connect.conectar();
            String sentencia = "INSERT INTO transaccion (idtransaccion, idcuenta, nombre_cuenta, descripcion, fecha_transaccion, debe_trans, haber_trans) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.connect.getConexion().prepareStatement(sentencia);
            java.sql.Date fecha_trans = new java.sql.Date(this.jfecha.getDate().getTime());

            ps.setInt(1, valorId);
            ps.setInt(2, valorNumericoCb1);
            ps.setString(3, valorTxt0);
            ps.setString(4, valorTxt1);
            ps.setDate(5, fecha_trans);
            ps.setDouble(6, valorTxt2);
            ps.setDouble(7, valorTxt3);

            ps.executeUpdate();

            // obtener las ultimas 3 transaccione spara validar los mensajes
            String consultaUltimasTransacciones = "SELECT debe_trans, haber_trans FROM transaccion ORDER BY idtransaccion DESC LIMIT 3";
            PreparedStatement psConsulta = this.connect.getConexion().prepareStatement(consultaUltimasTransacciones);
            ResultSet rs = psConsulta.executeQuery();

            double totalDebe2 = 0.0, totalHaber2 = 0.0;
            double totalDebe3 = 0.0, totalHaber3 = 0.0;
            int transaccionesContadas = 0;

            while (rs.next()) {
                double debe = rs.getDouble("debe_trans");
                double haber = rs.getDouble("haber_trans");
                //acumula el total debe y hahaber de las ultimas 2 transacciones
                if (transaccionesContadas < 2) { 
                    totalDebe2 += debe;
                    totalHaber2 += haber;
                }
                //acumula el total de las ultimas 3 transacciones
                totalDebe3 += debe; 
                totalHaber3 += haber;
                transaccionesContadas++;
            }

           
            if ((transaccionesContadas >= 2 && totalDebe2 == totalHaber2)
                    || (transaccionesContadas == 3 && totalDebe3 == totalHaber3)) {
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente. Se cumplió partida doble.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                        "Datos guardados correctamente.");
            }


            boolean necesitaIVA = CUENTAS_CON_IVA.contains(valorTxt0);

            if (necesitaIVA) {
                double iva = (valorTxt2 + valorTxt3) * 0.13;
                String cuentaIVA = valorTxt1.toLowerCase().contains("compra") ? "IVA Crédito Fiscal" : "IVA Débito Fiscal";
                double debeIVA = valorTxt1.toLowerCase().contains("compra") ? iva : 0.0;
                double haberIVA = valorTxt1.toLowerCase().contains("venta") ? iva : 0.0;

                String descripcionIVA = "IVA aplicado a la transacción " + valorId;

                String sentenciaIVA = "INSERT INTO transaccion (idtransaccion, idcuenta, nombre_cuenta, descripcion, fecha_transaccion, debe_trans, haber_trans) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement psIVA = this.connect.getConexion().prepareStatement(sentenciaIVA);

                psIVA.setInt(1, valorId + 1);
                psIVA.setInt(2, valorNumericoCb1);
                psIVA.setString(3, cuentaIVA);
                psIVA.setString(4, descripcionIVA);
                psIVA.setDate(5, fecha_trans);
                psIVA.setDouble(6, debeIVA);
                psIVA.setDouble(7, haberIVA);

                psIVA.executeUpdate();

                JOptionPane.showMessageDialog(this, "Transacción de IVA registrada automáticamente con ID: " + (valorId + 1), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                completarPartidaDoble();
 

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la transacción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        actualizarTabla(tbTransaccion);
        actualizarIdTransaccion();
    }//GEN-LAST:event_btnAgregarTransaccionActionPerformed
    
    private void actualizarIdTransaccion() {
        int nuevoId = obtenerNuevoIdTransaccion();  
        txtIdTransaccion.setText(String.valueOf(nuevoId));  
    }

    private int obtenerNuevoIdTransaccion() {
        int nuevoId = 1;  

        try {
            connect.conectar();

           
            String consultaUltimoId = "SELECT MAX(idtransaccion) AS max_id FROM transaccion";
            PreparedStatement psConsulta = this.connect.getConexion().prepareStatement(consultaUltimoId);
            ResultSet rs = psConsulta.executeQuery();

            if (rs.next()) {
               
                nuevoId = rs.getInt("max_id") + 1;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener el último ID: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return nuevoId; 
    }

    
    private void completarPartidaDoble() {
        try {
            
            String query = "SELECT MAX(idtransaccion) FROM transaccion";
            PreparedStatement ps = this.connect.getConexion().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int idUltimaTransaccion = 0;
            if (rs.next()) {
                idUltimaTransaccion = rs.getInt(1);
            }
            if (idUltimaTransaccion == 0) {
              
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                        "Por favor, guarde la transacción para cumplir partida doble.");
                return; 
            }

            query = "SELECT idtransaccion, debe_trans, haber_trans, descripcion FROM transaccion WHERE idtransaccion = ? OR idtransaccion = ? ORDER BY idtransaccion DESC";
            ps = this.connect.getConexion().prepareStatement(query);
            ps.setInt(1, idUltimaTransaccion - 1);
            ps.setInt(2, idUltimaTransaccion);
            rs = ps.executeQuery();
     
            double debeTrans1 = 0.0, haberTrans1 = 0.0;
            double debeTrans2 = 0.0, haberTrans2 = 0.0;
            String descripcion = "";

            
            if (rs.next()) {
                debeTrans1 = rs.getDouble("debe_trans");
                haberTrans1 = rs.getDouble("haber_trans");
               
                if (rs.getInt("idtransaccion") == idUltimaTransaccion - 1) {
                    descripcion = rs.getString("descripcion");
                }
            }
            if (rs.next()) {
                debeTrans2 = rs.getDouble("debe_trans");
                haberTrans2 = rs.getDouble("haber_trans");
              
                if (rs.getInt("idtransaccion") == idUltimaTransaccion - 1) {
                    descripcion = rs.getString("descripcion");
                }
            }
  
            double totalDebe = debeTrans1 + debeTrans2;
            double totalHaber = haberTrans1 + haberTrans2;

          
            txtIdTransaccion.setText(String.valueOf(idUltimaTransaccion + 1)); 
            txtDescripcion.setText(descripcion); 
       
            if (haberTrans2 > 0) {
               
                txtDebe.setText(String.valueOf(totalDebe + totalHaber));
                txtHaber.setText("0.0"); 
            } else if (debeTrans2 > 0) {
             
                txtHaber.setText(String.valueOf(totalDebe + totalHaber));
                txtDebe.setText("0.0");  
            } else {
               
                txtDebe.setText("0.0");
                txtHaber.setText("0.0");
            }
                 
            if (totalDebe != totalHaber) {      
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                        "¡Atención! Para cumplir con la partida doble, por favor elija una cuenta para guardar la transacción.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al completar la partida doble: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    
    private boolean isNumeric(String str) {
    if (str == null || str.isEmpty()) {
        return false;
    }
    try {
        Double.parseDouble(str);
    } catch (NumberFormatException e) {
        return false;
    }
    return true; 
    }
    
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

        txtDescripcion.setText("");
        cbCuenta.setSelectedIndex(0);
        jfecha.setDate(null);
        txtDebe.setText("0.0");
        txtHaber.setText("0.0");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tbTransaccion.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            int idtransaccion = (int) tbTransaccion.getValueAt(filaSeleccionada, 0);

            // Elimina la fila de la base de datos
            try {
                String sentencia = "DELETE FROM transaccion WHERE idtransaccion = ?";
                PreparedStatement ps = connect.getConexion().prepareStatement(sentencia);
                ps.setInt(1, idtransaccion);
                ps.executeUpdate();
                ps.close();
                
                JOptionPane.showMessageDialog(this, "La transacción se eliminó correctamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);

                // Elimina la fila de la Tabla
                DefaultTableModel modeloT = (DefaultTableModel) tbTransaccion.getModel();
                modeloT.removeRow(filaSeleccionada);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar eliminar la transacción.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
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
            java.util.logging.Logger.getLogger(RegistroTransacciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroTransacciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroTransacciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroTransacciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroTransacciones().setVisible(true);
            }
        });
    }
    
    private void llenarComboBox() {
        DefaultComboBoxModel value;
        
        this.cbCuenta.removeAllItems();
        
        try {
            cbCuenta.removeAllItems();
            String sentencia = "SELECT idcuenta, nombre_cuenta FROM cuenta ORDER BY idcuenta";
            PreparedStatement sentencia1;
            sentencia1 = null;
            sentencia1 = this.connect.getConexion().prepareCall(sentencia);
            
            ResultSet rs = sentencia1.executeQuery();
            value = new DefaultComboBoxModel();
            
            cbCuenta.setModel(value);
            
            while (rs.next()) {
                value.addElement(rs.getInt("idcuenta") + "-" + rs.getString("nombre_cuenta"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); //Maneja la excepción SQL.
        }
    }
    
    public void periodoContable() {
     

        try {
            // Obtener fechas de la base de datos
            String consultaFechas = "SELECT fecha_inicio, fecha_fin FROM periodo_contable";
            PreparedStatement sentenciaFechas = this.connect.getConexion().prepareStatement(consultaFechas);
            ResultSet rsFechas = sentenciaFechas.executeQuery();

            if (rsFechas.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMM yyyy");

                java.sql.Date fechaInicio = rsFechas.getDate("fecha_inicio");
                String fechaInicioStr = sdf.format(fechaInicio);

                java.sql.Date fechaFin = rsFechas.getDate("fecha_fin");
                String fechaFinStr = sdf.format(fechaFin);

                txtFinicio.setText(fechaInicioStr);
                txtFfin.setText(fechaFinStr);
            }
            rsFechas.close();

            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarTransaccion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jfecha;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tbTransaccion;
    private javax.swing.JTextField txtDebe;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JLabel txtFfin;
    private javax.swing.JLabel txtFinicio;
    private javax.swing.JTextField txtHaber;
    private javax.swing.JTextField txtIdTransaccion;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package formularios;
import clases.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import sistemacontable.SubCuenta;
/**
 *
 * @author Carlos Escobar - ES21001
 */
public class RegistroServicios extends javax.swing.JPanel {

    /**
     * Creates new form RegistroServicios
     */
    Conexion connect = new Conexion();
    CostosIndirectos costosIndirectos = new CostosIndirectos();
    
    public RegistroServicios() {
        initComponents();
        llenarComboBox();
        actualizarTabla(tbServicio);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tbServicio.getColumnCount(); i++) {
            tbServicio.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SpNumServicio = new javax.swing.JSpinner();
        cbTipoServicio = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        SpCantEmpleados = new javax.swing.JSpinner();
        spMeses = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtCostoTotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtManoObra = new javax.swing.JTextField();
        btnCalcularCosto = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbServicio = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnCostosIndirectos = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SOLICITUD DE SERVICIO");

        jLabel2.setText("Número de servicio:");

        cbTipoServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Tipo de servicio:");

        jLabel4.setText("Cliente:");

        jLabel5.setText("Desarroladores:");

        jLabel8.setText("Tiempo en meses:");

        jLabel6.setText("Descripción del servicio:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Precio Venta:         $");

        txtCostoTotal.setEditable(false);
        txtCostoTotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Costo Total:           $");

        txtPrecioVenta.setEditable(false);
        txtPrecioVenta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Mano de Obra:      $");

        txtManoObra.setEditable(false);
        txtManoObra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(txtPrecioVenta)
                    .addComponent(txtManoObra))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtManoObra))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCostoTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioVenta)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btnCalcularCosto.setText("Calcular Costo");
        btnCalcularCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularCostoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        tbServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre Cliente", "Servicio", "N° Trabajadores", "Descripción", "N° Meses", "Mano Obra", "Costo Total", "Precio Venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbServicio);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Listado de servicios");

        btnCostosIndirectos.setText("Registrar costos indirectos");
        btnCostosIndirectos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCostosIndirectosActionPerformed(evt);
            }
        });

        jLabel10.setText("Porcentaje CIF:");

        jLabel11.setText("32%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                .addGap(107, 107, 107))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SpNumServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(178, 178, 178))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbTipoServicio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(SpCantEmpleados, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spMeses, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnCalcularCosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCostosIndirectos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(144, 144, 144))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpNumServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbTipoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnCostosIndirectos)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(SpCantEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCalcularCosto))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(134, 134, 134)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalcularCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularCostoActionPerformed
        //recuperando datos de la BD
        String query = "SELECT cargo, costoreal FROM public.empleados";
    double costoRealDesarrollador = 0.0;
    double costoRealAnalista = 0.0;
    double costoRealCoordinador = 0.0;

    try {
        PreparedStatement stmt = connect.getConexion().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String cargo = rs.getString("cargo");
            double costoReal = rs.getDouble("costoreal");

            switch (cargo) {
                case "Desarrollador":
                    costoRealDesarrollador = costoReal;
                    break;
                case "Coordinador de proyecto":
                    costoRealCoordinador = costoReal;
                    break;
                case "Análista de sistema":
                    costoRealAnalista = costoReal;
                    break;
                default:
                    System.out.println("Cargo no reconocido: " + cargo);
                    break;
            }
        }
        rs.close();
        stmt.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    int numServicio = (int) SpNumServicio.getValue();
    String tipoServicio = cbTipoServicio.getSelectedItem().toString();
    String cliente = txtCliente.getText();
    int cantEmpleados = (int) SpCantEmpleados.getValue();
    String descripcion = txtDescripcion.getText();
    double manoObraDirecta = 0, costoIdirectoPor = 0;
    float total = 0, totalSinIva = 0, iva = 0;
    int cantmeses = (int) spMeses.getValue();
    double costosIdirectos = costosIndirectos.getCostosIndirectos();
    

    // Imprime el valor de tipoServicio para verificar el contenido
    System.out.println("Valor de tipoServicio: " + costosIdirectos);

    //Validando campis   
    if (numServicio == 0 || cantmeses == 0 || tipoServicio.isEmpty() || cliente.isEmpty() || descripcion.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
    } else {
        try {
            switch (tipoServicio) {
                case "Desarrollo de software personalizado":
                    manoObraDirecta = (cantEmpleados * costoRealDesarrollador) + (1 * costoRealAnalista) + (1 * costoRealCoordinador);
                    costoIdirectoPor = (costosIdirectos * cantmeses) / (manoObraDirecta + (costosIdirectos * cantmeses));
                    totalSinIva = (float) (manoObraDirecta + (costosIdirectos * costoIdirectoPor));
                    iva = (float) (totalSinIva * 0.13);
                    total = totalSinIva + iva;
                    break;

                case "Aplicaciones de gestion empresarial":
                    manoObraDirecta = (cantEmpleados * costoRealDesarrollador) + (1 * costoRealAnalista) + (1 * costoRealCoordinador);
                    costoIdirectoPor = (costosIdirectos * cantmeses) / (manoObraDirecta + (costosIdirectos * cantmeses));
                    totalSinIva = (float) (manoObraDirecta + (costosIdirectos * costoIdirectoPor));
                    iva = (float) (totalSinIva * 0.13);
                    total = totalSinIva + iva;
                    break;

                case "Consultoria tecnologica":
                    manoObraDirecta = (1 * costoRealDesarrollador);
                    costoIdirectoPor = ((costosIdirectos * cantmeses) / (manoObraDirecta + (costosIdirectos * cantmeses)));
                    totalSinIva = (float) (manoObraDirecta + (costosIdirectos * costoIdirectoPor));
                    iva = (float) (totalSinIva * 0.13);
                    total = totalSinIva + iva;
                    break;

                case "Mantenimiento y soporte tecnico":
                    manoObraDirecta = (1 * costoRealDesarrollador);
                    costoIdirectoPor = ((costosIdirectos * cantmeses) / (manoObraDirecta + (costosIdirectos * cantmeses)));
                    totalSinIva = (float) (manoObraDirecta + (costosIdirectos * costoIdirectoPor));
                    iva = (float) (totalSinIva * 0.13);
                    total = totalSinIva + iva;
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Tipo de servicio no reconocido: " + tipoServicio);
                    break;
            }
            txtManoObra.setText(String.format("%.2f", manoObraDirecta));
            txtCostoTotal.setText(String.format("%.2f", totalSinIva));
            txtPrecioVenta.setText(String.format("%.2f", total));
            btnCalcularCosto.setEnabled(false);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error, verifique los campos numéricos.");
        }
    }
    }//GEN-LAST:event_btnCalcularCostoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Obtener el servicio seleccionado
        Object selectedItem = cbTipoServicio.getSelectedItem();
        if (selectedItem == null || !(selectedItem instanceof SubCuenta)) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un servicio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SubCuenta selectedService = (SubCuenta) selectedItem;
        int valorNumericoCb1 = selectedService.getIdClasificacion();

        // Obtener otros valores de entrada
        String servicio = cbTipoServicio.getSelectedItem().toString();
        int numServicio = (int) SpNumServicio.getValue();
        String cliente = txtCliente.getText().trim();
        int cantEmpleados = (int) SpCantEmpleados.getValue();
        String descripcion = txtDescripcion.getText().trim();
        String txtCostoT = txtCostoTotal.getText().trim();
        String precioVenta = txtPrecioVenta.getText().trim();
        String manoObra = txtManoObra.getText().trim();
        int numMeses = (int) spMeses.getValue();

        // Validando los campos
        if (numServicio == 0 || cantEmpleados == 0 || numMeses == 0 || cliente.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtCostoT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe calcular el costo total antes.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            float costoTotal = Float.parseFloat(txtCostoT);
            float precioTotal = Float.parseFloat(precioVenta);
            double manoObraTotal = Double.parseDouble(manoObra);
            String sentencia = "INSERT INTO servicios (id, idservicio, nombre_cliente, cantEmpleados, descripcion, costoTotal, cantidad_meses, servicio, precioventa, mano_obra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.connect.getConexion().prepareStatement(sentencia);

            // Configuración de los parámetros de la consulta
            ps.setInt(1, numServicio);
            ps.setInt(2, valorNumericoCb1);
            ps.setString(3, cliente);
            ps.setInt(4, cantEmpleados);
            ps.setString(5, descripcion);
            ps.setFloat(6, costoTotal);
            ps.setInt(7, numMeses);
            ps.setString(8, servicio);
            ps.setFloat(9, precioTotal);
            ps.setDouble(10, manoObraTotal);
            
            

            // Ejecutar la actualización
            ps.executeUpdate();
            actualizarTabla(tbServicio);

            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar campos
            SpNumServicio.setValue(0);
            cbTipoServicio.setSelectedIndex(-1); // Desmarcar selección
            txtDescripcion.setText("");
            SpCantEmpleados.setValue(0);
            txtCliente.setText("");
            txtCostoTotal.setText("");
            txtPrecioVenta.setText("");
            txtManoObra.setText("");
            btnCalcularCosto.setEnabled(true);
            spMeses.setValue(0);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error, verifique los campos numéricos.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCostosIndirectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostosIndirectosActionPerformed
        JFrame frame = new JFrame("Registrar Costos Indirectos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new CostosIndirectos());
        frame.pack();
        frame.setLocationRelativeTo(this);

        frame.setResizable(false);

        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }//GEN-LAST:event_btnCostosIndirectosActionPerformed
    
    public void actualizarTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        try {
            String sentencia = "SELECT nombre_cliente, cantempleados, descripcion, costototal, cantidad_meses, servicio, precioventa, mano_obra FROM servicios ORDER BY nombre_cliente";
            PreparedStatement sentencia1;
            sentencia1 = null;
            sentencia1 = this.connect.getConexion().prepareCall(sentencia);
            ResultSet rs = sentencia1.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre_cliente");
                String servicio = rs.getString("servicio");
                int cantidadEmpleado = rs.getInt("cantempleados");
                String descripcion = rs.getString("descripcion");
                int meses = rs.getInt("cantidad_meses");
                double costo = rs.getDouble("costototal");
                double precio = rs.getDouble("precioventa");
                double manoObra = rs.getDouble("mano_obra");
                
                
                String costoAprox = String.format("%.2f", costo);
                String precioAprox = String.format("%.2f", precio);
                String manoAprox = String.format("%.2f", manoObra);
                
 
                modelo.addRow(new Object[]{nombre, servicio, cantidadEmpleado, descripcion, meses,manoAprox, costoAprox, precioAprox});

            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void llenarComboBox() {
        DefaultComboBoxModel value;
        this.cbTipoServicio.removeAllItems();
        try {
            cbTipoServicio.removeAllItems();
            String sentencia = "select * from tiposervicio order by idservicio";
            PreparedStatement sentencia1;
            sentencia1 = null;
            sentencia1 = this.connect.getConexion().prepareCall(sentencia);
            ResultSet rs = sentencia1.executeQuery();
            value = new DefaultComboBoxModel();
            cbTipoServicio.setModel(value);

            while (rs.next()) {
                SubCuenta aux = new SubCuenta();
                aux.setIdClasificacion(rs.getInt("idservicio"));
                aux.setNombre(rs.getString("nombre_servicio"));
                value.addElement(aux);

            }
            cbTipoServicio.addActionListener(evt -> {
                SubCuenta selectedService = (SubCuenta) cbTipoServicio.getSelectedItem(); 
                if (selectedService != null) {
                    String serviceName = selectedService.getNombre(); 

                    if (serviceName.equalsIgnoreCase("Consultoria tecnologica")
                            || serviceName.equalsIgnoreCase("Mantenimiento y soporte tecnico")) {

                        SpCantEmpleados.setEnabled(false);
                        SpCantEmpleados.setValue(1);
                    } else {
                        SpCantEmpleados.setEnabled(true);
                        SpCantEmpleados.setValue(0);
                    }
                }
            });

        } catch (SQLException ex) {
            ex.printStackTrace(); //Maneja la excepción SQL.
        }
    }








    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner SpCantEmpleados;
    private javax.swing.JSpinner SpNumServicio;
    private javax.swing.JButton btnCalcularCosto;
    private javax.swing.JButton btnCostosIndirectos;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbTipoServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JSpinner spMeses;
    private javax.swing.JTable tbServicio;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtManoObra;
    private javax.swing.JTextField txtPrecioVenta;
    // End of variables declaration//GEN-END:variables
}

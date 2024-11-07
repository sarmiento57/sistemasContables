/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package formularios;
import clases.Conexion;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import sistemacontable.SubCuenta;
/**
 *
 * @author Carlos Escobar - ES21001
 */
public class PlanillaEmpleado extends javax.swing.JPanel {

    /**
     * Creates new form PlanillaEmpleado
     */
    Conexion connect = new Conexion();
    private HashMap<String, Double> cargoSalarios = new HashMap<>();
    private HashMap<String, Double> diasLaborales = new HashMap<>();
    private HashMap<String, Double> horasLaboradas = new HashMap<>();
    private HashMap<String, Double> recargos = new HashMap<>();
    private HashMap<String, Double> isssPor = new HashMap<>();
    private HashMap<String, Double> afpPor = new HashMap<>();
    private HashMap<String, Double> incafPor = new HashMap<>();
    
    private double septimoDia;
    private double vaca;
    private double aguinaldo;

    
    public PlanillaEmpleado() {
        initComponents();
        actualizarTabla(tbEmpleados);
        LlenarComboBoxCargo();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tbEmpleados.getColumnCount(); i++) {
            tbEmpleados.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }


    private void LlenarComboBoxCargo() {
        DefaultComboBoxModel value;
        this.cbCargo.removeAllItems();
        cargoSalarios.clear(); 
        diasLaborales.clear(); 
        horasLaboradas.clear(); 
        recargos.clear(); 

        try {
            String sentencia = "SELECT nombre_cargo, salario_nominal, dias_laborados, horas_laborados, recargo, isss, afp, incaf FROM cargo ORDER BY idservicio";
            PreparedStatement sentencia1 = this.connect.getConexion().prepareCall(sentencia);
            ResultSet rs = sentencia1.executeQuery();
            value = new DefaultComboBoxModel();
            cbCargo.setModel(value);

            while (rs.next()) {
                String nombreCargo = rs.getString("nombre_cargo");
                double salarioNominal = rs.getDouble("salario_nominal");
                double dias = rs.getDouble("dias_laborados");
                double horas = rs.getDouble("horas_laborados");
                double recargo = rs.getDouble("recargo");
                double isss = rs.getDouble("isss");
                double afp = rs.getDouble("afp");
                double incaf = rs.getDouble("incaf");

               
                cargoSalarios.put(nombreCargo, salarioNominal);
                diasLaborales.put(nombreCargo, dias);
                horasLaboradas.put(nombreCargo, horas);
                recargos.put(nombreCargo, recargo);
                isssPor.put(nombreCargo, isss);
                afpPor.put(nombreCargo, afp);
                incafPor.put(nombreCargo, incaf);

                value.addElement(nombreCargo);
            }

            rs.close();
            sentencia1.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }
    }

    
    private double obtenerSalarioPorCargo(String cargo) {
     
        if (cargoSalarios.containsKey(cargo)) {
            return cargoSalarios.get(cargo);
        } else {
            JOptionPane.showMessageDialog(this, "Cargo no encontrado. Verifique la selección.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    
    private double obtenerDiasLaboradosPorCargo(String cargo) {
      
        if (diasLaborales.containsKey(cargo)) {
            return diasLaborales.get(cargo);
        } else {
            JOptionPane.showMessageDialog(this, "Cargo no encontrado. Verifique la selección.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private double obtenerHorasLaboradasPorCargo(String cargo) {
       
        if (horasLaboradas.containsKey(cargo)) {
            return horasLaboradas.get(cargo);
        } else {
            JOptionPane.showMessageDialog(this, "Cargo no encontrado. Verifique la selección.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private double obtenerRecargoPorCargo(String cargo) {
       
        if (recargos.containsKey(cargo)) {
            return recargos.get(cargo);
        } else {
            JOptionPane.showMessageDialog(this, "Cargo no encontrado. Verifique la selección.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    
    private double obtenerIsssPorCargo(String cargo) {

        if (isssPor.containsKey(cargo)) {
            return isssPor.get(cargo);
        } else {
            JOptionPane.showMessageDialog(this, "Cargo no encontrado. Verifique la selección.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    
    private double obtenerAfpPorCargo(String cargo) {

        if (afpPor.containsKey(cargo)) {
            return afpPor.get(cargo);
        } else {
            JOptionPane.showMessageDialog(this, "Cargo no encontrado. Verifique la selección.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    
    private double obtenerIncafPorCargo(String cargo) {

        if (incafPor.containsKey(cargo)) {
            return incafPor.get(cargo);
        } else {
            JOptionPane.showMessageDialog(this, "Cargo no encontrado. Verifique la selección.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }



    
public void actualizarTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); 

        try {
            String sentencia = "SELECT idempleado, cargo, nombres, apellidos, isss, afp, incaf, salario, costoReal, cant_meses, septimo_dia, vacaciones, aguinaldo FROM empleados ORDER BY idempleado";
            PreparedStatement sentencia1;
            sentencia1 = null;
            sentencia1 = this.connect.getConexion().prepareCall(sentencia);
            ResultSet rs = sentencia1.executeQuery();
            while (rs.next()) {
                String dui = rs.getString("idempleado");
                String cargo = rs.getString( "cargo");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");        
                String isss = rs.getString("isss");
                String afp = rs.getString("afp");
                String incaf = rs.getString("incaf");
                double salario = rs.getDouble("salario");
                String costoReal = rs.getString("costoReal");
                int cantMeses = rs.getInt("cant_meses");
                double septimoDia = rs.getDouble("septimo_dia");
                double vacaciones = rs.getDouble("vacaciones");
                double aguinaldo = rs.getDouble("aguinaldo");
                
                septimoDia = new BigDecimal(septimoDia).setScale(2, RoundingMode.HALF_UP).doubleValue();
                vacaciones = new BigDecimal(vacaciones).setScale(2, RoundingMode.HALF_UP).doubleValue();
                aguinaldo = new BigDecimal(aguinaldo).setScale(2, RoundingMode.HALF_UP).doubleValue();
                  

                modelo.addRow(new Object[]{dui,cargo, nombres, apellidos, cantMeses,vacaciones,aguinaldo, isss, afp, incaf, salario, costoReal});

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

        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEmpleados = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDui = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnCalcularPrestaciones = new javax.swing.JButton();
        txtIsss = new javax.swing.JTextField();
        txtIncaf = new javax.swing.JTextField();
        txtAfp = new javax.swing.JTextField();
        txtPrestaciones = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbCargo = new javax.swing.JComboBox<>();
        btnPuesto = new javax.swing.JButton();
        spMeses = new javax.swing.JSpinner();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PLANILLA GENERAL");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jLabel2.setText("DUI:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tbEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DUI", "Puesto", "Nombre", "Apellidos", "Meses trabajados", "Vacaciónes", "Aguinaldo", "ISSS", "AFP", "INCAF", "Salario diario", "Salario mensual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbEmpleados);
        if (tbEmpleados.getColumnModel().getColumnCount() > 0) {
            tbEmpleados.getColumnModel().getColumn(5).setMinWidth(80);
            tbEmpleados.getColumnModel().getColumn(5).setMaxWidth(80);
            tbEmpleados.getColumnModel().getColumn(6).setMinWidth(80);
            tbEmpleados.getColumnModel().getColumn(6).setMaxWidth(80);
            tbEmpleados.getColumnModel().getColumn(7).setMinWidth(50);
            tbEmpleados.getColumnModel().getColumn(7).setMaxWidth(50);
            tbEmpleados.getColumnModel().getColumn(8).setMinWidth(50);
            tbEmpleados.getColumnModel().getColumn(8).setMaxWidth(50);
            tbEmpleados.getColumnModel().getColumn(9).setMinWidth(50);
            tbEmpleados.getColumnModel().getColumn(9).setMaxWidth(50);
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Datos Personales");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Nombres:");

        jLabel5.setText("DUI:");

        jLabel6.setText("Apellidos:");

        jLabel7.setText("Meses trabajados:");

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        txtDui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDuiKeyTyped(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Calculo de prestaciones");

        jLabel10.setText("ISSS:");

        jLabel11.setText("AFP:");

        jLabel12.setText("INCAF:");

        jLabel13.setText("Costo real:");

        btnCalcularPrestaciones.setText("Calcular Prestaciones");
        btnCalcularPrestaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularPrestacionesActionPerformed(evt);
            }
        });

        txtIsss.setEditable(false);
        txtIsss.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIsss.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtIncaf.setEditable(false);
        txtIncaf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIncaf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtAfp.setEditable(false);
        txtAfp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAfp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPrestaciones.setEditable(false);
        txtPrestaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPrestaciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(237, 237, 237)
                                .addComponent(btnCalcularPrestaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAfp)
                                    .addComponent(txtIsss))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIncaf)
                                    .addComponent(txtPrestaciones)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addGap(223, 223, 223)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIsss, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(txtAfp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIncaf, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(txtPrestaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularPrestaciones)
                    .addComponent(btnAgregar))
                .addContainerGap())
        );

        jLabel8.setText("Cargo:  ");

        cbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnPuesto.setText("Agregar puesto");
        btnPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuestoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDui)
                            .addComponent(cbCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido)
                                    .addComponent(spMeses)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(90, 90, 90)
                                .addComponent(btnPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtDui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(spMeses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnEliminar)
                    .addComponent(btnPuesto))
                .addGap(69, 69, 69)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnBuscar))
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int[] filasSeleccionadas = tbEmpleados.getSelectedRows();

        if (filasSeleccionadas.length == 0) {
            JOptionPane.showMessageDialog(this, "No se han seleccionado empleados para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el empleado seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                connect.conectar();
                String sentencia = "DELETE FROM empleados WHERE idempleado = ?";
                PreparedStatement ps = this.connect.getConexion().prepareStatement(sentencia);

                for (int fila : filasSeleccionadas) {
                    String dui = tbEmpleados.getValueAt(fila, 0).toString();
                    ps.setString(1, dui);
                    ps.executeUpdate();
                }

                JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla(tbEmpleados);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String cargoSeleccionado = (String) cbCargo.getSelectedItem();
        double salarioNominal = obtenerSalarioPorCargo(cargoSeleccionado);

        if (salarioNominal == 0) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar el salario para el cargo seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos de los campos
        String tipoCargo = cbCargo.getSelectedItem().toString();
        String nombres = txtNombre.getText();
        int cantMeses = (int) spMeses.getValue(); 
        String apellidos = txtApellido.getText();
        String duiStr = txtDui.getText();
        String salarioMensual = txtPrestaciones.getText();
        String isss = txtIsss.getText();
        String afp = txtAfp.getText();
        String incaf = txtIncaf.getText();

        // Realiza la validación de campos vacios
        if (nombres.isEmpty() || cantMeses==0 || apellidos.isEmpty() || tipoCargo.isEmpty() || duiStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos. El DUI es obligatorio.");
        } else {

            String consultaDui = "SELECT COUNT(*) FROM empleados WHERE idempleado = ?";
            try {
                PreparedStatement psConsulta = connect.getConexion().prepareStatement(consultaDui);
                psConsulta.setString(1, duiStr);
                ResultSet resultado = psConsulta.executeQuery();
                resultado.next();
                int cantidadEmpleadosConDui = resultado.getInt(1);

                if (cantidadEmpleadosConDui > 0) {
                    JOptionPane.showMessageDialog(this, "El empleado con el DUI " + duiStr + " ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    try {
                        
              
                        try {
                            connect.conectar();
                            String sentencia = "INSERT INTO empleados (idempleado, cargo, nombres, apellidos, salario, isss, afp, incaf, costoReal, cant_meses, septimo_dia, vacaciones, aguinaldo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement ps = this.connect.getConexion().prepareStatement(sentencia);

                            ps.setString(1, duiStr);
                            ps.setString(2, tipoCargo);
                            ps.setString(3, nombres);
                            ps.setString(4, apellidos);
                            ps.setDouble(5, salarioNominal);
                            ps.setString(6, isss);
                            ps.setString(7, afp);
                            ps.setString(8, incaf);
                            ps.setString(9, salarioMensual);
                            ps.setInt(10, cantMeses);
                            ps.setDouble(11, septimoDia);
                            ps.setDouble(12, vaca);
                            ps.setDouble(13, aguinaldo);

                            ps.executeUpdate();

                            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                            txtNombre.setText("");
                            txtApellido.setText("");
                            txtDui.setText("");
                            spMeses.setValue(0); 
                            txtIsss.setText("");
                            txtAfp.setText("");
                            txtIncaf.setText("");
                            txtPrestaciones.setText("");
                            btnCalcularPrestaciones.setEnabled(true);

                            // Actualiza la tabla después de agregar un nuevo empleado.
                            actualizarTabla(tbEmpleados);

                        } catch (SQLException e) {
                             e.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Salario introducido es inválido", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "No se puede guardar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCalcularPrestacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularPrestacionesActionPerformed
        String cargoSeleccionado = (String) cbCargo.getSelectedItem();
        double salarioNominal = obtenerSalarioPorCargo(cargoSeleccionado);
        double diasLaborados = obtenerDiasLaboradosPorCargo(cargoSeleccionado);
        double horasLaboradas = obtenerHorasLaboradasPorCargo(cargoSeleccionado);
        double recargoPor = obtenerRecargoPorCargo(cargoSeleccionado);
        double isssPorcentaje = obtenerIsssPorCargo(cargoSeleccionado);
        double afpPorcentaje = obtenerAfpPorCargo(cargoSeleccionado);
        double incafPorcentaje = obtenerIncafPorCargo(cargoSeleccionado);
        
        
        int cantMeses = (int) spMeses.getValue();  
        double vacaciones = 0.0; 
        double diaAguinaldo = 0.0;
         //vaca
        if (cantMeses > 0 && cantMeses < 12) {
            
            vacaciones = (15.0 / 12) * cantMeses; 
        } else if (cantMeses >= 12 && cantMeses < 24) {
         
            vacaciones = 15;
        } else if (cantMeses >= 24 && cantMeses < 36) {
           
            vacaciones = 17;
        } else if (cantMeses >= 36 && cantMeses < 48) {
           
            vacaciones = 19;
        } else if (cantMeses >= 48 && cantMeses < 60) {
            
            vacaciones = 21;
        } else if (cantMeses >= 60 && cantMeses < 72) {
            
            vacaciones = 23;
        } else if (cantMeses >= 72 && cantMeses < 84) {
           
            vacaciones = 25;
        } else if (cantMeses >= 84 && cantMeses < 96) {
            
            vacaciones = 27;
        } else if (cantMeses >= 96 && cantMeses < 108) {
           
            vacaciones = 29;
        } else if (cantMeses >= 108) {
       
            vacaciones = 30;
        }
        
        //agui
        if (cantMeses > 0 && cantMeses <= 12) {
           
            diaAguinaldo = 15;
        } else if (cantMeses >= 36 && cantMeses <= 108) {
           
            diaAguinaldo = 19;
        } else if (cantMeses > 108) {
            
            diaAguinaldo = 21;
        }

           
 
        if (cantMeses == 0 ) {
            JOptionPane.showMessageDialog(this, "Por favor, complete el campo de meses trabajados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (salarioNominal == 0) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar los datos para el cargo seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("El salario del cargo " + cargoSeleccionado + " es: " + salarioNominal);
        
        //convirtiendo el recargo de porcentaje a numerico
        double recargo = 0;      
        recargo = ((recargoPor/100)+1);
        
        
        double Cisss = 0;
        double Cafp = 0;
        double Cincaf = 0;
        
        Cisss = (isssPorcentaje/100);
        Cafp = (afpPorcentaje/100);
        Cincaf = (incafPorcentaje/100);
           
            //SEPTIMO DIA
            septimoDia = salarioNominal * 7;
            vaca = ((salarioNominal * vacaciones) * (recargo)) / 52;
            aguinaldo = (salarioNominal * diaAguinaldo) / 52;
            
            System.out.println("El recargo es: " + septimoDia);
            System.out.println("El recargo es: " + vaca);
            System.out.println("El recargo es: " + aguinaldo);
            
            double isss = (septimoDia+vaca)*Cisss;
            double afp = (septimoDia+vaca)*Cafp;
            double incaf = (septimoDia+vaca)*Cincaf;
            double costoRealSemanal = (septimoDia+vaca+aguinaldo+isss+afp+incaf);
            double costoRealDiario = (costoRealSemanal/diasLaborados);
            double costoRealMensual = costoRealDiario*22;
            
            //imprimir en los campos
            txtPrestaciones.setText(String.format("%.2f", costoRealMensual));
            txtAfp.setText(String.format("%.2f", afp));
            txtIsss.setText(String.format("%.2f", isss));
            txtIncaf.setText(String.format("%.2f", incaf));
            btnCalcularPrestaciones.setEnabled(false); 
    }//GEN-LAST:event_btnCalcularPrestacionesActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String duiBusqueda = txtBuscar.getText();
        if (!duiBusqueda.isEmpty()) {
            buscarEmpleado(duiBusqueda);
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar un número de DUI.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtDuiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuiKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume(); 
        }
    }//GEN-LAST:event_txtDuiKeyTyped

    private void btnPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuestoActionPerformed
        JFrame frame = new JFrame("Registrar Cargo laboral");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new Puesto());
        frame.pack();
        frame.setLocationRelativeTo(this);

        frame.setResizable(false);

        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }//GEN-LAST:event_btnPuestoActionPerformed

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void buscarEmpleado(String dui) {
        DefaultTableModel modelo = (DefaultTableModel) tbEmpleados.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String duiEmpleado = modelo.getValueAt(i, 0).toString(); //el DUI está en la primera columna
            if (dui.equals(duiEmpleado)) {
                tbEmpleados.setRowSelectionInterval(i, i); // Se resalta la fila con el empleado encontrado
                Rectangle rect = tbEmpleados.getCellRect(i, 0, true);
                tbEmpleados.scrollRectToVisible(rect); // Se desplaza para hacer visible la fila(cuando la tabla tiene varios datos)
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Resultado de la Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        txtBuscar.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCalcularPrestaciones;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnPuesto;
    private javax.swing.JComboBox<String> cbCargo;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spMeses;
    private javax.swing.JTable tbEmpleados;
    private javax.swing.JTextField txtAfp;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDui;
    private javax.swing.JTextField txtIncaf;
    private javax.swing.JTextField txtIsss;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrestaciones;
    // End of variables declaration//GEN-END:variables
}

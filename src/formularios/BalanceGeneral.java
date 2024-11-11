/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package formularios;

import clases.Conexion;
import java.awt.Color;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

/**
 *
 * @author Carlos Escobar - ES21001
 */
public class BalanceGeneral extends javax.swing.JPanel {

    /**tbGeneral
     * Creates new form BalanceGeneral
     */
    Conexion connect = new Conexion();
    EstadoResultado estadoResultado = new EstadoResultado();
    public BalanceGeneral() {
        initComponents();
        actualizarTablaGeneral(tbGeneral);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tbGeneral.getColumnCount(); i++) {
            tbGeneral.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tbGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tbGeneral.rowAtPoint(evt.getPoint());
                int col = tbGeneral.columnAtPoint(evt.getPoint());

                if (row == tbGeneral.getRowCount() - 1 && "Imprimir".equals(tbGeneral.getValueAt(row, col))) {

                    generarPDF("BalanceGeneral.pdf", tbGeneral);
                }
            }
        });
      
    }
    public void actualizarTablaGeneral(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); 
        

        try {
        
            String consultaFechas = "SELECT fecha_inicio, fecha_fin FROM periodo_contable";
            PreparedStatement sentenciaFechas = this.connect.getConexion().prepareStatement(consultaFechas);
            ResultSet rsFechas = sentenciaFechas.executeQuery();

            if (rsFechas.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMM yyyy");

                java.sql.Date fechaInicio = rsFechas.getDate("fecha_inicio");
                String fechaInicioStr = sdf.format(fechaInicio);

                java.sql.Date fechaFin = rsFechas.getDate("fecha_fin");
                String fechaFinStr = sdf.format(fechaFin);

               
                lbFechaInicio.setText(fechaInicioStr);
                lbFechaFin.setText(fechaFinStr);
            }
            rsFechas.close();

          
            List<String> cuentasActivo = Arrays.asList("Caja", "Bancos", "Cuentas por cobrar", "IVA Credito Fiscal", "Anticipos a proveedores");
            List<String> cuentasNoActivo = Arrays.asList("Alquiler pagado por anticipado", "Mobiliario y equipo de oficina", "Depreciación acumulada");
            List<String> cuentasPasivo = Arrays.asList("Impuestos por pagar", "IVA Debito Fiscal", "Anticipos de Clientes", "Cuentas por pagar");
            List<String> cuentasNoPasivo = Arrays.asList("Documentos por pagar");
            List<String> cuentasPatrimonio = Arrays.asList("Capital social");

            
            String sentencia = "SELECT idcuenta, nombre_cuenta FROM cuenta WHERE nombre_cuenta IN ('Caja', 'Bancos', 'Cuentas por cobrar', "
                    + "'IVA Credito Fiscal', 'Anticipos a proveedores', 'Alquiler pagado por anticipado', 'Mobiliario y equipo de oficina', "
                    + "'Depreciación acumulada', 'Impuestos por pagar', 'IVA Debito Fiscal', 'Anticipos de Clientes', 'Cuentas por pagar', "
                    + "'Documentos por pagar', 'Capital social') ORDER BY idcuenta ASC";

            PreparedStatement sentencia1 = this.connect.getConexion().prepareStatement(sentencia);
            ResultSet rs = sentencia1.executeQuery();

            double activoCo = 0, activoNo = 0, activos = 0, pasivoCo = 0, pasivoNo = 0, pasivos = 0;
            double patrimonio = 0, patrimonios = 0, activoPasivo = 0, debe = 0, haber = 0;
            int filaActual = 0;

            modelo.addRow(new Object[]{"1", "ACTIVO", null,null, activos});
            modelo.addRow(new Object[]{"11", "ACTIVO CORRIENTE", null,null, activoCo});

            while (rs.next()) {
                int idcuenta = rs.getInt("idcuenta");
                String nombre = rs.getString("nombre_cuenta");

                String consultaTransacciones = "SELECT SUM(debe_trans) AS total_debe, SUM(haber_trans) AS total_haber FROM transaccion WHERE idcuenta = ?";
                PreparedStatement sentenciaTransacciones = this.connect.getConexion().prepareStatement(consultaTransacciones);
                sentenciaTransacciones.setInt(1, idcuenta);
                ResultSet rsTransacciones = sentenciaTransacciones.executeQuery();

                if (rsTransacciones.next()) {
                    debe = rsTransacciones.getDouble("total_debe");
                    haber = rsTransacciones.getDouble("total_haber");

                    if (cuentasActivo.contains(nombre)) {
                        activoCo += (debe - haber);
                    } else if (cuentasNoActivo.contains(nombre)) {
                        activoNo += (debe - haber);
                    } else if (cuentasPasivo.contains(nombre)) {
                        pasivoCo += (haber - debe);
                    } else if (cuentasNoPasivo.contains(nombre)) {
                        pasivoNo += (haber - debe);
                    } else if (cuentasPatrimonio.contains(nombre)) {
                        patrimonio += (haber - debe);
                    }
                }

                rsTransacciones.close();
              
                modelo.addRow(new Object[]{idcuenta, nombre, debe, haber});  
            }
            rs.close();
            
            double impuestoTreinta = estadoResultado.getImpuestos();
            
            activos = (activoCo + activoNo) - impuestoTreinta;
            pasivos = pasivoCo + pasivoNo;
            double utilidadEjercicio = estadoResultado.getUtilidadEjercicio();
            patrimonios = patrimonio + utilidadEjercicio;
            activoPasivo = patrimonios + pasivos;
            
            
            modelo.insertRow(7, new Object[]{"12", "ACTIVO NO CORRIENTE", null, null, activoNo});
            modelo.insertRow(11, new Object[]{"2", "PASIVOS", null, null, pasivos});
            modelo.insertRow(12, new Object[]{"21", "PASIVOS CORRIENTE", null, null, pasivoCo});
            modelo.insertRow(17, new Object[]{"22", "PASIVOS NO CORRIENTE", null, null, pasivoNo});
            modelo.insertRow(19, new Object[]{"3", "PATRIMONIO", null, null, patrimonios});

            // actualizar las filas del titulo
            modelo.setValueAt(activos, 0, 4);
            modelo.setValueAt(activoCo, 1, 4);
            modelo.addRow(new Object[]{"3302", "Utilidad Neta",null, utilidadEjercicio});
            modelo.addRow(new Object[]{"", "TOTAL PASIVO + CAPITAL",null,null, activoPasivo});
            modelo.addRow(new Object[]{"", "","","",""});
            modelo.addRow(new Object[]{"", "","","",""});
            modelo.addRow(new Object[]{"", "","","","Imprimir"});

            System.out.println("Patrimonios después de la suma: " + patrimonios);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void generarPDF(String nombreArchivo, JTable tabla) {
        try {
            PdfWriter writer = new PdfWriter(nombreArchivo);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Balance General").setBold().setFontSize(16));

            // Crear una tabla de iText para agregar datos
            int columnas = tabla.getColumnCount();
            Table pdfTable = new Table(columnas);

            // Agregar encabezados de las columnas
            for (int i = 0; i < columnas; i++) {
                pdfTable.addCell(new Cell().add(new Paragraph(tabla.getColumnName(i))));
            }

            // Agregar contenido de las filas
            for (int i = 0; i < tabla.getRowCount(); i++) {
                for (int j = 0; j < columnas; j++) {
                    Object valorCelda = tabla.getValueAt(i, j);
                    pdfTable.addCell(new Cell().add(new Paragraph(valorCelda != null ? valorCelda.toString() : "")));
                }
            }

            document.add(pdfTable);
            document.close();
            JOptionPane.showMessageDialog(this, "PDF generado con éxito en " + nombreArchivo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGeneral = new javax.swing.JTable();
        lbFechaFin = new javax.swing.JLabel();
        lbFechaInicio = new javax.swing.JLabel();

        lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("BALANCE GENERAL");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DESARROLLO DIGITAL S.A. DE C.V.");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("AL");

        tbGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código de cuenta", "Nombre de cuenta", "Debe", "Haber", "Saldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbGeneral);

        lbFechaFin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lbFechaInicio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(lbFechaInicio)
                .addGap(53, 53, 53)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44)
                .addComponent(lbFechaFin)
                .addGap(308, 308, 308))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbFechaFin)
                    .addComponent(lbFechaInicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbFechaFin;
    private javax.swing.JLabel lbFechaInicio;
    private javax.swing.JTable tbGeneral;
    // End of variables declaration//GEN-END:variables
}

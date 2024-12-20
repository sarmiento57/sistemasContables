/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package formularios;

import clases.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Escobar - ES21001
 */
public class EstadoResultado extends javax.swing.JPanel {

    /**
     * Creates new form EstadoResultado
     */
    Conexion connect = new Conexion();
    private double utilidadEjercicio;
    private double impuestoTreinta;
    public EstadoResultado() {
        initComponents();
        actualizarTablaResultado(jbResultado);
        actualizarTablaCapital(tbCapital, utilidadEjercicio);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < jbResultado.getColumnCount(); i++) {
            jbResultado.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < tbCapital.getColumnCount(); i++) {
            tbCapital.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        jbResultado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jbResultado.rowAtPoint(evt.getPoint());
                int col = jbResultado.columnAtPoint(evt.getPoint());

                if (row == jbResultado.getRowCount() - 1 && "Imprimir".equals(jbResultado.getValueAt(row, col))) {

                    generarPDF("EstadoResultados.pdf", jbResultado);
                }
            }
        });
        
        tbCapital.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tbCapital.rowAtPoint(evt.getPoint());
                int col = tbCapital.columnAtPoint(evt.getPoint());

                if (row == tbCapital.getRowCount() - 1 && "Imprimir".equals(tbCapital.getValueAt(row, col))) {

                    generarPDF("EstadoCapital.pdf", tbCapital);
                }
            }
        });
        
    }
    public void actualizarTablaResultado(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); 

        try {
          
            String consultaFechas = "SELECT fecha_inicio, fecha_fin FROM periodo_contable";
            PreparedStatement sentenciaFechas = this.connect.getConexion().prepareStatement(consultaFechas);
            ResultSet rsFechas = sentenciaFechas.executeQuery();

            if (rsFechas.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMM yyyy");
                java.sql.Date fechaInicio = rsFechas.getDate("fecha_inicio");
                java.sql.Date fechaFin = rsFechas.getDate("fecha_fin");
                String fechaInicioStr = sdf.format(fechaInicio);
                String fechaFinStr = sdf.format(fechaFin);
            }
            rsFechas.close();

            modelo.addRow(new Object[]{"4", "Ingresos", null}); 

           
            List<String> cuentasIngresos = Arrays.asList(
                    "Ingresos por Servicios", "Proyectos de Software Personalizado", "Mantenimiento y soporte"
            );
            List<String> cuentasGastos = Arrays.asList(
                    "Gastos de Personal Técnico", "Gastos de Administración", "Gastos de Oficina", "Gastos de Tecnología", "Gastos de venta"
            );

           
            String sentencia = "SELECT idcuenta, nombre_cuenta FROM cuenta "
                    + "WHERE nombre_cuenta IN ('Ingresos por Servicios', 'Proyectos de Software Personalizado', "
                    + "'Mantenimiento y soporte', 'Gastos de Personal Técnico', 'Gastos de Administración', "
                    + "'Gastos de Oficina', 'Gastos de Tecnología', 'Gastos de venta') "
                    + "ORDER BY idcuenta ASC";
            PreparedStatement sentencia1 = this.connect.getConexion().prepareStatement(sentencia);
            ResultSet rs = sentencia1.executeQuery();

            double totalIngresos = 0;
            double totalGastosOperativos = 0;
            double utilidadEjercicios = 0;
            
            //impuesto
            this.impuestoTreinta = 0;

            //neta
            this.utilidadEjercicio = 0;
            

            int filaActual = 0;

            while (rs.next()) {
                int idcuenta = rs.getInt("idcuenta");
                String nombre = rs.getString("nombre_cuenta");

                
                String consultaTransacciones = "SELECT SUM(debe_trans) AS total_debe, SUM(haber_trans) AS total_haber "
                        + "FROM transaccion WHERE idcuenta = ?";
                PreparedStatement sentenciaTransacciones = this.connect.getConexion().prepareStatement(consultaTransacciones);
                sentenciaTransacciones.setInt(1, idcuenta);
                ResultSet rsTransacciones = sentenciaTransacciones.executeQuery();

                double saldo = 0;
                if (rsTransacciones.next()) {
                    double debe = rsTransacciones.getDouble("total_debe");
                    double haber = rsTransacciones.getDouble("total_haber");

                    if (cuentasIngresos.contains(nombre)) {
                        saldo = haber - debe;
                        totalIngresos += saldo;
                    } else if (cuentasGastos.contains(nombre)) {
                        saldo = debe - haber;
                        totalGastosOperativos += saldo;
                    }
                }
                rsTransacciones.close();

             
                modelo.addRow(new Object[]{idcuenta, nombre, saldo});
                filaActual++;

                if (filaActual == 3) {
                    modelo.addRow(new Object[]{"", "Total Ingresos", totalIngresos});
                    filaActual++;

                    modelo.addRow(new Object[]{"5", "Gastos", null});
                    filaActual++;
                }
                if (filaActual == 10) {
                    modelo.addRow(new Object[]{"", "Total gastos operativos", totalGastosOperativos});
                    filaActual++;
                }

                utilidadEjercicios = (totalIngresos - totalGastosOperativos);
                this.impuestoTreinta = (utilidadEjercicios*0.3);
                //aproximar a dos decimales
                DecimalFormat df = new DecimalFormat("#.00");
                this.impuestoTreinta = Double.parseDouble(df.format(utilidadEjercicios * 0.3));
                
                this.utilidadEjercicio = (utilidadEjercicios - impuestoTreinta);
                

                if (filaActual == 11) {
                    modelo.addRow(new Object[]{"3302", "Utilidad de ejercicio", utilidadEjercicios});
                    filaActual++;
                }
                if (filaActual == 12) {
                    modelo.addRow(new Object[]{"", "30% de impuesto", impuestoTreinta});
                    filaActual++;
                }
                if (filaActual == 13) {
                    modelo.addRow(new Object[]{"", "Utilidad Neta", utilidadEjercicio});
                    filaActual++;
                }
                if (filaActual == 14) {
                    modelo.addRow(new Object[]{"", "", "Imprimir"});
                    filaActual++;
                }
                
            }
            rs.close();
            
            actualizarTablaCapital(tbCapital, this.utilidadEjercicio);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void actualizarTablaCapital(JTable tabla, double utilidad) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); 

        try {
    
            List<String> cuentasIngresos = Arrays.asList(
                    "Ingresos por Servicios", "Proyectos de Software Personalizado", "Mantenimiento y soporte"
            );

          
            String sentencia = "SELECT idcuenta, nombre_cuenta FROM cuenta "
                    + "WHERE nombre_cuenta IN ('Capital social') ORDER BY idcuenta ASC";
            PreparedStatement sentencia1 = this.connect.getConexion().prepareStatement(sentencia);
            ResultSet rs = sentencia1.executeQuery();

            int filaActual = 0;

            while (rs.next()) {
                int idcuenta = rs.getInt("idcuenta");
                String nombre = rs.getString("nombre_cuenta");

               
                String consultaTransacciones = "SELECT SUM(debe_trans) AS total_debe, SUM(haber_trans) AS total_haber "
                        + "FROM transaccion WHERE idcuenta = ?";
                PreparedStatement sentenciaTransacciones = this.connect.getConexion().prepareStatement(consultaTransacciones);
                sentenciaTransacciones.setInt(1, idcuenta);
                ResultSet rsTransacciones = sentenciaTransacciones.executeQuery();

                double saldo = 0;
                double capitalNuevo = 0;
                if (rsTransacciones.next()) {
                    double debe = rsTransacciones.getDouble("total_debe");
                    double haber = rsTransacciones.getDouble("total_haber");
                    saldo = haber - debe;
                }
                rsTransacciones.close();

               
                modelo.addRow(new Object[]{idcuenta, nombre, saldo});
                filaActual++;

                if (filaActual == 1) {
                 modelo.addRow(new Object[]{"3302", "Utilidad Neta",utilidad});
                 filaActual++;
                }
                capitalNuevo = saldo + utilidad;
                
                if (filaActual == 2) {
                    modelo.addRow(new Object[]{"", "Capital final", capitalNuevo});
                    filaActual++;
                }
                
                if (filaActual == 3) {
                    modelo.addRow(new Object[]{"", "", ""});
                    filaActual++;
                }
                
                if (filaActual == 4) {
                    modelo.addRow(new Object[]{"", "", "Imprimir"});
                    filaActual++;
                }
                
            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public double getUtilidadEjercicio() {
        return this.utilidadEjercicio;
    }
    public double getImpuestos() {
        return this.impuestoTreinta;
    }
    
    public void generarPDF(String nombreArchivo, JTable tabla) {
        try {
            // Crear el escritor y el documento PDF
            PdfWriter writer = new PdfWriter(nombreArchivo);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Agregar título al documento
            document.add(new Paragraph("Estado de Resultado").setBold().setFontSize(16));

            // Verificar si la tabla tiene datos
            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "La tabla está vacía. No hay datos para exportar.");
                return;
            }

            // Crear la tabla de iText con el mismo número de columnas que la tabla original
            int columnas = tabla.getColumnCount();
            Table pdfTable = new Table(columnas);

            // Agregar encabezados de columna
            for (int i = 0; i < columnas; i++) {
                pdfTable.addCell(new Cell().add(new Paragraph(tabla.getColumnName(i)).setBold()));
            }

            // Agregar filas de la tabla
            for (int i = 0; i < tabla.getRowCount(); i++) {
                for (int j = 0; j < columnas; j++) {
                    Object valorCelda = tabla.getValueAt(i, j);
                    pdfTable.addCell(new Cell().add(new Paragraph(valorCelda != null ? valorCelda.toString() : "")));
                }
            }

            // Agregar la tabla al documento
            document.add(pdfTable);

            // Cerrar el documento
            document.close();

            // Mensaje de éxito
            JOptionPane.showMessageDialog(this, "PDF generado con éxito en: " + nombreArchivo);
        } catch (Exception e) {
            // Manejar errores
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void generarPDFCapital(String nombreArchivo, JTable tabla) {
        try {
            // Crear el escritor y el documento PDF
            PdfWriter writer = new PdfWriter(nombreArchivo);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Agregar título al documento
            document.add(new Paragraph("Estado de Resultado").setBold().setFontSize(16));

            // Verificar si la tabla tiene datos
            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "La tabla está vacía. No hay datos para exportar.");
                return;
            }

            // Crear la tabla de iText con el mismo número de columnas que la tabla original
            int columnas = tabla.getColumnCount();
            Table pdfTable = new Table(columnas);

            // Agregar encabezados de columna
            for (int i = 0; i < columnas; i++) {
                pdfTable.addCell(new Cell().add(new Paragraph(tabla.getColumnName(i)).setBold()));
            }

            // Agregar filas de la tabla
            for (int i = 0; i < tabla.getRowCount(); i++) {
                for (int j = 0; j < columnas; j++) {
                    Object valorCelda = tabla.getValueAt(i, j);
                    pdfTable.addCell(new Cell().add(new Paragraph(valorCelda != null ? valorCelda.toString() : "")));
                }
            }

            // Agregar la tabla al documento
            document.add(pdfTable);

            // Cerrar el documento
            document.close();

            // Mensaje de éxito
            JOptionPane.showMessageDialog(this, "PDF generado con éxito en: " + nombreArchivo);
        } catch (Exception e) {
            // Manejar errores
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jbResultado = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCapital = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jbResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código de  cuenta", "Nombre de cuenta", "Saldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jbResultado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addGap(170, 170, 170))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbCapital.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código de  cuenta", "Nombre de  cuenta", "Saldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbCapital);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addGap(170, 170, 170))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ESTADO DE RESULTADO");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ESTADO DE CAPITAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jbResultado;
    private javax.swing.JTable tbCapital;
    // End of variables declaration//GEN-END:variables
}

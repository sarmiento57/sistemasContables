package formularios;

import com.formdev.flatlaf.FlatClientProperties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import clases.Conexion;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Carlos Escobar - ES21001
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
        
        JTableHeader header = tbLibroMayor.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());

                //abrir jframe desde el indice o titulo de la tabla
                if (column == 0) { 
                   
                    JFrame frame = new JFrame("Listado de transacciones");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new Transacciones());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setAlwaysOnTop(true);
                    frame.setVisible(true);
                }
            }
        });
        
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());

                //abrir jframe desde el indice o titulo de la tabla
                if (column == 3) {
                    generarPDF("LibroMayor.pdf", tbLibroMayor);
                }
            }
        });



    }

    public void actualizarTabla() {
        modelo.setRowCount(0); 

        try {
           
            String sentencia = "SELECT c.nombre_cuenta, t.debe_trans, t.haber_trans, t.descripcion "
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
                String descripcion = rs.getString("descripcion");

              
                if (!cuentaActual.equals(nombreCuenta)) {
                    if (!cuentaActual.isEmpty()) {
                     
                        double saldoCuenta;
                        if (CuentasDeber(cuentaActual)) {
                            saldoCuenta = totalDebe - totalHaber; 
                        } else if (CuentasHaber(cuentaActual)) {
                            saldoCuenta = totalHaber - totalDebe; 
                        } else {
                            saldoCuenta = 0; 
                        }
                        modelo.addRow(new Object[]{"TOTAL", totalDebe, totalHaber, saldoCuenta});
                        modelo.addRow(new Object[]{"", "", "", ""});  
                    }
                    
                    // meter la categorias de las cuentas en las filas
                    if ("Caja".equals(nombreCuenta)) {
                        modelo.addRow(new Object[]{"", "ACTIVOS", "", ""});
                        modelo.addRow(new Object[]{nombreCuenta, "Debe", "Haber", "Saldo"});}
                    else if ("Impuestos por pagar".equals(nombreCuenta)) {
                        modelo.addRow(new Object[]{"", "PASIVOS", "", ""});
                        modelo.addRow(new Object[]{nombreCuenta, "Debe", "Haber", "Saldo"});
                    } else if ("Capital social".equals(nombreCuenta)) {
                        modelo.addRow(new Object[]{"", "PATRIMONIO", "", ""});
                        modelo.addRow(new Object[]{nombreCuenta, "Debe", "Haber", "Saldo"});
                    } else if ("Ingresos por Servicios".equals(nombreCuenta)) {
                        modelo.addRow(new Object[]{"", "INGRESOS", "", ""});
                        modelo.addRow(new Object[]{nombreCuenta, "Debe", "Haber", "Saldo"});
                    } else if ("Gastos de Personal Técnico".equals(nombreCuenta)) {
                        modelo.addRow(new Object[]{"", "GASTOS", "", ""});
                        modelo.addRow(new Object[]{nombreCuenta, "Debe", "Haber", "Saldo"});
                    } else {
                        modelo.addRow(new Object[]{nombreCuenta, "Debe", "Haber", "Saldo"});
                    }
                    
                    
                    cuentaActual = nombreCuenta;
                    totalDebe = 0; 
                    totalHaber = 0; 

                }
            
                totalDebe += debe;
                totalHaber += haber;

                modelo.addRow(new Object[]{descripcion, debe, haber, ""});
            }

            
            if (!cuentaActual.isEmpty()) {
                double saldoCuenta;
                if (CuentasDeber(cuentaActual)) {
                    saldoCuenta = totalDebe - totalHaber; 
                } else if (CuentasHaber(cuentaActual)) {
                    saldoCuenta = totalHaber - totalDebe; 
                } else {
                    saldoCuenta = 0; 
                }
                modelo.addRow(new Object[]{"TOTAL", totalDebe, totalHaber, saldoCuenta});
                modelo.addRow(new Object[]{"", "", "", ""});
            }

            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    //cuentas que crecen en el debe 
    private boolean CuentasDeber(String nombre) {
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
    private boolean CuentasHaber(String nombre) {
        String[] cuentasDeHaber = {
            "Ingresos por Servicios", "Proyectos de Software Personalizado",
            "Mantenimiento y soporte", "Impuestos por pagar",
            "IVA Debito Fiscal", "Anticipos de Clientes",
            "Cuentas por pagar", "Documentos por pagar", "Capital social"
        };
        return Arrays.asList(cuentasDeHaber).contains(nombre);
    }
    
    private static class CustomCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            c.setBorder(BorderFactory.createEmptyBorder());

            Color activosColor = new Color(173, 216, 230);  
            Color pasivosColor = new Color(144, 238, 144); 
            Color patrimonioColor = new Color(255, 255, 204);  
            Color ingresosColor = new Color(255, 223, 186);  
            Color gastosColor = new Color(255, 182, 193);  

            Color defaultBackground = table.getBackground();
            //color del hover
            Color hoverColor = new Color(255, 165, 0, 100);
   
            Object categoryObj = table.getValueAt(row, 1);  

            String category = null;
            if (categoryObj != null) {
               
                if (categoryObj instanceof String) {
                    category = (String) categoryObj;
                } 
                else if (categoryObj instanceof Double) {
                    category = categoryObj.toString();
                }
            }

            Color rowColor = defaultBackground;  
            if (category != null) {
                switch (category) {
                    case "ACTIVOS":
                        rowColor = activosColor;
                        break;
                    case "PASIVOS":
                        rowColor = pasivosColor;
                        break;
                    case "PATRIMONIO":
                        rowColor = patrimonioColor;
                        break;
                    case "INGRESOS":
                        rowColor = ingresosColor;
                        break;
                    case "GASTOS":
                        rowColor = gastosColor;
                        break;
                    default:
                        rowColor = defaultBackground;  
                        break;
                }
            }
  
            if (category != null && (category.equals("ACTIVOS") || category.equals("PASIVOS") || category.equals("PATRIMONIO") || category.equals("INGRESOS") || category.equals("GASTOS"))) {
                if (column == 0 || column == 1 || column == 2 || column == 3) {
                    c.setBackground(rowColor);  
                }

                if (value != null) {
                    String valueStr = value.toString();
                    if (valueStr.equals("ACTIVOS") || valueStr.equals("PASIVOS") || valueStr.equals("PATRIMONIO") || valueStr.equals("INGRESOS") || valueStr.equals("GASTOS")) {
                        c.setForeground(Color.BLACK);  
                    } else {
                        c.setForeground(Color.BLACK); 
                    }
                }
            } else {
              
                if (hasDataInRow(table, row)) {
                    c.setBackground(new Color(0, 0, 0, 20));  
                } else {
                    c.setBackground(defaultBackground);  
                }

               
                if (value != null) {
                    c.setForeground(table.getForeground());  
                }
            }

            if (value != null && (value.toString().equals("Debe") || value.toString().equals("Haber"))) {
                c.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
            }
 
            if (column == 2 && value != null && !value.toString().isEmpty()) {
                Border currentBorder = c.getBorder();
                Border newBorder = BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY);
                c.setBorder(BorderFactory.createCompoundBorder(newBorder, currentBorder));
            }
            
           
            if (isSelected || hasFocus) {
                c.setBackground(hoverColor);  
            }

            return c;
        }

        private boolean hasDataInRow(JTable table, int row) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                Object cellValue = table.getValueAt(row, col);
                if (cellValue != null && !cellValue.toString().trim().isEmpty()) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public void generarPDF(String nombreArchivo, JTable tabla) {
        try {
            // Crear el escritor y el documento PDF
            PdfWriter writer = new PdfWriter(nombreArchivo);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Agregar título al documento
            document.add(new Paragraph("Libro mayor").setBold().setFontSize(16));

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
                "Ver transacciones", "", "", "Imprimir"
            }
        ));
        jScrollPane1.setViewportView(tbLibroMayor);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
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

package formularios;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import application.Application;
import clases.Conexion;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 *
 * @author sarsg
 */
public class LoginForm extends javax.swing.JPanel {
    public static Registro r = new Registro();
    Menu p;
  
    public LoginForm() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("al center center"));

        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        
        txtContra.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        btnInicio.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        txtUsuario.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "User Name");
        txtContra.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin1 = new formularios.PanelLogin();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtContra = new javax.swing.JPasswordField();
        btnInicio = new javax.swing.JButton();

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Login");
        panelLogin1.add(lbTitle);

        lbUser.setText("User Name");
        panelLogin1.add(lbUser);

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });
        panelLogin1.add(txtUsuario);

        lbPass.setText("Password");
        panelLogin1.add(lbPass);

        txtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraKeyPressed(evt);
            }
        });
        panelLogin1.add(txtContra);

        btnInicio.setText("Login");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        panelLogin1.add(btnInicio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addComponent(panelLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(panelLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        if (txtUsuario.getText().isEmpty() || txtContra.getPassword().length == 0) {
        JOptionPane.showMessageDialog(null, "Ingrese usuario y contraseña.");
    } else {
        String contra = new String(txtContra.getPassword());
        String usuario = new String(txtUsuario.getText());
        r.Insertar(usuario, contra);
        Conexion conexion = new Conexion();
        
        // Llama al método conectar para validar las credenciales
        conexion.conectar();

        if (conexion.getConexion() != null) {
            // Cambia al MainForm en la aplicación
            Application.login(); // Este método ya establece el MainForm
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        }

        // Limpiar los campos de texto
        txtUsuario.setText("");
        txtContra.setText("");
        txtUsuario.requestFocus();
    }
    }//GEN-LAST:event_btnInicioActionPerformed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            txtUsuario.transferFocus();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtContraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            btnInicio.doClick();
        }
    }//GEN-LAST:event_txtContraKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicio;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private formularios.PanelLogin panelLogin1;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

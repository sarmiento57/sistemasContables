package formularios;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import application.Application;
import clases.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import clases.PeriodoContable;
import formularios.FormDashboard;
import formularios.catalogoCuentas;
import formularios.LibroMayor;
import formularios.RegistroTransacciones;
import formularios.BalanceComprobacion; 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Carlos Escobar - ES21001
 */
public class MainForm extends JLayeredPane {
    Conexion connect = new Conexion();

    public MainForm() {
        init();
    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu();
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("icon/svg/" + icon, 0.8f));
    }
    public boolean existePeriodoContableActivo() {
        boolean existe = false;
        try {
            PreparedStatement stmt = connect.getConexion().prepareStatement("SELECT COUNT(*) FROM periodo_contable WHERE cerrado = false");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
    public boolean existePeriodoContableCerrado() {
        boolean existe = false;
        try {
            PreparedStatement stmt = connect.getConexion().prepareStatement("SELECT COUNT(*) FROM periodo_contable WHERE cerrado = true");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }


    private void initMenuEvent() {
    menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
        if (index == 0) {
            Application.showForm(new FormDashboard());
        } else if (index == 1) {
            if (subIndex == 1) {
                Application.showForm(new catalogoCuentas());
            } else if (subIndex == 2) {
                if (!existePeriodoContableActivo()) {
                    JFrame frame = new JFrame("Iniciar Periodo Contable");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new IniciarPeriodoContable());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setAlwaysOnTop(true);
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowIconified(java.awt.event.WindowEvent e) {
                            frame.setExtendedState(JFrame.NORMAL);
                        }
                    });
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                        "No hay un periodo contable activo. Por favor, inicie uno antes de registrar transacciones.");
                } else {
                    Application.showForm(new RegistroTransacciones());
                }
            } else if (subIndex == 3) {
                if (!existePeriodoContableCerrado()) {
                    JFrame frame = new JFrame("Cerrar Periodo Contable");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new CerrarPeriodoContable());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setAlwaysOnTop(true);
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowIconified(java.awt.event.WindowEvent e) {
                            frame.setExtendedState(JFrame.NORMAL);
                        }
                    });
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                            "Hay un periodo contable activo. Tiene que cerrarlo para ingrear al Libro Mayor.");
                } else {
                    Application.showForm(new LibroMayor());
                }
            } else if (subIndex == 4) {
                if (!existePeriodoContableCerrado()) {
                    JFrame frame = new JFrame("Cerrar Periodo Contable");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new CerrarPeriodoContable());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setAlwaysOnTop(true);
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowIconified(java.awt.event.WindowEvent e) {
                            frame.setExtendedState(JFrame.NORMAL);
                        }
                    });
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                            "Hay un periodo contable activo. Tiene que cerrarlo para ingrear al Balance de comprobación.");
                } else {
                    Application.showForm(new BalanceComprobacion());
                }
            } else if (subIndex == 5) {
                if (!existePeriodoContableCerrado()) {
                    JFrame frame = new JFrame("Cerrar Periodo Contable");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new CerrarPeriodoContable());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setAlwaysOnTop(true);
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowIconified(java.awt.event.WindowEvent e) {
                            frame.setExtendedState(JFrame.NORMAL);
                        }
                    });
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                            "Hay un periodo contable activo. Tiene que cerrarlo para ingrear al Estado de resultados.");
                } else {
                    Application.showForm(new EstadoResultado());
                }
            } else if (subIndex == 6) {
                if (!existePeriodoContableCerrado()) {
                    JFrame frame = new JFrame("Cerrar Periodo Contable");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new CerrarPeriodoContable());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setAlwaysOnTop(true);
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowIconified(java.awt.event.WindowEvent e) {
                            frame.setExtendedState(JFrame.NORMAL);
                        }
                    });
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                            "Hay un periodo contable activo. Tiene que cerrarlo para ingrear al Balance general.");
                } else {
                    Application.showForm(new BalanceGeneral());
                }
            } else {
                action.cancel();
            }
        } else if (index == 2) {
            if (subIndex == 0) {
                Application.showForm(new RegistroServicios());
            } else if (subIndex == 1) {
                Application.showForm(new PlanillaEmpleado());
            } else if (subIndex == 2) {
                Application.showForm(new RegistroServicios());
            } else {
                action.cancel();
            }
        } else if (index == 3) {
            Application.logout();
        } else if (index == 4) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro que quieres salir??",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {    
                Application.exit();
            }         
        }else {
            action.cancel();
        }
    });
}







    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}

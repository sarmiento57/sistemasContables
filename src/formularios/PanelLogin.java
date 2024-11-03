package formularios;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author sarsg
 */
public class PanelLogin extends JPanel {

    public PanelLogin() {
        setLayout(new MigLayout("fillx,wrap,insets 30 40 50 40, width 320", "[fill]", "[]20[][]15[][]30[]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
    }

}

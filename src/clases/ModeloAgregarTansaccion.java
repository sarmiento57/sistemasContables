package clases;

import sistemacontable.SubCuenta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Carlos Escobar - ES21001
 */
public class ModeloAgregarTansaccion extends AbstractTableModel {
    public List<SubCuenta> scuentas= new ArrayList<SubCuenta>();
    
    @Override
    public int getRowCount() {
        return scuentas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SubCuenta sub = scuentas.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = sub.getIdClasificacion();
                break;
            case 1:
                valor = sub.getNombre();
                break;
            case 2:
                valor = sub.getDebe();
                break;
            case 3:
                valor = sub.getHaber();
        }
        return valor;

    }
}

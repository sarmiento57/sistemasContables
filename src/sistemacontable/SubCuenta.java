package sistemacontable;

public class SubCuenta {
    private int idClasificacion;
    private String nombre;
    private double debe;
    private double haber;

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getDebe() {
        return debe;
    }
    public void setDebe(double debe) {
        this.debe = debe;
    }
    public double getHaber() {
        return haber;
    }
    public void setHaber(double haber) {
        this.haber = haber;
    }

    @Override
    public String toString() {
        return this.nombre; 
    }
}

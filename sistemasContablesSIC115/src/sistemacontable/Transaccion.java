package sistemacontable;

import java.util.Date;

public class Transaccion {

    private int tipo;
    private String descripcion;
    private Date fecha;
    private int tipoCuenta;
    private double debe;
    private double haber;
    
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public Date getFecha(){
        return fecha;
    }
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    
    public int getTipoCuenta(){
        return tipoCuenta;
    }
    public void setTipoCuenta(int tipoCuenta){
        this.tipoCuenta = tipoCuenta;
    }
    
    public double getDebe(){
        return debe;
    }
    public void setDebe(double Debe){
        this.debe = debe;
    }
    
    public double getHaber(){
        return haber;
    }
    public void setHaber(double haber){
        this.haber = haber;
    }
    
     @Override
    public String toString() {
        return "transaccion{" + "tipoTransaccion=" + tipo  + "tipoCuenta=" + tipoCuenta + ", fecha=" + fecha + "tipoCuenta=" + tipoCuenta + ", descripcion=" + descripcion+ "debe=" + debe + "haber=" + haber +'}';
    }
}


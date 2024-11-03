package sistemacontable;

public class Cuenta {
    private int idTipo;
    private String tipo_cuenta;
    
    public Cuenta(){
   
    }
    
    public int getIdTipo(){
        return idTipo;
    }
    public void setIdTipo(int idTipo){
        this.idTipo = idTipo;
    }
    
    public String getTipo_Cuenta(){
        return tipo_cuenta;
    }
    public void setTipo_Cuenta(String tipo_cuenta){
        this.tipo_cuenta = tipo_cuenta;
    }
    
    @Override
    public String toString() {
        return   tipo_cuenta;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formularios;

/**
 *
 * @author Carlos Escobar - ES21001
 */
public class NodoUsuario {
    //Atributos
    String user, pass;
    NodoUsuario siguiente;
    
    //Constructor
    public NodoUsuario(String user, String pass){
        this.user = user;
        this.pass = pass;
        siguiente = null;
    }
}
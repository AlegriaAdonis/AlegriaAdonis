
package com.mycompany.avanceproyect;

public class Clientes {
    private String Nombres;
    private int Cedula;
    private int Telefono;
    private String Correo;

    public Clientes(String Nombres, int Cedula, int Telefono, String Correo) {
        this.Nombres = Nombres;
        this.Cedula = Cedula;
        this.Telefono = Telefono;
        this.Correo = Correo;
    }

    public String getNombres() {
        return Nombres;
    }

    public int getCedula() {
        return Cedula;
    }

    public int getTelefono() {
        return Telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public void setCedula(int Cedula) {
        this.Cedula = Cedula;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
}

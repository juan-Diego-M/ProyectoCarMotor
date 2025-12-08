/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Asesor;

import Modelo.Vehiculo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public abstract class Asesor {
    protected String nombre;
    protected String foto; // Ruta a la fotografía del asesor
    protected String datosContacto; // Teléfono, email, etc.
    
    public Asesor() {}
    
    public Asesor(String nombre, String foto, String datosContacto) {
        this.nombre = nombre;
        this.foto = foto;
        this.datosContacto = datosContacto;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    
    public String getDatosContacto() { return datosContacto; }
    public void setDatosContacto(String datosContacto) { this.datosContacto = datosContacto; }
    
    // Métodos abstractos
    public abstract void asesorar();
    public abstract String obtenerDescripcion();
    public abstract void mostrarDatosContacto();
    public abstract double calcularHonorarios(Vehiculo v);
}

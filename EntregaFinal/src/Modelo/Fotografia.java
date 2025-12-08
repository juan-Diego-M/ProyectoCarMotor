/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
import java.util.Date;

public class Fotografia {
    private String tipo; // "exterior", "interior", "motor"
    private String rutaArchivo;
    private String resolucion;
    private Date fechaTomada;
    
    public Fotografia() {
        this.fechaTomada = new Date();
    }
    
    public Fotografia(String tipo, String rutaArchivo, String resolucion) {
        this();
        this.tipo = tipo;
        this.rutaArchivo = rutaArchivo;
        this.resolucion = resolucion;
    }
    
    // Getters y Setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getRutaArchivo() { return rutaArchivo; }
    public void setRutaArchivo(String rutaArchivo) { this.rutaArchivo = rutaArchivo; }
    
    public String getResolucion() { return resolucion; }
    public void setResolucion(String resolucion) { this.resolucion = resolucion; }
    
    public Date getFechaTomada() { return fechaTomada; }
    public void setFechaTomada(Date fechaTomada) { this.fechaTomada = fechaTomada; }
    
    public String mostrarFoto() { 
        return "[Foto: " + tipo + " - " + rutaArchivo + "]"; 
    }
    
    public void cambiarTipo(String nuevoTipo) {
        this.tipo = nuevoTipo;
    }
    
    public String obtenerDetalles() { 
        return "Tipo: " + tipo + ", Ruta: " + rutaArchivo + ", Resolución: " + resolucion;
    }
    
    public boolean guardarFoto(String ruta) { 
        this.rutaArchivo = ruta;
        return true; 
    }
    
    @Override
    public String toString() {
        return tipo + " (" + resolucion + ")";
    }
}
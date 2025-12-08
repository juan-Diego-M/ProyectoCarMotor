/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Historial;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
import java.util.Date;

public class HistorialEntry {
    private static long contadorId = 1;
    private long id;
    private Date fecha;
    private String descripcion;
    private String tipo; // "cambio de aceite", "revisión de frenos", "mantenimiento general", etc.
    private double costo;
    private String taller; // Taller donde se realizó el servicio
    
    public HistorialEntry() {
        this.id = contadorId++;
        this.fecha = new Date();
    }
    
    public HistorialEntry(String tipo, String descripcion, double costo, String taller) {
        this();
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.taller = taller;
    }
    
    public HistorialEntry(Date fecha, String tipo, String descripcion, double costo, String taller) {
        this.id = contadorId++;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.taller = taller;
    }
    
    // Getters y Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
    
    public String getTaller() { return taller; }
    public void setTaller(String taller) { this.taller = taller; }
    
    @Override
    public String toString() {
        return "[" + fecha + "] " + tipo + ": " + descripcion + " - $" + costo + " (Taller: " + taller + ")";
    }
}
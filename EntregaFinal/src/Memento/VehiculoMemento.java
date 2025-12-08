/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Memento;

import Modelo.Vehiculo;
import State.EstadoVehiculo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class VehiculoMemento {
    private Vehiculo vehiculoSnapshot;
    private EstadoVehiculo estado;
    private double precio;
    private String descripcion;
    
    // Constructor original (mantener compatibilidad)
    public VehiculoMemento(Vehiculo v) { 
        this.vehiculoSnapshot = v; 
    }
    
    // Constructor nuevo para guardar estado, precio y descripción
    public VehiculoMemento(EstadoVehiculo estado, double precio, String descripcion) {
        this.estado = estado;
        this.precio = precio;
        this.descripcion = descripcion;
    }
    
    public Vehiculo getVehiculoSnapshot() { return vehiculoSnapshot; }
    public EstadoVehiculo getEstado() { return estado; }
    public double getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }
}
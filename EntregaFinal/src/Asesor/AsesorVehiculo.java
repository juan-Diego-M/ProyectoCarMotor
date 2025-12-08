/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Asesor;

import Modelo.Vehiculo;
import Observer.ObservadorVehiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class AsesorVehiculo extends Asesor implements ObservadorVehiculo {
    private List<Vehiculo> vehiculosAsignados;
    private String especialidad; // Ej: "SUVs", "Sedanes", "Vehículos de lujo"
    
    public AsesorVehiculo() {
        super();
        this.vehiculosAsignados = new ArrayList<>();
    }
    
    public AsesorVehiculo(String nombre, String foto, String datosContacto, String especialidad) {
        super(nombre, foto, datosContacto);
        this.vehiculosAsignados = new ArrayList<>();
        this.especialidad = especialidad;
    }
    
    // Getters y Setters
    public List<Vehiculo> getVehiculosAsignados() { 
        return new ArrayList<>(vehiculosAsignados); 
    }
    
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    
    // Métodos para gestionar vehículos asignados
    public void asignarVehiculo(Vehiculo v) {
        if (v != null && !vehiculosAsignados.contains(v)) {
            vehiculosAsignados.add(v);
            v.setAsesor(this);
            v.agregarObservador(this);
            System.out.println("✓ Vehículo asignado a " + nombre + ": " + v);
        }
    }
    
    public void desasignarVehiculo(Vehiculo v) {
        if (vehiculosAsignados.remove(v)) {
            v.eliminarObservador(this);
            System.out.println("✓ Vehículo desasignado de " + nombre);
        }
    }
    
    public int getCantidadVehiculosAsignados() {
        return vehiculosAsignados.size();
    }
    
    @Override 
    public void asesorar() {
        System.out.println("[" + nombre + "] Proporcionando asesoría sobre vehículos...");
        System.out.println("Especialidad: " + (especialidad != null ? especialidad : "General"));
        System.out.println("Vehículos asignados: " + vehiculosAsignados.size());
    }
    
    @Override 
    public String obtenerDescripcion() {
        return "Asesor de Vehículos: " + nombre + 
               " | Especialidad: " + (especialidad != null ? especialidad : "General") +
               " | Vehículos: " + vehiculosAsignados.size();
    }
    
    @Override 
    public void mostrarDatosContacto() {
        System.out.println("=== CONTACTO DEL ASESOR ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Contacto: " + datosContacto);
        System.out.println("Especialidad: " + (especialidad != null ? especialidad : "General"));
    }
    
    @Override 
    public double calcularHonorarios(Vehiculo v) {
        // Honorarios = 2% del precio de venta del vehículo
        if (v != null) {
            return v.getPrecioVenta() * 0.02;
        }
        return 0;
    }
    
    @Override 
    public void notificarCambioEstado(Vehiculo v, String nuevoEstado) {
        System.out.println("[ASESOR " + nombre + "] Notificación: El vehículo " + 
                           v.getMarca() + " " + v.getModelo() + 
                           " cambió a estado: " + nuevoEstado);
    }
    
    @Override
    public String toString() {
        return nombre + " (Asesor de Vehículos)";
    }
}

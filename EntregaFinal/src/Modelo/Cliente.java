/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Observer.ObservadorVehiculo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class Cliente implements ObservadorVehiculo {
    private String nombre;
    private String identificacion;
    private String email;
    private String telefono;
    private Object factory; // AseguradoraFactory
    private Object builder; // VehiculoBuilder
    
    public Cliente() {}
    
    public Cliente(String nombre, String identificacion, String email, String telefono) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.telefono = telefono;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public void generarPoliza(Object factory) {}
    public void construirVehiculo() {}
    public Vehiculo getVehiculo(){ return null; }
    public void solicitarTestDrive(Vehiculo v, Object sede) {}
    
    @Override 
    public void notificarCambioEstado(Vehiculo v, String nuevoEstado) {
        System.out.println("[CLIENTE" + (nombre != null ? " " + nombre : "") + "] Notificación: Vehículo " + 
                           (v != null ? v.getMarca() + " " + v.getModelo() : "N/A") + 
                           " cambió a estado: " + nuevoEstado);
    }
}
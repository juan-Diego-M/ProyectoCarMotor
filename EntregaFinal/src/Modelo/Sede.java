/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Observer.ObservadorVehiculo;
import TestDrive.TestDrive;
import TestDrive.TestDriveSlot;
import Memento.VehiculoMemento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalTime;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class Sede implements ObservadorVehiculo {
    private String nombre;
    private String direccion;
    private String telefono;
    private Integer horarioApertura; // Hora en formato 24h (ej: 8 = 8:00 AM)
    private Integer horarioCierre; // Hora en formato 24h (ej: 18 = 6:00 PM)
    
    // Listas para gestionar vehículos y test drives
    private List<Vehiculo> vehiculos;
    private List<TestDriveSlot> slotsTestDrive;
    private List<TestDrive> testDrivesAgendados;
    private Map<Vehiculo, VehiculoMemento> historialEstados; // Para Memento pattern
    
    // Constructor vacío
    public Sede() {
        this.vehiculos = new ArrayList<>();
        this.slotsTestDrive = new ArrayList<>();
        this.testDrivesAgendados = new ArrayList<>();
        this.historialEstados = new HashMap<>();
    }
    
    // Constructor completo
    public Sede(String nombre, String direccion, String telefono, Integer horarioApertura, Integer horarioCierre) {
        this();
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public Integer getHorarioApertura() { return horarioApertura; }
    public void setHorarioApertura(Integer horarioApertura) { this.horarioApertura = horarioApertura; }
    
    public Integer getHorarioCierre() { return horarioCierre; }
    public void setHorarioCierre(Integer horarioCierre) { this.horarioCierre = horarioCierre; }
    
    public List<TestDriveSlot> getSlotsTestDrive() { return slotsTestDrive; }
    public List<TestDrive> getTestDrivesAgendados() { return testDrivesAgendados; }
    
    // Métodos para gestionar vehículos
    public void registrarVehiculo(Vehiculo v) {
        if (v != null && !vehiculos.contains(v)) {
            vehiculos.add(v);
            v.agregarObservador(this); // La sede observa cambios en el vehículo
            System.out.println("Vehículo registrado en sede " + nombre + ": " + v);
        }
    }
    
    public void eliminarVehiculo(Vehiculo v) {
        if (vehiculos.remove(v)) {
            v.eliminarObservador(this);
            System.out.println("Vehículo eliminado de sede " + nombre + ": " + v);
        }
    }
    
    public List<Vehiculo> listarVehiculos() { 
        return new ArrayList<>(vehiculos); // Retorna copia para evitar modificaciones externas
    }
    
    public int getCantidadVehiculos() {
        return vehiculos.size();
    }
    
    // Método para verificar si la sede está abierta
    public boolean estaAbierta() {
        LocalTime ahora = LocalTime.now();
        int horaActual = ahora.getHour();
        return horaActual >= horarioApertura && horaActual < horarioCierre;
    }
    
    // Método para asignar cliente (placeholder)
    public void asignarCliente(Vehiculo v, Cliente c) {
        System.out.println("Cliente asignado al vehículo en sede " + nombre);
    }
    
    // Métodos Memento para guardar/restaurar estados
    public void guardarEstadoVehiculo(Vehiculo v) {
        if (vehiculos.contains(v)) {
            historialEstados.put(v, v.guardarEstado());
            System.out.println("Estado del vehículo guardado en sede " + nombre);
        }
    }
    
    public void restaurarEstadoAnterior(Vehiculo v) {
        if (historialEstados.containsKey(v)) {
            v.restaurarEstado(historialEstados.get(v));
            System.out.println("Estado del vehículo restaurado en sede " + nombre);
        }
    }
    
    // Métodos para Test Drive
    public void agregarSlotTestDrive(TestDriveSlot slot) {
        if (slot != null) {
            slotsTestDrive.add(slot);
        }
    }
    
    public List<TestDriveSlot> obtenerSlotsDisponibles() {
        List<TestDriveSlot> disponibles = new ArrayList<>();
        for (TestDriveSlot slot : slotsTestDrive) {
            if (slot.isDisponible()) {
                disponibles.add(slot);
            }
        }
        return disponibles;
    }
    
    public void agendarTestDrive(Vehiculo v, Cliente cliente, TestDriveSlot slot) {
        if (vehiculos.contains(v) && slot.isDisponible()) {
            TestDrive testDrive = new TestDrive(v, cliente, slot, v.getAsesor());
            testDrive.confirmar();
            testDrivesAgendados.add(testDrive);
            slot.reservar();
            System.out.println("Test drive agendado en sede " + nombre);
        }
    }
    
    public void cancelarTestDrive(TestDrive testDrive) {
        if (testDrivesAgendados.contains(testDrive)) {
            testDrive.cancelar();
            testDrivesAgendados.remove(testDrive);
            System.out.println("Test drive cancelado en sede " + nombre);
        }
    }
    
    // Observer: notificación de cambio de estado
    @Override 
    public void notificarCambioEstado(Vehiculo v, String nuevoEstado) {
        System.out.println("[SEDE " + nombre + "] Notificación: Vehículo " + v.getMarca() + 
                           " " + v.getModelo() + " cambió a estado: " + nuevoEstado);
    }
    
    // Información de la sede
    public String obtenerInformacionCompleta() {
        StringBuilder info = new StringBuilder();
        info.append("=== SEDE: ").append(nombre).append(" ===\n");
        info.append("Dirección: ").append(direccion).append("\n");
        info.append("Teléfono: ").append(telefono).append("\n");
        info.append("Horario: ").append(horarioApertura).append(":00 - ")
            .append(horarioCierre).append(":00\n");
        info.append("Vehículos disponibles: ").append(vehiculos.size()).append("\n");
        info.append("Slots test drive disponibles: ").append(obtenerSlotsDisponibles().size()).append("\n");
        return info.toString();
    }
    
    @Override
    public String toString() {
        return nombre + " - " + direccion + " (Tel: " + telefono + ")";
    }
}
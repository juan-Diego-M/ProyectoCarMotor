/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestDrive;

import Asesor.AsesorVehiculo;
import Modelo.Cliente;
import Modelo.Vehiculo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class TestDrive {
    private TestDriveSlot slot;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private AsesorVehiculo asesor;
    private boolean confirmado;
    
    public TestDrive() {
        this.confirmado = false;
    }
    
    public TestDrive(Vehiculo vehiculo, Cliente cliente, TestDriveSlot slot, AsesorVehiculo asesor) {
        this();
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.slot = slot;
        this.asesor = asesor;
    }
    
    // Getters y Setters
    public TestDriveSlot getSlot() { return slot; }
    public void setSlot(TestDriveSlot slot) { this.slot = slot; }
    
    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public AsesorVehiculo getAsesor() { return asesor; }
    public void setAsesor(AsesorVehiculo asesor) { this.asesor = asesor; }
    
    public boolean isConfirmado() { return confirmado; }
    
    // Métodos
    public void confirmar() {
        if (slot != null && slot.isDisponible()) {
            slot.reservar();
            this.confirmado = true;
            System.out.println("Test drive confirmado para " + slot.getFechaHora());
        }
    }
    
    public void cancelar() {
        if (confirmado && slot != null) {
            slot.liberar();
            this.confirmado = false;
            System.out.println("Test drive cancelado");
        }
    }
    
    public String obtenerInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=== TEST DRIVE ===").append("\n");
        info.append("Vehículo: ").append(vehiculo != null ? vehiculo.toString() : "N/A").append("\n");
        info.append("Fecha/Hora: ").append(slot != null ? slot.toString() : "N/A").append("\n");
        info.append("Asesor: ").append(asesor != null ? asesor.getNombre() : "N/A").append("\n");
        info.append("Estado: ").append(confirmado ? "Confirmado" : "Pendiente").append("\n");
        return info.toString();
    }
    
    @Override
    public String toString() {
        return "TestDrive[" + (vehiculo != null ? vehiculo.getMarca() + " " + vehiculo.getModelo() : "N/A") + 
               ", " + (slot != null ? slot.getFechaHora() : "N/A") + "]";
    }
}

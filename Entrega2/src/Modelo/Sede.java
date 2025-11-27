/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import TestDrive.TestDrive;
import TestDrive.TestDriveSlot;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class Sede {
    private String nombre;
    private String direccion;
    private String telefono;
    private Integer horarioApertura;
    private Integer horarioCierre;
    // colecciones y slots
    public Sede() {}
    public void registrarVehiculo(Vehiculo v) {}
    public void eliminarVehiculo(Vehiculo v) {}
    public java.util.List<Vehiculo> listarVehiculos(){ return null; }
    public boolean estaAbierta() { return false; }
    public void asignarCliente(Vehiculo v, Cliente c) {}
    public void restaurarEstadoAnterior(Vehiculo v) {}
    // test drive operations
    public void agendarTestDrive(Vehiculo v, Cliente cliente, TestDriveSlot slot) {}
    public void cancelarTestDrive(TestDrive slot) {}
}
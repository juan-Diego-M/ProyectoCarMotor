/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import Modelo.Vehiculo;

/**
 *
 * @author ASUS
 */
public class EstadoVendido implements EstadoVehiculo {
    @Override public void vender(Vehiculo v) {}
    @Override public void retirar(Vehiculo v) {}
    @Override public void asignarCliente(Vehiculo v, Object cliente) {}
    @Override public void enviarMantenimiento(Vehiculo v) {}
    @Override public String nombreEstado() { return "Vendido"; }
}
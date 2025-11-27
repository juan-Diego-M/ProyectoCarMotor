/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ASUS
 */
public class Cliente {
    private Object factory; // AseguradoraFactory
    private Object builder; // VehiculoBuilder
    public Cliente() {}
    public void generarPoliza(Object factory) {}
    public void construirVehiculo() {}
    public Vehiculo getVehiculo(){ return null; }
    public void solicitarTestDrive(Vehiculo v, Object sede) {}
}
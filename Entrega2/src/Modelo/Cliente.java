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
public class Cliente {
    private Object factory; // AseguradoraFactory
    private Object builder; // VehiculoBuilder
    public Cliente() {}
    public void generarPoliza(Object factory) {}
    public void construirVehiculo() {}
    public Vehiculo getVehiculo(){ return null; }
    public void solicitarTestDrive(Vehiculo v, Object sede) {}
}
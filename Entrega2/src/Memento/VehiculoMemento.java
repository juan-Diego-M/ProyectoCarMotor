/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Memento;

import Modelo.Vehiculo;

/**
 *
 * @author ASUS
 */
public class VehiculoMemento {
    private Vehiculo vehiculoSnapshot;
    public VehiculoMemento(Vehiculo v) { this.vehiculoSnapshot = v; }
    public Vehiculo getVehiculoSnapshot() { return vehiculoSnapshot; }
}
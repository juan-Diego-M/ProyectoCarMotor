/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import Modelo.Vehiculo;

/**
 *
 * @author ASUS
 */
public class SedeObservador implements ObservadorVehiculo {
    @Override public void notificarCambioEstado(Vehiculo v, String nuevoEstado) {}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proxy;

import Modelo.Usuario;
import Modelo.Vehiculo;

/**
 *
 * @author ASUS
 */
public class VehiculoProxy {
    private Usuario usuario;
    private Vehiculo vehiculoReal;
    public VehiculoProxy(Vehiculo vehiculoReal, Usuario usuario) {
        this.vehiculoReal = vehiculoReal;
        this.usuario = usuario;
    }
    public void mostrarInformacion() {}
    public void actualizarPrecio(double nuevoPrecio) {}
    public double obtenerCosto() { return 0; }
}
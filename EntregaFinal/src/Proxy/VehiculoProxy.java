/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proxy;

import Modelo.Usuario;
import Modelo.Vehiculo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
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
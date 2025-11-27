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
public interface EstadoVehiculo {
    void vender(Vehiculo v);
    void retirar(Vehiculo v);
    void asignarCliente(Vehiculo v, Object cliente);
    void enviarMantenimiento(Vehiculo v);
    String nombreEstado();
}


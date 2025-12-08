/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;
import Modelo.Vehiculo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public interface EstadoVehiculo {
    void vender(Vehiculo v);
    void retirar(Vehiculo v);
    void asignarCliente(Vehiculo v, Object cliente);
    void enviarMantenimiento(Vehiculo v);
    String nombreEstado();
}


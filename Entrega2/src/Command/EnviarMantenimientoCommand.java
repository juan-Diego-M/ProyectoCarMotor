/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command;

import Modelo.Vehiculo;

/**
 *
 * @author ASUS
 */
public class EnviarMantenimientoCommand implements OperacionVehiculoCommand {
    private Vehiculo vehiculo;
    private Object sede;
    public EnviarMantenimientoCommand(Vehiculo v, Object s) { this.vehiculo = v; this.sede = s; }
    @Override public void ejecutar() {}
    @Override public void deshacer() {}
}

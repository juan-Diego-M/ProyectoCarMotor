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
public class VenderVehiculoCommand implements OperacionVehiculoCommand {
    private Vehiculo vehiculo;
    public VenderVehiculoCommand(Vehiculo v) { this.vehiculo = v; }
    @Override public void ejecutar() {}
    @Override public void deshacer() {}
}

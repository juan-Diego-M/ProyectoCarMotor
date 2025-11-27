/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Builder;
import Modelo.Vehiculo;

/**
 *
 * @author ASUS
 */
public class SUVBuilder implements VehiculoBuilder {
    private Vehiculo vehiculo;
    public SUVBuilder() { reset(); }
    @Override public void reset() { this.vehiculo = new Vehiculo(); }
    @Override public void setDatosBasicos() {}
    @Override public void construirMarca() {}
    @Override public void construirSerie() {}
    @Override public void construirTipo() {}
    @Override public void construirCapacidad() {}
    @Override public void construirPrecio() {}
    @Override public void construirFotos() {}
    @Override public Vehiculo getVehiculo(){ return vehiculo; }
}

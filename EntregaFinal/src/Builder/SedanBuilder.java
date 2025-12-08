/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Builder;
import Modelo.Vehiculo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class SedanBuilder implements VehiculoBuilder {
    private Vehiculo vehiculo;
    public SedanBuilder() { reset(); }
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
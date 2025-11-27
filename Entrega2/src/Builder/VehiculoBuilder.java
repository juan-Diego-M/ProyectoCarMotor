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
public interface VehiculoBuilder {
    void reset();
    void setDatosBasicos();
    void construirMarca();
    void construirSerie();
    void construirTipo();
    void construirCapacidad();
    void construirPrecio();
    void construirFotos();
    Vehiculo getVehiculo();
}

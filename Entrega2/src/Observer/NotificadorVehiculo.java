/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import Modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class NotificadorVehiculo {
    private List<ObservadorVehiculo> observadores = new ArrayList<>();
    public void agregarObservador(ObservadorVehiculo o) { observadores.add(o); }
    public void eliminarObservador(ObservadorVehiculo o) { observadores.remove(o); }
    public void notificar(Vehiculo v, String estado) {
        for (ObservadorVehiculo o : observadores) { o.notificarCambioEstado(v, estado); }
    }
}
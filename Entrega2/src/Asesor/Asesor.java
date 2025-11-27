/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Asesor;

import Modelo.Vehiculo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public abstract class Asesor {
    protected String nombre;
    protected String foto;
    protected String datosContacto;
    public Asesor(){}
    public abstract void asesorar();
    public abstract String obtenerDescripcion();
    public abstract void mostrarDatosContacto();
    public abstract double calcularHonorarios(Vehiculo v);
}

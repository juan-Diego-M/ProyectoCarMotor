/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Asesor;

import Modelo.Vehiculo;

/**
 *
 * @author ASUS
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

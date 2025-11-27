/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ASUS
 */
import Bridge.Motor;
import java.util.List;
import java.util.Stack;

public class Vehiculo {
    // atributos según UML
    private String marca;
    private String modelo;
    private String tipo;
    private int serie;
    private int pasajeros;
    private double precioVenta;
    private List<String> fotos;
    private String descripcion;
    private double costo;
    private Motor motor;
    // constructores
    public Vehiculo() {}
    // getters / setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public Motor getMotor() { return motor; }
    public void setMotor(Motor motor) { this.motor = motor; }
    // placeholder para más métodos que indica el UML
}
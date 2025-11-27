/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bridge;

/**
 *
 * @author ASUS
 */
public abstract class Motor {
    protected int potencia;
    protected double consumo;
    public Motor() {}
    public abstract void acelerar();
    public abstract void encender();
    public abstract int getConsumo();
    public abstract int getPotencia();
    public double calcularConsumoEspecifico(double km) { return 0; }
    public void registrarUso(double km) {}
}

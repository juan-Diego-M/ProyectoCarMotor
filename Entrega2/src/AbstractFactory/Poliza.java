/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AbstractFactory;

/**
 *
 * @author ASUS
 */
public abstract class Poliza {
    protected long id;
    protected double costo;
    protected String cobertura;
    public Poliza() {}
    public Poliza(long id, double costo, String cobertura) {}
    public long getId(){ return 0; }
    public double getCosto(){ return 0; }
    public String getCobertura(){ return null; }
    public void mostrarDetalles() {}
    public void calcularPrima() {}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AbstractFactory;

/**
 *
 * @author ASUS
 */
public abstract class AsistenciaVial {
    protected long id;
    protected double costo;
    protected String tipoServicio;
    public AsistenciaVial() {}
    public AsistenciaVial(long id, double costo, String tipoServicio) { }
    public long getId() { return 0; }
    public double getCosto() { return 0; }
    public String getTipoDeServicio() { return null; }
    public void mostrarDetalles() {}
}

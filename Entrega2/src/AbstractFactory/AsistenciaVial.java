/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AbstractFactory;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
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

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

    public AsistenciaVial(long id, double costo, String tipoServicio) {
        this.id = id;
        this.costo = costo;
        this.tipoServicio = tipoServicio;
    }

    public long getId() { return id; }
    public double getCosto() { return costo; }
    public String getTipoDeServicio() { return tipoServicio; }

    public void mostrarDetalles() {
        System.out.println("AsistenciaVial[id=" + id + ", tipoServicio=" + tipoServicio + ", costo=" + costo + "]");
    }
}
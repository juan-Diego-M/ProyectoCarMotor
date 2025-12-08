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
public abstract class Poliza {
    protected long id;
    protected double costo;
    protected String cobertura;

    public Poliza(long id, double costo, String cobertura) {
        this.id = id;
        this.costo = costo;
        this.cobertura = cobertura;
    }

    public long getId() { return id; }
    public double getCosto() { return costo; }
    public String getCobertura() { return cobertura; }

    public void mostrarDetalles() {
        System.out.println("Poliza[id=" + id + ", costo=" + costo + ", cobertura=" + cobertura + "]");
    }
    
    /**
     * Calcula el precio anual de la póliza basado en el valor del vehículo
     * @param valorVehiculo El valor del vehículo
     * @return El precio anual de la póliza
     */
    public double calcularPrecioAnual(double valorVehiculo) {
        // Por defecto, 5% del valor del vehículo
        return valorVehiculo * 0.05;
    }
}
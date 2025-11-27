/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mantenimiento;

import Visitor.MantenimientoVisitor;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class MantenimientoSimple implements MantenimientoComponent {
    private String fecha;
    private String tipo;
    private String taller;
    private String descripcion;
    private Double costo;
    public MantenimientoSimple() {}
    @Override public String obtenerDescripcion() { return null; }
    @Override public double obtenerCosto() { return 0; }
    @Override public void accept(MantenimientoVisitor v) {}
}

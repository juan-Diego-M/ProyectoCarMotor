/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mantenimiento;

import Visitor.MantenimientoVisitor;

/**
 *
 * @author ASUS
 */
public class MantenimientoManoObraEspecial extends MantenimientoSimple {
    private Double incremento;
    public MantenimientoManoObraEspecial() {}
    @Override public String obtenerDescripcion() { return null; }
    @Override public double obtenerCosto() { return 0; }
    @Override public void accept(MantenimientoVisitor v) {}
}
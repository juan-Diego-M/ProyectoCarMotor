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
public abstract class MantenimientoDecorator implements MantenimientoComponent {
    protected MantenimientoComponent wrappee;
    public MantenimientoDecorator(MantenimientoComponent c) { this.wrappee = c; }
    @Override public String obtenerDescripcion() { return wrappee != null ? wrappee.obtenerDescripcion() : null; }
    @Override public double obtenerCosto() { return wrappee != null ? wrappee.obtenerCosto() : 0; }
    @Override public void accept(MantenimientoVisitor v) { if (wrappee!=null) wrappee.accept(v); }
}

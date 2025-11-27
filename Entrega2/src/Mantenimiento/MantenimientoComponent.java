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
public interface MantenimientoComponent {
    String obtenerDescripcion();
    double obtenerCosto();
    void accept(MantenimientoVisitor v);
}


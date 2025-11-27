/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visitor;
import Mantenimiento.*;
/**
 *
 * @author ASUS
 */
public interface MantenimientoVisitor {
    void visitar(MantenimientoSimple m);
    void visitar(MantenimientoConIVA m);
    void visitar(MantenimientoConDescuento m);
    void visitar(MantenimientoCertificado m);
    void visitar(MantenimientoManoObraEspecial m);
}
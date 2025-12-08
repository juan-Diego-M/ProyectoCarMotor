/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visitor;
import Mantenimiento.*;
/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public interface MantenimientoVisitor {
    void visitar(MantenimientoSimple m);
    void visitar(MantenimientoConIVA m);
    void visitar(MantenimientoConDescuento m);
    void visitar(MantenimientoCertificado m);
    void visitar(MantenimientoManoObraEspecial m);
}
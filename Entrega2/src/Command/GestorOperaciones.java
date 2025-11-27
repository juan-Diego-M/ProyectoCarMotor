/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command;

import java.util.Stack;

/**
 *
 * @author ASUS
 */

public class GestorOperaciones {
    private Stack<OperacionVehiculoCommand> historial = new Stack<>();
    public void ejecutar(OperacionVehiculoCommand cmd) {}
    public void deshacerUltima() {}
}

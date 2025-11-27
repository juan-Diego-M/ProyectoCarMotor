/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command;

import java.util.Stack;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */

public class GestorOperaciones {
    private Stack<OperacionVehiculoCommand> historial = new Stack<>();
    public void ejecutar(OperacionVehiculoCommand cmd) {}
    public void deshacerUltima() {}
}

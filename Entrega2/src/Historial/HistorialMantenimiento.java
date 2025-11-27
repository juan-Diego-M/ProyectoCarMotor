/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Historial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class HistorialMantenimiento implements Iterable<HistorialEntry> {
    private List<HistorialEntry> historial = new ArrayList<>();
    public void agregar(HistorialEntry e) {}
    public void eliminar(HistorialEntry e) {}
    public String obtenerDescripcion() { return null; }
    public double obtenerCosto() { return 0; }
    @Override public Iterator<HistorialEntry> iterator() { return historial.iterator(); }
}
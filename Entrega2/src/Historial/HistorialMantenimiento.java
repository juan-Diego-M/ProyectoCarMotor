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
 * @author ASUS
 */
public class HistorialMantenimiento implements Iterable<HistorialEntry> {
    private List<HistorialEntry> historial = new ArrayList<>();
    public void agregar(HistorialEntry e) {}
    public void eliminar(HistorialEntry e) {}
    public String obtenerDescripcion() { return null; }
    public double obtenerCosto() { return 0; }
    @Override public Iterator<HistorialEntry> iterator() { return historial.iterator(); }
}
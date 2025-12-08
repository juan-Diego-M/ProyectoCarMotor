/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Historial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class HistorialMantenimiento implements Iterable<HistorialEntry> {
    private List<HistorialEntry> historial;
    
    public HistorialMantenimiento() {
        this.historial = new ArrayList<>();
    }
    
    // Métodos para gestionar el historial
    public void agregar(HistorialEntry e) {
        if (e != null) {
            historial.add(e);
            System.out.println("✓ Entrada de mantenimiento agregada: " + e.getTipo());
        }
    }
    
    public void eliminar(HistorialEntry e) {
        if (historial.remove(e)) {
            System.out.println("✓ Entrada de mantenimiento eliminada");
        }
    }
    
    public List<HistorialEntry> obtenerHistorial() {
        return new ArrayList<>(historial); // Retorna copia
    }
    
    public String obtenerDescripcion() {
        if (historial.isEmpty()) {
            return "No hay registros de mantenimiento";
        }
        
        StringBuilder desc = new StringBuilder();
        desc.append("=== HISTORIAL DE MANTENIMIENTO ===").append("\n");
        desc.append("Total de servicios: ").append(historial.size()).append("\n");
        desc.append("Costo total: $").append(String.format("%.2f", obtenerCosto())).append("\n\n");
        
        for (HistorialEntry entry : historial) {
            desc.append(entry.toString()).append("\n");
        }
        
        return desc.toString();
    }
    
    public double obtenerCosto() {
        double total = 0;
        for (HistorialEntry entry : historial) {
            total += entry.getCosto();
        }
        return total;
    }
    
    public int getCantidadServicios() {
        return historial.size();
    }
    
    // Métodos de búsqueda y filtrado
    public List<HistorialEntry> buscarPorTipo(String tipo) {
        List<HistorialEntry> resultado = new ArrayList<>();
        for (HistorialEntry entry : historial) {
            if (entry.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(entry);
            }
        }
        return resultado;
    }
    
    public List<HistorialEntry> buscarPorTaller(String taller) {
        List<HistorialEntry> resultado = new ArrayList<>();
        for (HistorialEntry entry : historial) {
            if (entry.getTaller() != null && entry.getTaller().equalsIgnoreCase(taller)) {
                resultado.add(entry);
            }
        }
        return resultado;
    }
    
    // Ordenar por fecha (más reciente primero)
    public void ordenarPorFecha() {
        Collections.sort(historial, new Comparator<HistorialEntry>() {
            @Override
            public int compare(HistorialEntry e1, HistorialEntry e2) {
                return e2.getFecha().compareTo(e1.getFecha()); // Descendente
            }
        });
    }
    
    @Override 
    public Iterator<HistorialEntry> iterator() { 
        return historial.iterator(); 
    }
}
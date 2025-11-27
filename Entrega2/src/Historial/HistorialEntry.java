/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Historial;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class HistorialEntry {
    private long id;
    private java.util.Date fecha;
    private String descripcion;
    private String tipo;
    private double costo;
    public HistorialEntry() {}
    public long getId(){ return 0; }
    public java.util.Date getFecha(){ return null; }
    public String getDescripcion(){ return null; }
    public String getTipo(){ return null; }
    public double getCosto(){ return 0; }
}
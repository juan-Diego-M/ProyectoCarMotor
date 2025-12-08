/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Asesor;

import Modelo.Vehiculo;
import Banco.EntidadBancaria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class AsesorFinanciero extends Asesor {
    private EntidadBancaria entidadBancaria;
    private List<String> planesFinanciamiento;
    
    public AsesorFinanciero() {
        super();
        this.planesFinanciamiento = new ArrayList<>();
        inicializarPlanes();
    }
    
    public AsesorFinanciero(String nombre, String foto, String datosContacto, EntidadBancaria entidad) {
        super(nombre, foto, datosContacto);
        this.entidadBancaria = entidad;
        this.planesFinanciamiento = new ArrayList<>();
        inicializarPlanes();
    }
    
    private void inicializarPlanes() {
        planesFinanciamiento.add("12 meses");
        planesFinanciamiento.add("24 meses");
        planesFinanciamiento.add("36 meses");
        planesFinanciamiento.add("48 meses");
        planesFinanciamiento.add("60 meses");
    }
    
    // Getters y Setters
    public EntidadBancaria getEntidadBancaria() { return entidadBancaria; }
    public void setEntidadBancaria(EntidadBancaria entidadBancaria) { 
        this.entidadBancaria = entidadBancaria; 
    }
    
    public List<String> getPlanesFinanciamiento() { 
        return new ArrayList<>(planesFinanciamiento); 
    }
    
    @Override 
    public void asesorar() {
        System.out.println("[" + nombre + "] Proporcionando asesoría financiera...");
        if (entidadBancaria != null) {
            System.out.println("Entidad bancaria: " + entidadBancaria.getNombre());
            System.out.println("Tasa de interés: " + String.format("%.2f", entidadBancaria.getTasaInteres() * 100) + "%");
        }
        System.out.println("Planes disponibles: " + planesFinanciamiento);
    }
    
    @Override 
    public String obtenerDescripcion() {
        return "Asesor Financiero: " + nombre + 
               (entidadBancaria != null ? " | Banco: " + entidadBancaria.getNombre() : "");
    }
    
    @Override 
    public void mostrarDatosContacto() {
        System.out.println("=== CONTACTO DEL ASESOR FINANCIERO ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Contacto: " + datosContacto);
        if (entidadBancaria != null) {
            System.out.println("Entidad: " + entidadBancaria.getNombre());
        }
    }
    
    @Override 
    public double calcularHonorarios(Vehiculo v) {
        // Honorarios = 1.5% del precio de venta del vehículo
        if (v != null) {
            return v.getPrecioVenta() * 0.015;
        }
        return 0;
    }
    
    /**
     * Calcula y muestra opciones de financiamiento para un vehículo
     */
    public void calcularOpcionesFinanciamiento(Vehiculo vehiculo) {
        if (vehiculo == null || entidadBancaria == null) {
            System.out.println("No se puede calcular financiamiento");
            return;
        }
        
        System.out.println("\n=== OPCIONES DE FINANCIAMIENTO ===");
        System.out.println("Vehículo: " + vehiculo);
        System.out.println("Precio: $" + String.format("%.2f", vehiculo.getPrecioVenta()));
        System.out.println("Entidad: " + entidadBancaria.getNombre());
        System.out.println("\nPLANES:");
        
        for (String plan : planesFinanciamiento) {
            int meses = Integer.parseInt(plan.split(" ")[0]);
            double cuota = entidadBancaria.calcularCuotaMensual(vehiculo.getPrecioVenta(), meses);
            System.out.println(plan + ": $" + String.format("%.2f", cuota) + " mensuales");
        }
    }
    
    @Override
    public String toString() {
        return nombre + " (Asesor Financiero)";
    }
}

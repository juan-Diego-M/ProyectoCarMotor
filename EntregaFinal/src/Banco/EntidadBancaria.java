/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Asesor.AsesorFinanciero;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class EntidadBancaria {
    private String nombre;
    private String logo; // Ruta al logo
    private AsesorFinanciero asesorFinanciero;
    private double tasaInteres; // Tasa de interés anual (ej: 0.12 = 12%)
    
    public EntidadBancaria() {
        this.tasaInteres = 0.15; // Tasa por defecto 15%
    }
    
    public EntidadBancaria(String nombre, String logo, double tasaInteres) {
        this.nombre = nombre;
        this.logo = logo;
        this.tasaInteres = tasaInteres;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    
    public AsesorFinanciero getAsesorFinanciero() { return asesorFinanciero; }
    public void setAsesorFinanciero(AsesorFinanciero asesorFinanciero) { 
        this.asesorFinanciero = asesorFinanciero; 
    }
    
    public double getTasaInteres() { return tasaInteres; }
    public void setTasaInteres(double tasaInteres) { this.tasaInteres = tasaInteres; }
    
    // Métodos de negocio
    
    /**
     * Calcula la cuota mensual de un préstamo usando la fórmula de amortización francesa
     * @param monto Monto del préstamo
     * @param plazoMeses Plazo en meses
     * @return Cuota mensual
     */
    public double calcularCuotaMensual(double monto, int plazoMeses) {
        if (plazoMeses <= 0 || monto <= 0) {
            return 0;
        }
        
        double tasaMensual = tasaInteres / 12; // Tasa mensual
        
        if (tasaMensual == 0) {
            return monto / plazoMeses;
        }
        
        // Fórmula: C = P * [i * (1 + i)^n] / [(1 + i)^n - 1]
        double factorPotencia = Math.pow(1 + tasaMensual, plazoMeses);
        double cuota = monto * (tasaMensual * factorPotencia) / (factorPotencia - 1);
        
        return Math.round(cuota * 100.0) / 100.0; // Redondear a 2 decimales
    }
    
    /**
     * Simula la aprobación de crédito (lógica simplificada)
     * @param usuario Usuario solicitante
     * @param monto Monto solicitado
     * @return true si se aprueba, false en caso contrario
     */
    public boolean aprobarCredito(Object usuario, double monto) {
        // Lógica simplificada: aprueba si el monto es menor a 100,000,000
        return monto > 0 && monto <= 100000000;
    }
    
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== ENTIDAD BANCARIA ===").append("\n");
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Tasa de interés: ").append(String.format("%.2f", tasaInteres * 100)).append("%\n");
        if (asesorFinanciero != null) {
            info.append("Asesor financiero: ").append(asesorFinanciero.getNombre()).append("\n");
            info.append("Contacto asesor: ").append(asesorFinanciero.getDatosContacto()).append("\n");
        }
        return info.toString();
    }
    
    public void asociarAsesor(AsesorFinanciero a) {
        this.asesorFinanciero = a;
    }
    
    public double obtenerTasaInteres() { 
        return tasaInteres; 
    }
    
    @Override
    public String toString() {
        return nombre + " (Tasa: " + String.format("%.2f", tasaInteres * 100) + "%)";
    }
}

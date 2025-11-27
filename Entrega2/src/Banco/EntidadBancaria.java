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
    private String logo;
    private AsesorFinanciero asesorFinanciero;
    private double tasaInteres;
    public EntidadBancaria() {}
    public double calcularCuotaMensual(double monto, int plazoMeses) { return 0; }
    public boolean aprobarCredito(Object usuario, double monto) { return false; }
    public String mostrarInformacion(){ return null; }
    public void asociarAsesor(AsesorFinanciero a) {}
    public double obtenerTasaInteres(){ return 0; }
}

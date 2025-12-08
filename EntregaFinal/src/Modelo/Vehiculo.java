/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
import Bridge.Motor;
import Observer.ObservadorVehiculo;
import State.EstadoVehiculo;
import State.EstadoDisponible;
import Asesor.AsesorVehiculo;
import Historial.HistorialMantenimiento;
import Memento.VehiculoMemento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Vehiculo {
    // Atributos según enunciado
    private String marca;
    private String modelo;
    private String tipo; // SUV, camioneta 4x4, sedan, etc.
    private int serie;
    private int ultimoDigitoPlaca; // NUEVO: último dígito de placa
    private int pasajeros; // capacidad de pasajeros
    private double precioVenta;
    private List<Fotografia> fotografias; // fotografías del vehículo
    private String descripcion;
    private double costo;
    private Motor motor; // Bridge pattern
    
    // Relaciones
    private AsesorVehiculo asesor; // Asesor asignado
    private EstadoVehiculo estado; // State pattern
    private HistorialMantenimiento historialMantenimiento;
    private List<ObservadorVehiculo> observadores = new ArrayList<>();
    private Stack<VehiculoMemento> mementos = new Stack<>(); // Memento pattern
    
    // Atributo para aseguradora
    private boolean candidatoSeguroTodoRiesgo;
    
    // Constructor vacío
    public Vehiculo() {
        this.fotografias = new ArrayList<>();
        this.historialMantenimiento = new HistorialMantenimiento();
        this.estado = new EstadoDisponible(); // Estado inicial
        this.observadores = new ArrayList<>();
        this.candidatoSeguroTodoRiesgo = false;
    }
    
    // Constructor completo
    public Vehiculo(String marca, String modelo, String tipo, int serie, int ultimoDigitoPlaca, 
                    int pasajeros, double precioVenta, Motor motor) {
        this();
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.serie = serie;
        this.ultimoDigitoPlaca = ultimoDigitoPlaca;
        this.pasajeros = pasajeros;
        this.precioVenta = precioVenta;
        this.motor = motor;
    }
    
    // Getters y Setters completos
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public int getSerie() { return serie; }
    public void setSerie(int serie) { this.serie = serie; }
    
    public int getUltimoDigitoPlaca() { return ultimoDigitoPlaca; }
    public void setUltimoDigitoPlaca(int ultimoDigitoPlaca) { this.ultimoDigitoPlaca = ultimoDigitoPlaca; }
    
    public int getPasajeros() { return pasajeros; }
    public void setPasajeros(int pasajeros) { this.pasajeros = pasajeros; }
    
    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }
    
    public List<Fotografia> getFotografias() { return fotografias; }
    public void setFotografias(List<Fotografia> fotografias) { this.fotografias = fotografias; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
    
    public Motor getMotor() { return motor; }
    public void setMotor(Motor motor) { this.motor = motor; }
    
    public AsesorVehiculo getAsesor() { return asesor; }
    public void setAsesor(AsesorVehiculo asesor) { this.asesor = asesor; }
    
    public EstadoVehiculo getEstado() { return estado; }
    public void setEstado(EstadoVehiculo estado) { this.estado = estado; }
    
    public HistorialMantenimiento getHistorialMantenimiento() { return historialMantenimiento; }
    public void setHistorialMantenimiento(HistorialMantenimiento historialMantenimiento) { 
        this.historialMantenimiento = historialMantenimiento; 
    }
    
    public boolean isCandidatoSeguroTodoRiesgo() { return candidatoSeguroTodoRiesgo; }
    public void setCandidatoSeguroTodoRiesgo(boolean candidatoSeguroTodoRiesgo) { 
        this.candidatoSeguroTodoRiesgo = candidatoSeguroTodoRiesgo; 
    }
    
    // Métodos para fotografías
    public void agregarFotografia(Fotografia foto) {
        this.fotografias.add(foto);
    }
    
    public void eliminarFotografia(Fotografia foto) {
        this.fotografias.remove(foto);
    }
    
    // Métodos Observer
    public void agregarObservador(ObservadorVehiculo o) { 
        observadores.add(o); 
    }
    
    public void eliminarObservador(ObservadorVehiculo o) { 
        observadores.remove(o); 
    }
    
    public void notificar(String estado) {
        for (ObservadorVehiculo o : observadores) { 
            o.notificarCambioEstado(this, estado); 
        }
    }
    
    // Métodos Memento
    public VehiculoMemento guardarEstado() {
        return new VehiculoMemento(this.estado, this.precioVenta, this.descripcion);
    }
    
    public void restaurarEstado(VehiculoMemento memento) {
        this.estado = memento.getEstado();
        this.precioVenta = memento.getPrecio();
        this.descripcion = memento.getDescripcion();
    }
    
    public void guardarEnHistorial() {
        mementos.push(guardarEstado());
    }
    
    public void restaurarUltimoEstado() {
        if (!mementos.isEmpty()) {
            restaurarEstado(mementos.pop());
        }
    }
    
    // Método para información completa del vehículo
    public String obtenerInformacionCompleta() {
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMACIÓN DEL VEHÍCULO ===\n");
        info.append("Marca: ").append(marca).append("\n");
        info.append("Modelo: ").append(modelo).append("\n");
        info.append("Tipo: ").append(tipo).append("\n");
        info.append("Serie: ").append(serie).append("\n");
        info.append("Último dígito placa: ").append(ultimoDigitoPlaca).append("\n");
        info.append("Capacidad: ").append(pasajeros).append(" pasajeros\n");
        info.append("Precio: $").append(String.format("%.2f", precioVenta)).append("\n");
        info.append("Motor: ").append(motor != null ? motor.getClass().getSimpleName() : "N/A").append("\n");
        info.append("Estado: ").append(estado != null ? estado.getClass().getSimpleName() : "N/A").append("\n");
        info.append("Asesor: ").append(asesor != null ? asesor.getNombre() : "Sin asignar").append("\n");
        info.append("Candidato seguro todo riesgo: ").append(candidatoSeguroTodoRiesgo ? "Sí" : "No").append("\n");
        return info.toString();
    }
    
    @Override
    public String toString() {
        return marca + " " + modelo + " (" + tipo + ") - $" + String.format("%.2f", precioVenta);
    }
}
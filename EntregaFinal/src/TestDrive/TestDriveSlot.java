/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestDrive;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDriveSlot {
    private LocalDateTime fechaHora;
    private boolean disponible;
    
    public TestDriveSlot() {
        this.disponible = true;
    }
    
    public TestDriveSlot(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
        this.disponible = true;
    }
    
    // Getters y Setters
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    // Métodos
    public void reservar() {
        this.disponible = false;
    }
    
    public void liberar() {
        this.disponible = true;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaHora.format(formatter) + (disponible ? " [Disponible]" : " [Reservado]");
    }
}
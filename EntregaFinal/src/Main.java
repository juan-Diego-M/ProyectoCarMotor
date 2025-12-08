/*
 * Punto de entrada principal del Sistema CarMotor
 */
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Sistema de Gestión del Concesionario CarMotor
 * 
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class Main {
    public static void main(String[] args) {
        // Configurar el Look and Feel del sistema para mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, se usa el Look and Feel por defecto
        }

        // Lanzar la interfaz gráfica moderna en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            InterfazCarMotorModerna ventana = new InterfazCarMotorModerna();
            ventana.setVisible(true);
        });
    }
}

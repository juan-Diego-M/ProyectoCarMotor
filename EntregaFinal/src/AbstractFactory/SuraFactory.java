/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AbstractFactory;

/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class SuraFactory implements AseguradoraFactory {
    @Override
    public Poliza crearPoliza() {
        // id constante para ejemplo simple
        return new PolizaSura(1L, 1_200_000.0, "Todo riesgo");
    }

    @Override
    public AsistenciaVial crearAsistencia() {
        return new AsistenciaVialSura(101L, 150_000.0, "Grua");
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AbstractFactory;

/**
 *
 * @author ASUS
 */
public class SuraFactory implements AseguradoraFactory {
    @Override public Poliza crearPoliza() { return new PolizaSura(0,0,""); }
    @Override public AsistenciaVial crearAsistencia() { return new AsistenciaVialSura(0,0,""); }
}
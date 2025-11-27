/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AbstractFactory;

/**
 *
 * @author ASUS
 */
public class LibertyFactory implements AseguradoraFactory {
    @Override public Poliza crearPoliza() { return new PolizaLiberty(0,0,""); }
    @Override public AsistenciaVial crearAsistencia() { return new AsistenciaVialLiberty(0,0,""); }
}

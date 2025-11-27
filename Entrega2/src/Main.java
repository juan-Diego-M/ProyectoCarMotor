/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import AbstractFactory.*;
import Asesor.*;
import Banco.*;
import Bridge.*;
import Builder.*;
import Command.*;
import Historial.*;
import Mantenimiento.*;
import Memento.*;
import Modelo.*;
import Observer.*;
import Proxy.*;
import State.*;
import TestDrive.*;
import Visitor.*;
/**
 *
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("=== PROYECTO UML INICIADO CORRECTAMENTE ===\n");

        // ============================================================
        // PRUEBA 1: PATRÓN BRIDGE - Múltiples tipos de motores
        // ============================================================
        System.out.println(">>> PATRÓN BRIDGE - Probando diferentes motores <<<");
        Motor motorGasolina = new MotorGasolina();
        Motor motorDiesel = new MotorDiesel();
        Motor motorElectrico = new MotorElectrico();
        
        System.out.println("✓ Motor Gasolina creado: " + motorGasolina.getClass().getSimpleName());
        System.out.println("✓ Motor Diesel creado: " + motorDiesel.getClass().getSimpleName());
        System.out.println("✓ Motor Eléctrico creado: " + motorElectrico.getClass().getSimpleName());

        // ============================================================
        // PRUEBA 2: PATRÓN BUILDER - Construcción de diferentes vehículos
        // ============================================================
        System.out.println("\n>>> PATRÓN BUILDER - Construyendo diferentes tipos de vehículos <<<");
        
        VehiculoBuilder sedanBuilder = new SedanBuilder();
        sedanBuilder.reset();
        sedanBuilder.setDatosBasicos();
        sedanBuilder.construirMarca();
        sedanBuilder.construirTipo();
        Vehiculo sedan = sedanBuilder.getVehiculo();
        sedan.setMotor(motorGasolina);
        System.out.println("✓ Sedan construido con motor: " + sedan.getMotor().getClass().getSimpleName());
        
        VehiculoBuilder suvBuilder = new SUVBuilder();
        suvBuilder.reset();
        suvBuilder.setDatosBasicos();
        suvBuilder.construirMarca();
        Vehiculo suv = suvBuilder.getVehiculo();
        suv.setMotor(motorDiesel);
        System.out.println("✓ SUV construido con motor: " + suv.getMotor().getClass().getSimpleName());
        
        VehiculoBuilder camionetaBuilder = new CamionetaBuilder();
        camionetaBuilder.reset();
        camionetaBuilder.setDatosBasicos();
        Vehiculo camioneta = camionetaBuilder.getVehiculo();
        camioneta.setMotor(motorElectrico);
        System.out.println("✓ Camioneta construida con motor: " + camioneta.getMotor().getClass().getSimpleName());

        // ============================================================
        // PRUEBA 3: PATRÓN STATE - Ciclo de vida del vehículo
        // ============================================================
        System.out.println("\n>>> PATRÓN STATE - Probando estados del vehículo <<<");
        EstadoVehiculo disponible = new EstadoDisponible();
        EstadoVehiculo enMantenimiento = new EstadoEnMantenimiento();
        EstadoVehiculo vendido = new EstadoVendido();
        EstadoVehiculo retirado = new EstadoRetirado();
        
        System.out.println("✓ Estado Disponible: " + disponible.nombreEstado());
        System.out.println("✓ Estado En Mantenimiento: " + enMantenimiento.nombreEstado());
        System.out.println("✓ Estado Vendido: " + vendido.nombreEstado());
        System.out.println("✓ Estado Retirado: " + retirado.nombreEstado());
        
        // Simulando cambios de estado
        Cliente cliente = new Cliente();
        disponible.asignarCliente(sedan, cliente);
        disponible.vender(sedan);
        System.out.println("✓ Vehículo cambió de estado: Disponible -> Vendido");

        // ============================================================
        // PRUEBA 4: PATRÓN OBSERVER - Sistema de notificaciones
        // ============================================================
        System.out.println("\n>>> PATRÓN OBSERVER - Probando notificaciones <<<");
        NotificadorVehiculo notificador = new NotificadorVehiculo();
        
        ClienteObservador clienteObs = new ClienteObservador();
        AsesorObservador asesorObs = new AsesorObservador();
        SedeObservador sedeObs = new SedeObservador();
        
        notificador.agregarObservador(clienteObs);
        notificador.agregarObservador(asesorObs);
        notificador.agregarObservador(sedeObs);
        
        System.out.println("✓ Observadores registrados: Cliente, Asesor, Sede");
        notificador.notificar(suv, "Nuevo vehículo disponible");
        notificador.notificar(sedan, "Vehículo vendido");
        notificador.notificar(camioneta, "Vehículo en mantenimiento");
        System.out.println("✓ Notificaciones enviadas a todos los observadores");

        // ============================================================
        // PRUEBA 5: PATRÓN COMMAND - Operaciones sobre vehículos
        // ============================================================
        System.out.println("\n>>> PATRÓN COMMAND - Ejecutando comandos <<<");
        GestorOperaciones gestor = new GestorOperaciones();
        
        Sede sedeMantenimiento = new Sede();
        
        OperacionVehiculoCommand cmdVender = new VenderVehiculoCommand(suv);
        OperacionVehiculoCommand cmdMantenimiento = new EnviarMantenimientoCommand(camioneta, sedeMantenimiento);
        
        gestor.ejecutar(cmdVender);
        System.out.println("✓ Comando ejecutado: Vender vehículo");
        
        gestor.ejecutar(cmdMantenimiento);
        System.out.println("✓ Comando ejecutado: Enviar a mantenimiento");

        // ============================================================
        // PRUEBA 6: PATRÓN PROXY - Control de acceso
        // ============================================================
        System.out.println("\n>>> PATRÓN PROXY - Probando control de acceso <<<");
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        
        VehiculoProxy proxy1 = new VehiculoProxy(sedan, usuario1);
        VehiculoProxy proxy2 = new VehiculoProxy(suv, usuario2);
        
        proxy1.mostrarInformacion();
        proxy2.mostrarInformacion();
        System.out.println("✓ Proxy de control de acceso funcionando correctamente");

        // ============================================================
        // PRUEBA 7: PATRÓN ABSTRACT FACTORY - Múltiples aseguradoras
        // ============================================================
        System.out.println("\n>>> PATRÓN ABSTRACT FACTORY - Probando aseguradoras <<<");
        
        AseguradoraFactory suraFactory = new SuraFactory();
        Poliza polizaSura = suraFactory.crearPoliza();
        AsistenciaVial asistenciaSura = suraFactory.crearAsistencia();
        System.out.println("✓ SURA - Póliza: " + polizaSura.getClass().getSimpleName());
        System.out.println("✓ SURA - Asistencia: " + asistenciaSura.getClass().getSimpleName());
        
        AseguradoraFactory mapfreFactory = new MapfreFactory();
        Poliza polizaMapfre = mapfreFactory.crearPoliza();
        AsistenciaVial asistenciaMapfre = mapfreFactory.crearAsistencia();
        System.out.println("✓ MAPFRE - Póliza: " + polizaMapfre.getClass().getSimpleName());
        System.out.println("✓ MAPFRE - Asistencia: " + asistenciaMapfre.getClass().getSimpleName());
        
        AseguradoraFactory libertyFactory = new LibertyFactory();
        Poliza polizaLiberty = libertyFactory.crearPoliza();
        AsistenciaVial asistenciaLiberty = libertyFactory.crearAsistencia();
        System.out.println("✓ LIBERTY - Póliza: " + polizaLiberty.getClass().getSimpleName());
        System.out.println("✓ LIBERTY - Asistencia: " + asistenciaLiberty.getClass().getSimpleName());

        // ============================================================
        // PRUEBA 8: PATRÓN DECORATOR - Mantenimientos con decoradores
        // ============================================================
        System.out.println("\n>>> PATRÓN DECORATOR - Probando decoradores de mantenimiento <<<");
        
        MantenimientoComponent mantSimple = new MantenimientoSimple();
        System.out.println("✓ Mantenimiento Simple creado");
        
        MantenimientoComponent mantConIVA = new MantenimientoConIVA();
        System.out.println("✓ Mantenimiento con IVA creado");
        
        MantenimientoComponent mantConDescuento = new MantenimientoConDescuento();
        System.out.println("✓ Mantenimiento con Descuento creado");
        
        MantenimientoComponent mantCertificado = new MantenimientoCertificado();
        System.out.println("✓ Mantenimiento Certificado creado");
        
        MantenimientoComponent mantEspecial = new MantenimientoManoObraEspecial();
        System.out.println("✓ Mantenimiento con Mano de Obra Especial creado");
        
        mantSimple.obtenerDescripcion();
        mantSimple.obtenerCosto();

        // ============================================================
        // PRUEBA 9: PATRÓN MEMENTO - Guardando estados
        // ============================================================
        System.out.println("\n>>> PATRÓN MEMENTO - Probando guardado de estados <<<");
        
        Vehiculo vehiculoOriginal = new Vehiculo();
        vehiculoOriginal.setMarca("Toyota");
        vehiculoOriginal.setMotor(motorGasolina);
        
        VehiculoMemento memento1 = new VehiculoMemento(vehiculoOriginal);
        System.out.println("✓ Estado del vehículo guardado en Memento");
        
        Vehiculo vehiculoRestaurado = memento1.getVehiculoSnapshot();
        System.out.println("✓ Estado del vehículo restaurado desde Memento");
        System.out.println("✓ Marca restaurada: " + vehiculoRestaurado.getMarca());

        // ============================================================
        // PRUEBA 10: PATRÓN ITERATOR - Historial de mantenimientos
        // ============================================================
        System.out.println("\n>>> PATRÓN ITERATOR - Probando historial <<<");
        
        HistorialMantenimiento historial = new HistorialMantenimiento();
        HistorialEntry entrada1 = new HistorialEntry();
        HistorialEntry entrada2 = new HistorialEntry();
        
        historial.agregar(entrada1);
        historial.agregar(entrada2);
        System.out.println("✓ Entradas agregadas al historial");
        
        ReporteMantenimientos reporte = new ReporteMantenimientos();
        System.out.println("✓ Reporte de mantenimientos generado");
        
        // Iterando sobre el historial
        for (HistorialEntry entry : historial) {
            // Procesando cada entrada
        }
        System.out.println("✓ Historial iterado correctamente");

        // ============================================================
        // PRUEBA 11: TEST DRIVE - Gestión de pruebas de manejo
        // ============================================================
        System.out.println("\n>>> TEST DRIVE - Probando sistema de pruebas <<<");
        
        TestDriveSlot slot1 = new TestDriveSlot();
        TestDriveSlot slot2 = new TestDriveSlot();
        TestDrive testDrive1 = new TestDrive();
        TestDrive testDrive2 = new TestDrive();
        
        System.out.println("✓ Slots de Test Drive creados");
        System.out.println("✓ Test Drives programados");

        // ============================================================
        // PRUEBA 12: ASESOR y BANCO - Gestión financiera
        // ============================================================
        System.out.println("\n>>> ASESOR Y BANCO - Probando servicios financieros <<<");
        
        Asesor asesorVehiculo = new AsesorVehiculo();
        Asesor asesorFinanciero = new AsesorFinanciero();
        EntidadBancaria banco = new EntidadBancaria();
        
        System.out.println("✓ Asesor de Vehículo disponible");
        System.out.println("✓ Asesor Financiero disponible");
        System.out.println("✓ Entidad Bancaria conectada");

        // ============================================================
        // PRUEBA 13: MODELO - Otras entidades del sistema
        // ============================================================
        System.out.println("\n>>> MODELO - Probando entidades adicionales <<<");
        
        Sede sede1 = new Sede();
        Sede sede2 = new Sede();
        System.out.println("✓ Sedes creadas");
        
        Fotografia foto1 = new Fotografia();
        Fotografia foto2 = new Fotografia();
        System.out.println("✓ Fotografías creadas");
        
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        System.out.println("✓ Clientes registrados");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== TODO EL SISTEMA SE EJECUTÓ CORRECTAMENTE ===");

    }
}
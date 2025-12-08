/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Modelo.*;
import Asesor.*;
import Banco.*;
import AbstractFactory.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase Fachada para gestionar todo el sistema del concesionario
 * Esta clase es el punto de entrada principal para el frontend
 * 
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class GestorConcesionario {
    private static GestorConcesionario instancia; // Singleton
    
    // Listas principales del sistema
    private List<Sede> sedes;
    private List<Vehiculo> todosLosVehiculos; // Todos los vehículos de todas las sedes
    private List<AsesorVehiculo> asesoresVehiculo;
    private List<AsesorFinanciero> asesoresFinancieros;
    private List<EntidadBancaria> entidadesBancarias;
    private Map<String, AseguradoraFactory> aseguradoras; // Factories de aseguradoras
    
    // Constructor privado (Singleton)
    private GestorConcesionario() {
        this.sedes = new ArrayList<>();
        this.todosLosVehiculos = new ArrayList<>();
        this.asesoresVehiculo = new ArrayList<>();
        this.asesoresFinancieros = new ArrayList<>();
        this.entidadesBancarias = new ArrayList<>();
        this.aseguradoras = new HashMap<>();
        inicializarAseguradoras();
    }
    
    // Método para obtener la instancia única (Singleton)
    public static GestorConcesionario getInstancia() {
        if (instancia == null) {
            instancia = new GestorConcesionario();
        }
        return instancia;
    }
    
    // Inicializar las fábricas de aseguradoras
    private void inicializarAseguradoras() {
        aseguradoras.put("SURA", new SuraFactory());
        aseguradoras.put("MAPFRE", new MapfreFactory());
        aseguradoras.put("LIBERTY", new LibertyFactory());
    }
    
    // ========================================================================
    // MÉTODOS PARA GESTIONAR SEDES
    // ========================================================================
    
    public void agregarSede(Sede sede) {
        if (sede != null && !sedes.contains(sede)) {
            sedes.add(sede);
        }
    }
    
    public void eliminarSede(Sede sede) {
        sedes.remove(sede);
    }
    
    public List<Sede> obtenerTodasLasSedes() {
        return new ArrayList<>(sedes);
    }
    
    public Sede buscarSedePorNombre(String nombre) {
        for (Sede sede : sedes) {
            if (sede.getNombre().equalsIgnoreCase(nombre)) {
                return sede;
            }
        }
        return null;
    }
    
    // ========================================================================
    // MÉTODOS PARA GESTIONAR VEHÍCULOS
    // ========================================================================
    
    /**
     * Registra un vehículo en una sede específica
     */
    public void registrarVehiculo(Vehiculo vehiculo, Sede sede) {
        if (vehiculo != null && sede != null) {
            sede.registrarVehiculo(vehiculo);
            if (!todosLosVehiculos.contains(vehiculo)) {
                todosLosVehiculos.add(vehiculo);
            }
        }
    }
    
    /**
     * Obtiene todos los vehículos de todas las sedes
     */
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return new ArrayList<>(todosLosVehiculos);
    }
    
    /**
     * Busca vehículos por marca
     */
    public List<Vehiculo> buscarPorMarca(String marca) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : todosLosVehiculos) {
            if (v.getMarca() != null && v.getMarca().toLowerCase().contains(marca.toLowerCase())) {
                resultado.add(v);
            }
        }
        return resultado;
    }
    
    /**
     * Busca vehículos por último dígito de placa
     */
    public List<Vehiculo> buscarPorUltimoDigitoPlaca(int digito) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : todosLosVehiculos) {
            if (v.getUltimoDigitoPlaca() == digito) {
                resultado.add(v);
            }
        }
        return resultado;
    }
    
    /**
     * Busca vehículos por tipo (SUV, sedan, camioneta, etc.)
     */
    public List<Vehiculo> buscarPorTipo(String tipo) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : todosLosVehiculos) {
            if (v.getTipo() != null && v.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(v);
            }
        }
        return resultado;
    }
    
    /**
     * Busca vehículos por modelo
     */
    public List<Vehiculo> buscarPorModelo(String modelo) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : todosLosVehiculos) {
            if (v.getModelo() != null && v.getModelo().toLowerCase().contains(modelo.toLowerCase())) {
                resultado.add(v);
            }
        }
        return resultado;
    }
    
    /**
     * Busca vehículos por rango de precio
     */
    public List<Vehiculo> buscarPorRangoPrecio(double precioMin, double precioMax) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : todosLosVehiculos) {
            if (v.getPrecioVenta() >= precioMin && v.getPrecioVenta() <= precioMax) {
                resultado.add(v);
            }
        }
        return resultado;
    }
    
    /**
     * Busca vehículos por capacidad de pasajeros
     */
    public List<Vehiculo> buscarPorCapacidadPasajeros(int capacidad) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : todosLosVehiculos) {
            if (v.getPasajeros() >= capacidad) {
                resultado.add(v);
            }
        }
        return resultado;
    }
    
    // ========================================================================
    // MÉTODOS PARA ORDENAR VEHÍCULOS
    // ========================================================================
    
    /**
     * Ordena vehículos por precio (ascendente o descendente)
     */
    public List<Vehiculo> ordenarPorPrecio(List<Vehiculo> vehiculos, boolean ascendente) {
        List<Vehiculo> resultado = new ArrayList<>(vehiculos);
        Collections.sort(resultado, new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo v1, Vehiculo v2) {
                if (ascendente) {
                    return Double.compare(v1.getPrecioVenta(), v2.getPrecioVenta());
                } else {
                    return Double.compare(v2.getPrecioVenta(), v1.getPrecioVenta());
                }
            }
        });
        return resultado;
    }
    
    /**
     * Ordena vehículos por modelo (alfabéticamente)
     */
    public List<Vehiculo> ordenarPorModelo(List<Vehiculo> vehiculos) {
        List<Vehiculo> resultado = new ArrayList<>(vehiculos);
        Collections.sort(resultado, new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo v1, Vehiculo v2) {
                return v1.getModelo().compareToIgnoreCase(v2.getModelo());
            }
        });
        return resultado;
    }
    
    /**
     * Ordena vehículos por marca (alfabéticamente)
     */
    public List<Vehiculo> ordenarPorMarca(List<Vehiculo> vehiculos) {
        List<Vehiculo> resultado = new ArrayList<>(vehiculos);
        Collections.sort(resultado, new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo v1, Vehiculo v2) {
                return v1.getMarca().compareToIgnoreCase(v2.getMarca());
            }
        });
        return resultado;
    }
    
    /**
     * Ordena vehículos por capacidad de pasajeros
     */
    public List<Vehiculo> ordenarPorCapacidad(List<Vehiculo> vehiculos, boolean ascendente) {
        List<Vehiculo> resultado = new ArrayList<>(vehiculos);
        Collections.sort(resultado, new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo v1, Vehiculo v2) {
                if (ascendente) {
                    return Integer.compare(v1.getPasajeros(), v2.getPasajeros());
                } else {
                    return Integer.compare(v2.getPasajeros(), v1.getPasajeros());
                }
            }
        });
        return resultado;
    }
    
    // ========================================================================
    // MÉTODOS PARA ASEGURADORAS
    // ========================================================================
    
    /**
     * Obtiene cotizaciones de pólizas de todas las aseguradoras para un vehículo
     */
    public Map<String, Double> obtenerCotizacionesSeguro(Vehiculo vehiculo) {
        Map<String, Double> cotizaciones = new HashMap<>();
        
        if (!vehiculo.isCandidatoSeguroTodoRiesgo()) {
            return cotizaciones; // Retorna vacío si no es candidato
        }
        
        for (Map.Entry<String, AseguradoraFactory> entry : aseguradoras.entrySet()) {
            String nombreAseguradora = entry.getKey();
            AseguradoraFactory factory = entry.getValue();
            
            // Crear póliza y obtener precio
            Poliza poliza = factory.crearPoliza();
            double precioAnual = poliza.calcularPrecioAnual(vehiculo.getPrecioVenta());
            
            cotizaciones.put(nombreAseguradora, precioAnual);
        }
        
        return cotizaciones;
    }
    
    /**
     * Obtiene información detallada de seguros para un vehículo
     */
    public String obtenerInformacionSeguros(Vehiculo vehiculo) {
        if (!vehiculo.isCandidatoSeguroTodoRiesgo()) {
            return "Este vehículo no es candidato para seguro todo riesgo.";
        }
        
        StringBuilder info = new StringBuilder();
        info.append("=== COTIZACIONES DE SEGURO TODO RIESGO ===\n");
        info.append("Vehículo: ").append(vehiculo).append("\n");
        info.append("Precio: $").append(String.format("%.2f", vehiculo.getPrecioVenta())).append("\n\n");
        
        Map<String, Double> cotizaciones = obtenerCotizacionesSeguro(vehiculo);
        for (Map.Entry<String, Double> entry : cotizaciones.entrySet()) {
            info.append(entry.getKey()).append(": $")
                .append(String.format("%.2f", entry.getValue()))
                .append(" anual\n");
        }
        
        return info.toString();
    }
    
    // ========================================================================
    // MÉTODOS PARA GESTIONAR ASESORES
    // ========================================================================
    
    public void agregarAsesorVehiculo(AsesorVehiculo asesor) {
        if (asesor != null && !asesoresVehiculo.contains(asesor)) {
            asesoresVehiculo.add(asesor);
        }
    }
    
    public void agregarAsesorFinanciero(AsesorFinanciero asesor) {
        if (asesor != null && !asesoresFinancieros.contains(asesor)) {
            asesoresFinancieros.add(asesor);
        }
    }
    
    public List<AsesorVehiculo> obtenerAsesoresVehiculo() {
        return new ArrayList<>(asesoresVehiculo);
    }
    
    public List<AsesorFinanciero> obtenerAsesoresFinancieros() {
        return new ArrayList<>(asesoresFinancieros);
    }
    
    /**
     * Asigna un asesor a un vehículo
     */
    public void asignarAsesorAVehiculo(Vehiculo vehiculo, AsesorVehiculo asesor) {
        if (vehiculo != null && asesor != null) {
            asesor.asignarVehiculo(vehiculo);
        }
    }
    
    // ========================================================================
    // MÉTODOS PARA GESTIONAR BANCOS
    // ========================================================================
    
    public void agregarEntidadBancaria(EntidadBancaria entidad) {
        if (entidad != null && !entidadesBancarias.contains(entidad)) {
            entidadesBancarias.add(entidad);
        }
    }
    
    public List<EntidadBancaria> obtenerEntidadesBancarias() {
        return new ArrayList<>(entidadesBancarias);
    }
    
    /**
     * Obtiene opciones de financiamiento de todos los bancos para un vehículo
     */
    public Map<String, Map<Integer, Double>> obtenerOpcionesFinanciamiento(Vehiculo vehiculo) {
        Map<String, Map<Integer, Double>> opciones = new HashMap<>();
        int[] plazos = {12, 24, 36, 48, 60}; // Plazos en meses
        
        for (EntidadBancaria banco : entidadesBancarias) {
            Map<Integer, Double> cuotasPorPlazo = new HashMap<>();
            
            for (int plazo : plazos) {
                double cuota = banco.calcularCuotaMensual(vehiculo.getPrecioVenta(), plazo);
                cuotasPorPlazo.put(plazo, cuota);
            }
            
            opciones.put(banco.getNombre(), cuotasPorPlazo);
        }
        
        return opciones;
    }
    
    // ========================================================================
    // MÉTODOS DE UTILIDAD Y ESTADÍSTICAS
    // ========================================================================
    
    /**
     * Obtiene estadísticas generales del concesionario
     */
    public String obtenerEstadisticas() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS DEL CONCESIONARIO ===\n");
        stats.append("Total de sedes: ").append(sedes.size()).append("\n");
        stats.append("Total de vehículos: ").append(todosLosVehiculos.size()).append("\n");
        stats.append("Asesores de vehículos: ").append(asesoresVehiculo.size()).append("\n");
        stats.append("Asesores financieros: ").append(asesoresFinancieros.size()).append("\n");
        stats.append("Entidades bancarias: ").append(entidadesBancarias.size()).append("\n");
        
        // Precio promedio
        if (!todosLosVehiculos.isEmpty()) {
            double precioPromedio = todosLosVehiculos.stream()
                .mapToDouble(Vehiculo::getPrecioVenta)
                .average()
                .orElse(0.0);
            stats.append("Precio promedio: $").append(String.format("%.2f", precioPromedio)).append("\n");
        }
        
        return stats.toString();
    }
    
    /**
     * Obtiene los vehículos más caros
     */
    public List<Vehiculo> obtenerVehiculosMasCaros(int cantidad) {
        List<Vehiculo> ordenados = ordenarPorPrecio(todosLosVehiculos, false);
        return ordenados.subList(0, Math.min(cantidad, ordenados.size()));
    }
    
    /**
     * Obtiene los vehículos más económicos
     */
    public List<Vehiculo> obtenerVehiculosMasEconomicos(int cantidad) {
        List<Vehiculo> ordenados = ordenarPorPrecio(todosLosVehiculos, true);
        return ordenados.subList(0, Math.min(cantidad, ordenados.size()));
    }
    
    /**
     * Obtiene tipos de vehículos disponibles
     */
    public Set<String> obtenerTiposDisponibles() {
        Set<String> tipos = new HashSet<>();
        for (Vehiculo v : todosLosVehiculos) {
            if (v.getTipo() != null) {
                tipos.add(v.getTipo());
            }
        }
        return tipos;
    }
    
    /**
     * Obtiene marcas disponibles
     */
    public Set<String> obtenerMarcasDisponibles() {
        Set<String> marcas = new HashSet<>();
        for (Vehiculo v : todosLosVehiculos) {
            if (v.getMarca() != null) {
                marcas.add(v.getMarca());
            }
        }
        return marcas;
    }
    
    // ========================================================================
    // MÉTODOS PARA IMPRIMIR INFORMACIÓN (útil para debugging y consola)
    // ========================================================================
    
    public void imprimirTodosLosVehiculos() {
        System.out.println("\n=== CATÁLOGO COMPLETO DE VEHÍCULOS ===");
        for (Vehiculo v : todosLosVehiculos) {
            System.out.println(v.obtenerInformacionCompleta());
            System.out.println("---");
        }
    }
    
    public void imprimirVehiculosPorSede() {
        System.out.println("\n=== VEHÍCULOS POR SEDE ===");
        for (Sede sede : sedes) {
            System.out.println("\n" + sede.obtenerInformacionCompleta());
            for (Vehiculo v : sede.listarVehiculos()) {
                System.out.println("  - " + v);
            }
        }
    }
}

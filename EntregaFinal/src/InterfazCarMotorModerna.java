/*
 * Interfaz Gráfica Moderna del Sistema CarMotor
 */
import Gestor.GestorConcesionario;
import Modelo.*;
import Asesor.*;
import Banco.*;
import Bridge.*;
import TestDrive.*;
import Historial.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.time.LocalDateTime;
import java.io.File;

/**
 * Interfaz gráfica moderna del concesionario CarMotor
 * 
 * @author Juan Diego Martínez – 20231020131
 * @author Luis Felipe Suárez – 2023102033
 * @author Carlos Brito – 20241020147
 * @author Iván López – 20232020113
 */
public class InterfazCarMotorModerna extends JFrame {
    private GestorConcesionario gestor;
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;
    private JTextArea areaDetalles;
    private JLabel labelLogo;
    
    // Paleta de colores moderna
    private static final Color COLOR_PRIMARIO = new Color(41, 128, 185);      // Azul elegante
    private static final Color COLOR_SECUNDARIO = new Color(52, 73, 94);      // Azul oscuro
    private static final Color COLOR_ACENTO = new Color(231, 76, 60);         // Rojo acento
    private static final Color COLOR_EXITO = new Color(46, 204, 113);         // Verde
    private static final Color COLOR_FONDO = new Color(236, 240, 241);        // Gris claro
    private static final Color COLOR_PANEL = new Color(255, 255, 255);        // Blanco
    private static final Color COLOR_TEXTO = new Color(44, 62, 80);           // Texto oscuro
    private static final Color COLOR_HOVER = new Color(52, 152, 219);         // Azul hover
    
    // Componentes de búsqueda
    private JTextField txtBuscar;
    private JComboBox<String> cbTipoBusqueda;
    private JComboBox<String> cbOrdenamiento;
    
    public InterfazCarMotorModerna() {
        gestor = GestorConcesionario.getInstancia();
        inicializarDatos();
        configurarVentana();
        crearComponentes();
        cargarVehiculos();
    }
    
    private void inicializarDatos() {
        // Crear sedes
        Sede sedeBogota = new Sede("CarMotor Bogotá", "Calle 100 #15-20", "601-2345678", 8, 18);
        Sede sedeMedellin = new Sede("CarMotor Medellín", "Av. El Poblado #45-12", "604-3456789", 9, 19);
        Sede sedeCali = new Sede("CarMotor Cali", "Cra 5 #70-80", "602-4567890", 8, 17);
        
        gestor.agregarSede(sedeBogota);
        gestor.agregarSede(sedeMedellin);
        gestor.agregarSede(sedeCali);
        
        // Crear asesores
        AsesorVehiculo asesor1 = new AsesorVehiculo("Carlos Rodríguez", "/img/carlos.jpg", 
                                                      "carlos@carmotor.com | 300-1234567", "SUVs y Camionetas");
        AsesorVehiculo asesor2 = new AsesorVehiculo("Ana Martínez", "/img/ana.jpg", 
                                                      "ana@carmotor.com | 301-2345678", "Sedanes");
        AsesorVehiculo asesor3 = new AsesorVehiculo("Luis Fernández", "/img/luis.jpg", 
                                                      "luis@carmotor.com | 302-3456789", "Vehículos de Lujo");
        
        gestor.agregarAsesorVehiculo(asesor1);
        gestor.agregarAsesorVehiculo(asesor2);
        gestor.agregarAsesorVehiculo(asesor3);
        
        // Crear bancos
        EntidadBancaria bancolombia = new EntidadBancaria("Bancolombia", "/img/bancolombia.png", 0.12);
        EntidadBancaria davivienda = new EntidadBancaria("Davivienda", "/img/davivienda.png", 0.13);
        EntidadBancaria bogota = new EntidadBancaria("Banco de Bogotá", "/img/bogota.png", 0.115);
        
        gestor.agregarEntidadBancaria(bancolombia);
        gestor.agregarEntidadBancaria(davivienda);
        gestor.agregarEntidadBancaria(bogota);
        
        // Crear vehículos de ejemplo
        crearVehiculosEjemplo(sedeBogota, sedeMedellin, sedeCali, asesor1, asesor2, asesor3);
        
        // Crear slots de test drive
        crearSlotsTestDrive(sedeBogota);
        crearSlotsTestDrive(sedeMedellin);
        crearSlotsTestDrive(sedeCali);
    }
    
    private void crearSlotsTestDrive(Sede sede) {
        for (int dia = 1; dia <= 7; dia++) {
            for (int hora = 10; hora <= 17; hora++) {
                TestDriveSlot slot = new TestDriveSlot(
                    LocalDateTime.now().plusDays(dia).withHour(hora).withMinute(0).withSecond(0)
                );
                sede.agregarSlotTestDrive(slot);
            }
        }
    }
    
    private void crearVehiculosEjemplo(Sede sede1, Sede sede2, Sede sede3, 
                                       AsesorVehiculo a1, AsesorVehiculo a2, AsesorVehiculo a3) {
        // Vehículo 1: Toyota RAV4
        Vehiculo rav4 = new Vehiculo("Toyota", "RAV4", "SUV", 2022, 4, 5, 85000000, new MotorGasolina());
        rav4.setDescripcion("SUV familiar, perfecto estado");
        rav4.setCandidatoSeguroTodoRiesgo(true);
        rav4.agregarFotografia(new Fotografia("exterior", "/fotos/rav4_ext.jpg", "1920x1080"));
        rav4.agregarFotografia(new Fotografia("interior", "/fotos/rav4_int.jpg", "1920x1080"));
        rav4.getHistorialMantenimiento().agregar(
            new HistorialEntry(new Date(122, 5, 15), "Cambio de aceite", 
                             "Cambio de aceite y filtro", 150000, "Toyota Bogotá")
        );
        gestor.asignarAsesorAVehiculo(rav4, a1);
        gestor.registrarVehiculo(rav4, sede1);
        
        // Vehículo 2: Mazda 3
        Vehiculo mazda3 = new Vehiculo("Mazda", "Mazda 3", "Sedan", 2021, 7, 5, 65000000, new MotorGasolina());
        mazda3.setDescripcion("Sedan deportivo, excelente estado");
        mazda3.setCandidatoSeguroTodoRiesgo(false);
        mazda3.agregarFotografia(new Fotografia("exterior", "/fotos/mazda3_ext.jpg", "1920x1080"));
        gestor.asignarAsesorAVehiculo(mazda3, a2);
        gestor.registrarVehiculo(mazda3, sede1);
        
        // Vehículo 3: Chevrolet Tahoe
        Vehiculo tahoe = new Vehiculo("Chevrolet", "Tahoe", "Camioneta 4x4", 2020, 2, 7, 120000000, new MotorDiesel());
        tahoe.setDescripcion("Camioneta de lujo, 4x4");
        tahoe.setCandidatoSeguroTodoRiesgo(true);
        gestor.asignarAsesorAVehiculo(tahoe, a3);
        gestor.registrarVehiculo(tahoe, sede2);
        
        // Vehículo 4: BMW X5
        Vehiculo bmw = new Vehiculo("BMW", "X5", "SUV", 2023, 9, 5, 180000000, new MotorGasolina());
        bmw.setDescripcion("SUV de lujo premium");
        bmw.setCandidatoSeguroTodoRiesgo(true);
        gestor.asignarAsesorAVehiculo(bmw, a3);
        gestor.registrarVehiculo(bmw, sede3);
        
        // Vehículo 5: Renault Logan
        Vehiculo logan = new Vehiculo("Renault", "Logan", "Sedan", 2019, 5, 5, 35000000, new MotorGasolina());
        logan.setDescripcion("Sedan económico, ideal para familia");
        logan.setCandidatoSeguroTodoRiesgo(false);
        gestor.asignarAsesorAVehiculo(logan, a2);
        gestor.registrarVehiculo(logan, sede3);
        
        // Vehículo 6: Tesla Model 3
        Vehiculo tesla = new Vehiculo("Tesla", "Model 3", "Sedan", 2023, 0, 5, 150000000, new MotorElectrico());
        tesla.setDescripcion("Vehículo eléctrico de última generación");
        tesla.setCandidatoSeguroTodoRiesgo(true);
        gestor.asignarAsesorAVehiculo(tesla, a2);
        gestor.registrarVehiculo(tesla, sede1);
        
        // Vehículo 7: Nissan Kicks
        Vehiculo kicks = new Vehiculo("Nissan", "Kicks", "SUV", 2021, 3, 5, 72000000, new MotorGasolina());
        kicks.setDescripcion("SUV urbano compacto");
        kicks.setCandidatoSeguroTodoRiesgo(false);
        gestor.asignarAsesorAVehiculo(kicks, a1);
        gestor.registrarVehiculo(kicks, sede2);
        
        // Vehículo 8: Ford Ranger
        Vehiculo ranger = new Vehiculo("Ford", "Ranger", "Camioneta 4x4", 2022, 8, 5, 95000000, new MotorDiesel());
        ranger.setDescripcion("Camioneta 4x4 para trabajo y aventura");
        ranger.setCandidatoSeguroTodoRiesgo(true);
        gestor.asignarAsesorAVehiculo(ranger, a1);
        gestor.registrarVehiculo(ranger, sede2);
    }
    
    private void configurarVentana() {
        setTitle("CarMotor - Sistema de Gestión de Concesionario");
        setSize(1400, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO);
    }
    
    private void crearComponentes() {
        setLayout(new BorderLayout(15, 15));
        
        // Panel superior con logo y título
        add(crearPanelSuperior(), BorderLayout.NORTH);
        
        // Panel central con tabla y detalles
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBackground(COLOR_FONDO);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        
        // Panel izquierdo: búsqueda y tabla
        JPanel panelIzquierdo = new JPanel(new BorderLayout(10, 10));
        panelIzquierdo.setBackground(COLOR_FONDO);
        panelIzquierdo.add(crearPanelBusqueda(), BorderLayout.NORTH);
        panelIzquierdo.add(crearPanelTabla(), BorderLayout.CENTER);
        
        // Panel derecho: detalles
        JPanel panelDerecho = crearPanelDetalles();
        
        panelCentral.add(panelIzquierdo, BorderLayout.CENTER);
        panelCentral.add(panelDerecho, BorderLayout.EAST);
        
        add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior con botones de acción
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_SECUNDARIO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        // Logo en la esquina izquierda
        try {
            File logoFile = new File("imagenes/logo.jpg");
            if (logoFile.exists()) {
                ImageIcon logoIcon = new ImageIcon("imagenes/logo.jpg");
                Image logoImg = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                labelLogo = new JLabel(new ImageIcon(logoImg));
                labelLogo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
                panel.add(labelLogo, BorderLayout.WEST);
            }
        } catch (Exception e) {
            System.err.println("No se pudo cargar el logo: " + e.getMessage());
        }
        
        // Título y subtítulo en el centro
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        panelTitulo.setBackground(COLOR_SECUNDARIO);
        
        JLabel lblTitulo = new JLabel("CARMOTOR");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSubtitulo = new JLabel("Sistema de Gestión de Concesionario");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(189, 195, 199));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelTitulo.add(lblTitulo);
        panelTitulo.add(Box.createVerticalStrut(5));
        panelTitulo.add(lblSubtitulo);
        
        panel.add(panelTitulo, BorderLayout.CENTER);
        
        // Información adicional en la esquina derecha
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(COLOR_SECUNDARIO);
        
        JLabel lblVehiculos = new JLabel(gestor.obtenerTodosLosVehiculos().size() + " Vehículos");
        lblVehiculos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblVehiculos.setForeground(COLOR_EXITO);
        lblVehiculos.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        JLabel lblSedes = new JLabel(gestor.obtenerTodasLasSedes().size() + " Sedes");
        lblSedes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSedes.setForeground(new Color(189, 195, 199));
        lblSedes.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        panelInfo.add(lblVehiculos);
        panelInfo.add(Box.createVerticalStrut(3));
        panelInfo.add(lblSedes);
        
        panel.add(panelInfo, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Panel superior con título
        JLabel lblTitulo = new JLabel("Búsqueda y Filtros");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COLOR_PRIMARIO);
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        // Panel central con controles
        JPanel panelControles = new JPanel(new GridLayout(2, 1, 10, 10));
        panelControles.setBackground(COLOR_PANEL);
        
        // Fila 1: Búsqueda
        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        fila1.setBackground(COLOR_PANEL);
        
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblBuscar.setForeground(COLOR_TEXTO);
        
        txtBuscar = new JTextField(20);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_PRIMARIO, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        cbTipoBusqueda = new JComboBox<>(new String[]{
            "Marca", "Último dígito placa", "Tipo", "Modelo", "Rango precio", "Capacidad"
        });
        cbTipoBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        estilizarComboBox(cbTipoBusqueda);
        
        JButton btnBuscar = crearBotonEstilizado("Buscar", COLOR_PRIMARIO);
        btnBuscar.addActionListener(e -> buscarVehiculos());
        
        JButton btnLimpiar = crearBotonEstilizado("Limpiar", COLOR_ACENTO);
        btnLimpiar.addActionListener(e -> {
            txtBuscar.setText("");
            cargarVehiculos();
        });
        
        fila1.add(lblBuscar);
        fila1.add(txtBuscar);
        fila1.add(cbTipoBusqueda);
        fila1.add(btnBuscar);
        fila1.add(btnLimpiar);
        
        // Fila 2: Ordenamiento
        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        fila2.setBackground(COLOR_PANEL);
        
        JLabel lblOrdenar = new JLabel("Ordenar por:");
        lblOrdenar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblOrdenar.setForeground(COLOR_TEXTO);
        
        cbOrdenamiento = new JComboBox<>(new String[]{
            "Precio (menor a mayor)", "Precio (mayor a menor)", 
            "Marca (A-Z)", "Marca (Z-A)", 
            "Modelo", "Capacidad"
        });
        cbOrdenamiento.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        estilizarComboBox(cbOrdenamiento);
        
        JButton btnOrdenar = crearBotonEstilizado("Aplicar", COLOR_EXITO);
        btnOrdenar.addActionListener(e -> ordenarVehiculos());
        
        fila2.add(lblOrdenar);
        fila2.add(cbOrdenamiento);
        fila2.add(btnOrdenar);
        
        panelControles.add(fila1);
        panelControles.add(fila2);
        
        panel.add(panelControles, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Título
        JLabel lblTitulo = new JLabel("Inventario de Vehículos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COLOR_PRIMARIO);
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        // Tabla
        String[] columnas = {"Marca", "Modelo", "Tipo", "Año", "Placa", "Pasajeros", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaVehiculos.setRowHeight(35);
        tablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVehiculos.setSelectionBackground(COLOR_HOVER);
        tablaVehiculos.setSelectionForeground(Color.WHITE);
        tablaVehiculos.setGridColor(new Color(189, 195, 199));
        
        // Estilizar encabezado
        JTableHeader header = tablaVehiculos.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(COLOR_PRIMARIO);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Centrar contenido de columnas numéricas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaVehiculos.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Año
        tablaVehiculos.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Placa
        tablaVehiculos.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); // Pasajeros
        
        // Renderer para precio
        DefaultTableCellRenderer precioRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.RIGHT);
                if (!isSelected) {
                    setForeground(COLOR_EXITO);
                    setFont(new Font("Segoe UI", Font.BOLD, 13));
                }
                return c;
            }
        };
        tablaVehiculos.getColumnModel().getColumn(6).setCellRenderer(precioRenderer);
        
        // Listener para selección
        tablaVehiculos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetallesVehiculo();
            }
        });
        
        // Ajustar anchos
        tablaVehiculos.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaVehiculos.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablaVehiculos.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablaVehiculos.getColumnModel().getColumn(3).setPreferredWidth(60);
        tablaVehiculos.getColumnModel().getColumn(4).setPreferredWidth(60);
        tablaVehiculos.getColumnModel().getColumn(5).setPreferredWidth(80);
        tablaVehiculos.getColumnModel().getColumn(6).setPreferredWidth(120);
        
        JScrollPane scrollPane = new JScrollPane(tablaVehiculos);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelDetalles() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(COLOR_PANEL);
        panel.setPreferredSize(new Dimension(420, 0));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Título
        JLabel lblTitulo = new JLabel("Detalles del Vehículo");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COLOR_PRIMARIO);
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        // Área de detalles
        areaDetalles = new JTextArea();
        areaDetalles.setEditable(false);
        areaDetalles.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaDetalles.setMargin(new Insets(10, 10, 10, 10));
        areaDetalles.setBackground(new Color(250, 250, 250));
        areaDetalles.setForeground(COLOR_TEXTO);
        areaDetalles.setLineWrap(true);
        areaDetalles.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(areaDetalles);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));
        
        JButton btnSeguros = crearBotonAccion("Ver Seguros", COLOR_PRIMARIO);
        btnSeguros.addActionListener(e -> mostrarSeguros());
        
        JButton btnFinanciamiento = crearBotonAccion("Financiamiento", COLOR_EXITO);
        btnFinanciamiento.addActionListener(e -> mostrarFinanciamiento());
        
        JButton btnHistorial = crearBotonAccion("Historial", new Color(155, 89, 182));
        btnHistorial.addActionListener(e -> mostrarHistorial());
        
        JButton btnTestDrive = crearBotonAccion("Test Drive", COLOR_ACENTO);
        btnTestDrive.addActionListener(e -> agendarTestDrive());
        
        JButton btnEstadisticas = crearBotonAccion("Estadísticas", new Color(230, 126, 34));
        btnEstadisticas.addActionListener(e -> mostrarEstadisticas());
        
        panel.add(btnSeguros);
        panel.add(btnFinanciamiento);
        panel.add(btnHistorial);
        panel.add(btnTestDrive);
        panel.add(btnEstadisticas);
        
        return panel;
    }
    
    private JButton crearBotonEstilizado(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
        // Efecto hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(color.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(color);
            }
        });
        
        return boton;
    }
    
    private JButton crearBotonAccion(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(180, 45));
        
        // Efecto hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(color.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(color);
            }
        });
        
        return boton;
    }
    
    private void estilizarComboBox(JComboBox<String> combo) {
        combo.setBackground(Color.WHITE);
        combo.setForeground(COLOR_TEXTO);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_PRIMARIO, 1),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
    }
    
    private void cargarVehiculos() {
        List<Vehiculo> vehiculos = gestor.obtenerTodosLosVehiculos();
        actualizarTabla(vehiculos);
    }
    
    private void actualizarTabla(List<Vehiculo> vehiculos) {
        modeloTabla.setRowCount(0);
        for (Vehiculo v : vehiculos) {
            modeloTabla.addRow(new Object[]{
                v.getMarca(),
                v.getModelo(),
                v.getTipo(),
                v.getSerie(),
                v.getUltimoDigitoPlaca(),
                v.getPasajeros(),
                String.format("$%,.0f", v.getPrecioVenta())
            });
        }
    }
    
    private void buscarVehiculos() {
        String criterio = txtBuscar.getText().trim();
        if (criterio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un criterio de búsqueda", 
                                        "Búsqueda", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        List<Vehiculo> resultados = new ArrayList<>();
        String tipoBusqueda = (String) cbTipoBusqueda.getSelectedItem();
        
        try {
            switch (tipoBusqueda) {
                case "Marca":
                    resultados = gestor.buscarPorMarca(criterio);
                    break;
                case "Último dígito placa":
                    resultados = gestor.buscarPorUltimoDigitoPlaca(Integer.parseInt(criterio));
                    break;
                case "Tipo":
                    resultados = gestor.buscarPorTipo(criterio);
                    break;
                case "Modelo":
                    resultados = gestor.buscarPorModelo(criterio);
                    break;
                case "Rango precio":
                    String[] partes = criterio.split("-");
                    if (partes.length == 2) {
                        double min = Double.parseDouble(partes[0].trim());
                        double max = Double.parseDouble(partes[1].trim());
                        resultados = gestor.buscarPorRangoPrecio(min, max);
                    } else {
                        throw new IllegalArgumentException("Formato: min-max");
                    }
                    break;
                case "Capacidad":
                    resultados = gestor.buscarPorCapacidadPasajeros(Integer.parseInt(criterio));
                    break;
            }
            
            actualizarTabla(resultados);
            
            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontraron vehículos con ese criterio",
                    "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error en la búsqueda: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void ordenarVehiculos() {
        String criterio = (String) cbOrdenamiento.getSelectedItem();
        List<Vehiculo> vehiculos = gestor.obtenerTodosLosVehiculos();
        
        switch (criterio) {
            case "Precio (menor a mayor)":
                vehiculos = gestor.ordenarPorPrecio(vehiculos, true);
                break;
            case "Precio (mayor a menor)":
                vehiculos = gestor.ordenarPorPrecio(vehiculos, false);
                break;
            case "Marca (A-Z)":
                vehiculos = gestor.ordenarPorMarca(vehiculos);
                break;
            case "Marca (Z-A)":
                List<Vehiculo> temp = gestor.ordenarPorMarca(vehiculos);
                Collections.reverse(temp);
                vehiculos = temp;
                break;
            case "Modelo":
                vehiculos = gestor.ordenarPorModelo(vehiculos);
                break;
            case "Capacidad":
                vehiculos = gestor.ordenarPorCapacidad(vehiculos, true);
                break;
        }
        
        actualizarTabla(vehiculos);
    }
    
    private void mostrarDetallesVehiculo() {
        int filaSeleccionada = tablaVehiculos.getSelectedRow();
        if (filaSeleccionada < 0) return;
        
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) return;
        
        StringBuilder detalles = new StringBuilder();
        detalles.append("===================================\n");
        detalles.append("      INFORMACION DEL VEHICULO\n");
        detalles.append("===================================\n\n");
        
        detalles.append("Marca: ").append(vehiculo.getMarca()).append("\n");
        detalles.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
        detalles.append("Tipo: ").append(vehiculo.getTipo()).append("\n");
        detalles.append("Año: ").append(vehiculo.getSerie()).append("\n");
        detalles.append("Último dígito placa: ").append(vehiculo.getUltimoDigitoPlaca()).append("\n");
        detalles.append("Capacidad: ").append(vehiculo.getPasajeros()).append(" pasajeros\n");
        detalles.append("Precio: $").append(String.format("%,.0f", vehiculo.getPrecioVenta())).append("\n");
        detalles.append("Motor: ").append(vehiculo.getMotor().getClass().getSimpleName()
                                          .replace("Motor", "")).append("\n\n");
        
        detalles.append("Descripción:\n");
        detalles.append(vehiculo.getDescripcion()).append("\n\n");
        
        if (vehiculo.getAsesor() != null) {
            detalles.append("- - - - - - - - - - - - - - - - - - - -\n\n");
            detalles.append("ASESOR ASIGNADO\n");
            detalles.append("- - - - - - - - - - - - - - - - - - - -\n");
            detalles.append("Nombre: ").append(vehiculo.getAsesor().getNombre()).append("\n");
            detalles.append("Contacto: ").append(vehiculo.getAsesor().getDatosContacto()).append("\n");
            detalles.append("Especialidad: ").append(vehiculo.getAsesor().getEspecialidad()).append("\n\n");
        }
        
        detalles.append("- - - - - - - - - - - - - - - - - - - -\n");
        detalles.append("Seguro todo riesgo: ")
               .append(vehiculo.isCandidatoSeguroTodoRiesgo() ? "SÍ " : "NO")
               .append("\n");
        
        areaDetalles.setText(detalles.toString());
    }
    
    private Vehiculo obtenerVehiculoSeleccionado() {
        int filaSeleccionada = tablaVehiculos.getSelectedRow();
        if (filaSeleccionada < 0) return null;
        
        String marca = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        String modelo = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        
        for (Vehiculo v : gestor.obtenerTodosLosVehiculos()) {
            if (v.getMarca().equals(marca) && v.getModelo().equals(modelo)) {
                return v;
            }
        }
        return null;
    }
    
    private void mostrarSeguros() {
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un vehículo",
                                        "Seguros", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!vehiculo.isCandidatoSeguroTodoRiesgo()) {
            JOptionPane.showMessageDialog(this, 
                "Este vehículo no califica para seguro todo riesgo",
                "Seguros", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Map<String, Double> cotizaciones = gestor.obtenerCotizacionesSeguro(vehiculo);
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("COTIZACIONES DE SEGURO TODO RIESGO\n");
        mensaje.append("- - - - - - - - - - - - - - - - - - - -\n\n");
        mensaje.append("Vehículo: ").append(vehiculo.getMarca()).append(" ")
               .append(vehiculo.getModelo()).append("\n");
        mensaje.append("Valor: $").append(String.format("%,.0f", vehiculo.getPrecioVenta()))
               .append("\n\n");
        mensaje.append("- - - - - - - - - - - - - - - - - - - -\n");
        mensaje.append("PRECIOS ANUALES:\n");
        mensaje.append("- - - - - - - - - - - - - - - - - - - -\n\n");
        
        for (Map.Entry<String, Double> entry : cotizaciones.entrySet()) {
            mensaje.append("🏢 ").append(entry.getKey()).append("\n");
            mensaje.append("   $").append(String.format("%,.0f", entry.getValue()))
                   .append(" / año\n\n");
        }
        
        JTextArea area = new JTextArea(mensaje.toString());
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 300));
        
        JOptionPane.showMessageDialog(this, scroll, "Cotizaciones de Seguros",
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarFinanciamiento() {
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un vehículo",
                                        "Financiamiento", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String cuotaStr = JOptionPane.showInputDialog(this, 
            "Ingrese la cuota inicial (0 para ninguna):",
            "Financiamiento", JOptionPane.QUESTION_MESSAGE);
        
        if (cuotaStr == null) return;
        
        try {
            double cuotaInicial = Double.parseDouble(cuotaStr);
            Map<String, Map<Integer, Double>> opciones = gestor.obtenerOpcionesFinanciamiento(vehiculo);
            
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("OPCIONES DE FINANCIAMIENTO\n");
            mensaje.append("═══════════════════════════════════\n\n");
            mensaje.append("Vehículo: ").append(vehiculo.getMarca()).append(" ")
                   .append(vehiculo.getModelo()).append("\n");
            mensaje.append("Precio: $").append(String.format("%,.0f", vehiculo.getPrecioVenta())).append("\n");
            mensaje.append("Cuota inicial: $").append(String.format("%,.0f", cuotaInicial)).append("\n");
            mensaje.append("A financiar: $").append(String.format("%,.0f", vehiculo.getPrecioVenta() - cuotaInicial))
                   .append("\n\n");
            
            for (Map.Entry<String, Map<Integer, Double>> banco : opciones.entrySet()) {
                mensaje.append("- - - - - - - - - - - - - - - - - - - -\n");
                mensaje.append("🏦 ").append(banco.getKey()).append("\n");
                mensaje.append("- - - - - - - - - - - - - - - - - - - -\n");
                
                for (Map.Entry<Integer, Double> plazo : banco.getValue().entrySet()) {
                    mensaje.append(String.format("  %2d meses: $%,.0f / mes\n",
                                                plazo.getKey(), plazo.getValue()));
                }
                mensaje.append("\n");
            }
            
            JTextArea area = new JTextArea(mensaje.toString());
            area.setEditable(false);
            area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(450, 400));
            
            JOptionPane.showMessageDialog(this, scroll, "Opciones de Financiamiento",
                                        JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un valor numérico válido",
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarHistorial() {
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un vehículo",
                                        "Historial", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        HistorialMantenimiento historial = vehiculo.getHistorialMantenimiento();
        
        if (historial.getCantidadServicios() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Este vehículo no tiene historial de mantenimientos",
                "Historial", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("HISTORIAL DE MANTENIMIENTOS\n");
        mensaje.append("===================================\n\n");
        mensaje.append("Vehiculo: ").append(vehiculo.getMarca()).append(" ")
               .append(vehiculo.getModelo()).append("\n\n");
        
        int contador = 1;
        for (HistorialEntry entry : historial) {
            mensaje.append("-----------------------------------\n");
            mensaje.append("Mantenimiento #").append(contador++).append("\n");
            mensaje.append("-----------------------------------\n");
            mensaje.append("Fecha: ").append(entry.getFecha()).append("\n");
            mensaje.append("Tipo: ").append(entry.getTipo()).append("\n");
            mensaje.append("Descripción: ").append(entry.getDescripcion()).append("\n");
            mensaje.append("Costo: $").append(String.format("%,.0f", entry.getCosto())).append("\n");
            mensaje.append("Taller: ").append(entry.getTaller()).append("\n\n");
        }
        
        JTextArea area = new JTextArea(mensaje.toString());
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(450, 400));
        
        JOptionPane.showMessageDialog(this, scroll, "Historial de Mantenimientos",
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void agendarTestDrive() {
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un vehículo",
                                        "Test Drive", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Buscar la sede del vehículo
        Sede sedeVehiculo = null;
        for (Sede sede : gestor.obtenerTodasLasSedes()) {
            if (sede.listarVehiculos().contains(vehiculo)) {
                sedeVehiculo = sede;
                break;
            }
        }
        
        if (sedeVehiculo == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la sede del vehículo",
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        List<TestDriveSlot> slotsDisponibles = sedeVehiculo.obtenerSlotsDisponibles();
        if (slotsDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay slots disponibles para test drive en esta sede",
                "Test Drive", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Crear diálogo personalizado
        JDialog dialogo = new JDialog(this, "Agendar Test Drive", true);
        dialogo.setLayout(new BorderLayout(15, 15));
        dialogo.setSize(600, 550);
        dialogo.setLocationRelativeTo(this);
        
        // Panel de información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(COLOR_PANEL);
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_PRIMARIO, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel lblTitulo = new JLabel("AGENDAR TEST DRIVE");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(COLOR_PRIMARIO);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblVehiculo = new JLabel("Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        lblVehiculo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblVehiculo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblSede = new JLabel("Sede: " + sedeVehiculo.getNombre());
        lblSede.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSede.setForeground(COLOR_TEXTO);
        lblSede.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelInfo.add(lblTitulo);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblVehiculo);
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(lblSede);
        
        // Panel de slots
        JPanel panelSlots = new JPanel(new BorderLayout(5, 5));
        panelSlots.setBackground(COLOR_PANEL);
        panelSlots.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel lblSlots = new JLabel("Horarios disponibles:");
        lblSlots.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (int i = 0; i < Math.min(slotsDisponibles.size(), 20); i++) {
            modeloLista.addElement(slotsDisponibles.get(i).getFechaHora().toString()
                                   .replace("T", " a las "));
        }
        
        JList<String> listaSlots = new JList<>(modeloLista);
        listaSlots.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        listaSlots.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollSlots = new JScrollPane(listaSlots);
        scrollSlots.setPreferredSize(new Dimension(550, 150));
        
        panelSlots.add(lblSlots, BorderLayout.NORTH);
        panelSlots.add(scrollSlots, BorderLayout.CENTER);
        
        // Panel de datos del cliente
        JPanel panelCliente = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCliente.setBackground(COLOR_PANEL);
        panelCliente.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARIO),
                "Datos del Cliente",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 13),
                COLOR_PRIMARIO
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblNombre = new JLabel("Nombre completo:");
        JTextField txtNombre = new JTextField();
        
        JLabel lblId = new JLabel("Cédula:");
        JTextField txtId = new JTextField();
        
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();
        
        panelCliente.add(lblNombre);
        panelCliente.add(txtNombre);
        panelCliente.add(lblId);
        panelCliente.add(txtId);
        panelCliente.add(lblEmail);
        panelCliente.add(txtEmail);
        panelCliente.add(lblTelefono);
        panelCliente.add(txtTelefono);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(COLOR_PANEL);
        
        final Vehiculo vehiculoFinal = vehiculo;
        final Sede sedeFinal = sedeVehiculo;
        final List<TestDriveSlot> slotsFinal = slotsDisponibles;
        
        JButton btnConfirmar = crearBotonAccion("Confirmar", COLOR_EXITO);
        btnConfirmar.addActionListener(e -> {
            int indiceSeleccionado = listaSlots.getSelectedIndex();
            if (indiceSeleccionado < 0) {
                JOptionPane.showMessageDialog(dialogo, "Por favor seleccione un horario",
                                            "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (txtNombre.getText().trim().isEmpty() || txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Por favor complete los datos obligatorios (Nombre y Cédula)",
                                            "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Cliente cliente = new Cliente(
                txtNombre.getText().trim(),
                txtId.getText().trim(),
                txtEmail.getText().trim(),
                txtTelefono.getText().trim()
            );
            
            TestDriveSlot slotSeleccionado = slotsFinal.get(indiceSeleccionado);
            sedeFinal.agendarTestDrive(vehiculoFinal, cliente, slotSeleccionado);
            
            JOptionPane.showMessageDialog(dialogo,
                "TEST DRIVE AGENDADO EXITOSAMENTE\n\n" +
                "Vehículo: " + vehiculoFinal + "\n" +
                "Cliente: " + cliente.getNombre() + "\n" +
                "Fecha/Hora: " + slotSeleccionado.getFechaHora().toString().replace("T", " a las ") + "\n" +
                "Sede: " + sedeFinal.getNombre() + "\n\n" +
                "Nos pondremos en contacto con usted para confirmar.",
                "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            
            dialogo.dispose();
        });
        
        JButton btnCancelar = crearBotonAccion("Cancelar", COLOR_ACENTO);
        btnCancelar.addActionListener(e -> dialogo.dispose());
        
        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);
        
        // Agregar componentes al diálogo
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(COLOR_FONDO);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        panelPrincipal.add(panelInfo, BorderLayout.NORTH);
        panelPrincipal.add(panelSlots, BorderLayout.CENTER);
        
        JPanel panelSur = new JPanel(new BorderLayout(10, 10));
        panelSur.setBackground(COLOR_FONDO);
        panelSur.add(panelCliente, BorderLayout.CENTER);
        panelSur.add(panelBotones, BorderLayout.SOUTH);
        
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);
        
        dialogo.add(panelPrincipal);
        dialogo.setVisible(true);
    }
    
    private void mostrarEstadisticas() {
        List<Vehiculo> vehiculos = gestor.obtenerTodosLosVehiculos();
        
        int totalVehiculos = vehiculos.size();
        double valorTotal = vehiculos.stream().mapToDouble(Vehiculo::getPrecioVenta).sum();
        double precioPromedio = valorTotal / totalVehiculos;
        
        Map<String, Integer> porTipo = new HashMap<>();
        Map<String, Integer> porMarca = new HashMap<>();
        
        for (Vehiculo v : vehiculos) {
            porTipo.put(v.getTipo(), porTipo.getOrDefault(v.getTipo(), 0) + 1);
            porMarca.put(v.getMarca(), porMarca.getOrDefault(v.getMarca(), 0) + 1);
        }
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("ESTADÍSTICAS DEL CONCESIONARIO\n");
        mensaje.append("- - - - - - - - - - - - - - - - - - - -\n\n");
    
        mensaje.append(" RESUMEN GENERAL\n");
        mensaje.append("- - - - - - - - - - - - - - - - - - - -\n");
        mensaje.append("Total de vehículos: ").append(totalVehiculos).append("\n");
        mensaje.append("Valor total inventario: $").append(String.format("%,.0f", valorTotal)).append("\n");
        mensaje.append("Precio promedio: $").append(String.format("%,.0f", precioPromedio)).append("\n\n");
        
        mensaje.append(" VEHÍCULOS POR TIPO\n");
        mensaje.append("- - - - - - - - - - - - - - - - - - - -\n");
        for (Map.Entry<String, Integer> entry : porTipo.entrySet()) {
            mensaje.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        mensaje.append("\n VEHÍCULOS POR MARCA\n");
        mensaje.append("- - - - - - - - - - - - - - - - - - - -\n");
        for (Map.Entry<String, Integer> entry : porMarca.entrySet()) {
            mensaje.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        mensaje.append("\n SEDES\n");
        mensaje.append("- - - - - - - - - - - - - - - - - - - -\n");
        for (Sede sede : gestor.obtenerTodasLasSedes()) {
            mensaje.append(sede.getNombre()).append(": ")
                   .append(sede.getCantidadVehiculos()).append(" vehículos\n");
        }
        
        JTextArea area = new JTextArea(mensaje.toString());
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(450, 450));
        
        JOptionPane.showMessageDialog(this, scroll, "Estadísticas del Concesionario",
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new InterfazCarMotorModerna().setVisible(true);
        });
    }
}

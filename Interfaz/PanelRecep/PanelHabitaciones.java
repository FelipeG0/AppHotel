package PanelRecep;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import ContDatos.Habitacion;
import GUI.Ventana;
import Logica.Hotel;

public class PanelHabitaciones extends JPanel {
    private JTable tablaHabitaciones;

    public PanelHabitaciones(ArrayList<Habitacion> habitaciones, Ventana ventana) {     
    	setLayout(new BorderLayout(20,20));
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelSuperior, BorderLayout.NORTH);

        // Botón "<-" (Regresar)
        JButton botonRegresar = new JButton("<-");
        botonRegresar.setForeground(Color.BLACK);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.setBorderPainted(false);
        botonRegresar.setOpaque(false);
        botonRegresar.setFont(new Font("Arial Black", Font.BOLD, 10));
        panelSuperior.add(botonRegresar, BorderLayout.WEST);
        botonRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.pantallaRecep();
            }
        });

        // Label
        JLabel labelCrearHabitaciones = new JLabel("Habitaciones Filtradas", SwingConstants.CENTER);
        labelCrearHabitaciones.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelSuperior.add(labelCrearHabitaciones, BorderLayout.CENTER);

        // Crear modelo de tabla con 6 columnas
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Piso", "Tipo", "Balcon", "Vista", "Cocina", "Disponible"});

        // Agregar filas a la tabla con los datos de las habitaciones
        for (Habitacion habitacion : habitaciones) {
            tableModel.addRow(new Object[]{
                    habitacion.getIdHabitacion(),
                    habitacion.getUbicacion(),
                    habitacion.getTipo(),
                    habitacion.getBalcon(),
                    habitacion.getVista(),
                    habitacion.getCocinaintegrada(),
                    habitacion.getDisponible()
            });
        }

        // Crear la tabla con el modelo
        tablaHabitaciones = new JTable(tableModel);

        // Agregar la tabla al JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaHabitaciones);

        // Establecer tamaño preferido del JScrollPane para habilitar el scroll cuando sea necesario
        scrollPane.setPreferredSize(new Dimension(500, 300));

        // Agregar el JScrollPane al panel
        add(scrollPane, BorderLayout.CENTER);
    }
    
}

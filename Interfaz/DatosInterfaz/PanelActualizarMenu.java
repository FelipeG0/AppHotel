package DatosInterfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import GUI.Ventana;
import Logica.Admin;

public class PanelActualizarMenu extends JPanel{
	private JTextField rutaArchivo;
	
	public PanelActualizarMenu(Ventana ventana){
		setLayout(new BorderLayout(20, 20));

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
                ventana.mostrarPantallaAdmin();
            }
        });

        // Label "Crear habitaciones"
        JLabel labelCrearHabitaciones = new JLabel("Cargar el menú del Restaurante", SwingConstants.CENTER);
        labelCrearHabitaciones.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelSuperior.add(labelCrearHabitaciones, BorderLayout.CENTER);

        // Panel central
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        add(panelCentral, BorderLayout.CENTER);

        // Caja de texto
        rutaArchivo = new JTextField();
        rutaArchivo.setPreferredSize(new Dimension(400, 50));
        PH.setPlaceHolder(rutaArchivo, "Ingrese la ruta del archivo");
        panelCentral.add(rutaArchivo);

        // Botón "Configurar info Manualmente"
        JButton botonCrearManualmente = new JButton("Configurar información de un Plato/Bebida");
        botonCrearManualmente.setForeground(Color.BLACK);
        botonCrearManualmente.setBackground(Color.WHITE);
        botonCrearManualmente.setBorderPainted(true);
        botonCrearManualmente.setOpaque(false);
        botonCrearManualmente.setFont(new Font("Georgia", Font.PLAIN, 12));
        panelCentral.add(botonCrearManualmente);
        botonCrearManualmente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.configurarPB();
                ventana.mostrarPantallaAdmin();
            }
        });

        // Panel inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelInferior, BorderLayout.SOUTH);

        // Botón "Cargar"
        JButton botonCargar = new JButton("Cargar");
        botonCargar.setForeground(Color.WHITE);
        botonCargar.setBackground(Color.BLACK);
        botonCargar.setBorderPainted(false);
        botonCargar.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelInferior.add(botonCargar);
        botonCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Admin admin = ventana.getAdminI();
                try {
					admin.cargar_menu(rutaArchivo.getText());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(PanelActualizarMenu.this, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
            }
        });
    }
}


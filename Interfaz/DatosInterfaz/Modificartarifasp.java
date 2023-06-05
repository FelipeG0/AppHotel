package DatosInterfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import GUI.Ventana;
import Logica.Admin;

import java.awt.*;
import javax.swing.*;

public class Modificartarifasp extends JPanel {
	JComboBox<String> comboBox;
	JTextField caja1;

    public Modificartarifasp(Ventana ventana) {
    	
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
                ventana.mostrarPantallaAdmin();
            }
        });

        // Label "Modificar tarifas"
        JLabel labelCrearHabitaciones = new JLabel("Modificar tarifas de los servicios", SwingConstants.CENTER);
        labelCrearHabitaciones.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelSuperior.add(labelCrearHabitaciones, BorderLayout.CENTER);

        // Panel central
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        add(panelCentral, BorderLayout.CENTER);
        
        // Etiqueta
        JLabel label = new JLabel("Selecciona un valor:");
        panelCentral.add(label);

        // Lista desplegable (combobox)
        comboBox = new JComboBox<>();
        comboBox.addItem("Seleccionar");
        comboBox.addItem("guia turistico");
        comboBox.addItem("spa");
        panelCentral.add(comboBox);

        // Caja de texto
        caja1 = new JTextField();
        caja1.setPreferredSize(new Dimension(400, 50));
        PH.setPlaceHolder(caja1, "Costo por persona");
        panelCentral.add(caja1);
        
        // Panel inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelInferior, BorderLayout.SOUTH);

        // Botón de actualización
        JButton boton3 = new JButton("Actualizar");
        boton3.setForeground(Color.WHITE);
        boton3.setBackground(Color.BLACK);
        boton3.setBorderPainted(false);
        boton3.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelInferior.add(boton3);
        boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.getAdminI().modificar_tarifa_servicio(comboBox.getSelectedItem().toString(), Integer.parseInt(caja1.getText()));      
                comboBox.setSelectedItem("Seleccionar");
                PH.setPlaceHolder(caja1, "Costo por persona");
            }
        });
    }
}

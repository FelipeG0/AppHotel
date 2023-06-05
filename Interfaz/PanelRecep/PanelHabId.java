package PanelRecep;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ContDatos.Habitacion;
import DatosInterfaz.Agghabitacionarchivo;
import DatosInterfaz.PH;
import GUI.Ventana;
import Logica.Admin;
import Logica.Recepcionista;

public class PanelHabId extends JPanel{
	private Recepcionista recep;
	private JTable tablaHabitaciones;
	
	public PanelHabId(ArrayList<Habitacion> habitaciones, Ventana ventana) {
		recep = ventana.getRecepI();
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
                ventana.pantallaRecep();
            }
        });
        
        // Panel central
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        add(panelCentral, BorderLayout.CENTER);

		//Etiquetas
		JLabel label1 = new JLabel("Habitaciones",SwingConstants.CENTER);
		label1.setBounds(100,20,800,23);
		label1.setFont(new Font("Helvetica",Font.BOLD,20));
		panelCentral.add(label1);
		
		//Tabla
		DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Piso", "Tipo", "Balcon", "Vista", "Cocina", "Disponible"});  
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
        scrollPane.setPreferredSize(new Dimension(500, 250));

        // Agregar el JScrollPane al panel
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        //Caja de texto
  		JTextField caja2 = new JTextField();
  		caja2.setBounds(35,120,360,40);
  		PH.setPlaceHolder(caja2, "Ingrese el ID de la habitación a consultar");
  		panelCentral.add(caja2);
        
        // Panel inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelInferior, BorderLayout.SOUTH);
        
        //Botones
  		JButton boton2 = new JButton("Confirmar");
  		boton2.setBounds(240,230,150,40);
  		boton2.setForeground(Color.WHITE);
  		boton2.setBackground(Color.BLACK);
  		boton2.setBorderPainted(false);
  		boton2.setFont(new Font("Helvetica",Font.BOLD,18));
  		panelInferior.add(boton2);
  		boton2.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  			    Habitacion hab = recep.habitacion_ID(caja2.getText());
  			    tableModel.setRowCount(0); // Elimina todas las filas existentes en el DefaultTableModel
  			    tableModel.addRow(new Object[]{
  			        hab.getIdHabitacion(),
  			        hab.getUbicacion(),
  			        hab.getTipo(),
  			        hab.getBalcon(),
  			        hab.getVista(),
  			        hab.getCocinaintegrada(),
  			        hab.getDisponible()
  			    });
  			    tablaHabitaciones.repaint(); // Repinta la tabla para mostrar los cambios
  			}
        });
	}

}

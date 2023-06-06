package PanelRecep;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import javax.swing.*;

import DatosInterfaz.Agghabitacionarchivo;
import DatosInterfaz.PH;
import GUI.Ventana;
import Logica.Recepcionista;



public class PanelReserva extends JPanel{
	private Recepcionista recep;
	JRadioButton rboton1;
	JRadioButton rboton2;
	JRadioButton rboton3; 


	public PanelReserva(Ventana ventana) {
		recep = ventana.getRecepI();
		setLayout(null);
		
		// Botón "<-" (Regresar)
        JButton botonRegresar = new JButton("<-");
        botonRegresar.setBounds(15,20,50,23);
        botonRegresar.setForeground(Color.BLACK);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.setBorderPainted(false);
        botonRegresar.setOpaque(false);
        botonRegresar.setFont(new Font("Arial Black", Font.BOLD, 10));
        add(botonRegresar, BorderLayout.WEST);
        botonRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.pantallaRecep();
            }
        });
        
		//Etiquetas
		JLabel label1 = new JLabel("Crear Reserva",SwingConstants.CENTER);
		label1.setBounds(120,20,190,23);
		label1.setFont(new Font("Helvetica",Font.BOLD,20));
		add(label1);
		
		JLabel label2 = new JLabel("Documento",SwingConstants.CENTER);
		label2.setBounds(27,70,120,23);
		label2.setFont(new Font("Helvetica",Font.PLAIN,18));
		add(label2);
		
		JLabel label3 = new JLabel("Tipo habitación",SwingConstants.CENTER);
		label3.setBounds(27,340,150,23);
		label3.setFont(new Font("Helvetica",Font.PLAIN,18));
		add(label3);
		
		JLabel label4 = new JLabel("Fecha inicial",SwingConstants.CENTER);
		label4.setBounds(27,160,120,23);
		label4.setFont(new Font("Helvetica",Font.PLAIN,18));
		add(label4);
		
		JLabel label5 = new JLabel("Fecha final",SwingConstants.CENTER);
		label5.setBounds(27,250,120,23);
		label5.setFont(new Font("Helvetica",Font.PLAIN,18));
		add(label5);

		//Cajas de texto
		JTextField caja1 = new JTextField();
		caja1.setBounds(35,105,360,40);
		PH.setPlaceHolder(caja1, "Ingrese los documentos del grupo separados por guión(-)");
		add(caja1);
		
		JTextField caja3 = new JTextField();
		caja3.setBounds(35,200,360,40);
		PH.setPlaceHolder(caja3, "Ingrese la fecha de ingreso de la reserva(YYYY-MM-DD)");
		add(caja3);
		
		JTextField caja4 = new JTextField();
		caja4.setBounds(35,290,360,40);
		PH.setPlaceHolder(caja4, "Ingrese la fecha de salida de la reserva(YYYY-MM-DD)");
		add(caja4);
		
		//Botones para el tipo de habitación
		JRadioButton rboton1 = new JRadioButton("Estandar",false);
		rboton1.setBounds(35,390,120,20);
		rboton1.setFont(new Font("Helvetica",Font.BOLD,15));
		add(rboton1);
		
		JRadioButton rboton2 = new JRadioButton("Suite",false);
		rboton2.setBounds(170,350,80,100);
		rboton2.setFont(new Font("Helvetica",Font.BOLD,15));
		add(rboton2);
		
		JRadioButton rboton3 = new JRadioButton("Suite doble",false);
		rboton3.setBounds(280,350,120,100);
		rboton3.setFont(new Font("Helvetica",Font.BOLD,15));
		add(rboton3);
		
		//Botones
		JButton boton2 = new JButton("Crear");
		boton2.setBounds(150,470,150,40);
		boton2.setForeground(Color.WHITE);
		boton2.setBackground(Color.BLACK);
		boton2.setBorderPainted(false);
		boton2.setFont(new Font("Helvetica",Font.BOLD,18));
		add(boton2);
		boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String tipoHab = obtenerTiposHabitacionesSeleccionadas(rboton1, rboton2, rboton3);
                try {
					String reserva = recep.nuevaReserva(caja1.getText(), tipoHab, caja3.getText(), caja4.getText());
					if (reserva.equals("No existe")) {
						JOptionPane.showMessageDialog(PanelReserva.this, "Algun huesped no se ha creado en la basse de datos", "Error", JOptionPane.ERROR_MESSAGE);
						ventana.crearHuesped();
					}else {
						JOptionPane.showMessageDialog(PanelReserva.this, reserva, "Reserva exitosa", JOptionPane.INFORMATION_MESSAGE);						
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PanelReserva.this, "Hubo algún error al crear la reserva", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
                rboton1.setSelected(false);
                rboton2.setSelected(false);
                rboton3.setSelected(false);
                PH.setPlaceHolder(caja1, "Ingrese los documentos del grupo separados por guión(-)");
                PH.setPlaceHolder(caja3, "Ingrese la fecha de ingreso de la reserva(YYYY-MM-DD)");
                PH.setPlaceHolder(caja4, "Ingrese la fecha de salida de la reserva(YYYY-MM-DD)");
                
            }
        });
	}
	public String obtenerTiposHabitacionesSeleccionadas(JRadioButton rboton1, JRadioButton rboton2, JRadioButton rboton3) {
	    StringBuilder tiposSeleccionados = new StringBuilder();

	    if (rboton1.isSelected()) {
	        tiposSeleccionados.append("estandar");
	    }

	    if (rboton2.isSelected()) {
	        if (tiposSeleccionados.length() > 0) {
	            tiposSeleccionados.append("-");
	        }
	        tiposSeleccionados.append("suite");
	    }

	    if (rboton3.isSelected()) {
	        if (tiposSeleccionados.length() > 0) {
	            tiposSeleccionados.append("-");
	        }
	        tiposSeleccionados.append("suite doble");
	    }

	    return tiposSeleccionados.toString();
	}

}

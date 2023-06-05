package PanelRecep;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import DatosInterfaz.Agghabitacionarchivo;
import DatosInterfaz.PH;
import GUI.Ventana;
import Logica.Recepcionista;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PanelHuesped extends JPanel{
	private Recepcionista recep;
	
	private JTextField nombre;
	private JTextField documento;
	private JTextField telefono;
	private JTextField correo;
	private JTextField edad;
	private String tipo;

	public PanelHuesped(Ventana ventana) {
		recep = ventana.getRecepI();
		setLayout(null);

		//Etiquetas
		JLabel label1 = new JLabel("Crear Cliente",SwingConstants.CENTER);
		label1.setBounds(120,10,190,23);
		label1.setFont(new Font("Helvetica",Font.BOLD,18));
		add(label1);
		
		JLabel label2 = new JLabel("Nombre",SwingConstants.CENTER);
		label2.setBounds(15,50,120,23);
		label2.setFont(new Font("Helvetica",Font.PLAIN,16));
		add(label2);
		
		JLabel label3 = new JLabel("Documento",SwingConstants.CENTER);
		label3.setBounds(27,140,120,23);
		label3.setFont(new Font("Helvetica",Font.PLAIN,16));
		add(label3);
		
		JLabel label4 = new JLabel("Telefono",SwingConstants.CENTER);
		label4.setBounds(17,230,120,23);
		label4.setFont(new Font("Helvetica",Font.PLAIN,16));
		add(label4);
		
		JLabel label5 = new JLabel("Correo",SwingConstants.CENTER);
		label5.setBounds(12,310,120,23);
		label5.setFont(new Font("Helvetica",Font.PLAIN,16));
		add(label5);
		
		JLabel label6 = new JLabel("Edad",SwingConstants.CENTER);
		label6.setBounds(12,400,120,23);
		label6.setFont(new Font("Helvetica",Font.PLAIN,16));
		add(label6);	
		
		//Cajas de texto
		nombre = new JTextField();
		nombre.setBounds(35,85,340,30);
		PH.setPlaceHolder(nombre, "Ingrese el nombre");
		add(nombre);
		
		documento = new JTextField();
		documento.setBounds(35,170,340,30);
		PH.setPlaceHolder(documento, "Ingrese el documento");
		add(documento);
		
		telefono = new JTextField();
		telefono.setBounds(35,270,340,30);
		PH.setPlaceHolder(telefono, "Ingrese el numero de telefono");
		add(telefono);
		
		correo = new JTextField();
		correo.setBounds(35,350,340,30);
		PH.setPlaceHolder(correo, "Ingrese el correo");
		add(correo);
		
		edad = new JTextField();
		edad.setBounds(35,440,340,30);
		PH.setPlaceHolder(edad, "Ingrese la edad del huesped");
		add(edad);
		
		//Botones para la seleccion del tipo de huesped
		JRadioButton rboton1 = new JRadioButton("Titular",false);
		rboton1.setBounds(50,500,180,20);
		rboton1.setFont(new Font("Helvetica",Font.BOLD,15));
		add(rboton1);
		
		JRadioButton rboton2 = new JRadioButton("Acompañante",false);
		rboton2.setBounds(230,500,180,20);
		rboton2.setFont(new Font("Helvetica",Font.BOLD,15));
		add(rboton2);
		
		//Botones
		JButton boton2 = new JButton("Aceptar");
		boton2.setBounds(240,530,130,30);
		boton2.setForeground(Color.WHITE);
		boton2.setBackground(Color.BLACK);
		boton2.setBorderPainted(false);
		boton2.setFont(new Font("Helvetica",Font.BOLD,15));
		add(boton2);
		boton2.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (rboton1.isSelected()) {
                    tipo = "titular";
                } else if (rboton2.isSelected()) {
                    tipo = "acompañante";
                } else {
                	JOptionPane.showMessageDialog(PanelHuesped.this, "Debe seleccionar el tipo de hueped", "Error", JOptionPane.ERROR_MESSAGE);
                }
            	try {
            		recep.crearHuespued(documento.getText(), nombre.getText(), telefono.getText(), correo.getText(), Integer.parseInt(edad.getText()), tipo);
            	}catch(Exception e1) {
            		JOptionPane.showMessageDialog(PanelHuesped.this, "Error al crear el huesped", "Error", JOptionPane.ERROR_MESSAGE);
            	}
            	
            	ventana.pantallaRecep();
            }
        });
		
		JButton boton3 = new JButton("Cancelar");
		boton3.setBounds(50,530,130,30);
		boton3.setForeground(Color.BLACK);
		boton3.setBackground(Color.WHITE);
		boton3.setBorderPainted(false);
		boton3.setFont(new Font("Helvetica",Font.BOLD,15));
		add(boton3);
		boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.pantallaRecep();
            }
        });					
	}
}

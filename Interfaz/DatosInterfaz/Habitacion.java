package DatosInterfaz;

import java.awt.*;
import javax.swing.*;

public class Habitacion extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public JPanel panel;

	public Habitacion() {
		setSize(500,325);
		setTitle("Registrarse");
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		iniciarcomp();
	}
	
	private void iniciarcomp(){
		paneles();
		etiquetas();
		botones();
		cajasTexto();
	}
	
	private void paneles(){
		panel = new JPanel();
		panel.setLayout(null);
		this.getContentPane() .add(panel);
	}
	
	private void etiquetas(){
		panel = new JPanel();
		
		panel.setLayout(null);
		this.getContentPane() .add(panel);
		
		JLabel label1 = new JLabel("Agregar Habitación",SwingConstants.CENTER);
		label1.setBounds(150,20,170,23);
		label1.setFont(new Font("Helvetica",Font.BOLD,18));
		panel.add(label1);
		
		JLabel label2 = new JLabel("Crear habitacion mediante archivo",SwingConstants.CENTER);
		label2.setBounds(19,75,250,12);
		label2.setFont(new Font("Georgia",Font.PLAIN,15));
		panel.add(label2);
		

	} 
	private void botones(){
		JButton boton1 = new JButton("<");
		boton1.setBounds(50,21,50,20);
		boton1.setForeground(Color.BLACK);
		boton1.setBackground(Color.WHITE);
		boton1.setBorderPainted(false);
		boton1.setOpaque(false);
		boton1.setFont(new Font("Arial Black",Font.BOLD,18));
		panel.add(boton1);
		
		JButton boton2 = new JButton("Cargar");
		boton2.setBounds(30,200,400,40);
		boton2.setForeground(Color.WHITE);
		boton2.setBackground(Color.BLACK);
		boton2.setBorderPainted(false);
		boton2.setFont(new Font("Helvetica",Font.BOLD,18));
		panel.add(boton2);
		
		JButton boton3 = new JButton("Crear Habitacion Manualmente");
		boton3.setBounds(185,150,300,20);
		boton3.setForeground(Color.BLACK);
		boton3.setBackground(Color.WHITE);
		boton3.setBorderPainted(false);
		boton1.setOpaque(false);
		boton3.setFont(new Font("Georgia",Font.PLAIN,12));
		panel.add(boton3);
	}
	


	private void cajasTexto(){
	
		JTextField caja1 = new JTextField();
		caja1.setBounds(30,100,400,50);
		caja1.setText("Ingrese el archivo");
		panel.add(caja1);

	}
	

}

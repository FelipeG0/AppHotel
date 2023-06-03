package DatosInterfaz;

import java.awt.*;
import javax.swing.*;

public class Modificartarifas extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public JPanel panel;

	public Modificartarifas() {
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
		
		JLabel label1 = new JLabel("Modificar Tarifas",SwingConstants.CENTER);
		label1.setBounds(150,20,170,23);
		label1.setFont(new Font("Helvetica",Font.BOLD,18));
		panel.add(label1);
		
		JLabel label2 = new JLabel("Fecha Inicial",SwingConstants.CENTER);
		label2.setBounds(30,130,100,12);
		label2.setFont(new Font("Georgia",Font.PLAIN,15));
		panel.add(label2);
		
		JLabel label3 = new JLabel("Fecha Final",SwingConstants.CENTER);
		label3.setBounds(30,130,650,12);
		label3.setFont(new Font("Georgia",Font.PLAIN,15));
		panel.add(label3);
		

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
		
		JButton boton2 = new JButton("Seleccionar tipo de habitacion  v");
		boton2.setBounds(30,65,270,35);
		boton2.setForeground(Color.WHITE);
		boton2.setBackground(Color.GRAY);
		boton2.setBorderPainted(false);
		boton2.setFont(new Font("Helvetica",Font.BOLD,15));
		panel.add(boton2);
		
		JButton boton3 = new JButton("Actualizar");
		boton3.setBounds(30,230,400,40);
		boton3.setForeground(Color.WHITE);
		boton3.setBackground(Color.BLACK);
		boton3.setBorderPainted(false);
		boton3.setFont(new Font("Helvetica",Font.BOLD,18));
		panel.add(boton3);
		

	}
	


	private void cajasTexto(){
	
		JTextField caja1 = new JTextField();
		caja1.setBounds(330,65,120,35);
		caja1.setText("$ Valor");
		panel.add(caja1);
		
		JTextField caja2 = new JTextField();
		caja2.setBounds(35,150,120,35);
		caja2.setText("DD/MM/AA");
		panel.add(caja2);
		
		JTextField caja3 = new JTextField();
		caja3.setBounds(315,150,120,35);
		caja3.setText("DD/MM/AA");
		panel.add(caja3);

	}
	
	

}
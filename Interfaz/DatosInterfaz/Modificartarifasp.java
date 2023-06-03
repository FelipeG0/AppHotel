package DatosInterfaz;

import java.awt.*;
import javax.swing.*;

public class Modificartarifasp extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public JPanel panel;

	public Modificartarifasp() {
		setSize(500,250);
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
		
		JLabel label1 = new JLabel("Modificar Tarifas Platos/Bebidas",SwingConstants.CENTER);
		label1.setBounds(100,20,300,23);
		label1.setFont(new Font("Helvetica",Font.BOLD,18));
		panel.add(label1);

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
		
		JButton boton2 = new JButton("Seleccionar tipo de servicio  v");
		boton2.setBounds(30,65,270,35);
		boton2.setForeground(Color.WHITE);
		boton2.setBackground(Color.GRAY);
		boton2.setBorderPainted(false);
		boton2.setFont(new Font("Helvetica",Font.BOLD,15));
		panel.add(boton2);
		
		JButton boton3 = new JButton("Actualizar");
		boton3.setBounds(30,130,400,40);
		boton3.setForeground(Color.WHITE);
		boton3.setBackground(Color.BLACK);
		boton3.setBorderPainted(false);
		boton3.setFont(new Font("Helvetica",Font.BOLD,18));
		panel.add(boton3);
		

	}
	


	private void cajasTexto(){
	
		JTextField caja1 = new JTextField();
		caja1.setBounds(310,65,120,35);
		caja1.setText("$ Valor");
		panel.add(caja1);
	

	}
	
	

}
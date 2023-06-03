package DatosInterfaz;

import java.awt.*;

import javax.swing.*;

public class Agghabitacion extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public JPanel panel;

	public Agghabitacion() {
		setSize(450,450);
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
		label1.setBounds(120,20,190,23);
		label1.setFont(new Font("Helvetica",Font.BOLD,20));
		panel.add(label1);
	
	} 
	private void botones(){
		
		JButton boton2 = new JButton("Continuar");
		boton2.setBounds(40,330,350,40);
		boton2.setForeground(Color.WHITE);
		boton2.setBackground(Color.BLACK);
		boton2.setBorderPainted(false);
		boton2.setFont(new Font("Helvetica",Font.BOLD,18));
		panel.add(boton2);
	}

	private void cajasTexto(){
	
		JTextField caja1 = new JTextField();
		caja1.setBounds(35,70,360,40);
		caja1.setText("Numero de Habitación");
		panel.add(caja1);
		
		JTextField caja2 = new JTextField();
		caja2.setBounds(35,130,220,40);
		caja2.setText("Tipo de Habitacion");
		panel.add(caja2);
		
		JTextField caja3 = new JTextField();
		caja3.setBounds(270,130,125,40);
		caja3.setText("Max Personas");
		panel.add(caja3);
		
		JTextField caja4 = new JTextField();
		caja4.setBounds(35,190,360,40);
		caja4.setText("Caracteristicas");
		panel.add(caja4);
		
		JTextField caja5 = new JTextField();
		caja5.setBounds(35,250,220,40);
		caja5.setText("$ Valor");
		panel.add(caja5);
		
		JTextField caja6 = new JTextField();
		caja6.setBounds(270,250,125,40);
		caja6.setText("Piso");
		panel.add(caja6);
		


	}
	

}
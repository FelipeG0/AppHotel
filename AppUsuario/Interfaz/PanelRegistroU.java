package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import GUI.Ventana;
import Logica.LoginUser;

public class PanelRegistroU extends JPanel{
	private JTextField nombreT;
	private JPasswordField contrasenaT;
	private JPasswordField confirmacionContrasenaT;
	private JButton iniciarSesion;
	private JButton registrarse;
	
	public PanelRegistroU(VentanaU ventana) {
		setLayout(null);
		
		//Botones
		registrarse = new JButton("Registrarse");
		registrarse.setBounds(28,425,300,40);
		registrarse.setForeground(Color.WHITE);
		registrarse.setBackground(Color.BLACK);
		registrarse.setBorderPainted(false);
		add(registrarse);
		
        registrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                String usuario = nombreT.getText();		             
				char[] contrasena = contrasenaT.getPassword();
				String contrasenaS = new String(contrasena);
				char[] contrasena2 = confirmacionContrasenaT.getPassword();
				String contrasenaS2 = new String(contrasena);
				
				if (contrasenaS.equals(contrasenaS2)) {
					if (LoginUser.register(usuario, contrasenaS)) {
						JOptionPane.showMessageDialog(null, "Registro realizado con exito", "Información", JOptionPane.INFORMATION_MESSAGE);
					}				
				}else {
					JOptionPane.showMessageDialog(PanelRegistroU.this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
				}  
				ventana.mostrarLogin();
            }
        });
		
		iniciarSesion = new JButton("Ya tienes cuenta? Iniciar Sesión");
		iniciarSesion.setBounds(28,470,300,40);
		iniciarSesion.setForeground(Color.BLACK);
		iniciarSesion.setBackground(Color.WHITE);
		iniciarSesion.setOpaque(false);
		iniciarSesion.setBorderPainted(false);
		add(iniciarSesion);
		
		iniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.mostrarLogin();
			}
			
		});
		
		//Etiquetas
		JLabel label1 = new JLabel("Bienvenido!",SwingConstants.CENTER);
		label1.setBounds(20,20,100,13);
		label1.setFont(new Font("Helvetica",Font.PLAIN,18));
		add(label1);
		
		JLabel label2 = new JLabel("Registrarse",SwingConstants.CENTER);
		label2.setBounds(20,60,120,23);
		label2.setFont(new Font("Helvetica",Font.BOLD,21));
		add(label2);
		
		JLabel label3 = new JLabel("Nombre",SwingConstants.LEFT);
		label3.setBounds(20,110,140,20);
		label3.setFont(new Font("Georgia",Font.PLAIN, 15));
		add(label3);
		
		JLabel label4 = new JLabel("Contraseña",SwingConstants.LEFT);
		label4.setBounds(20,180,140,20);
		label4.setFont(new Font("Georgia",Font.PLAIN,15));
		add(label4);
		
		JLabel label5 = new JLabel("Confirmar contraseña",SwingConstants.LEFT);
		label5.setBounds(15,260,200,12);
		label5.setFont(new Font("Georgia",Font.PLAIN,15));
		add(label5);
		
		
		//Cajas de texto 
		nombreT = new JTextField();
		nombreT.setBounds(25,130,300,40);
		PH.setPlaceHolder(nombreT, "Ingrese su nombre");
		add(nombreT);
		
		contrasenaT = new JPasswordField();
		contrasenaT.setBounds(25,205,300,40);
		PH.setPlaceHolderC(contrasenaT, "Ingrese su contraseña");
		add(contrasenaT);
		
		confirmacionContrasenaT = new JPasswordField();
		confirmacionContrasenaT.setBounds(25,280,300,40);
		PH.setPlaceHolderC(confirmacionContrasenaT, "Confirme su contraseña");
		add(confirmacionContrasenaT);
		
	}

}

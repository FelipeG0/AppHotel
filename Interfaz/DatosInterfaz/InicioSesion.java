package DatosInterfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import GUI.Ventana;
import Logica.Login;

public class InicioSesion extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField usuarioT;
	private JPasswordField contrasenaT;
	private JButton iniciarSesion;
	private JButton registrarse;

	public InicioSesion(Ventana ventana) {		
		setLayout(null);
		
		//Botones
		iniciarSesion = new JButton("Ingresar");
		iniciarSesion.setBounds(28,425,300,40);
		iniciarSesion.setForeground(Color.WHITE);
		iniciarSesion.setBackground(Color.BLACK);
		iniciarSesion.setBorderPainted(false);
		add(iniciarSesion);
		
		iniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = usuarioT.getText();			
				char[] contrasena = contrasenaT.getPassword();
				String contrasenaS = new String(contrasena);
				try {
					boolean ingresado = autenticar(usuario, contrasenaS);
					if (ingresado) {
						int tipoUsuario = Login.gettipo_usuario();
						if (tipoUsuario == 1) {
							ventana.mostrarPantallaAdmin();
						}else if(tipoUsuario == 2){
							ventana.pantallaRecep();
						}else {
							ventana.pantallaEmpleado();
						}
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(InicioSesion.this, "Error al iniciar sesión", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		});
		
		registrarse = new JButton("Registrarse");
		registrarse.setBounds(28,470,300,40);
		registrarse.setForeground(Color.BLACK);
		registrarse.setBackground(Color.WHITE);
		registrarse.setBorderPainted(false);
		add(registrarse);
		
		registrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.registro();;
			}
			
		});
		
		
		//Etiquetas
		JLabel label1 = new JLabel("Bienvenido!",SwingConstants.CENTER);
		label1.setBounds(20,20,100,20);
		label1.setFont(new Font("Helvetica",Font.PLAIN,18));
		add(label1);
		
		JLabel label2 = new JLabel("Inicio de Sesión",SwingConstants.CENTER);
		label2.setBounds(20,60,170,20);
		label2.setFont(new Font("Helvetica",Font.BOLD,21));
		add(label2);
		
		JLabel label3 = new JLabel("Nombre de Usuario",SwingConstants.CENTER);
		label3.setBounds(20,130,140,20);
		label3.setFont(new Font("Georgia",Font.PLAIN,15));
		add(label3);
		
		JLabel label4 = new JLabel("Contraseña",SwingConstants.CENTER);
		label4.setBounds(20,240,100,20);
		label4.setFont(new Font("Georgia",Font.PLAIN,15));
		add(label4);
		
		//Botón de recordarme
		JRadioButton rd1 = new JRadioButton();
		rd1.setBounds(28,320,300,40);
		rd1.setText("Recordarme");
		add(rd1);
		
		//Cajas de texto
		usuarioT = new JTextField();
		usuarioT.setBounds(28,160,300,35);
		PH.setPlaceHolder(usuarioT, "Ingrese el usuario");
		add(usuarioT);
		
		contrasenaT = new JPasswordField();
		contrasenaT.setBounds(28,270,300,40);
		PH.setPlaceHolderC(contrasenaT, "Ingrese la contraseña");
		contrasenaT.requestFocus(false);;
		add(contrasenaT);
		
		
	}
	
	private boolean autenticar(String usuario, String contrasena) throws FileNotFoundException, IOException {
		return Login.iniciarsesion(usuario, contrasena);
	}
}

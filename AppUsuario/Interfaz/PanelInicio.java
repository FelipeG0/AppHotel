package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DatosInterfaz.InicioSesion;
import DatosInterfaz.PH;
import Logica.App;
import Logica.Hotel;

public class PanelInicio extends JPanel{
	App app;
	
	public PanelInicio(String user, VentanaU ventana) {
		setLayout(new BorderLayout()); 
    	
    	JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(0, 1, 10, 10)); // Layout vertical
        panelBotones.setBorder(new EmptyBorder(10, 20, 10, 20)); // Espacio interno
        
        // Etiquetas
        JLabel label1 = new JLabel("Bienvenido " + user, SwingConstants.CENTER);
        label1.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelBotones.add(label1);
        
        JLabel label2 = new JLabel("¿Qué deseas hacer hoy?", SwingConstants.CENTER);
        label2.setFont(new Font("Helvetica", Font.PLAIN, 15));
        panelBotones.add(label2);
        
        // Botones
        JButton opcion1 = crearBoton("Consultar las habitaciones disponibles para reserva");
        opcion1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Si en algún filtro desea ver todas las habitaciones, no ingresar una respuesta");
				ventana.mostrarHabitaciones();
			}			
		});
        panelBotones.add(opcion1);
        JButton opcion2 = crearBoton("Reservar una habitación");
        opcion2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				ventana.mostrarReserva();
			}			
		});
        panelBotones.add(opcion2);
        JButton opcion3 = crearBoton("Reservar una habitación");
        opcion3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				ventana.mostrarPagar();
			}			
		});
        panelBotones.add(opcion3);
        JButton opcion6 = crearBoton("Cancelar reserva");
        opcion6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.mostrarCancelarReserva();
			}			
		});
        JButton opcion8 = crearBoton("Salir");
        opcion8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InicioSesionU inicioS = ventana.getPantallaLogin();
				JTextField usuarioT = inicioS.getUsuarioT();
				JPasswordField contrasenaT = inicioS.getContrasenaT();;
				PH.setPlaceHolder(usuarioT, "Ingrese el usuario");
				PH.setPlaceHolderC(contrasenaT, "Ingrese la contraseña");
				ventana.mostrarLogin();
				
			}			
		});
        panelBotones.add(opcion8);        
        add(panelBotones);
    }
    
    public JButton crearBoton(String nombre) {
        JButton boton = new JButton(nombre);
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.BLACK);
        
        return boton;
    }
}

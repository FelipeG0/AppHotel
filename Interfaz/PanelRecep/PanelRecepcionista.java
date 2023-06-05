package PanelRecep;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DatosInterfaz.InicioSesion;
import DatosInterfaz.PH;
import DatosInterfaz.PanelAdmin;
import GUI.Ventana;
import Logica.Admin;
import Logica.Hotel;
import Logica.Recepcionista;

public class PanelRecepcionista extends JPanel{
	Recepcionista recep;
    
    public PanelRecepcionista(Ventana ventana) {
    	recep = ventana.getRecepI();
    	setLayout(new BorderLayout()); 
    	
    	JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(0, 1, 10, 10)); // Layout vertical
        panelBotones.setBorder(new EmptyBorder(10, 20, 10, 20)); // Espacio interno
        
        // Etiquetas
        JLabel label1 = new JLabel("Bienvenido Recepcionista", SwingConstants.CENTER);
        label1.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelBotones.add(label1);
        
        JLabel label2 = new JLabel("¿Qué deseas hacer hoy?", SwingConstants.CENTER);
        label2.setFont(new Font("Helvetica", Font.PLAIN, 15));
        panelBotones.add(label2);
        
        // Botones
        JButton opcion1 = crearBoton("Consultar inventario de habitaciones");
        opcion1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Si en algún filtro desea ver todas las habitaciones, no ingresar una respuesta");
				ventana.filtroHabs();
				
			}			
		});
        panelBotones.add(opcion1);
        JButton opcion2 = crearBoton("Consultar información de habitación por ID");
        opcion2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				ventana.habPorId();
			}			
		});
        panelBotones.add(opcion2);
        JButton opcion3 = crearBoton("Crear huesped");
        opcion3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.crearHuesped();
			}			
		});
        panelBotones.add(opcion3);
        JButton opcion4 = crearBoton("Realizar reserva e informar tarifa");
        opcion4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.reserva();
			}			
		});
        panelBotones.add(opcion4);
        JButton opcion5 = crearBoton("Consultar reserva");
        opcion5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.actualizarMenu();
			}			
		});
        panelBotones.add(opcion5);
        JButton opcion6 = crearBoton("Cancelar reserva");
        opcion6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.configurarPB();
			}			
		});
        panelBotones.add(opcion6);
        JButton opcion7 = crearBoton("Registrar un pago");
        opcion7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.pantallaPago();
				
			}			
		});
        panelBotones.add(opcion7);
        JButton opcion8 = crearBoton("Salir");
        opcion8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Hotel.actualizar_archivos();
				InicioSesion inicioS = ventana.getInicioSesion();
				JTextField usuarioT = inicioS.getUsuarioT();
				JPasswordField contrasenaT = inicioS.getContrasenaT();;
				PH.setPlaceHolder(usuarioT, "Ingrese el usuario");
				PH.setPlaceHolderC(contrasenaT, "Ingrese la contraseña");
				ventana.inicioSesion();
				
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


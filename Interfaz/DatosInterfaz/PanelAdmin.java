package DatosInterfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Ventana;
import Logica.Admin;
import Logica.Hotel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelAdmin extends JPanel {
	Admin admin;
    
    public PanelAdmin(Ventana ventana) {
    	admin = ventana.getAdminI();
    	setLayout(new BorderLayout()); 
    	
    	JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(0, 1, 10, 10)); // Layout vertical
        panelBotones.setBorder(new EmptyBorder(10, 20, 10, 20)); // Espacio interno
        
        // Etiquetas
        JLabel label1 = new JLabel("Bienvenido Administrador", SwingConstants.CENTER);
        label1.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelBotones.add(label1);
        
        JLabel label2 = new JLabel("¿Qué deseas hacer hoy?", SwingConstants.CENTER);
        label2.setFont(new Font("Helvetica", Font.PLAIN, 15));
        panelBotones.add(label2);
        
        // Botones
        JButton opcion1 = crearBoton("Crear habitaciones");
        opcion1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.crearHabs();
			}			
		});
        panelBotones.add(opcion1);
        JButton opcion2 = crearBoton("Actualizar las tarifas por tipo de habitación");
        opcion2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				ventana.actualizarTarifas();			
			}			
		});
        panelBotones.add(opcion2);
        JButton opcion3 = crearBoton("Verificar ausencia de tarifas para las habitaciones");
        opcion3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msj = "";
				ArrayList<String> problemas = admin.verificar_tarifas();
				if (problemas.isEmpty()) {
					JOptionPane.showMessageDialog(PanelAdmin.this, "No hay problemas con las tarifas estacblecidas", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}else {
					for(String problema : problemas) {
						msj += problema + "\n";
					}
					JOptionPane.showMessageDialog(PanelAdmin.this, msj, "Error", JOptionPane.ERROR_MESSAGE);
				}				
			}			
		});
        panelBotones.add(opcion3);
        JButton opcion4 = crearBoton("Modificar las tarifas de los servicios ofrecidos");
        opcion4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.tarifasServicios();
			}			
		});
        panelBotones.add(opcion4);
        JButton opcion5 = crearBoton("Actualizar menú restaurante");
        opcion5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.actualizarMenu();
			}			
		});
        panelBotones.add(opcion5);
        JButton opcion6 = crearBoton("Configurar detalles de un plato/bebida");
        opcion6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.configurarPB();
			}			
		});
        panelBotones.add(opcion6);
        JButton opcion7 = crearBoton("Modificar las características del Hotel");
        opcion7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.caracteristicasHotel();
				
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


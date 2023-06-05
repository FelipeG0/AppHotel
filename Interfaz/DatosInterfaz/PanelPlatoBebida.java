package DatosInterfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import GUI.Ventana;
import Logica.Admin;

public class PanelPlatoBebida extends JDialog{
	private Admin admin;
	private JTextField nombre;
	private JComboBox<String> tipo;
	private JTextField tarifa;
	
	public PanelPlatoBebida(Ventana ventana) {
		super(ventana, "Crear Habitación", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(ventana);

        JPanel panelDialogo = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Etiqueta
        JLabel label1 = new JLabel("Información de un Plato/Bebida", SwingConstants.CENTER);
        label1.setFont(new Font("Helvetica", Font.BOLD, 20));
        panelDialogo.add(label1, BorderLayout.NORTH);
        
        //Cajas de texto 
        nombre = new JTextField();
        nombre.setBounds(35,70,360,40);
        PH.setPlaceHolder(nombre, "Nombre");
		panel.add(nombre);
		
		tarifa = new JTextField();
		tarifa.setBounds(35,130,220,40);
		PH.setPlaceHolder(tarifa, "Tarifa");
		panel.add(tarifa);
		
		//Lista desplegable
        JLabel label = new JLabel("Selecciona el tipo de Alimento:");
        panel.add(label, BorderLayout.EAST);
        tipo = new JComboBox<>();
        tipo.addItem("Seleccionar");
        tipo.addItem("plato");
        tipo.addItem("bebida");
        panel.add(tipo);
        		
		//CheckBox
        JLabel etiqueta2 = new JLabel("Selecciona la comida en la que esta disponible: ");
        etiqueta2.setFont(new Font("Helvetica", Font.PLAIN, 15));

        JPanel panelCheckbox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox desayuno = new JCheckBox("Desayuno");
        desayuno.setFont(new Font("Helvetica", Font.PLAIN, 12));
        JCheckBox almuerzo = new JCheckBox("Almuerzo");
        almuerzo.setFont(new Font("Helvetica", Font.PLAIN, 12));
        JCheckBox cena = new JCheckBox("Cena");
        cena.setFont(new Font("Helvetica", Font.PLAIN, 12));

        panelCheckbox.add(etiqueta2);
        panelCheckbox.add(desayuno);
        panelCheckbox.add(almuerzo);
        panelCheckbox.add(cena);
        panel.add(panelCheckbox);
        
		JLabel etiqueta1 = new JLabel("Lugares en los que esta disponible: ");
        etiqueta1.setFont(new Font("Helvetica", Font.PLAIN, 15));

        JPanel panelCheckbox1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox checkbox1 = new JCheckBox("Restaurante");
        checkbox1.setFont(new Font("Helvetica", Font.PLAIN, 12));
        JCheckBox checkbox2 = new JCheckBox("Servicio al cuarto");
        checkbox2.setFont(new Font("Helvetica", Font.PLAIN, 12));
        JCheckBox checkbox3 = new JCheckBox("Gastro bar");
        checkbox3.setFont(new Font("Helvetica", Font.PLAIN, 12));

        panelCheckbox1.add(etiqueta1);
        panelCheckbox1.add(checkbox1);
        panelCheckbox1.add(checkbox2);
        panelCheckbox1.add(checkbox3);
        panel.add(panelCheckbox1);

        // Boton
        JButton boton2 = new JButton("Guardar");
        boton2.setBounds(40, 330, 350, 40);
        boton2.setForeground(Color.WHITE);
        boton2.setBackground(Color.BLACK);
        boton2.setBorderPainted(false);
        boton2.setFont(new Font("Helvetica", Font.BOLD, 18));
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	admin = ventana.getAdminI();
            	String lugarDisp = obtenerLugaresDisp(checkbox1, checkbox2, checkbox3);    
            	String comidaDisp = obtenerComidaDisp(desayuno, almuerzo, cena);
                admin.modificar_info_menu(nombre.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(tarifa.getText()), comidaDisp, lugarDisp);
                tipo.setSelectedItem("Seleccionar");               
                PH.setPlaceHolder(nombre, "Nombre del alimento");
                PH.setPlaceHolder(tarifa, "Costo por persona");
                desayuno.setSelected(false);
                almuerzo.setSelected(false);
                cena.setSelected(false);              
                checkbox1.setSelected(false);
                checkbox2.setSelected(false);
                checkbox3.setSelected(false);
                dispose();
            }
        });
        panelBoton.add(boton2);

        panelDialogo.add(panel, BorderLayout.CENTER);
        panelDialogo.add(panelBoton, BorderLayout.SOUTH);

        setContentPane(panelDialogo);
	}
	
	private String obtenerLugaresDisp(JCheckBox checkbox1, JCheckBox checkbox2, JCheckBox checkbox3) {
        String msj = "";
		if (checkbox1.isSelected()) {
            msj += "Restaurante, "; 
        } else if (checkbox2.isSelected()) {
        	msj += "Servicio al cuarto, "; 
        } else if (checkbox3.isSelected()) {
            msj += "Gastro bar"; 
        } else {
            msj += "No se han establecido los lugares de disponibilidad";
        }
		return msj;
    }
	private String obtenerComidaDisp(JCheckBox checkbox1, JCheckBox checkbox2, JCheckBox checkbox3) {
        String msj = "";
		if (checkbox1.isSelected()) {
            msj += "Desayuno, "; 
        } else if (checkbox2.isSelected()) {
        	msj += "Almuerzo, "; 
        } else if (checkbox3.isSelected()) {
            msj += "Cena"; 
        } else {
            msj += "No se han establecido los horarios de comidas disponibles";
        }
		return msj;
    }
}



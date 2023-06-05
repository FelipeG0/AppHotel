package PanelRecep;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import ContDatos.Habitacion;
import DatosInterfaz.Modificartarifas;
import DatosInterfaz.PH;
import GUI.Ventana;
import Logica.Recepcionista;

public class DialogoHabs extends JDialog{
	private Recepcionista recep;
	private ArrayList<Habitacion> habsFiltradas;
	
    private JTextField piso;
    private String tipo = "";
    private JCheckBox cocinaA;
    private JCheckBox balconA;
    private JCheckBox vistaA;
    private JCheckBox disponibleA;
	
	public DialogoHabs(Ventana ventana) {
		super(ventana, "Crear Habitación", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 320);
        setLocationRelativeTo(ventana);
        
        recep = ventana.getRecepI();
        
        JPanel panelDialogo = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //Etiqueta
        JLabel label1 = new JLabel("Filtar habitaciones", SwingConstants.CENTER);
        label1.setFont(new Font("Helvetica", Font.BOLD, 20));
        panelDialogo.add(label1, BorderLayout.NORTH);
       
        
        // Cajas de texto
        piso = new JTextField();
        PH.setPlaceHolder(piso, "Ingrese el piso para filtrar");
        panel.add(piso);
        
        //CheckBox para tipo de habitación
        
        JLabel etiqueta = new JLabel("Tipo de habitación: ");
        etiqueta.setFont(new Font("Helvetica", Font.PLAIN, 15));

        JPanel panelCheckbox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox checkbox1 = new JCheckBox("Estandar");
        checkbox1.setFont(new Font("Helvetica", Font.PLAIN, 12));
        JCheckBox checkbox2 = new JCheckBox("Suite");
        checkbox2.setFont(new Font("Helvetica", Font.PLAIN, 12));
        JCheckBox checkbox3 = new JCheckBox("Suite Doble");
        checkbox3.setFont(new Font("Helvetica", Font.PLAIN, 12));

        panelCheckbox.add(etiqueta);
        panelCheckbox.add(checkbox1);
        panelCheckbox.add(checkbox2);
        panelCheckbox.add(checkbox3);
        panel.add(panelCheckbox);
        
        Map<JPanel, JCheckBox> cocina = booleanos("Con cocina integrada: ");
        Map<JPanel, JCheckBox> balcon = booleanos("Con balcon:                 ");
        Map<JPanel, JCheckBox> vista = booleanos("Con vista:                    ");  
        Map<JPanel, JCheckBox> disponible = booleanos("Disponible:                  ");
        
        for (Map.Entry<JPanel, JCheckBox> items : cocina.entrySet()) {
            panel.add(items.getKey());
            cocinaA = items.getValue();            
        }
        for (Map.Entry<JPanel, JCheckBox> items : balcon.entrySet()) {
            panel.add(items.getKey());
            balconA = items.getValue();            
        }
        for (Map.Entry<JPanel, JCheckBox> items : vista.entrySet()) {
            panel.add(items.getKey());
            vistaA = items.getValue();            
        } 
        for (Map.Entry<JPanel, JCheckBox> items : disponible.entrySet()) {
            panel.add(items.getKey());
            disponibleA = items.getValue();            
        } 
        
        // Boton
        JButton boton2 = new JButton("Filtrar");
        boton2.setBounds(40, 330, 350, 40);
        boton2.setForeground(Color.WHITE);
        boton2.setBackground(Color.BLACK);
        boton2.setBorderPainted(false);
        boton2.setFont(new Font("Helvetica", Font.BOLD, 18));
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
            	String pisoVar = "";
            	tipo = obtenerTipoHabitacion(checkbox1, checkbox2, checkbox3);                         
                String cocinaVar = obtenerValorCheckbox(cocinaA);
                String balconVar = obtenerValorCheckbox(balconA);
                String vistaVar = obtenerValorCheckbox(vistaA);
                String disponibleVar = obtenerValorCheckbox(disponibleA);
                if (piso.getText().equals("Ingrese el piso para filtrar")) {
                	pisoVar = "";
                }else {
                	pisoVar = piso.getText();
                }
                habsFiltradas = (ArrayList<Habitacion>) recep.info_Habitaciones(pisoVar, tipo, balconVar, vistaVar, cocinaVar, disponibleVar);                        
                dispose();
                PH.setPlaceHolder(piso, "Ingrese el piso para filtrar");
                checkbox1.setSelected(false);
                checkbox2.setSelected(false);
                checkbox3.setSelected(false);
                ventana.habs(habsFiltradas);
            }
        });
        panelBoton.add(boton2);
        
        // Ver todas las habitaciones
        JButton botonCrearManualmente = new JButton("Ver todas las habitaciones");
        botonCrearManualmente.setForeground(Color.BLACK);
        botonCrearManualmente.setBackground(Color.WHITE);
        botonCrearManualmente.setBorderPainted(true);
        botonCrearManualmente.setOpaque(false);
        botonCrearManualmente.setFont(new Font("Georgia", Font.PLAIN, 12));
        panelBoton.add(botonCrearManualmente, BorderLayout.NORTH);
        botonCrearManualmente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {           
                habsFiltradas = (ArrayList<Habitacion>) recep.info_Habitaciones("", "", "", "", "", "");
                dispose();
                ventana.habs(habsFiltradas);
            }
        });
        panelDialogo.add(panel, BorderLayout.CENTER);
        panelDialogo.add(panelBoton, BorderLayout.SOUTH);

        setContentPane(panelDialogo);

        
	}	
	private String obtenerTipoHabitacion(JCheckBox checkbox1, JCheckBox checkbox2, JCheckBox checkbox3) {
        if (checkbox1.isSelected()) {
            return "estandar";
        } else if (checkbox2.isSelected()) {
            return "suite";
        } else if (checkbox3.isSelected()) {
            return "suite Doble";
        } else {
            return "";
        }
    }
	
	private Map<JPanel, JCheckBox> booleanos(String strEtiqueta) {
        JLabel etiqueta = new JLabel(strEtiqueta);
        etiqueta.setFont(new Font("Helvetica", Font.PLAIN, 15));

        JPanel panelCheckbox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox trueBox = new JCheckBox("true");
        trueBox.setFont(new Font("Helvetica", Font.PLAIN, 12));
        JCheckBox falseBox = new JCheckBox("false");
        falseBox.setFont(new Font("Helvetica", Font.PLAIN, 12));

        panelCheckbox.add(etiqueta);
        panelCheckbox.add(trueBox);
        panelCheckbox.add(falseBox);
        
        Map<JPanel, JCheckBox> rta = new HashMap<>();
        rta.put(panelCheckbox, trueBox);

        return rta;
    }
	
	private String obtenerValorCheckbox(JCheckBox checkBox) {
		String msj = "";
		if (checkBox.isSelected()) {
			return checkBox.getText();
		}else {
			return msj;
		}
    }
	
}

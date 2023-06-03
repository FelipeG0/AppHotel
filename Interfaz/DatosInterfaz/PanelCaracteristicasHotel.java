package DatosInterfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import GUI.Ventana;
import Logica.Hotel;

public class PanelCaracteristicasHotel extends JDialog{
	private JTextField[] camposTexto;
	private JCheckBox[][] checkBoxes;
	
	public PanelCaracteristicasHotel(Ventana ventana) {
		super(ventana, "Caracteristicas Hotel", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 200);
        setLocationRelativeTo(ventana);
        
        JPanel panelPrincipal = new JPanel(new BorderLayout());        
        JLabel labelTitulo = new JLabel("Características");
        labelTitulo.setFont(new Font("Helvetica", Font.BOLD, 20));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(labelTitulo, BorderLayout.NORTH);                   
        
        JPanel panel = new JPanel(new GridLayout(3, 2));   
        
        ArrayList<String> strings = Hotel.getAtributos().get("String");
		ArrayList<String> booleans = Hotel.getAtributos().get("Boolean");

        camposTexto = new JTextField[2];
        checkBoxes = new JCheckBox[2][3];
        for (int i = 0; i < strings.size(); i++) {
        	//etiquetas.add(strings.get(i));	
            JLabel etiqueta = new JLabel(strings.get(i) + ":", SwingConstants.CENTER);
            etiqueta.setFont(new Font("Helvetica", Font.PLAIN, 15));
            camposTexto[i] = new JTextField(10);
            panel.add(etiqueta);
            panel.add(camposTexto[i]);
        }
        
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < booleans.size()/2; j++) {
            	//etiquetas.add(strings.get(i));	
                JLabel etiqueta = new JLabel(booleans.get(i * 3 + j) + ":");               
                etiqueta.setFont(new Font("Helvetica", Font.PLAIN, 15));
                JPanel panelCheckbox = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JCheckBox checkboxTrue = new JCheckBox("True");
                checkboxTrue.setFont(new Font("Helvetica", Font.PLAIN, 12));
                JCheckBox checkboxFalse = new JCheckBox("False");
                checkboxFalse.setFont(new Font("Helvetica", Font.PLAIN, 12));

                panelCheckbox.add(etiqueta);
                panelCheckbox.add(checkboxTrue);
                panelCheckbox.add(checkboxFalse);

                checkBoxes[i][j] = checkboxTrue;

                panel.add(panelCheckbox);
            }
        }
        
        JButton botonAceptar = new JButton("Modificar");
        botonAceptar.setBounds(28,425,300,40);
        botonAceptar.setForeground(Color.WHITE);
        botonAceptar.setBackground(Color.BLACK);
        botonAceptar.setBorderPainted(false);
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < strings.size(); i++) {              
                    String textoCampo = camposTexto[i].getText();                    
                    if (i == 0 && !textoCampo.isBlank()) {
                    	Hotel.setNombreHotel(textoCampo);
                    }else if(i == 1 && !textoCampo.isBlank()){
                    	Hotel.setZonasHumedas(textoCampo);
                    }                                    
                }
                for (int i = 0; i < strings.size(); i++) {
                    for (int j = 0; j < booleans.size()/2; j++) {
                        boolean seleccionado = checkBoxes[i][j].isSelected();
                        if ((i * 3 + j) == 0) {
                        	Hotel.setParqueaderoPago(seleccionado);
                        }else if((i * 3 + j) == 1)  {
                        	Hotel.setPiscina(seleccionado);
                        }else if((i * 3 + j) == 2) {
                        	Hotel.setBbq(seleccionado);
                        }else if((i * 3 + j) == 3) {
                        	Hotel.setWifiGratis(seleccionado);
                        }else if((i * 3 + j) == 4) {
                        	Hotel.setRecep24horas(seleccionado);
                        }else if((i * 3 + j) == 5) {
                        	Hotel.setMascotas(seleccionado);
                        }
                    }
                }
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(botonAceptar);

        panelPrincipal.add(panel, BorderLayout.CENTER);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
	}

}

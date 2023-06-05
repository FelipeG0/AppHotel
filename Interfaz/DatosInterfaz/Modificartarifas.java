package DatosInterfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import GUI.Ventana;
import Logica.Admin;

public class Modificartarifas extends JPanel {
	private Admin admin;
    private JTextField fechaIni;
    private JTextField fechaFin;
    private JTextField valor;

    public Modificartarifas(Ventana ventana) {
    	admin = ventana.getAdminI(); 
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelSuperior, BorderLayout.NORTH);

        // Botón "<-" (Regresar)
        JButton botonRegresar = new JButton("<-");
        botonRegresar.setForeground(Color.BLACK);
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.setBorderPainted(false);
        botonRegresar.setOpaque(false);
        botonRegresar.setFont(new Font("Arial Black", Font.BOLD, 10));
        panelSuperior.add(botonRegresar, BorderLayout.WEST);
        botonRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.mostrarPantallaAdmin();
            }
        });

        // Label "Modificar tarifas"
        JLabel labelCrearHabitaciones = new JLabel("Modificar tarifas por tipo de habitación", SwingConstants.CENTER);
        labelCrearHabitaciones.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelSuperior.add(labelCrearHabitaciones, BorderLayout.CENTER);

        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        add(panelCentral, BorderLayout.CENTER);

        // Panel para etiquetas
        JPanel panelLabels = new JPanel(new GridLayout(3, 1, 0, 10));
        panelCentral.add(panelLabels, BorderLayout.WEST);

        // Etiquetas
        JLabel label4 = new JLabel("Costo", SwingConstants.LEFT);
        label4.setFont(new Font("Helvetica", Font.PLAIN, 15));
        panelLabels.add(label4);
        
        JLabel label2 = new JLabel("Fecha Inicial", SwingConstants.LEFT);
        label2.setFont(new Font("Helvetica", Font.PLAIN, 15));
        panelLabels.add(label2);

        JLabel label3 = new JLabel("Fecha Final", SwingConstants.LEFT);
        label3.setFont(new Font("Helvetica", Font.PLAIN, 15));
        panelLabels.add(label3);

        // Panel para cajas de texto
        JPanel panelText = new JPanel(new GridLayout(3, 1, 0, 10));
        panelCentral.add(panelText, BorderLayout.CENTER);

        // Cajas de texto
        valor = new JTextField();
        PH.setPlaceHolder(valor, "Costo de la Habitación");
        panelText.add(valor);

        fechaIni = new JTextField();
        PH.setPlaceHolder(fechaIni, "Fecha en formato (YYYY/MM/DD");
        panelText.add(fechaIni);

        fechaFin = new JTextField();
        PH.setPlaceHolder(fechaFin, "Fecha final en formato (YYYY/MM/DD");
        panelText.add(fechaFin);

        // Panel para checkboxes
        JPanel panelCheckbox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCheckbox.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelCentral.add(panelCheckbox, BorderLayout.SOUTH);

        JLabel etiqueta = new JLabel("Tipo de habitación: ");
        etiqueta.setFont(new Font("Helvetica", Font.PLAIN, 15));
        panelCheckbox.add(etiqueta);

        JCheckBox checkbox1 = new JCheckBox("Estandar");
        checkbox1.setFont(new Font("Helvetica", Font.PLAIN, 12));
        panelCheckbox.add(checkbox1);

        JCheckBox checkbox2 = new JCheckBox("Suite");
        checkbox2.setFont(new Font("Helvetica", Font.PLAIN, 12));
        panelCheckbox.add(checkbox2);

        JCheckBox checkbox3 = new JCheckBox("Suite Doble");
        checkbox3.setFont(new Font("Helvetica", Font.PLAIN, 12));
        panelCheckbox.add(checkbox3);

        // Panel inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelInferior, BorderLayout.SOUTH);

        JButton boton3 = new JButton("Actualizar");
        boton3.setForeground(Color.WHITE);
        boton3.setBackground(Color.BLACK);
        boton3.setBorderPainted(false);
        boton3.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelInferior.add(boton3, BorderLayout.SOUTH);
        boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            	            	           
            	String tipoHab = obtenerTipoHabitacion(checkbox1, checkbox2, checkbox3);			
                admin.cargar_tarifas(tipoHab, Integer.parseInt(valor.getText()), fechaIni.getText(), fechaFin.getText());
                JOptionPane.showMessageDialog(Modificartarifas.this, "Tarifas actualizadas con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                ventana.mostrarPantallaAdmin();
            }
        });
        
        // Ver tarifa actual
        JButton botonCrearManualmente = new JButton("Ver la tarifas actuales");
        botonCrearManualmente.setForeground(Color.BLACK);
        botonCrearManualmente.setBackground(Color.WHITE);
        botonCrearManualmente.setBorderPainted(true);
        botonCrearManualmente.setOpaque(false);
        botonCrearManualmente.setFont(new Font("Georgia", Font.PLAIN, 12));
        panelInferior.add(botonCrearManualmente, BorderLayout.NORTH);
        botonCrearManualmente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String msj = "";
            	msj += admin.tarifa_actual("estandar") + "\n";
            	msj += admin.tarifa_actual("suite") + "\n";
            	msj += admin.tarifa_actual("suite doble") + "\n";
            	JOptionPane.showMessageDialog(Modificartarifas.this, msj, "Éxito", JOptionPane.INFORMATION_MESSAGE);            	          
            }
        });
    }
    
    private String obtenerTipoHabitacion(JCheckBox checkbox1, JCheckBox checkbox2, JCheckBox checkbox3) {
        if (checkbox1.isSelected()) {
            return "estandar";
        } else if (checkbox2.isSelected()) {
            return "suite";
        } else if (checkbox3.isSelected()) {
            return "suite Doble";
        } else {
            return "vacio";
        }
    }
}




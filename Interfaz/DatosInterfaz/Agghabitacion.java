package DatosInterfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import GUI.Ventana;
import Logica.Admin;

public class Agghabitacion extends JDialog {
	private Admin admin;
    private JTextField nHab;
    private JTextField piso;
    private String tipo = "vacio";
    private JCheckBox cocinaA;
    private JCheckBox balconA;
    private JCheckBox vistaA;
    

    public Agghabitacion(Ventana ventana) {
        super(ventana, "Crear Habitación", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(ventana);

        JPanel panelDialogo = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Etiqueta
        JLabel label1 = new JLabel("Agregar Habitación", SwingConstants.CENTER);
        label1.setFont(new Font("Helvetica", Font.BOLD, 20));
        panelDialogo.add(label1, BorderLayout.NORTH);

        // Cajas de texto
        nHab = new JTextField();
        nHab.setPreferredSize(new Dimension(360, 40));
        PH.setPlaceHolder(nHab, "Numero de Habitación");
        panel.add(nHab);

        piso = new JTextField();
        piso.setPreferredSize(new Dimension(220, 40));
        PH.setPlaceHolder(piso, "Piso");
        panel.add(piso);

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
        
        Map<JPanel, JCheckBox> cocina = booleanos("Tiene cocina integrada: ");
        Map<JPanel, JCheckBox> balcon = booleanos("Tiene balcon:                 ");
        Map<JPanel, JCheckBox> vista = booleanos("Tiene vista:                    ");   
        
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

        // Boton
        JButton boton2 = new JButton("Crear");
        boton2.setBounds(40, 330, 350, 40);
        boton2.setForeground(Color.WHITE);
        boton2.setBackground(Color.BLACK);
        boton2.setBorderPainted(false);
        boton2.setFont(new Font("Helvetica", Font.BOLD, 18));
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	admin = ventana.getAdminI();
            	tipo = obtenerTipoHabitacion(checkbox1, checkbox2, checkbox3);                         
                Boolean cocinaVar = obtenerValorCheckbox(cocinaA);
                Boolean balconVar = obtenerValorCheckbox(balconA);
                Boolean vistaVar = obtenerValorCheckbox(vistaA);
                admin.crear_habitaciones(nHab.getText(), Integer.parseInt(piso.getText()), tipo, balconVar, vistaVar, cocinaVar, true);
                dispose();
            }
        });
        panelBoton.add(boton2);

        panelDialogo.add(panel, BorderLayout.CENTER);
        panelDialogo.add(panelBoton, BorderLayout.SOUTH);

        setContentPane(panelDialogo);
    }
    
    private Map<JPanel, JCheckBox> booleanos(String strEtiqueta) {
        JLabel etiqueta = new JLabel(strEtiqueta);
        etiqueta.setFont(new Font("Helvetica", Font.PLAIN, 15));

        JPanel panelCheckbox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox trueBox = new JCheckBox("Si");
        trueBox.setFont(new Font("Helvetica", Font.PLAIN, 12));
        JCheckBox falseBox = new JCheckBox("No");
        falseBox.setFont(new Font("Helvetica", Font.PLAIN, 12));

        panelCheckbox.add(etiqueta);
        panelCheckbox.add(trueBox);
        panelCheckbox.add(falseBox);
        
        Map<JPanel, JCheckBox> rta = new HashMap<>();
        rta.put(panelCheckbox, trueBox);

        return rta;
    }
    
    private String obtenerTipoHabitacion(JCheckBox checkbox1, JCheckBox checkbox2, JCheckBox checkbox3) {
        if (checkbox1.isSelected()) {
            return "Estandar";
        } else if (checkbox2.isSelected()) {
            return "Suite";
        } else if (checkbox3.isSelected()) {
            return "Suite Doble";
        } else {
            return "vacio";
        }
    }

    private Boolean obtenerValorCheckbox(JCheckBox checkBox) {
        return checkBox.isSelected();
    }
}



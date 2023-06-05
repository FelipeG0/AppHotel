package PanelRecep;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import DatosInterfaz.PH;
import GUI.Ventana;
import Logica.Recepcionista;
import ProcesoDePago.CargaPasarela;
import ProcesoDePago.PasarelaPago;

public class PanelPago extends JPanel{
	private Recepcionista recep;
	
	private JTextField docTitular;
	private JComboBox<String> pasarela;
	private JTextField numeroTarjeta;
	private JTextField propietarioTarjeta;
	private JTextField fechaExpiracion;
	private JTextField cvc;
	
	
	public PanelPago(Ventana ventana) {
		recep = ventana.getRecepI();
		
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

        // Label "Regitrar pago"
        JLabel labelCrearHabitaciones = new JLabel("Registrar un pago", SwingConstants.CENTER);
        labelCrearHabitaciones.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelSuperior.add(labelCrearHabitaciones, BorderLayout.CENTER);
        
        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        add(panelCentral, BorderLayout.CENTER);
        
        // Cajas de texto
        docTitular = new JTextField();
        PH.setPlaceHolder(docTitular, "Ingrese el documento del titular de la reserva");
        panelCentral.add(docTitular);

        numeroTarjeta = new JTextField();
        PH.setPlaceHolder(numeroTarjeta, "Ingrese el numero de la tarjeta");
        panelCentral.add(numeroTarjeta);

        propietarioTarjeta = new JTextField();
        PH.setPlaceHolder(propietarioTarjeta, "Ingrese el nombre del propietario de la tarjeta");
        panelCentral.add(propietarioTarjeta);

        fechaExpiracion = new JTextField();
        PH.setPlaceHolder(fechaExpiracion, "Ingrese la fecha de caducidad(MM-YY)");
        panelCentral.add(fechaExpiracion);
        
        cvc = new JTextField();
        PH.setPlaceHolder(cvc, "Ingrese el codigo de seguridad");
        panelCentral.add(cvc);
        
        //Lista desplegable para pasarela
        JLabel label = new JLabel("Seleccione la pasarela para realizar el pago:");
        panelCentral.add(label);
        pasarela = new JComboBox<>();
        pasarela.addItem("Seleccionar");
        pasarela.addItem("PayPal");
        pasarela.addItem("PayU");
        pasarela.addItem("Sire");
        panelCentral.add(pasarela);
        
        //Panel para el boton de pago
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH);
        
        // Boton
        JButton boton2 = new JButton("Pagar");
        boton2.setBounds(40, 330, 350, 40);
        boton2.setForeground(Color.WHITE);
        boton2.setBackground(Color.BLACK);
        boton2.setBorderPainted(false);
        boton2.setFont(new Font("Helvetica", Font.BOLD, 18));
        panelBoton.add(boton2, BorderLayout.CENTER);
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String pasarelaVar = pasarela.getSelectedItem().toString();
            	List<PasarelaPago> pasarelas = null;
				try {
					pasarelas = CargaPasarela.obtenerPasarelas();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PanelPago.this, "Error cargando la pasarela", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
            	int opcion = pasarelaSele(pasarela);
            	PasarelaPago pasarelaSeleccionada = pasarelas.get(opcion);      
            	String[] infoTarjeta = new String[4];
            	infoTarjeta[0] = numeroTarjeta.getText();
    			infoTarjeta[1] = propietarioTarjeta.getText();
    			infoTarjeta[2] = fechaExpiracion.getText();
    			infoTarjeta[3] = cvc.getText();
            	try {
            		String factura = recep.pagoYfactura(docTitular.getText(), pasarelaSeleccionada, infoTarjeta);
            		JOptionPane.showMessageDialog(PanelPago.this, factura, "Factura", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(PanelPago.this, "Error procesando el pago", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
            	ventana.pantallaRecep();
            }
        });
        
        
        
	}
	
	public int pasarelaSele(JComboBox<String> var) {
		String seleccion = var.getSelectedItem().toString();
		int rta = 0;
		if (seleccion.equals("PayPal")) {
			rta = 0;
		}else if(seleccion.equals("PayU")) {
			rta = 1;
		}else if(seleccion.equals("Sire")) {
			rta = 2;
		}
		return rta;
	}
}




























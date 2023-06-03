package GUI;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import DatosInterfaz.*;
import Logica.Hotel;

public class Ventana extends JFrame{
	private Hotel hotel;
	private InicioSesion inicioSesion;
	private PanelRegistro registro;
	private PanelAdmin admin;
	private PanelRecepcionista recep;
	private PanelEmpleado empleado;
	private PanelCrearHabitaciones cHabs;
	private PanelActualizarTarifas aTarifas;
	private PanelVerificarTarifas vTarifas;
	private PanelTarifaServicios tarifasS;
	private PanelActualizarMenu aMenu;
	private PanelPlatoBebida platoBebida;
	private PanelCaracteristicasHotel cHotel;
	
	
	public Ventana() {
		//this.hotel = hotel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		this.inicioSesion = new InicioSesion(this);
		this.registro = new PanelRegistro(this);
		this.admin = new PanelAdmin(this);
		this.recep = new PanelRecepcionista(this);
		this.empleado = new PanelEmpleado(this);
		this.cHabs = new PanelCrearHabitaciones(this);
		this.aTarifas = new PanelActualizarTarifas(this);
		this.vTarifas = new PanelVerificarTarifas(this);
		this.aMenu = new PanelActualizarMenu(this);
		this.platoBebida = new PanelPlatoBebida(this);
		this.cHotel = new PanelCaracteristicasHotel(this);
		
		
		inicioSesion();		
	}
	
	public void inicioSesion() {
		mostrarPanel(inicioSesion);
	}
	public void registro() {
		mostrarPanel(registro);
	}
	public void mostrarPantallaAdmin() {
		mostrarPanel(admin);
	}
	public void pantallaRecep() {
		mostrarPanel(recep);
	}
	public void pantallaEmpleado() {
		mostrarPanel(empleado);
	}
	public void crearHabs() {
		mostrarPanel(cHabs);
	}
	public void actualizarTarifas() {
		mostrarPanel(aTarifas);
	}
	public void verificarTarifas() {
		mostrarPanel(vTarifas);
	}
	public void tarifasServicios() {
		mostrarPanel(tarifasS);
	}
	public void actualizarMenu() {
		mostrarPanel(aMenu);
	}
	public void configurarPB() {
		//mostrarPanel(platoBebida);
	}
	public void caracteristicasHotel() {
		cHotel = new PanelCaracteristicasHotel(this);
		cHotel.setVisible(true);;		
	}
	
	public void mostrarPopUp(JDialog popUp) {
	//	popUp
	}
	public void mostrarPanel(JPanel panel) {
		setContentPane(panel);
		revalidate();
		repaint();
	}
}

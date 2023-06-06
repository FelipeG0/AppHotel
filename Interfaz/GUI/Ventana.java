package GUI;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ContDatos.Habitacion;
import DatosInterfaz.*;
import Logica.Admin;
import Logica.Hotel;
import Logica.Recepcionista;
import PanelRecep.DialogoHabs;
import PanelRecep.PanelHabId;
import PanelRecep.PanelHabitaciones;
import PanelRecep.PanelHuesped;
import PanelRecep.PanelPago;
import PanelRecep.PanelRecepcionista;
import PanelRecep.PanelReserva;
import PanelRecep.PanelHuesped;

public class Ventana extends JFrame{
	protected Hotel hotel;
	protected Admin adminI;
	protected Recepcionista recepI;
	
	private InicioSesion inicioSesion;
	private PanelRegistro registro;
	private PanelAdmin admin;
	private PanelEmpleado empleado;
	private PanelActualizarMenu aMenu;
	private PanelPlatoBebida platoBebida;
	private PanelCaracteristicasHotel cHotel;
	private Agghabitacion aggHab;
	private Agghabitacionarchivo aggArchivo;
	private Modificartarifas modTarifas;
	private Modificartarifasp tarifasServ;
	
	private PanelRecepcionista recepPanel;
	private ArrayList<Habitacion> habitacionesArrayList = new ArrayList<>();
	private DialogoHabs filtroHabs;
	private PanelHabitaciones habsP;
	private PanelHuesped huespedP;
	private PanelHabId habId;
	private PanelReserva reserva;
	private PanelPago pago;
	
	
	public Ventana() throws IOException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		adminI = new Admin();
		recepI = new Recepcionista();
		hotel = new Hotel();
		
		this.inicioSesion = new InicioSesion(this);
		this.registro = new PanelRegistro(this);
		this.admin = new PanelAdmin(this);		
		this.empleado = new PanelEmpleado(this);
		this.aMenu = new PanelActualizarMenu(this);
		this.platoBebida = new PanelPlatoBebida(this);
		this.cHotel = new PanelCaracteristicasHotel(this);
		this.aggHab = new Agghabitacion(this);
		this.aggArchivo = new Agghabitacionarchivo(this);
		this.modTarifas = new Modificartarifas(this);
		this.tarifasServ = new Modificartarifasp(this);
		
		this.recepPanel = new PanelRecepcionista(this);
		this.filtroHabs = new DialogoHabs(this);
		this.habitacionesArrayList = new ArrayList<>(Hotel.getMapa_habitaciones().values());
		this.habsP = new PanelHabitaciones(habitacionesArrayList, this);
		this.huespedP = new PanelHuesped(this);
		this.habId = new PanelHabId(habitacionesArrayList, this);
		this.reserva = new PanelReserva(this);
		this.pago = new PanelPago(this);
		

		inicioSesion();		
	}
	//Inicio sesion y Registro
	public void inicioSesion() {		
		mostrarPanel(inicioSesion);
	}
	public void registro() {
		mostrarPanel(registro);
	}
	
	//Paneles del Administrador
	public void mostrarPantallaAdmin() {
		mostrarPanel(admin);
	}
	public void crearHabs() {
		mostrarPanel(aggArchivo);		
	}
	public void crearHabsManual() {
		aggHab.setVisible(true);	
	}
	public void actualizarTarifas() {
		mostrarPanel(modTarifas);
	}
	public void tarifasServicios() {
		mostrarPanel(tarifasServ);
	}
	public void actualizarMenu() {
		mostrarPanel(aMenu);
	}
	public void configurarPB() {
		platoBebida.setVisible(true);
	}
	public void caracteristicasHotel() {
		cHotel.setVisible(true);;		
	}
	
	//Recepcionista
	public void pantallaRecep() {
		mostrarPanel(recepPanel);
	}
	public void filtroHabs() {
		filtroHabs.setVisible(true);
	}
	public void habs(ArrayList<Habitacion> habsFiltradas) {
		habsP = new PanelHabitaciones(habsFiltradas, this);
		mostrarPanel(habsP);
	}
	public void crearHuesped() {
		mostrarPanel(huespedP);
	}
	public void habPorId() {
		mostrarPanel(habId);
	}
	public void reserva() {
		mostrarPanel(reserva);
	}
	public void pantallaPago() {
		mostrarPanel(pago);
	}
	
	
	//Empleado
	public void pantallaEmpleado() {
		mostrarPanel(empleado);
	}
	
	
	//Metodo para repintar la ventana
	public void mostrarPanel(JPanel panel) {
		setContentPane(panel);
		revalidate();
		repaint();
	}
	
	//Getters y setters
	public InicioSesion getInicioSesion() {
		return inicioSesion;
	}
	public void setInicioSesion(InicioSesion inicioSesion) {
		this.inicioSesion = inicioSesion;
	}
	public Admin getAdminI() {
		return adminI;
	}
	public void setAdminI(Admin adminI) {
		this.adminI = adminI;
	}
	public Recepcionista getRecepI() {
		return recepI;
	}
	public void setRecepI(Recepcionista recepI) {
		this.recepI = recepI;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
}

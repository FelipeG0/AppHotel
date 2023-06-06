package Interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Logica.App;
import Logica.LoginUser;

public class VentanaU extends JFrame{
	protected App app;
	
	private InicioSesionU pantallaLogin;
	private String user;
	private PanelRegistroU pantallaRegister;
	private PanelInicio pantallaInicio;
	private PanelReserva pantallaReserva;
	private PanelFiltrarHabitaciones pantallaHabitacionesDisp;
	private PanelPagar pantallaPagar;
	private PanelCancelarReserva pantallaCancelarReserva;
	
	public VentanaU(App app) {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setVisible(true);

		this.app = app;
		this.pantallaLogin = new InicioSesionU(this);
		user = pantallaLogin.getUsuarioT().getText();
		this.pantallaRegister = new PanelRegistroU(this);
		this.pantallaInicio = new PanelInicio(user, this);
		
				        
		mostrarLogin();
		
	}
	//Pantallas
	public void mostrarLogin() {
		mostrarPanel(pantallaLogin);
	}
	public void mostrarRegister() {
		mostrarPanel(pantallaRegister);
	}
	public void mostrarOpciones() {
		mostrarPanel(pantallaInicio);
	}
	public void mostrarHabitaciones() {
		mostrarPanel(pantallaHabitacionesDisp);
	}
	public void mostrarReserva() {
		mostrarPanel(pantallaReserva);
	}
	public void mostrarPagar() {
		mostrarPanel(pantallaPagar);
	}
	public void mostrarCancelarReserva() {
		mostrarPanel(pantallaCancelarReserva);
	}
	
	//Metodo para repintar la ventana
	public void mostrarPanel(JPanel panel) {
		setContentPane(panel);
		revalidate();
		repaint();
	}

	//Getters and Setter
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public InicioSesionU getPantallaLogin() {
		return pantallaLogin;
	}
	public void setPantallaLogin(InicioSesionU pantallaLogin) {
		this.pantallaLogin = pantallaLogin;
	} 
	
}

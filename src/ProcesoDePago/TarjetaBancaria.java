package ProcesoDePago;

public class TarjetaBancaria {
	private String numeroTarjeta;
    private String nombrePropietario;
    private String fechaExpiracion;
    private int codigoSeguridad;
    private String Tipo;
    
	public TarjetaBancaria(String numeroTarjeta, String nombrePropietario, String fechaExpiracion,
			int codigoSeguridad) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.nombrePropietario = nombrePropietario;
		this.fechaExpiracion = fechaExpiracion;
		this.codigoSeguridad = codigoSeguridad;
		this.Tipo = "";
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public int getCodigoSeguridad() {
		return codigoSeguridad;
	}
	public void setCodigoSeguridad(int codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	@Override
	public String toString() {
		return "NumeroTarjeta = " + numeroTarjeta + ", nombre del propietario = " + nombrePropietario
				+ ", Tipo = " + Tipo
				+ ".";
	}
    
	
    
}

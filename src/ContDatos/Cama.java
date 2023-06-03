package ContDatos;

public class Cama {
	private String tipo;
	private int capacidad;
	
	public Cama(String tipo, int capacidad) {
		super();
		this.tipo = tipo;
		this.capacidad = capacidad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	

}

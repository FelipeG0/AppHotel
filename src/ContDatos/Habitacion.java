package ContDatos;

public class Habitacion {
	private String idHabitacion;
	private int ubicacion;
	private String tipo;
	private Boolean balcon;
	private Boolean vista;
	private Boolean cocinaintegrada;
	private Boolean disponible;
	
	
	
	public Habitacion(String idHabitacion, int ubicacion, String tipo, Boolean balcon, Boolean vista,
			Boolean cocinaintegrada, Boolean disponible) {
		super();
		this.idHabitacion = idHabitacion;
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.balcon = balcon;
		this.vista = vista;
		this.cocinaintegrada = cocinaintegrada;
		this.disponible = disponible;
	}
	
	public String getIdHabitacion() {
		return idHabitacion;
	}
	public void setIdHabitacion(String idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
	public int getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(int ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Boolean getBalcon() {
		return balcon;
	}
	public void setBalcon(Boolean balcon) {
		this.balcon = balcon;
	}
	public Boolean getVista() {
		return vista;
	}
	public void setVista(Boolean vista) {
		this.vista = vista;
	}
	public Boolean getCocinaintegrada() {
		return cocinaintegrada;
	}
	public void setCocinaintegrada(Boolean cocinaintegrada) {
		this.cocinaintegrada = cocinaintegrada;
	}
	public Boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}



	@Override
    public String toString() {
        return "Detalles habitacion " + idHabitacion + ": " + 
                "piso = " + ubicacion +
                ", tipo = '" + tipo + '\'' +
                ", tieneBalcon = " + balcon +
                ", tieneVista = " + vista +
                ", tieneCocinaIntegrada = " + cocinaintegrada +
                ", disponible = " + disponible +
                '.';
    }

}

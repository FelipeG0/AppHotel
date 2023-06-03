package ContDatos;

public class Huesped {
    private String documento;
    private String nombre;
    private String telefono;
    private String correo;
    private int edad;
    private String tipo;
	public Huesped(String documento, String nombre, String telefono, String correo, int edad, String tipo) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.edad = edad;
		this.tipo = tipo;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Huesped " + nombre + ": " + "Documento = " + documento + ", telefono = " + telefono + ", correo = "
				+ correo + ", edad = " + edad + ", tipo = " + tipo ;
	}
    
    
}
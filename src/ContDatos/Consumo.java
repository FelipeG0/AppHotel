package ContDatos;


public class Consumo {
    private String tipoServicio;
    private double valor;
    private Boolean pago;
    private Factura factura;
   


	public Consumo(String tipoServicio) {
	        this.tipoServicio = tipoServicio;
	        this.pago = false;
	}
	public Consumo(String tipoServicio, double valor) {
		this.tipoServicio = tipoServicio;
		this.valor = valor; 
	}
	
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Boolean getPago() {
		return pago;
	}
	public void setPago(Boolean pago) {
		this.pago = pago;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}




	@Override
	public String toString() {
		return tipoServicio + " valor " + valor;
	}
	
	
    
}

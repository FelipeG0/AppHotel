package ProcesoDePago;

public class Transaccion {
	private String numeroCuenta;
	private int numeroTransaccion;
	private double valorTransaccion;
	
	public Transaccion(String numeroCuenta, double valorTransaccion) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.numeroTransaccion = 0;
		this.valorTransaccion = valorTransaccion;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public int getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(int numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public double getValorTransaccion() {
		return valorTransaccion;
	}

	public void setValorTransaccion(double valorTransaccion) {
		this.valorTransaccion = valorTransaccion;
	}

	@Override
	public String toString() {
		return "NumeroTransaccion = " + numeroTransaccion + ", cuenta destino = " + numeroCuenta 
				+ ", valor transaccion = " + valorTransaccion + ".";
	}
	
	
}


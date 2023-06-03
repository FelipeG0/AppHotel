package ContDatos;

public class TipoHabitacion {
    private String tipo;
    private int capacidad_maxima;
    private int camas_grandes;
    private int camas_pequenas;
	private int tamanioMetros;
	private Boolean aireAcondicionado;
	private Boolean calefaccion;
	private Boolean tv;
	private Boolean cafetera;
	private Boolean ropaCama;
	private Boolean tapeteHipoalergenico;
	private Boolean plancha;
	private Boolean secadorDePelo;
	private int voltajeAC;
	private Boolean usbA;
	private Boolean usbC;

    public TipoHabitacion(String tipo, int capacidad_maxima, int camas_grandes, int camas_pequenas,
			int tamanioMetros, Boolean aireAcondicionado, Boolean calefaccion, Boolean tv,
			Boolean cafetera, Boolean ropaCama, Boolean tapeteHipoalergenico, Boolean plancha, Boolean secadorDePelo,
			int voltajeAC, Boolean usbA, Boolean usbC) {
		super();
		this.tipo = tipo;
		this.capacidad_maxima = capacidad_maxima;
		this.camas_grandes = camas_grandes;
		this.camas_pequenas = camas_pequenas;
		this.tamanioMetros = tamanioMetros;
		this.aireAcondicionado = aireAcondicionado;
		this.calefaccion = calefaccion;
		this.tv = tv;
		this.cafetera = cafetera;
		this.ropaCama = ropaCama;
		this.tapeteHipoalergenico = tapeteHipoalergenico;
		this.plancha = plancha;
		this.secadorDePelo = secadorDePelo;
		this.voltajeAC = voltajeAC;
		this.usbA = usbA;
		this.usbC = usbC;
	}



	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCapacidad_maxima() {
		return capacidad_maxima;
	}
	public void setCapacidad_maxima(int capacidad_maxima) {
		this.capacidad_maxima = capacidad_maxima;
	}
	public int getCamas_grandes() {
		return camas_grandes;
	}
	public void setCamas_grandes(int camas_grandes) {
		this.camas_grandes = camas_grandes;
	}
	public int getCamas_pequenas() {
		return camas_pequenas;
	}
	public void setCamas_pequenas(int camas_pequenas) {
		this.camas_pequenas = camas_pequenas;
	}
	public int getTamanioMetros() {
		return tamanioMetros;
	}
	public void setTamanioMetros(int tamanioMetros) {
		this.tamanioMetros = tamanioMetros;
	}
	public Boolean getAireAcondicionado() {
		return aireAcondicionado;
	}
	public void setAireAcondicionado(Boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}
	public Boolean getCalefaccion() {
		return calefaccion;
	}
	public void setCalefaccion(Boolean calefaccion) {
		this.calefaccion = calefaccion;
	}
	public Boolean getTv() {
		return tv;
	}
	public void setTv(Boolean tv) {
		this.tv = tv;
	}
	public Boolean getCafetera() {
		return cafetera;
	}
	public void setCafetera(Boolean cafetera) {
		this.cafetera = cafetera;
	}
	public Boolean getRopaCama() {
		return ropaCama;
	}
	public void setRopaCama(Boolean ropaCama) {
		this.ropaCama = ropaCama;
	}
	public Boolean getTapeteHipoalergenico() {
		return tapeteHipoalergenico;
	}
	public void setTapeteHipoalergenico(Boolean tapeteHipoalergenico) {
		this.tapeteHipoalergenico = tapeteHipoalergenico;
	}
	public Boolean getPlancha() {
		return plancha;
	}
	public void setPlancha(Boolean plancha) {
		this.plancha = plancha;
	}
	public Boolean getSecadorDePelo() {
		return secadorDePelo;
	}
	public void setSecadorDePelo(Boolean secadorDePelo) {
		this.secadorDePelo = secadorDePelo;
	}
	public int getVoltajeAC() {
		return voltajeAC;
	}
	public void setVoltajeAC(int voltajeAC) {
		this.voltajeAC = voltajeAC;
	}
	public Boolean getUsbA() {
		return usbA;
	}
	public void setUsbA(Boolean usbA) {
		this.usbA = usbA;
	}
	public Boolean getUsbC() {
		return usbC;
	}
	public void setUsbC(Boolean usbC) {
		this.usbC = usbC;
	}
	
}

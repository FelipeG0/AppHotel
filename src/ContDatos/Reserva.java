package ContDatos;

import java.time.*;
import java.util.*;

public class Reserva {
	private static int ultimoIdReserva = 1;
	private int idReserva;
    private LocalDate fechaIn;
    private LocalDate fechaFin;
    private int ninos;
    private Huesped titular;
    private ArrayList<Huesped> grupo;
    private ArrayList<Habitacion> habsReservadas;
    private double tarifaHabs;
    private double saldo;
    private ArrayList<Factura> facturas;
    
	public Reserva(LocalDate fechaIn, LocalDate fechaFin) {
		super();
		this.titular = null;
		this.idReserva = ultimoIdReserva;
		this.fechaIn = fechaIn;
		this.fechaFin = fechaFin;
		ninos = 0;
		this.grupo = new ArrayList<>();
		this.habsReservadas = new ArrayList<>();	
		this.tarifaHabs = 0;
		this.facturas = new ArrayList<>();
		this.saldo = 0;
	
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int id) {
		this.idReserva = id;
	}
	public void setIdReserva() {
		this.idReserva = ultimoIdReserva;
	}
	public LocalDate getFechaIn() {
		return fechaIn;
	}
	public void setFechaIn(LocalDate fechaIn) {
		this.fechaIn = fechaIn;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getNinos() {
		return ninos;
	}
	public void setNinos(int ninos) {
		this.ninos = ninos;
	}
	public ArrayList<Huesped> getGrupo() {
		return grupo;
	}
	public void setGrupo(ArrayList<Huesped> grupo) {
		this.grupo = grupo;
	}
	public ArrayList<Habitacion> getHabsReservadas() {
		return habsReservadas;
	}
	public void setHabsReservadas(ArrayList<Habitacion> habsReservadas) {
		this.habsReservadas = habsReservadas;
	}
	
	public void agregarHabitacion(Habitacion hab) {
		habsReservadas.add(hab);
	}
    public void agregarHuesped(Huesped huesped) {
    	grupo.add(huesped);
    }
    
    public Huesped getTitular() {
		return titular;
	}
	public void setTitular(Huesped titular) {
		this.titular = titular;
	}
	public static int getUltimoIdReserva() {
		return ultimoIdReserva;
	}
	public static void setUltimoIdReserva(int ultimoIdReserva) {
		Reserva.ultimoIdReserva = ultimoIdReserva + 1;
	}
	public double getTarifaHabs() {
		return tarifaHabs;
	}
	public void setTarifaHabs(double tarifasHabs) {
		this.tarifaHabs = tarifasHabs;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo += saldo;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(Factura factura) {
		facturas.add(factura);
	}
	
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserva ").append(idReserva).append(": ")
                .append("Fecha de ingreso =  ").append(fechaIn)
                .append(", Fecha de salida = ").append(fechaFin)
                .append(", Niños = ").append(ninos)
                .append(", Titular de reserva = ").append(titular)
                .append(", \ngrupo = [");
        for (Huesped h : grupo) {
            sb.append(h.toString()).append("\n, ");
        }
        if (!grupo.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("],")
                .append(" \nhabitaciones reservadas = [");
        for (Habitacion h : habsReservadas) {
            sb.append(h.toString()).append("\n, ");
        }
        if (!habsReservadas.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]")
        .append(", \nTarifa total de noches reservadas = $ ").append(tarifaHabs)
        .append(", Saldo asociado a la resrva = ").append(saldo)
        .append(".\n");
        return sb.toString();
    }
    
}

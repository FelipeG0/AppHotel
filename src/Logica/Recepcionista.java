package Logica;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import ProcesoDePago.*;
import ContDatos.*;

public class Recepcionista {
	private Hotel hotel;
	
	private Map<String, Habitacion> mapahabitaciones = new HashMap<>();
	private Map<Integer, Reserva> reservas = new HashMap<>();
	private Map<String, Huesped> mapaHuespedes = new HashMap<>();
	private Map<String, Integer> mapaCamas = new HashMap<>();
	private Map<String, TipoHabitacion> mapaTipoHabs = new HashMap<>();
	private Map<String, TarifaTipoHabitacion> mapaTarifasHabs = new HashMap<>();
	
	public Recepcionista() {
		mapahabitaciones = Hotel.getMapa_habitaciones();
		reservas = Hotel.getMapaReservas();
		mapaHuespedes = Hotel.getMapaHuespedes();
		mapaCamas = Hotel.getMapaCamas();
		mapaTipoHabs = Hotel.getMapa_tipos_habitaciones();
		mapaTarifasHabs = Hotel.getMapaTarifasHabs();
	}
	
	public List<Habitacion> info_Habitaciones(String piso, String tipo, String balcon, String vista, String cocina, String disp) {
	    List<Habitacion> habitacionesFiltradas = mapahabitaciones.values().stream()
	        .filter(h -> piso.isEmpty() || Integer.toString(h.getUbicacion()).equals(piso))
	        .filter(h -> tipo.isEmpty() || h.getTipo().equals(tipo))
	        .filter(h -> balcon.isEmpty() || Boolean.toString(h.getBalcon()).equals(balcon))
	        .filter(h -> vista.isEmpty() || Boolean.toString(h.getVista()).equals(vista))
	        .filter(h -> cocina.isEmpty() || Boolean.toString(h.getCocinaintegrada()).equals(cocina))
	        .filter(h -> disp.isEmpty() || Boolean.toString(h.getDisponible()).equals(disp))
	        .collect(Collectors.toList());
	    return habitacionesFiltradas;
	}
	
	public Habitacion habitacion_ID(String idHab) {
		return mapahabitaciones.get(idHab);
	}
	
	public void crearHuespued(String documento, String nombre, String telefono, String correo, int edad, String tipo) {
		Huesped huesped = new Huesped(documento, nombre, telefono, correo, edad, tipo);
		mapaHuespedes.put(documento, huesped);
	}
	public String nuevaReserva(String documentos, String tipoHab, String fechaIn, String fechaFin) throws Exception{
		
		try {
			if (documentos.isEmpty() || tipoHab.isEmpty() || fechaIn.isEmpty() || fechaFin.isEmpty()) {
				throw new Exception("\nAlguno de los campos necesarios no ha sido proporcionado, verifique e intente de nuevo. \n");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
				
        LocalDate fechainicial = LocalDate.parse(fechaIn);
        LocalDate fechafinal = LocalDate.parse(fechaFin);
        LocalDate fechaActual = LocalDate.now();
        try {
        	if (fechainicial.isBefore(fechaActual) || fechafinal.isAfter(fechaActual.plusYears(1))) {
        		throw new Exception("\nLas fechas ingresadas no son validas, verifique e intente de nuevo. \n");          
        	}
        } catch (Exception e) {
        	return e.getMessage();
        }
    	Reserva reserva = new Reserva(fechainicial, fechafinal);         
        Reserva.setUltimoIdReserva(reserva.getIdReserva());
        String[] set = documentos.split("-");
		for (String doc : set) {
			Huesped h = Hotel.getMapaHuespedes().get(doc);
			if (h.getTipo().equals("titular")) {
				reserva.setTitular(h);
			} else {
				reserva.agregarHuesped(h);
			}
			if (h.getEdad() < 18) {
	    		reserva.setNinos(reserva.getNinos() + 1);
	    	}
		}
		List<Habitacion> habs = info_Habitaciones("",tipoHab,"","","","true");
		int huespedesRestantes = set.length;
		int capacidad = mapaTipoHabs.get(tipoHab).getCapacidad_maxima();
		int cantidadHabitacionesNecesarias = (int) Math.ceil((double) huespedesRestantes / capacidad);
		try {
			if (habs.size() < cantidadHabitacionesNecesarias) {
	        throw new Exception("\nNo hay suficientes habitaciones disponibles para alojar a todos los huéspedes en la reserva.\n");
	    	}
		} catch (Exception e) {
			return e.getMessage();
		}
		int i = 0;	
		while (huespedesRestantes > 0) {
			reserva.agregarHabitacion(habs.get(i));
			habs.get(i).setDisponible(false);
			huespedesRestantes -= capacidad;
			i += 1;
		}
		int habsReservadas = reserva.getHabsReservadas().size();
		int tarifa = mapaTarifasHabs.get(tipoHab).getTarifa();
		int noches = (int) ChronoUnit.DAYS.between(fechainicial, fechafinal);		
		try {
			if (tarifa != 0) {
			reserva.setTarifaHabs(tarifa * habsReservadas * noches);
			} else {
				throw new Exception("\nNo hay una tarifa establecida para el rango de fecha escogido. \n");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		reservas.put(reserva.getIdReserva(), reserva);  
		return reserva.toString();
	}
	
	public Object infoReserva(String documentoTitular) {
		try {
			for (Reserva rese : reservas.values()) {
				if (rese.getTitular().getDocumento().equals(documentoTitular)) {
					return rese;
				}
			}
			throw new Exception("\nNo existe una reserva asiganada a un titular con ese numero de documento. \n");
		} catch (Exception e) {
			return "\n" + e.getMessage() + "\n";
		}
	}
	
	public String cancelarReserva(String docTitular) {
		String msj = "\nNo es posible cancelar la reserva, debido a que faltan menos de 48 horas para la fecha de ingreso. \n";
		Reserva reserva = null;
		LocalDate fechaIn = null;
		LocalDate fechaActual = LocalDate.now().plusDays(2);
		for (Reserva rese : reservas.values()) {
			if (rese.getTitular().getDocumento().equals(docTitular)) {
				reserva = rese;
				fechaIn = rese.getFechaIn();
			}
		}
		if (reserva != null) {
			if (fechaActual.isBefore(fechaIn)) {
				reservas.remove(reserva.getIdReserva());
				msj = "\nReserva cancelada con exito. \n";
			}
		} else {
			msj = "\nNo existe una reserva asiganada a un titular con ese numero de documento. \n";
		}	
		return msj;
	}
	
	public String  pagoYfactura(String docTitular, PasarelaPago pasarela, String[] infoTarjeta) throws IOException {
		String msj = "";
		Object info = infoReserva(docTitular);
		Reserva reserva = null;
		//Verificación y casting del retorno
		try {
			if (info instanceof Reserva) {
			    reserva = (Reserva) info;
			} else {
			    throw new Exception((String) info);
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		double valorAcancelar = reserva.getSaldo() + reserva.getTarifaHabs();
		int cvc = Integer.parseInt(infoTarjeta[3]);
		//Conexión a pasarela
		TarjetaBancaria tarjeta = new TarjetaBancaria(infoTarjeta[0], infoTarjeta[1], infoTarjeta[2], cvc);
		Transaccion transaccion = new Transaccion(Hotel.getNumeroCuenta(), valorAcancelar);	
		Boolean validacion = pasarela.procesoPago(tarjeta, transaccion);
		if (validacion) {
			//Instanciar factura
			Factura factura = new Factura(reserva.getTitular(), valorAcancelar);
			Factura.setUltimoIdFactura();
			//Instanciar consumos (Estadia y saldo)
			Consumo estadia = new Consumo("Estadía");
			estadia.setValor(reserva.getTarifaHabs());
			estadia.setPago(validacion);
			Consumo saldo = new Consumo("Saldo");
			saldo.setValor(reserva.getSaldo());
			saldo.setPago(validacion);
			//Añadir consumos
			factura.setConsumos(estadia);
			factura.setConsumos(saldo);
			//Factura para retornar
			msj = factura.toString();
			Factura.guardarFactura(factura);
		} else {
			msj = "Error procesando el pago. \n";
		}
		return msj;
	}
	
	
}



package Logica;

import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

import ContDatos.*;

public class Admin {
	private Hotel hotel;
	private Map<String, Habitacion> habitaciones = new HashMap<>();
	private Map<String, TarifaTipoHabitacion> tarifaHabitaciones = new HashMap<>();
	private Map<String, Servicios> servicios = new HashMap<>();
	private Map<String, ArrayList<Alimento>> mapaMenu = new HashMap<>();
	
	public Admin() throws FileNotFoundException, IOException {
		habitaciones = Hotel.getMapa_habitaciones();
		tarifaHabitaciones = Hotel.getMapTarifaHab();
		servicios = Hotel.getMapa_servicios();
		mapaMenu = Hotel.getMapMenu();
	}
	
	public void crear_habitaciones(String idHab, int piso, String tipo, Boolean balcon, Boolean vista, Boolean cocina, Boolean disponible) {
		Habitacion habitacion = new Habitacion(idHab, piso, tipo, balcon, vista, cocina, disponible);
		if (habitaciones.containsKey(idHab)) {
			habitaciones.replace(idHab, habitacion);
		} else{
			habitaciones.put(idHab, habitacion);
		}
	}
	public void cargar_habitaciones(String ruta_archivo) throws FileNotFoundException, IOException {
        	File archivo = new File(ruta_archivo);
    		hotel.cargarhabitaciones(archivo);
		
    }
	public void cargar_tarifas_habitaciones(String ruta_archivo) throws FileNotFoundException, IOException {
        File archivo = new File(ruta_archivo);
        hotel.cargartarifashabs(archivo);
    }
	public ArrayList<String> tarifa_actual(String tipoHab) {
		TarifaTipoHabitacion tarifa = tarifaHabitaciones.get(tipoHab);
		ArrayList<String> actual = new ArrayList<>();
		actual.add(tipoHab);
		actual.add(String.valueOf(tarifa.getTarifa()));
		actual.add(tarifa.getFechafinal().toString());
		actual.add(tarifa.getFechainicial().toString());
		return actual;
	}
	public void cargar_tarifas(String tipoHab, int tarifa, String fechainicial, String fechafinal) {
		TarifaTipoHabitacion tarifahab = tarifaHabitaciones.get(tipoHab);
		tarifahab.setTarifa(tarifa);
		int[] fecha = Arrays.stream(fechainicial.split("/")).mapToInt(Integer::parseInt).toArray();
        LocalDate fechaini = LocalDate.of(fecha[0], fecha[1], fecha[2]);
        int[] fechaf = Arrays.stream(fechainicial.split("/")).mapToInt(Integer::parseInt).toArray();
        LocalDate fechafin = LocalDate.of(fechaf[0], fechaf[1], fechaf[2]);
		tarifahab.setFechainicial(fechaini);
		tarifahab.setFechafinal(fechafin);
	}
	public ArrayList<String> verificar_tarifas() {
	    String[] tipos = {"estandar", "suite", "suite doble"};
	    ArrayList<String> problemas = new ArrayList<>();

	    LocalDate fechaActual = LocalDate.now();

	    for (int i = 0; i < tipos.length; i++) {
	        LocalDate fechaini = tarifaHabitaciones.get(tipos[i]).getFechainicial();
	        LocalDate fechafin = tarifaHabitaciones.get(tipos[i]).getFechafinal();

	        if (fechaActual.isBefore(fechaini)) {
	            problemas.add(String.format("La tarifa de las habitaciones de tipo %s aún no ha sido asignada y comenzará a regir a partir de %s.", tipos[i], fechaini));
	        } else if (fechaActual.isAfter(fechafin)) {
	            problemas.add(String.format("No se encuentra tarifa asignada para las habitaciones de tipo %s. La última fecha registrada es %s, se recomienda establecer una tarifa.", tipos[i], fechafin));
	        } else {
	            long diasHastaVencimiento = ChronoUnit.DAYS.between(fechaActual, fechafin);
	            if (diasHastaVencimiento < 365) {
	                problemas.add(String.format("La tarifa de las habitaciones de tipo %s vence en %d días, se recomienda establecer una nueva tarifa que supere los 365 días.", tipos[i], diasHastaVencimiento));
	            }
	        }
	    }
	    return problemas;
	}
	public void modificar_tarifa_servicio(String servicio, int tarifa) {
		servicios.get(servicio).setTarifaporpersona(tarifa);
	}
	public void cargar_menu(String ruta) throws FileNotFoundException, IOException {
		File archivo = new File(ruta);
		hotel.cargarmenu(archivo);
	}
	public void modificar_info_menu(String nombre, String tipo,int tarifa, String comidaDisp, String lugarDisp) {
		Alimento alimento = new Alimento(nombre, tipo, tarifa, comidaDisp, lugarDisp);
		ArrayList<Alimento> set = mapaMenu.get(tipo);
		Boolean existe = false;
        int x = -1; //Posición del objeto en la lista
    
        for (Alimento food : set) {
        	if (food.getNombre().equals(nombre)) {
        		existe = true;
        		alimento = food;
        		x = set.indexOf(food);
        	}
        }
        if (existe) {
        	set.set(x, alimento);
        } else {
        	set.add(alimento);
        	mapaMenu.put(tipo, set);
        } 
	}
}



















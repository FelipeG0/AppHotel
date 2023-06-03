package ContDatos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
//import com.fasterxml.jackson.databind.ObjectMapper;

import Logica.Hotel;


public class Factura {
	private static File archivofacturas = new File("data/historialFacturas.txt");
	//private static final ObjectMapper objectMapper = new ObjectMapper();
	private static int ultimoIdFactura = 1;
	private int idFactura;
	private LocalDate fecha;
	private Huesped huesped;
    private ArrayList<Consumo> consumos;
    private double total;

    public Factura(Huesped huesped, double total) {
    	this.idFactura = ultimoIdFactura;
    	this.fecha = LocalDate.now();
		this.huesped = huesped;
		this.consumos = new ArrayList<>();
		this.total = total;
	}
    
    public static void guardarFactura(Factura factura){
    	StringBuilder sb = new StringBuilder();
		sb.append(factura.getIdFactura()).append(":")
		.append("[")
		.append(factura.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(";")
		.append(factura.getHuesped().getNombre()).append(";[");
		for (Consumo c : factura.consumos) {
			sb.append(c.getTipoServicio()).append(";")
			.append(Math.round(c.getValor())).append("]");
			//.append(Boolean.toString(c.getPago())).append(";");
		}
		sb.append(";").append(Math.round(factura.getTotal())).append("]");
		
    	try (FileWriter writer = new FileWriter(archivofacturas, true)){
    		writer.write("\n" + sb.toString());
		
    	}catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }

    	
    
    public void agregarConsumo(Consumo consumo) {
        consumos.add(consumo);
        if(consumo.getPago() == false) {
        	total += consumo.getValor();
        }
    }

	public static int getUltimoIdFactura() {
		return ultimoIdFactura;
	}

	public static void setUltimoIdFactura() {
		Factura.ultimoIdFactura = ultimoIdFactura + 1;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int id) {
		this.idFactura = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

	public ArrayList<Consumo> getConsumos() {
		return consumos;
	}

	public void setConsumos(Consumo consumo) {
		consumos.add(consumo);
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Hotel.getNombreHotel()).append("\n")
		.append("Factura : ").append(idFactura)
        .append("\nFecha =  ").append(fecha)
        .append("\nNombre huesped = ").append(huesped.getNombre())
        .append("\nConsumo     ").append("Precio");
        for (Consumo c : consumos) {
            sb.append("\n").append(c.getTipoServicio()).append("     ").append(c.getValor());
        }
        sb.append("\nTotal = $ ").append(total)
        .append("\n");
        return sb.toString();
    }
    
	
    
    
}

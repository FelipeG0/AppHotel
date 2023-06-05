package Logica;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import ContDatos.*;

public class Hotel {
	private static Admin admin;
	private static Recepcionista recep;
	private static Empleado empleado;
	private static String nombreHotel;
	private static Boolean parqueaderoPago;
	private static Boolean piscina;
	private static String zonasHumedas;
    private static Boolean bbq;
	private static Boolean wifiGratis;
    private static Boolean recep24horas;
	private static Boolean mascotas;
	private static String numeroCuenta;
	private static File archivohotel = new File("data/infoHotel.txt");
    private static File archivotiposhabitaciones = new File("data/tiposdehabitaciones.txt");
    private static File archivomenu = new File("data/menu.txt");
    private static File archivohabitaciones = new File("data/habitaciones.txt");
    private static File archivousuarios = new File("data/usuarios.txt");
    private static File archivoservicios = new File("data/servicioshotel.txt");
    private static File archivotarifashabs = new File("data/tarifashabitaciones.txt");
    private static File archivohuespedes = new File("data/huespedesactuales.txt");
    private static File archivoreservas = new File("data/reservasactuales.txt");
    private static File archivocamas = new File("data/camas.txt");
    private static File archivofacturas = new File("data/historialFacturas.txt");
    
    private static Map<String, TipoHabitacion> mapa_tipos_habitaciones = new HashMap<>();
    private static Map<String, ArrayList<Alimento> > mapMenu = new HashMap<>();
    private static ArrayList<Alimento> listaBebidas = new ArrayList<>();
    private static ArrayList<Alimento> listaComidas = new ArrayList<>();
    private static Map<String, Habitacion> mapa_habitaciones = new HashMap<>(100);
    private static Map<String, Usuario> users = new HashMap<>();
    private static Map<String, Servicios> mapa_servicios = new HashMap<>();
    private static Map<String, TarifaTipoHabitacion> mapaTarifasHabs = new HashMap<>();
    private static Map<String, Huesped> mapaHuespedes = new HashMap<>();
    private static Map<Integer, Reserva> mapaReservas = new HashMap<>();
    private static Map<String, Integer> mapaCamas = new HashMap<>();
    private static Map<String, ArrayList<Factura>> mapaFacturas = new HashMap<>();
    private static Map<String, ArrayList<String>> atributos = new HashMap<>();
 
    public Hotel() throws FileNotFoundException, IOException{
    	BufferedReader br = new BufferedReader(new FileReader(archivohotel));
		String linea = br.readLine();
        linea = br.readLine();

		String[] partes = linea.split(";");
		Hotel.nombreHotel = partes[0];
		Hotel.parqueaderoPago = Boolean.parseBoolean(partes[1]);
		Hotel.piscina = Boolean.parseBoolean(partes[2]);
		Hotel.zonasHumedas = partes[3];
		Hotel.bbq = Boolean.parseBoolean(partes[4]);
		Hotel.wifiGratis = Boolean.parseBoolean(partes[5]);
		Hotel.recep24horas = Boolean.parseBoolean(partes[6]);
		Hotel.mascotas = Boolean.parseBoolean(partes[7]);
		Hotel.numeroCuenta = partes[8];
		ArrayList<String> strings = new ArrayList<>();
		ArrayList<String> booleans = new ArrayList<>();
		
		strings.add("Nombre Hotel");
		booleans.add("Parqueadero pago");
		booleans.add("Piscina");
		strings.add("Zonas Humedas");
		booleans.add("Zona BBQ");
		booleans.add("Wifi gratis");
		booleans.add("Recepción 24H");
		booleans.add("Mascotas");
		atributos.put("String", strings);
		atributos.put("Boolean", booleans);
		
		br.close();

		Hotel.admin = new Admin();
		Hotel.recep = new Recepcionista();
		Hotel.empleado = new Empleado();
		
		cargarDatos();
		
        //estos archivos aun no estan creados y creo que es mejor ponerlos como atributos estaticos
        //String archivoconsumos;
        //String archivofacturas;

    }
    public void cargarDatos() throws FileNotFoundException, IOException {
    	cargarhabitaciones(archivohabitaciones);
    	cargartiposhabitaciones();
    	cargartarifashabs(archivotarifashabs);
    	cargarservicios();
    	cargarmenu(archivomenu);	
    	cargarHuespedes(archivohuespedes);
    	cargarReservas(archivoreservas);
    	cargarCamas();
    	
    }
    public static void actualizar_archivos() {
    	actualizarArchivoHabitaciones();
    	actualizarArchivoTarifas();
    	actualizarArchivoTarifasServicios();
    	actualizarArchivoMenu();
    	actualizarArchivoReservas();
    	actualizarArchivoFacturas();
    	actualizarCaracteristicasHotel();
    	actualizarArchivoHuespedes();
    }
    public void cargarhabitaciones(File archivo) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String linea = br.readLine();
        linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String idHab = partes[0];
			int piso = Integer.parseInt(partes[1]);
            String tipo = partes[2];
            boolean balcon = Boolean.parseBoolean(partes[3]);
            boolean vista = Boolean.parseBoolean(partes[4]);
            boolean cocina = Boolean.parseBoolean(partes[5]);
            boolean disponible = Boolean.parseBoolean(partes[6]);
			Habitacion habitacion = new Habitacion(idHab, piso, tipo, balcon, vista, cocina, disponible);
			if (mapa_habitaciones.containsKey(idHab)) {
				mapa_habitaciones.replace(idHab, habitacion);
			} else {
				mapa_habitaciones.put(idHab, habitacion);
			}
			linea = br.readLine();
		}	
		br.close();
	}
    
    public void cargartiposhabitaciones() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivotiposhabitaciones));
		String linea = br.readLine();
        linea = br.readLine();

		while (linea != null)
		{
			String[] partes = linea.split(";");
			String tipoHab = partes[0];
			int capMax = Integer.parseInt(partes[1]);
            int camasDobles = Integer.parseInt(partes[2]);
            int camasSencillas = Integer.parseInt(partes[3]);
            int tamanioMetros = Integer.parseInt(partes[4]);
            Boolean aireAcondicionado = Boolean.parseBoolean(partes[5]);  
            Boolean calefaccion = Boolean.parseBoolean(partes[6]);
            Boolean tv = Boolean.parseBoolean(partes[7]);
            Boolean cafetera = Boolean.parseBoolean(partes[8]); 
            Boolean ropaCama = Boolean.parseBoolean(partes[9]);
            Boolean tapeteHipoalergenico = Boolean.parseBoolean(partes[10]); 
            Boolean plancha = Boolean.parseBoolean(partes[11]);
            Boolean secadorDePelo = Boolean.parseBoolean(partes[12]); 
            int voltajeAC = Integer.parseInt(partes[13]);
            Boolean usbA = Boolean.parseBoolean(partes[14]);
            Boolean usbC = Boolean.parseBoolean(partes[15]);
            TipoHabitacion caracteristicas = new TipoHabitacion(tipoHab, capMax, camasDobles, camasSencillas, tamanioMetros, aireAcondicionado, calefaccion, tv, cafetera, ropaCama, tapeteHipoalergenico, plancha, secadorDePelo, voltajeAC, usbA, usbC);
            mapa_tipos_habitaciones.put(tipoHab, caracteristicas);
			linea = br.readLine();
		}	
		br.close();
    }
    
    public void cargartarifashabs(File archivo) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
		String linea = br.readLine();
        linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String tipohab = partes[0];
			int tarifa = Integer.parseInt(partes[1]);
            String fechaS = partes[2];
            String fechaSfinal = partes[3];
            int[] fecha = Arrays.stream(fechaS.split("-")).mapToInt(Integer::parseInt).toArray();
            LocalDate fechainicial = LocalDate.of(fecha[0], fecha[1], fecha[2]);
            int[] fechaF = Arrays.stream(fechaSfinal.split("-")).mapToInt(Integer::parseInt).toArray();
            LocalDate fechafinal = LocalDate.of(fechaF[0], fechaF[1], fechaF[2]);
			TarifaTipoHabitacion tarifashabs = new TarifaTipoHabitacion(tipohab, tarifa, fechainicial, fechafinal);
			if (mapaTarifasHabs.containsKey(tipohab)) {
				mapaTarifasHabs.replace(tipohab, tarifashabs);
			} else {
				mapaTarifasHabs.put(tipohab, tarifashabs);
			}
			linea = br.readLine();
		}	
		br.close();
    }
    
    public void cargarHuespedes(File archivo) throws FileNotFoundException, IOException {
    	BufferedReader br = new BufferedReader(new FileReader(archivo));
		String linea = br.readLine();
        linea = br.readLine();
		//documento;nombre;telefono;correo;edad;titular/acompañante
		while (linea != null)
		{ 
			String[] partes = linea.split(";");
			String documento = partes[0];
			String nombre = partes[1];
			String telefono = partes[2];
			String correo = partes[3];
			int edad = Integer.parseInt(partes[4]);
			String tipo = partes[5];
			Huesped huesped = new Huesped(documento, nombre, telefono, correo, edad, tipo);
			mapaHuespedes.put(documento, huesped);
			linea = br.readLine();	
		}
		br.close();
    }
    
    public void cargarReservas(File archivo) throws FileNotFoundException, IOException {
    	BufferedReader br = new BufferedReader(new FileReader(archivo));
		String linea = br.readLine();
		int ultimoIdReserva = Integer.parseInt(br.readLine());
		Reserva.setUltimoIdReserva(ultimoIdReserva);
        linea = br.readLine();
        
		while (linea != null)
		{		
			String[] partes = linea.split(";");
			int idReserva = Integer.parseInt(partes[0]);
			String fechainicial = partes[1];
            int[] fecha = Arrays.stream(fechainicial.split("-")).mapToInt(Integer::parseInt).toArray();
            LocalDate fechaIn = LocalDate.of(fecha[0], fecha[1], fecha[2]);
            String fechafinal = partes[2];
            int[] fechaF = Arrays.stream(fechafinal.split("-")).mapToInt(Integer::parseInt).toArray();
            LocalDate fechaFin = LocalDate.of(fechaF[0], fechaF[1], fechaF[2]);
            String documentoTitular = partes[3]; 
            Reserva reserva = new Reserva(fechaIn, fechaFin);
            reserva.setTitular(mapaHuespedes.get(documentoTitular));
            reserva.setIdReserva(idReserva);
            if (!partes[4].isEmpty()) {
            	String[] docsGrupo = partes[4].split("-");
                for (String id : docsGrupo) { 
                	Huesped huesped = mapaHuespedes.get(id);
            		reserva.agregarHuesped(huesped);	          	
                }
            }            
            reserva.setNinos(Integer.parseInt(partes[5]));
            String[] idhabsReservadas = partes[6].split("-");
            for (String idHab : idhabsReservadas) {            	
            	reserva.agregarHabitacion(mapa_habitaciones.get(idHab));
            }
            reserva.setTarifaHabs(Integer.parseInt(partes[7]));
            mapaReservas.put(idReserva, reserva);
			linea = br.readLine();
		}	
		br.close();
	} 

    public void cargarmenu(File archivo) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
		String linea = br.readLine();
        linea = br.readLine();
        mapMenu.put("comida", listaComidas);
        mapMenu.put("bebida", listaBebidas);
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombre = partes[0];
            String tipo = partes[1];
            int precio = Integer.parseInt(partes[2]);
            String comidadisp = partes[3];
            String lugardisp = partes[4];
            Alimento alimento = new Alimento(nombre, tipo, precio, comidadisp, lugardisp);
            
            ArrayList<Alimento> set = mapMenu.get(tipo);
            Boolean existe = false;
            int x = -1; //Posición del objeto en la lista
        
            for (Alimento food : set) {
            	if (food.getNombre().equals(nombre)) {
            		existe = true;
            		x = set.indexOf(food);
            		break;
            	}
            }
            if (existe) {
            	set.set(x, alimento);
            } else {
            	set.add(alimento);
            	mapMenu.put(tipo, set);
            }     	       
			linea = br.readLine(); 
		}	
		br.close();
    }
    
    public void cargarservicios() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivoservicios));
		String linea = br.readLine();
        linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombre = partes[0];
            String ubicacion = partes[1];
			int tarifaporpersona = Integer.parseInt(partes[2]);
            String diasdisp = partes[3];
            String horariosdisp = partes[4];
			Servicios servicio = new Servicios(nombre, ubicacion, tarifaporpersona, diasdisp, horariosdisp);
			mapa_servicios.put(nombre, servicio);
			linea = br.readLine();
		}	
		br.close();
    }
    
    public void cargarCamas() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivocamas));
		String linea = br.readLine();
        linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String tipo = partes[0];
			int capacidad = Integer.parseInt(partes[1]);
			mapaCamas.put(tipo, capacidad);
			linea = br.readLine();
		}	
		br.close();
    }
    
    public void cargarFacturas() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivofacturas));
		String linea = br.readLine();
        linea = br.readLine();
		
		while (linea != null)
		{
			Huesped huesped = null;
			Double total = 0.0;
			ArrayList<Factura> listaFacturas = new ArrayList<>();
			String[] partes = linea.split(":");
			String idReserva = partes[0];
			String[] facturas = leerLista(partes[1]);
			for (String factura : facturas) {
				String[] facturaA = factura.split(";");
				String idFact = facturaA[0];
				LocalDate fecha = convertirFecha(facturaA[1]);
				String nombre = facturaA[2];
				huesped = mapaHuespedes.get(nombre);
				Factura fact = new Factura(huesped, total);
				String[] consumos = leerLista(facturaA[3]);
				for (String consumoC : consumos) {
					String[] consumo = consumoC.split(";");
					String tipoServicio = consumo[0];
					String valor = consumo[1];
					Consumo servicio = new Consumo(tipoServicio, Double.parseDouble(valor));
					fact.setConsumos(servicio);
				}
				total = Double.parseDouble(facturaA[4]);
				fact.setFecha(fecha); 
				fact.setHuesped(huesped);
				fact.setIdFactura(Integer.parseInt(idFact));
				fact.setTotal(total);
				listaFacturas.add(fact);
			}
			mapaFacturas.put(idReserva, listaFacturas);
			linea = br.readLine();
		}	
		br.close();
    }
    
    public static void actualizarArchivoHabitaciones() {
    	try {
            FileWriter escritor = new FileWriter(archivohabitaciones);
            escritor.write("id;ubicacion(piso);tipo;tienebalcon;tienevista;tienecocina;disponible\n");
            for (Habitacion set : mapa_habitaciones.values()) {
            	escritor.write(set.getIdHabitacion() + ";" + set.getUbicacion() + ";" + set.getTipo() + ";" 
                		+ set.getBalcon() + ";"+ set.getVista() + ";" + set.getCocinaintegrada() + ";" + 
                		set.getDisponible() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public static void actualizarArchivoTarifas() {
    	try {
            FileWriter escritor = new FileWriter(archivotarifashabs);
            escritor.write("tipohab;tarifa;fechainicial;fechafinal \n");
            for (TarifaTipoHabitacion set : mapaTarifasHabs.values()) {
            	escritor.write(set.getTipohab() + ";" + set.getTarifa() + ";" + set.getFechainicial() 
            	+ ";" + set.getFechafinal() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public static void actualizarArchivoTarifasServicios() {
    	try {
            FileWriter escritor = new FileWriter(archivoservicios);
            escritor.write("nombre;ubicacion;tarifaporpersona;diasdisp;horariosdisp \n");
            for (Servicios set : mapa_servicios.values()) {
            	escritor.write(set.getNombre() + ";" + set.getUbicacion() + ";" + set.getTarifaporpersona()
            			+ ";" + set.getDiasdisp() + ";" + set.getHorarioDisp() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public static void actualizarArchivoMenu() {
    	try {
            FileWriter escritor = new FileWriter(archivomenu);
            escritor.write("nombre;tipo;precio;comidadisp;lugardisp \n");
            for (ArrayList<Alimento> set : mapMenu.values()) {
            	for (Alimento alimento : set) {
	            	escritor.write(alimento.getNombre() + ";" + alimento.getTipo() + ";" + alimento.getTarifa()
	            			+ ";" + alimento.getComidaDisp() + ";" + alimento.getLugarDisp() + "\n");
            	}
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public static void actualizarArchivoReservas() {
    	try {
            FileWriter escritor = new FileWriter(archivoreservas);
            escritor.write("id Reserva;fechaIn;fechaFin;documento titular;documentosGrupo;niños;habitacionesreservadas;totalEstadía \n");
            escritor.write((Reserva.getUltimoIdReserva() -1) + "\n");
            StringBuilder sb = new StringBuilder();
            
            for (Reserva reserva : mapaReservas.values()) {            	
                sb.append(reserva.getIdReserva()).append(";")
                    .append(reserva.getFechaIn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(";")
                    .append(reserva.getFechaFin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(";")
                    .append(reserva.getTitular().getDocumento()).append(";");

                String documentosGrupo = "";
                for (int i = 0; i < reserva.getGrupo().size(); i++) {
                    documentosGrupo += reserva.getGrupo().get(i).getDocumento();
                    if (i != reserva.getGrupo().size() - 1) {
                        documentosGrupo += "-";
                    }
                }
                sb.append(documentosGrupo).append(";")
                    .append(reserva.getNinos()).append(";");

                // construir la cadena de habitaciones reservadas
                String habsReservadas = "";
                for (int i = 0; i < reserva.getHabsReservadas().size(); i++) {
                    habsReservadas += reserva.getHabsReservadas().get(i).getIdHabitacion();
                    if (i != reserva.getHabsReservadas().size() - 1) {
                        habsReservadas += "-";
                    }
                }
                sb.append(habsReservadas).append(";")
                .append(Math.round(reserva.getTarifaHabs())).append("\n");
            }
            escritor.write(sb.toString());
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public static void actualizarArchivoFacturas() {
    	try {
            FileWriter escritor = new FileWriter(archivofacturas);
            escritor.write("nombre;tipo;precio;comidadisp;lugardisp \n");
            for (ArrayList<Alimento> set : mapMenu.values()) {
            	for (Alimento alimento : set) {
	            	escritor.write(alimento.getNombre() + ";" + alimento.getTipo() + ";" + alimento.getTarifa()
	            			+ ";" + alimento.getComidaDisp() + ";" + alimento.getLugarDisp() + "\n");
            	}
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public static void actualizarArchivoHuespedes() {
    	try {
            FileWriter escritor = new FileWriter(archivohuespedes);
            escritor.write("documento;nombre;telefono;correo;edad;titular/acompañante \n");
            for (Huesped set : mapaHuespedes.values()) {
            	escritor.write(set.getDocumento()+ ";" + set.getNombre() + ";" + set.getTelefono() 
            	+ ";" + set.getCorreo() + ";" + set.getEdad() + ";" + set.getTipo() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public static void actualizarCaracteristicasHotel() {
    	try {
            FileWriter escritor = new FileWriter(archivohotel);
            escritor.write("nombreHotel;parqueaderoPago;Piscina;zonasHumedas;bbq;wifiGratis;recep24horas;mascotas;numeroDeCuenta \n");         
            escritor.write(Hotel.getNombreHotel() + ";" + Hotel.getParqueaderoPago() + ";" + Hotel.getPiscina()
			+ ";" + Hotel.getZonasHumedas() + ";" + Hotel.getBbq() + ";" + Hotel.getWifiGratis() + ";" + Hotel.getRecep24horas() 
			+ ";" + Hotel.getMascotas() + ";" + Hotel.getNumeroCuenta() + "\n");        	       
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public LocalDate convertirFecha(String fecha) {
        int[] fechaSeparada = Arrays.stream(fecha.split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDate fechaConvertida = LocalDate.of(fechaSeparada[0], fechaSeparada[1], fechaSeparada[2]);
        return fechaConvertida;
    }
    
    public String[] leerLista(String lista) {
    	String datos = lista.substring(1, lista.length() - 1);
    	String[] datosSeparados = datos.split(",");
    	return datosSeparados;
    }
     
    public static File getArchivotiposhabitaciones() {
		return archivotiposhabitaciones;
	}

	public static void setArchivotiposhabitaciones(File archivotiposhabitaciones) {
		Hotel.archivotiposhabitaciones = archivotiposhabitaciones;
	}

	public static File getArchivomenu() {
		return archivomenu;
	}

	public static void setArchivomenu(File archivomenu) {
		Hotel.archivomenu = archivomenu;
	}

	public static File getArchivohabitaciones() {
		return archivohabitaciones;
	}

	public static void setArchivohabitaciones(File archivohabitaciones) {
		Hotel.archivohabitaciones = archivohabitaciones;
	}

	public static File getArchivousuarios() {
		return archivousuarios;
	}

	public static void setArchivousuarios(File archivousuarios) {
		Hotel.archivousuarios = archivousuarios;
	}

	public static File getArchivoservicios() {
		return archivoservicios;
	}

	public static void setArchivoservicios(File archivoservicios) {
		Hotel.archivoservicios = archivoservicios;
	}

	public static File getArchivotarifashabs() {
		return archivotarifashabs;
	}

	public static void setArchivotarifashabs(File archivotarifashabs) {
		Hotel.archivotarifashabs = archivotarifashabs;
	}

	public static Map<String, TipoHabitacion> getMapa_tipos_habitaciones() {
		return mapa_tipos_habitaciones;
	}

	public static void setMapa_tipos_habitaciones(Map<String, TipoHabitacion> mapa_tipos_habitaciones) {
		Hotel.mapa_tipos_habitaciones = mapa_tipos_habitaciones;
	}

	public static Map<String, ArrayList<Alimento>> getMapMenu() {
		return mapMenu;
	}

	public static void setMapMenu(Map<String, ArrayList<Alimento>> mapMenu) {
		Hotel.mapMenu = mapMenu;
	}

	public static Map<String, Habitacion> getMapa_habitaciones() {
		return mapa_habitaciones;
	}

	public static void setMapa_habitaciones(Map<String, Habitacion> mapa_habitaciones) {
		Hotel.mapa_habitaciones = mapa_habitaciones;
	}

	public static Map<String, Usuario> getUsers() {
		return users;
	}

	public static void setUsers(Map<String, Usuario> users) {
		Hotel.users = users;
	}
	
	public static Map<String, Servicios> getMapa_servicios() {
		return mapa_servicios;
	}
	public static void setMapa_servicios(Map<String, Servicios> mapa_servicios) {
		Hotel.mapa_servicios = mapa_servicios;
	}
	public static Map<String, TarifaTipoHabitacion> getMapaTarifasHabs() {
		return mapaTarifasHabs;
	}
	public static void setMapaTarifasHabs(Map<String, TarifaTipoHabitacion> mapaTarifasHabs) {
		Hotel.mapaTarifasHabs = mapaTarifasHabs;
	}
	public static Map<String, TarifaTipoHabitacion> getMapTarifaHab() {
		return mapaTarifasHabs;
	}

	public static void setMapTarifaHab(Map<String, TarifaTipoHabitacion> mapaTarifasHabs) {
		Hotel.mapaTarifasHabs = mapaTarifasHabs;
	}
	

	public static Map<String, Huesped> getMapaHuespedes() {
		return mapaHuespedes;
	}
	public static void setMapaHuespedes(Map<String, Huesped> mapaHuespedes) {
		Hotel.mapaHuespedes = mapaHuespedes;
	}
	public static Map<Integer, Reserva> getMapaReservas() {
		return mapaReservas;
	}
	public static void setMapaReservas(Map<Integer, Reserva> mapaReservas) {
		Hotel.mapaReservas = mapaReservas;
	}
	public static Map<String, Usuario> getMapaUsuarios() {
		return users;
	}
	

    public static Map<String, Integer> getMapaCamas() {
		return mapaCamas;
	}
	public static void setMapaCamas(Map<String, Integer> mapaCamas) {
		Hotel.mapaCamas = mapaCamas;
	}
	public static File getarchivousuarios() {
        //File archivo = new File(archivousuarios);
        return archivousuarios;
    }
	public static String getNombreHotel() {
		return nombreHotel;
	}
	public static void setNombreHotel(String nombreHotel) {
		Hotel.nombreHotel = nombreHotel;
	}
	public static Boolean getParqueaderoPago() {
		return parqueaderoPago;
	}
	public static void setParqueaderoPago(Boolean parqueaderoPago) {
		Hotel.parqueaderoPago = parqueaderoPago;
	}
	public static Boolean getPiscina() {
		return piscina;
	}
	public static void setPiscina(Boolean piscina) {
		Hotel.piscina = piscina;
	}
	public static String getZonasHumedas() {
		return zonasHumedas;
	}
	public static void setZonasHumedas(String zonasHumedas) {
		Hotel.zonasHumedas = zonasHumedas;
	}
	public static Boolean getBbq() {
		return bbq;
	}
	public static void setBbq(Boolean bbq) {
		Hotel.bbq = bbq;
	}
	public static Boolean getWifiGratis() {
		return wifiGratis;
	}
	public static void setWifiGratis(Boolean wifiGratis) {
		Hotel.wifiGratis = wifiGratis;
	}
	public static Boolean getRecep24horas() {
		return recep24horas;
	}
	public static void setRecep24horas(Boolean recep24horas) {
		Hotel.recep24horas = recep24horas;
	}
	public static Boolean getMascotas() {
		return mascotas;
	}
	public static void setMascotas(Boolean mascotas) {
		Hotel.mascotas = mascotas;
	}
	public static String getNumeroCuenta() {
		return numeroCuenta;
	}
	public static void setNumeroCuenta(String numeroCuenta) {
		Hotel.numeroCuenta = numeroCuenta;
	}
	public static ArrayList<Alimento> getListaBebidas() {
		return listaBebidas;
	}
	public static void setListaBebidas(ArrayList<Alimento> listaBebidas) {
		Hotel.listaBebidas = listaBebidas;
	}
	public static ArrayList<Alimento> getListaComidas() {
		return listaComidas;
	}
	public static void setListaComidas(ArrayList<Alimento> listaComidas) {
		Hotel.listaComidas = listaComidas;
	}
	public static Map<String, ArrayList<Factura>> getMapaFacturas() {
		return mapaFacturas;
	}
	public static void setMapaFacturas(Map<String, ArrayList<Factura>> mapaFacturas) {
		Hotel.mapaFacturas = mapaFacturas;
	}
	public static Map<String, ArrayList<String>> getAtributos() {
		return atributos;
	}
	public static void setAtributos(Map<String, ArrayList<String>> atributos) {
		Hotel.atributos = atributos;
	}
	
	
}

package Main;

import java.io.*;
import java.util.*;

import Logica.*;
import ContDatos.*;
import ProcesoDePago.CargaPasarela;
import ProcesoDePago.PasarelaPago;
//import ProcesoDePago.;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Aplicacion{
	private static Hotel hotel;
	private static Admin admin = new Admin();
	private static Recepcionista recep = new Recepcionista();
    private static boolean loggedIn = false;
    private static int tipo_user = 0;

    public void mostrarMenu() throws FileNotFoundException, IOException {
        while (!loggedIn) {
        System.out.println("Bienvenido. Seleccione una opción (el número correspondiente):\n");
        System.out.println("1. Iniciar sesión\n");
        System.out.println("2. Registrarse\n");
        System.out.println("3. Salir\n");
        int respuesta = Integer.parseInt(input(""));

        if (respuesta == 1) {
            //loggedIn = Login.iniciarsesion();
            if (loggedIn == true) {
                tipo_user = Login.gettipo_usuario();
                mostrarMenuLoggeado(loggedIn, tipo_user);
            }
        } else if (respuesta == 2) {
            //Login.registrarse();
        } else if (respuesta == 3) {
            System.exit(0);
            break;
        } else {
            System.out.println("Invalid choice.");
            break;
        }

        }
    }

    public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

    public static void mostrarMenuLoggeado(boolean ingreso, int tipoUser) {
        if (ingreso == true && tipoUser != 0) {
            if (tipoUser == 1) {
                while (ingreso) {
                    try {
                        mostrarMenuAdmin();
                        int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción: \n"));
                        if (opcion_seleccionada == 1) {
                        	String rutaArchivo = input("Por favor ingrese la ruta del archivo: \n");
                        	admin.cargar_habitaciones(rutaArchivo);
                        }
                        if (opcion_seleccionada == 2) {
                        	String idHab = input("Ingrese el Id de la habitación: \n");
                        	int piso = Integer.parseInt(input("Ingrese el piso en el que se encuentra la habitación: \n"));
                        	String tipo = input("Ingrese el tipo de habitación: \n");
                        	System.out.println("Para las siguientes opciones ingrese true o false");
                        	Boolean balcon = Boolean.parseBoolean(input("La habitación cuenta con balcon: \n"));
                        	Boolean vista = Boolean.parseBoolean(input("La habitación cuenta con vista: \n"));
                        	Boolean cocina = Boolean.parseBoolean(input("La habitación cuenta con cocina integrada: \n"));
                        	Boolean disponible = true;
                        	admin.crear_habitaciones(idHab, piso, tipo, balcon, vista, cocina, disponible);
                        }
                        if (opcion_seleccionada == 3) {
                        	String ruta = input("Ingrese la ruta del archivo: \n");
                        	admin.cargar_tarifas_habitaciones(ruta);
                        }
                        if (opcion_seleccionada == 4) {
                        	String tipoHab = input("Seleccione el tipo de habitación a modificar: \n"
                        			+ "Estandar \nSuite \nSuite doble \n");
                        	System.out.println(admin.tarifa_actual(tipoHab));
                        	int tarifa = Integer.parseInt(input("Ingrese la tarifa nueva: \n"));
                        	String fechainicial = input("Ingrese la tarifa fecha inicial(YYYY/MM/DD): \n");
                        	String fechafinal = input("Ingrese la tarifa fecha final(YYYY/MM/DD): \\n");
                        	admin.cargar_tarifas(tipoHab, tarifa, fechainicial, fechafinal);
                        }
                        if (opcion_seleccionada == 5) {
                        	ArrayList<String> problemas = admin.verificar_tarifas();
                        	for (int i = 0; i < problemas.size(); i++) {                        
                        		System.out.println(problemas.get(i));
                        	}                    
                        }
                        if (opcion_seleccionada == 6) {
                        	String opcion_servicio = input("Seleccione el servicio para actualizar la tarifa por persona: \n"
                        			+ "Spa \nGuia turistico \n");
                        	int tarifa = Integer.parseInt(input("Ingrese la tarifa por persona: \n"));
                        	admin.modificar_tarifa_servicio(opcion_servicio, tarifa);
                        }
                        if (opcion_seleccionada == 7) {
                        	String rutaArchivo = input("Por favor ingrese la ruta del archivo: \n");
                        	admin.cargar_menu(rutaArchivo);
                        }
                        if (opcion_seleccionada == 8) {
                        	System.out.println("A continuación ingrese la información de la bebida o el plato a modificar: \n");
                        	String nombre = input("Ingrese el nombre: \n");
                        	String tipo = input("Ingrese el tipo(bebida, comida): \n");
                        	int tarifa = Integer.parseInt(input("Ingrese la tarifa: \n"));
                        	String comidaDisp = input("Ingrese las comidas en las que esta disponible(desayuno, almuerzo, cena): \n");
                        	String lugarDisp = input("Ingrese el(los) lugares en los que esta disponible: \n");
                        	admin.modificar_info_menu(nombre, tipo, tarifa, comidaDisp, lugarDisp);
                        }
                        if (opcion_seleccionada == 0) {
                        	Hotel hotel = new Hotel();
                        	hotel.actualizar_archivos();
                        	System.exit(0);
                        }
                        
                    } catch (Exception e) {
                        System.out.println("Debe seleccionar uno de los números de las opciones.");                        
                    }
                }
            }
            else if (tipoUser == 2) {
                while (ingreso) {
                    try {
                        mostrarMenuRecep();
                        int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción: \n"));
                        if (opcion_seleccionada == 1) {
                        	System.out.println("Si no desea un filtro especifico, oprima la tecla enter. \n");
                        	String piso = input("Ingrese el piso de preferencia: ");
                        	String tipo = input("\nIngrese el tipo de preferencia: ");
                        	String balcon = input("\nIngrese true o false para indicar si desea la habitacion con balcon: ");
                        	String vista = input("\nIngrese true o false para indicar si desea la habitacion con vista: ");
                        	String cocina = input("\nIngrese true o false para indicar si desea la habitacion con cocina integrada: ");
                        	String disp = input("\nIngrese true o false para indicar la disponibilidad de la habitación: ");
                        	List<Habitacion> habsFiltradas= recep.info_Habitaciones(piso, tipo, balcon, vista, cocina, disp);
                        	for (Habitacion hab : habsFiltradas) {
                        		System.out.print(hab.toString() + "\n");
                        	}                        
                        }
                        if (opcion_seleccionada == 2) {
                        	String idHab = input("Ingrese el Id de la habitación que deseea encontrar: ");
                        	System.out.println(recep.habitacion_ID(idHab).toString() + "\n");                        	
                        }
                        if (opcion_seleccionada == 3) {
                        	System.out.println("Ingrese los datos del huesped a crear: \n");
                        	String documento = input("Digite el documento de identidad: "); 
                        	String nombre = input("\nDigite nombres y apellidos: "); 
                        	String telefono = input("\nDigite el numero de telefono: ");
                        	String correo = input("\nDigite el correo electronico: ");
                        	int edad = Integer.parseInt(input("\nDigite la edad: "));                       
                        	String tipo = input("\nIngrese titular/acompañante según corresponda al usuario: ");
                        	recep.crearHuespued(documento, nombre, telefono, correo, edad, tipo);
                        }
                        if (opcion_seleccionada == 4) {
                        	String documentos = input("Ingrese los documento de los huespedes separados por guión(-): \n");
                        	String tipo = input("\nIngrese el tipo de habitación (estandar, doble, suite) para asignar las habitaciones: ");                   
                        	String fechaIn = input("\nIngrese la fecha de inicio de la reserva, separada por guión(-) en formato(YYYY-MM-DD): \n");
                        	String fechaFin = input("\nIngrese la fecha final de la reserva, separada por guión(-) en formato(YYYY-MM-DD): \n");
                        	System.out.println(recep.nuevaReserva(documentos, tipo, fechaIn, fechaFin));                        	
                        }
                        if (opcion_seleccionada == 5) {
                        	String documentoTitular = input("Ingrese el documento del titular, para consultar la información de la reserva: ");
                        	System.out.println(recep.infoReserva(documentoTitular).toString());
                        }
                        if (opcion_seleccionada == 6) {
                        	String documentoTitular = input("Ingrese el documento del titular, para cancelar la reserva: ");
                        	System.out.println(recep.cancelarReserva(documentoTitular));
                        }
                        if (opcion_seleccionada == 7) {
                        	String documentoTitular = input("Ingrese el documento del titular de la reserva: ");
                        	List<PasarelaPago> pasarelas = null;
                        	try {
                        		pasarelas = CargaPasarela.obtenerPasarelas();
                        		System.out.println("Pasarelas de pago diponibles: ");
                                for (int i = 0; i < pasarelas.size(); i++) {
                                    System.out.println((i + 1) + ". " + pasarelas.get(i).getClass().getSimpleName());
                                }
                                if (pasarelas.get(1) == null) {
                                	throw new Exception("No se logro conectar las pasarelas diponibles");
                                }
                        	} catch (Exception e) {
                        		System.out.println(e.getMessage());
                        	}
                        	                     
                            String opcion = input("Seleccione una de las opciones: ");
                            PasarelaPago pasarela = pasarelas.get(Integer.parseInt(opcion) - 1);                            
                            String[] infoTarjeta = new String[4];
                            String numeroTarjeta = input("Digite el número de tarjeta: ");
                            String nombrePropietario = input("Ingrese el nombre del propietario de la tarjeta: ");
                            String fechaExp = input("Ingrese la fecha de expiracion en formato(MM-YY): ");
                			String cvc = input("Digite el codigo de seguridad de la tarjeta: ");
                			infoTarjeta[0] = numeroTarjeta;
                			infoTarjeta[1] = nombrePropietario;
                			infoTarjeta[2] = fechaExp;
                			infoTarjeta[3] = cvc;
                			System.out.println(recep.pagoYfactura(documentoTitular, pasarela, infoTarjeta));
                        }
                        if (opcion_seleccionada == 8) {
                        	//liberar habitaciones, verificando si no deben nada
                        }
                        if (opcion_seleccionada == 0) {
                        	Hotel hotel = new Hotel();
                        	hotel.actualizar_archivos();
                        	System.exit(0);
                        }
                        
                    } catch (Exception e) {
                        System.out.println("Debe seleccionar uno de los números de las opciones.");
                    }
                }
            }
            else {
                while (ingreso) {
                    try {
                        mostrarMenuOtroEmp();
                    } catch (Exception e) {
                        System.out.println("Debe seleccionar uno de los números de las opciones.");
                    }
                }
            }
        } else {
            
        }
    }

    public static void mostrarMenuAdmin() {
        System.out.println("1. Cargar archivo para crear habitación(es). \n");
        System.out.println("2. Crear habitación manualmente. \n");
        System.out.println("3. Cargar archivo para actualizar las tarifas de las habitaciones. \n");
        System.out.println("4. Actualizar la tarifa de una habitación manualmente. \n");
        System.out.println("5. Verificar ausencia de tarifas para las habitaciones. \n");
        System.out.println("6. Modificar las tarifas de los servicios ofrecidos. \n");
        System.out.println("7. Cargar archivo para actualizar el menú del restaurante. \n");
        System.out.println("8. Configurar información del plato/bebida.\n");
        System.out.println("9. Modificar las carcteristicas del Hotel: ");//FALTA
        System.out.println("0. Salir\n");
    }

    public static void mostrarMenuRecep() {
        System.out.println("1. Consultar el inventario y las caracteristicas de las habitaciones. \n");
        System.out.println("2. Consultar Habitación por ID.\n");
        System.out.println("3. Crear huesped. \n");
        System.out.println("4. Realizar reserva e informar tarifa. \n");
        System.out.println("5. Consultar información de una reserva. \n");
        System.out.println("6. Cancelar reserva. \n");
        System.out.println("7. Registrar el pago y generar factura, para los consumos realizados durante la estadía. \n");
        System.out.println("8. Realizar check-out. "); //FALTA
        System.out.println("0. Salir\n");
    }

    public static void mostrarMenuOtroEmp() {
        System.out.println("1. Registrar y generar factura de los servicios consumidos por un huesped. \n");
        System.out.println("2. Registrar el pago de un servicio. \n");
        // debe generarse una factura si el consumo se pagó inmediatamente. (if dentro de la 1era opcion)
        System.out.println("3. Obtener informacion del menu. \n");
        System.out.println("0. Salir\n");
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Hotel hotel = new Hotel();
        hotel.cargarDatos();
        
        Aplicacion consola = new Aplicacion();
        consola.mostrarMenu();
        
    }
    
}


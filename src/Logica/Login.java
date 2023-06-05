package Logica;

import java.io.*;
import java.util.*;

import ContDatos.*;


public class Login {
    
    private static boolean loggedIn = false;
    private static int tipo_usuario = 0;
    

    public static boolean registrarse(String username, String password, int userType) {
    	boolean exito = false;
    	FileWriter escritor;
        
        try {
        //String username = input("Ingrese el nombre de usuario a registrar: \n");
        //String password = input("Ingrese la clave a registrar: \n");
        //int userType = Integer.parseInt(input("Ingrese el tipo de usuario a registrar (1. admin, 2. recepcionista, 3. otro): \n"));
        
        if (userType==1 || userType==2 || userType==3) {
            Usuario nuevoUsuario = new Usuario(username, password, userType);
            Map<String, Usuario> mapa_usuarios = Hotel.getMapaUsuarios();
            mapa_usuarios.put(username, nuevoUsuario);
            File archivo_usuarios = Hotel.getarchivousuarios();
            escritor = new FileWriter(archivo_usuarios, true);
            escritor.write(registrarsignin(username, password, userType));
            escritor.close();
            exito = true;
        }
        else {
            System.out.println("ERROR: Recuerde ingresar valores validos. Intente nuevamente");
        }

        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
			e.printStackTrace();
        }
        
        return exito;

    }
    public static String registrarsignin(String username, String password, int userType) {
        String registro = "";
        registro += username + ";";
        registro += password + ";";
        registro += userType + "\n";
        return registro;
    }

    public static boolean iniciarsesion(String username, String password) throws FileNotFoundException, IOException {
        

        File archivo_usuarios = Hotel.getarchivousuarios();
        BufferedReader lector = new BufferedReader(new FileReader(archivo_usuarios));
        String linea = lector.readLine();

        linea = lector.readLine();
        while (linea != null) {
            String[] partes = linea.split(";");
            String nombre = partes[0];
            String clave = partes[1];
            int tipoUser = Integer.parseInt(partes[2]);

            if (username.equals(nombre) && password.equals(clave)) {
                loggedIn = true;
                tipo_usuario = tipoUser;
                lector.close();
                break;
            }
            linea = lector.readLine();
        }
        if (loggedIn == false) {
            System.out.println("Usuario o clave incorrecta, intente de nuevo");
        }
        lector.close();
        return loggedIn;
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

    public static int gettipo_usuario() {
        return tipo_usuario;
    }

    
}



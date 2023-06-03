package ContDatos;

import java.util.*;
import java.io.*;

public class Restaurante {
    public String ubicacion;
    public Map<String, String> horario;
    public String archivoMenu;

    public Restaurante(String ubicacion, Map<String, String> horario, String archivoMenu) {
		this.ubicacion = ubicacion;
		this.horario = horario;
		this.archivoMenu = archivoMenu;
	}

    public static List<String> menu(String archivoMenu) throws IOException {
        List<String> lineas = new ArrayList<>();
        FileReader fr = new FileReader(archivoMenu);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            lineas.add(linea);
        }
        br.close();
        fr.close();
        return lineas;
    }


    
}

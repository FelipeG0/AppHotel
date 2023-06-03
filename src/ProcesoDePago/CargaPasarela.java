package ProcesoDePago;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CargaPasarela {
	public static List<PasarelaPago> obtenerPasarelas() throws Exception {
        List<PasarelaPago> pasarelas = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader("data/Pasarelas.txt"));
        String linea = br.readLine();
        linea = br.readLine();
        
        while (linea != null) {
            Class<?> clasePasarela = Class.forName(linea);
            pasarelas.add((PasarelaPago) clasePasarela.getDeclaredConstructor().newInstance());
            linea = br.readLine();
        }
        br.close();
        return pasarelas;
    }
}




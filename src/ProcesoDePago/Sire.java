package ProcesoDePago;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Sire implements PasarelaPago{

	@Override
	public boolean procesoPago(TarjetaBancaria infoTarjeta, Transaccion transaccion) {
		String numeroTarjeta = infoTarjeta.getNumeroTarjeta();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
		LocalDate fechaExp = LocalDate.parse("01-" + infoTarjeta.getFechaExpiracion(), formato);
		String cvc = Integer.toString(infoTarjeta.getCodigoSeguridad());
		
		Boolean exito = false;
		if (numeroTarjeta.length() >= 15 && LocalDate.now().isBefore(fechaExp) && (cvc.length() == 3 || cvc.length() ==4)) {
			int numInicial = Character.getNumericValue(numeroTarjeta.charAt(0));
			if (numInicial == 4) {
				//Tarjeta visa
				infoTarjeta.setTipo("Visa");
				exito = true;
			} else if (numInicial == 5){
				//Tarjeta Master Card
				infoTarjeta.setTipo("Master card");
				exito = true;
			} else if (numInicial == 34 || numInicial == 37) {
				//Tarjeta American Express
				infoTarjeta.setTipo("American express");
				exito = true;
			}
		}
		int tamCuenta = transaccion.getNumeroCuenta().length();
		double valor = transaccion.getValorTransaccion();
		if (tamCuenta < 10 || valor < 0) {
			exito = false;
		}
		Random random = new Random();
	    int numeroAleatorio = random.nextInt(50000) + 5000;
	    transaccion.setNumeroTransaccion(numeroAleatorio);
	    
	    try {
	    	if (exito) {
	    		FileWriter writer = new FileWriter("data/Sire.log", true);
	            writer.write("Proceso de pago exitoso. Información de la tarjeta: " + infoTarjeta.toString()
	                + " Transaction info: " + transaccion.toString() + "\n");
	            writer.close();
	    	}   
        } catch (IOException e) {
            // Error en escritura
            return false;
        }
		return exito;
	}
}

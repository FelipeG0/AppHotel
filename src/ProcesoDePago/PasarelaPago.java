package ProcesoDePago;

public interface PasarelaPago {
    boolean procesoPago(TarjetaBancaria infoTarjeta, Transaccion transaccion);
}
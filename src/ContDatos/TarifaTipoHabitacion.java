package ContDatos;

import java.time.*;

public class TarifaTipoHabitacion {
    private String tipohab;
    private int tarifa;
    private LocalDate fechainicial;
    private LocalDate fechafinal;
    
    public TarifaTipoHabitacion(String tipohab, int tarifa, LocalDate fechainicial, LocalDate fechafinal){
        this.tipohab = tipohab;
        this.tarifa = tarifa;
        this.fechainicial = fechainicial;
        this.fechafinal = fechafinal;

    }

    public String getTipohab() {
        return tipohab;
    }

    public void setTipohab(String tipohab) {
        this.tipohab = tipohab;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public LocalDate getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(LocalDate fechainicial) {
        this.fechainicial = fechainicial;
    }

    public LocalDate getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(LocalDate fechafinal) {
        this.fechafinal = fechafinal;
    }

    

    
}

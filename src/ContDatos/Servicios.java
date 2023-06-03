package ContDatos;

public class Servicios {
    private String nombre;
    private String ubicacion;
    private int tarifaporpersona;
    private String diasdisp;
    private String horarioDisp;

    public Servicios(String nombre, String ubicacion, int tarifaporpersona, String diasdisp, String horarioDisp) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tarifaporpersona = tarifaporpersona;
        this.diasdisp = diasdisp;
        this.horarioDisp = horarioDisp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getTarifaporpersona() {
        return tarifaporpersona;
    }

    public void setTarifaporpersona(int tarifaporpersona) {
        this.tarifaporpersona = tarifaporpersona;
    }

    public String getDiasdisp() {
        return diasdisp;
    }

    public void setDiasdisp(String diasdisp) {
        this.diasdisp = diasdisp;
    }

    public String getHorarioDisp() {
        return horarioDisp;
    }

    public void setHorarioDisp(String horarioDisp) {
        this.horarioDisp = horarioDisp;
    }
}

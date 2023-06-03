package ContDatos;

public class Alimento {
    private String nombre;
    private String tipo;
    private int tarifa;
    private String comidaDisp;
    private String lugarDisp;
    
    public Alimento(String nombre, String tipo, int tarifa, String comidaDisp, String lugarDisp) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.tarifa = tarifa;
        this.comidaDisp = comidaDisp;
        this.lugarDisp = lugarDisp;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getTarifa() {
		return tarifa;
	}

	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}

	public String getComidaDisp() {
		return comidaDisp;
	}

	public void setComidaDisp(String comidaDisp) {
		this.comidaDisp = comidaDisp;
	}

	public String getLugarDisp() {
		return lugarDisp;
	}

	public void setLugarDisp(String lugarDisp) {
		this.lugarDisp = lugarDisp;
	}
    
    
    
}

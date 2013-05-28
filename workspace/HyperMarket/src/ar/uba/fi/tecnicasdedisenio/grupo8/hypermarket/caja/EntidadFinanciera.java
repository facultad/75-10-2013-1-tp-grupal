package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class EntidadFinanciera implements IEntidadFinanciera {
	
	private long id;
	private String nombreEntidad;
	
	public EntidadFinanciera(long id, String nombreEntidad) {
		this.id = id;
		this.nombreEntidad = nombreEntidad;
	}
	
	@Override
	public String getNombreEntidad() {
		return this.nombreEntidad;
	}

	@Override
	public long getId() {
		return this.id;
	}
}

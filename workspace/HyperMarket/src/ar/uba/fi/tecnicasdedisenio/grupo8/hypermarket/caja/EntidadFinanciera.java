package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class EntidadFinanciera implements IEntidadFinanciera {
	
	private long id=IdGenerator.getInstance().getNewId();
	private String nombreEntidad;
	
	public EntidadFinanciera(String nombreEntidad) {
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

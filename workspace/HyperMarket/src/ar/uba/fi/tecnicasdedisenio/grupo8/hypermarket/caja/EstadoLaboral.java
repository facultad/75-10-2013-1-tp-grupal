package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class EstadoLaboral implements IEstadoLaboral {

	private String descripcion;
	private long id;
	
	public EstadoLaboral(String descripcion) {
		this.descripcion = descripcion;
		this.id = IdGenerator.getInstance().getNewId();
	}

	@Override
	public long getId() {
		return this.id;
	}

	
}

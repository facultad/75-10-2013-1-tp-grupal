package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class MedioPago implements IMedioPago{
	private long id = IdGenerator.getInstance().getNewId();
	private String descripcion;
	
	public MedioPago (String desc){
		this.descripcion=desc;
	}
	
	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public String getDescripcion() {
		return this.descripcion;
	}

}

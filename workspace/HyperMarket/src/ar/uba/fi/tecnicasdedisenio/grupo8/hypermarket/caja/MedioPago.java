package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class MedioPago implements IMedioPago{

	public static IMedioPago NoDefinido = new MedioPago("No definido");
	private long id = IdGenerator.getInstance().getNewId();
	private String descripcion;
	
	IEntidadFinanciera entidadFinanciera;
	
	public MedioPago (String desc){
		this.descripcion=desc;
	}
	
	@Override
	public long getId() {
		return this.id;
	}
	
	public void setEntidadFinanciera(IEntidadFinanciera entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}
	
	public IEntidadFinanciera getEntidadFinanciera() {
		return entidadFinanciera;
	}

	@Override
	public boolean hasEntidadFinanciera() {
		return (entidadFinanciera != null);
	}

	@Override
	public String getDescripcion() {
		return this.descripcion;
	}

}

package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class MedioPago implements IMedioPago{
	private long id;
	
	IEntidadFinanciera entidadFinanciera;
	
	public MedioPago (long id){
		this.id=id;
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

}

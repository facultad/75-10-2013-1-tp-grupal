package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class MedioPago implements IMedioPago{
	private long id;
	
	public MedioPago (long id){
		this.id=id;
	}
	
	@Override
	public long getId() {
		return this.id;
	}

}

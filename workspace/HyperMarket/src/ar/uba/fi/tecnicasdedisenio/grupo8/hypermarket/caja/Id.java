package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Id {
	long id;
	
	public Id(){
		this.id = IdGenerator.getInstance().getNewId();
	}
	
}

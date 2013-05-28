package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public interface IMedioPago extends Identificable {

	public IEntidadFinanciera getEntidadFinanciera();

	public boolean hasEntidadFinanciera();
	
	String getDescripcion();
}

package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.EntidadFinanciera;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEntidadFinanciera;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;

public class MedioPagoMock implements IMedioPago{

	private IEntidadFinanciera entidadFinanciera;

	long id;
	public MedioPagoMock(long id) {
		this.id = id;
	}
	
	public MedioPagoMock(IEntidadFinanciera entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public IEntidadFinanciera getEntidadFinanciera() {
		return this.entidadFinanciera;
	}

	@Override
	public boolean hasEntidadFinanciera() {
		return this.entidadFinanciera != null;
	}

	@Override
	public String getDescripcion() {
		return null;
	}

	@Override
	public void setEntidadFinanciera(IEntidadFinanciera entidadFinanciera) {
		throw new UnsupportedOperationException();
	}

}

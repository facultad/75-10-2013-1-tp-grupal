package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.EntidadFinanciera;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEntidadFinanciera;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;

public class MedioPagoMock implements IMedioPago{

	private IEntidadFinanciera entidadFinanciera;

	public MedioPagoMock(IEntidadFinanciera entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IEntidadFinanciera getEntidadFinanciera() {
		return this.entidadFinanciera;
	}

	@Override
	public boolean hasEntidadFinanciera() {
		return this.entidadFinanciera != null;
	}

}

package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMarca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IRubro;

public class ProductoMock implements IProducto {

	private int id;
	private double importe;
	private IRubro rubro;
	private IMarca marca;

	public ProductoMock(int id, double importe) {
		this.setId(id);
		this.setImporte(importe);
	}


	public ProductoMock(int id, double importe,IRubro rubro) {
		this.setId(id);
		this.setImporte(importe);
		this.rubro=rubro;
	}

	public ProductoMock(int id, double importe, IMarca marca) {
		this.setId(id);
		this.setImporte(importe);
		this.marca=marca;
	}


	private void setImporte(double importe) {
		this.importe=importe;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public double getPrecioUnitario() {
		return this.importe;
	}


	@Override
	public IRubro getRubro() {
		return this.rubro;
	}


	@Override
	public IMarca getMarca() {
		return this.marca;
	}

}

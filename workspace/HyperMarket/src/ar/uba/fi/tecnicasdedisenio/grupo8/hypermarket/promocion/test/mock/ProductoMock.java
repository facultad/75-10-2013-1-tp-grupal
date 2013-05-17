package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;

public class ProductoMock implements IProducto {

	private int id;
	private double importe;

	public ProductoMock(int id) {
		// TODO Auto-generated constructor stub
		this.setId(id);
		this.setImporte(10);
	}

	public ProductoMock(int id, double importe) {
		// TODO Auto-generated constructor stub
		this.setId(id);
		this.setImporte(importe);
	}

	private void setImporte(double importe) {
		// TODO Auto-generated method stub
		this.importe=importe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public double getPrecioUnitario() {
		// TODO Auto-generated method stub
		return this.importe;
	}

}

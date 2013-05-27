package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ProductoSinMarcaAsignada;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ProductoSinRubroAsignado;

public class Producto implements IProducto{

	private int id;
	private double importe;
	private IRubro rubro;
	private IMarca marca;

	public Producto(int id) {
		this.setId(id);
	}

	public Producto(int id, double importe) {
		this.setId(id);
		this.setImporte(importe);
	}

	public Producto(int id, double importe,IRubro rubro) {
		this.setId(id);
		this.setImporte(importe);
		this.rubro=rubro;
	}

	public Producto(int id, double importe, IMarca marca) {
		this.setId(id);
		this.setImporte(importe);
		this.marca=marca;
	}

	public Producto(int id, double importe, IMarca marca, IRubro rubro) {
		this.setId(id);
		this.setImporte(importe);
		this.marca=marca;
		this.rubro=rubro;
	}	
	
	private void setImporte(double importe) {
		this.importe=importe;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	@Override
	public double getPrecioUnitario() {
		return this.importe;
	}

	@Override
	public IRubro getRubro() throws ProductoSinRubroAsignado{
		if (this.rubro==null) 
			throw new ProductoSinRubroAsignado();
		else 
			return this.rubro;
	}

	@Override
	public IMarca getMarca() throws ProductoSinMarcaAsignada{
		if (this.marca==null) 
			throw new ProductoSinMarcaAsignada();
		else 
			return this.marca;
	}
}
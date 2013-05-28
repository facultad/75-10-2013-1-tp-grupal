package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ProductoSinMarcaAsignada;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ProductoSinRubroAsignado;

public class Producto implements IProducto{

	private double importe;
	private IRubro rubro=Rubro.getRubroNoDefinido();
	private IMarca marca=Marca.getMarcaNoDefinida();
	private long id = IdGenerator.getInstance().getNewId();

	public Producto(double importe) {
		this.setImporte(importe);
	}

	public Producto(double importe,IRubro rubro) {
		this.setImporte(importe);
		this.rubro=rubro;
	}

	public Producto(double importe, IMarca marca) {
		this.setImporte(importe);
		this.marca=marca;
	}

	public Producto(double importe, IMarca marca, IRubro rubro) {
		this.setImporte(importe);
		this.marca=marca;
		this.rubro=rubro;
	}	
	
	private void setImporte(double importe) {
		this.importe=importe;
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
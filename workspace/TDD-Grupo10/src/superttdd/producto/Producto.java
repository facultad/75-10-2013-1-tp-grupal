package superttdd.producto;

public class Producto implements IProducto {

	private RegistroProducto registroProducto;

	private Double porcentajeDescuento;

	private Double precioActual;

	private Producto(Producto unProducto) {
		this.registroProducto = unProducto.registroProducto;
		this.porcentajeDescuento = unProducto.porcentajeDescuento;
		this.precioActual = unProducto.precioActual;
	}

	public Producto(RegistroProducto registroProducto) {
		this.registroProducto = registroProducto;
		this.porcentajeDescuento = 0.0;
		this.precioActual = registroProducto.getPrecio();
	}

	public IProducto clonar() {
		return new Producto(this);
	}

	public CategoriaProducto getCategoriaProducto() {
		return registroProducto.getCategoria();
	}

	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public MarcaProducto getMarca() {
		return registroProducto.getMarca();
	}

	@Override
	public void addPorcentajeDescuento(Double porcentajeDescuento) {
		Double descuento = this.precioActual * (porcentajeDescuento) / 100.0;
		if (descuento <= precioActual) {
			this.precioActual -= descuento;
		}
		// this.porcentajeDescuento += porcentajeDescuento ;
		// if(porcentajeMaximoSuperado()) {
		// this.porcentajeDescuento = PORCENTAJE_MAX;
		// }
	}

	private Boolean porcentajeMaximoSuperado() {
		if (this.porcentajeDescuento >= PORCENTAJE_MAX) {
			return true;
		}
		return false;
	}

	@Override
	public String getNombre() {
		return registroProducto.getNombre();
	}

	@Override
	public Double getPrecioBase() {
		return registroProducto.getPrecio();
	}

	@Override
	public Double getPrecioFinal() {
		// Double descuento = registroProducto.getPrecio() * porcentajeDescuento
		// / 100;
		//
		// return (registroProducto.getPrecio() - descuento);
		return precioActual;
	}

	public RegistroProducto getRegistroProducto() {
		return registroProducto;
	}

	@Override
	public boolean validarCategoria(CategoriaProducto categoria) {
		if (this.registroProducto.getCategoria() != null) {
			return this.registroProducto.getCategoria().sonIguales(categoria);
		}
		return false;
	}

	@Override
	public boolean validarMarca(MarcaProducto marca) {
		if (this.registroProducto.getMarca() != null) {
			return this.registroProducto.getMarca().sonIguales(marca);
		}
		return false;
	}

	@Override
	public boolean validarRegistroProducto(RegistroProducto registro) {
		return this.registroProducto.equals(registro);
	}
	
	@Override
	public boolean equals(Object o) {
		Producto producto = (Producto)o;
		return (producto.porcentajeDescuento==this.porcentajeDescuento) && 
				(producto.registroProducto.equals(this.registroProducto));
	}

}

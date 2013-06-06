package superttdd.producto;

public class ProductoCuantificable implements IProducto {
	
	RegistroProducto registro;
	private Double porcentajeDescuento;
	Double cantidad;
	
	private ProductoCuantificable(ProductoCuantificable unProducto) {
		this.registro = unProducto.registro;
		this.cantidad = unProducto.cantidad;
	}
	
	public ProductoCuantificable(RegistroProducto registro, Double cantidad) {
		this.registro = registro;
		this.porcentajeDescuento = 0.0;
		this.cantidad = cantidad;
	}
	
	public IProducto clonar() {
		return new ProductoCuantificable(this);
	}
	
	@Override
	public boolean validarCategoria(CategoriaProducto categoria) {
		return this.registro.getCategoria().sonIguales(categoria);
	}

	@Override
	public boolean validarMarca(MarcaProducto marca) {
		return this.registro.getMarca().sonIguales(marca);
	}

	@Override
	public boolean validarRegistroProducto(RegistroProducto registro) {
		return this.registro.equals(registro);
	}

	@Override
	public Double getPrecioFinal() {
		Double descuento = getPrecioBase() * porcentajeDescuento / 100.0;
		Double precioDescontado = (getPrecioBase() - descuento >= 0)? getPrecioBase() - descuento : 0;
		return (precioDescontado * cantidad);
	}

	@Override
	public Double getPrecioBase() {
		return registro.getPrecio();
	}

	@Override
	public void addPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento += porcentajeDescuento ;
		if(porcentajeMaximoSuperado()) {
			this.porcentajeDescuento = PORCENTAJE_MAX;
		}
	}

	@Override
	public String getNombre() {
		return registro.getNombre();
	}
	
	private Boolean porcentajeMaximoSuperado() {
		if(this.porcentajeDescuento >= PORCENTAJE_MAX) {
			return true;
		}
		return false;
	}

	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

}

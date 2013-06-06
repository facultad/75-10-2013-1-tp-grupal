package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.IProducto;
import superttdd.producto.ProductoCombo;
import superttdd.producto.RegistroProducto;

public class OfertaConjuntoProds extends Oferta {
	List<RegistroProducto> registro_productos;

	public OfertaConjuntoProds(List<RegistroProducto> registro_productos,
			Double porcentajeDescuento) {
		super(porcentajeDescuento);
		if (registro_productos != null) {
			this.registro_productos = registro_productos;
		} else {
			this.registro_productos = new ArrayList<RegistroProducto>();
		}
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		Boolean hubo_coincidencias = true;
		List<IProducto> productos_encontrados;
		List<RegistroProducto> copia_registros;

		// productos que coinciden con un registro de la oferta
		productos_encontrados = new ArrayList<IProducto>();
		copia_registros = new ArrayList<RegistroProducto>(registro_productos);
		if (registro_productos.isEmpty()) {
			return;
		}

		// SMELL #2 es un codigo autodocumentado - innecesario el comment
		while (hubo_coincidencias) {
			/*
			 * Por cada registro de la oferta, busca en todos los productos si
			 * alguno coincide. Si encuentra uno, lo agrega a los productos
			 * encontrados y saca ese registro de la lista copia de registros
			 */
			for (RegistroProducto registro : registro_productos) {
				for (IProducto producto : productos) {
					if (producto.validarRegistroProducto(registro)) {
						if (copia_registros.contains(registro)) {
							productos_encontrados.add(producto);
							copia_registros.remove(registro);
						}
					}
				}
			}

			// Termina si no encuentra combos
			if (!copia_registros.isEmpty()) {
				hubo_coincidencias = false;
			}
			generarCombos(productos, productos_encontrados, copia_registros);

			productos_encontrados.clear();
			copia_registros.clear();
			// copia de los registros de la oferta
			copia_registros.addAll(registro_productos);
		}
	}

	// SMELL #3 los nombres de los métodos no dejan en claro que es lo que sucede
	private void generarCombos(List<IProducto> productos,
			List<IProducto> productos_encontrados,
			List<RegistroProducto> copia_registros) {
		/*
		 * Si se encontraron todos los registros de la oferta (lista copia
		 * vacia), se aplica el descuento a los productos que coinciden. Se
		 * quitan de la lista de productos y se arma el producto combo que
		 * almacena ambos pero comparte la misma interfaz.
		 */
		if (copia_registros.isEmpty()) {
			for (IProducto producto : productos_encontrados) {
				productos.remove(producto);
			}
			ProductoCombo combo = new ProductoCombo(productos_encontrados);
			combo.addPorcentajeDescuento(porcentajeDescuento);
			productos.add(combo);
		}
	}

	// TODO: corregir, deberia buscar hasta que no queden combos
	@Override
	public List<IProducto> filtrarProductos(List<IProducto> productos) {

		Boolean hubo_coincidencias = true;
		List<IProducto> prodsAplican = new ArrayList<IProducto>();

		// productos que coinciden con un registro de la oferta
		List<IProducto> productos_encontrados = new ArrayList<IProducto>();

		// copia de los registros de la oferta
		List<RegistroProducto> copia_registros = new ArrayList<RegistroProducto>(
				registro_productos);

		while (hubo_coincidencias) {

			/*
			 * Por cada registro de la oferta, busca en todos los productos si
			 * alguno coincide. Si encuentra uno, lo agrega a los productos
			 * encontrados y saca ese registro de la lista copia de registros
			 */
			for (RegistroProducto registro : registro_productos) {
				for (IProducto producto : productos) {
					if (producto.validarRegistroProducto(registro)) {
						if (copia_registros.contains(registro)) {
							productos_encontrados.add(producto);
							copia_registros.remove(registro);
						}
					}
				}
			}

			// Termina si no encuentra combos
			if (!copia_registros.isEmpty()) {
				hubo_coincidencias = false;
			} else {
				for (IProducto producto : productos_encontrados) {
					productos.remove(producto);
				}
				ProductoCombo combo = new ProductoCombo(productos_encontrados);
				productos.add(combo);
				prodsAplican.add(combo);
			}
			productos_encontrados.clear();
			copia_registros.clear();
			copia_registros.addAll(registro_productos);
		}

		return prodsAplican;
	}
}

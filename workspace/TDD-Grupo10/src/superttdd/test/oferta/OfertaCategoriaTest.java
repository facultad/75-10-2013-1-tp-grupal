package superttdd.test.oferta;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.OfertaCategoria;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaCategoriaTest {

	CategoriaProducto categoria;
	CategoriaProducto otraCategoria;
	OfertaCategoria oferta;
	MarcaProducto marca;
	List<IProducto> productos;
	Double porcentajeDescuento;
	
	@Before
	public void setUp() {
		categoria = new CategoriaProducto("Farmacia");
		otraCategoria = new CategoriaProducto("Almacén");
		marca = new MarcaProducto("Marca");
		porcentajeDescuento = 15.0;
		oferta = new OfertaCategoria(categoria, porcentajeDescuento);
		productos = new ArrayList<IProducto>();
	}

	@Test
	public void  aplicarDescuentoAProducto() {
		RegistroProducto registro=new RegistroProducto(categoria, marca, "Producto", 150.0);
		Producto mockProd = spy(new Producto(registro));
		productos.add(mockProd);

		oferta.aplicarOferta(productos);
		
		verify(mockProd, times(1)).addPorcentajeDescuento(porcentajeDescuento);
	}

	@Test
	public void noAplicarDescuentoAProducto() {
		RegistroProducto registro=new RegistroProducto(otraCategoria, marca, "Producto", 150.0);
		Producto mockProd = spy(new Producto(registro));
		productos.add(mockProd);

		oferta.aplicarOferta(productos);
		
		verify(mockProd, times(0)).addPorcentajeDescuento(anyDouble());
	}


}


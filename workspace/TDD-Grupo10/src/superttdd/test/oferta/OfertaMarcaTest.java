package superttdd.test.oferta;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaMarcaTest {

	OfertaMarca oferta;
	List<IProducto> productos;
	MarcaProducto marcaOferta;
	MarcaProducto marcaNoEnOferta;
	CategoriaProducto categoria;
	Double porcentajeDescuento;

	@Before
	public void setUp() {
		marcaOferta = new MarcaProducto("Coca Cola");
		marcaNoEnOferta = new MarcaProducto("Pepsi");
		categoria = new CategoriaProducto("Categoria");
		porcentajeDescuento = 10.0;
		oferta = new OfertaMarca(marcaOferta, porcentajeDescuento);
		productos = new ArrayList<IProducto>();
	}
	
	@Test
	public void aplicarDescuentoAProducto() {
		RegistroProducto registro=new RegistroProducto(categoria, marcaOferta, "Producto", 9.0);
		Producto mockProd = spy(new Producto(registro));
		productos.add(mockProd);

		oferta.aplicarOferta(productos);
		
		verify(mockProd, times(1)).addPorcentajeDescuento(porcentajeDescuento);
	}

	@Test
	public void noAplicarDescuentoAProducto() {
		RegistroProducto registro=new RegistroProducto(categoria, marcaNoEnOferta, "Producto", 9.0);
		Producto mockProd = spy(new Producto(registro));
		productos.add(mockProd);
		oferta.aplicarOferta(productos);
		verify(mockProd, times(0)).addPorcentajeDescuento(anyDouble());
	}

}

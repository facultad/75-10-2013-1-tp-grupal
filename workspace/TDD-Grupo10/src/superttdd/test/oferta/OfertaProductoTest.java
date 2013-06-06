package superttdd.test.oferta;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.OfertaProducto;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaProductoTest {
	List<IProducto> productos;
	MarcaProducto marca_producto, marca_oferta;
	CategoriaProducto categoria_producto, categoria_oferta;
	RegistroProducto registro_producto, registro_oferta;
	OfertaProducto ofertaProducto;
	Double porcentajeDescuento=10.0;

	@Before
	public void setUp() throws Exception {		
		marca_oferta = new MarcaProducto("Pepsi");
		categoria_oferta = new CategoriaProducto("Gaseosas");
		registro_oferta = new RegistroProducto(categoria_oferta, marca_oferta, "Producto 2", 20.0);
		ofertaProducto=new OfertaProducto(registro_oferta, porcentajeDescuento);
		productos=new ArrayList<IProducto>();
	}

	@Test
	public void NoAplicaSiNoEsMismoRegistro() {
		marca_producto = new MarcaProducto("Coca Cola");
		categoria_producto = new CategoriaProducto("Gaseosas");
		registro_producto=new RegistroProducto(categoria_producto, marca_producto, "Producto 1", 9.0);	
		Producto mockProd = spy(new Producto(registro_producto));
		productos.add(mockProd);
		
		ofertaProducto.aplicarOferta(productos);
		
		verify(mockProd, times(0)).addPorcentajeDescuento(anyDouble());		
	}
	
	@Test
	public void AplicaSiEsMismoRegistro() {
		Producto mockProd = spy(new Producto(registro_oferta));
		productos.add(mockProd);
		
		ofertaProducto.aplicarOferta(productos);
		
		verify(mockProd, times(1)).addPorcentajeDescuento(anyDouble());		
	}
	
	@Test
	public void aplicaSoloAlProductoDelMismoRegistro() {
		marca_producto = new MarcaProducto("Coca Cola");
		categoria_producto = new CategoriaProducto("Gaseosas");
		registro_producto=new RegistroProducto(categoria_producto, marca_producto, "Producto 1", 9.0);	
		Producto mockProd1 = spy(new Producto(registro_producto));
		productos.add(mockProd1);
		
		Producto mockProd2 = spy(new Producto(registro_oferta));
		productos.add(mockProd2);
		
		ofertaProducto.aplicarOferta(productos);
		
		verify(mockProd1, times(0)).addPorcentajeDescuento(anyDouble());	
		
		verify(mockProd2, times(1)).addPorcentajeDescuento(anyDouble());		
	}

}

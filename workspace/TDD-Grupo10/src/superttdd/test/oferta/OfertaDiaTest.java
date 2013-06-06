package superttdd.test.oferta;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import superttdd.caja.DiaSemana;
import superttdd.ofertas.OfertaDia;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaDiaTest {

	OfertaDia oferta;
	IProducto producto;
	List<IProducto> productos;
	Double porcentajeDescuento;
	
	@Test
	public void hoyHayOferta() {
		prepararOfertaParaHoy();
		oferta.aplicarOferta(productos);
		verify(producto, times(1)).addPorcentajeDescuento(porcentajeDescuento);
	}

	@Test
	public void hoyNOHayOferta() {
		prepararEscenarioParaQueNoHayaOfertaHoy();
		oferta.aplicarOferta(productos);
		verify(producto, times(0)).addPorcentajeDescuento(anyDouble());
	}
	
	private void prepararOfertaParaHoy() {
		productos = new ArrayList<IProducto>();
		List<DiaSemana> diasOferta = new ArrayList<DiaSemana>();
		diasOferta.add(DiaSemana.HOY);
		diasOferta.add(DiaSemana.VIERNES);
		diasOferta.add(DiaSemana.LUNES);
		
		porcentajeDescuento = 10.0;
		
		oferta = new OfertaDia(porcentajeDescuento, diasOferta);
		producto = spy(new Producto(new RegistroProducto(mock(CategoriaProducto.class), mock(MarcaProducto.class), "Producto", 100.0)));
		
		productos.add(producto);
	}
	
	private void prepararEscenarioParaQueNoHayaOfertaHoy() {
		productos = new ArrayList<IProducto>();
		List<DiaSemana> diasOferta = new ArrayList<DiaSemana>();
		
		if(DiaSemana.HOY.equals(DiaSemana.SABADO) ) {
			diasOferta.add(DiaSemana.DOMINGO);
			diasOferta.add(DiaSemana.LUNES);
		} else {
			diasOferta.add(DiaSemana.SABADO);
		}
		
		porcentajeDescuento = 10.0;
		
		oferta = new OfertaDia(porcentajeDescuento, diasOferta);
		producto = spy(new Producto(new RegistroProducto(mock(CategoriaProducto.class), mock(MarcaProducto.class), "Producto", 100.0)));
		
		productos.add(producto);
	}
	
}

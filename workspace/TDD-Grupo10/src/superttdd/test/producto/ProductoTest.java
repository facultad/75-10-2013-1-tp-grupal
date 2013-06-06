package superttdd.test.producto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class ProductoTest {

	Producto producto;
	MarcaProducto marca;
	CategoriaProducto categoria;
	
	@Before
	public void setUp() {
		String nombre = "ProductoTest";
		Double precioBase = 100.0;
		marca = new MarcaProducto("Pepsi");
		categoria = new CategoriaProducto("Categoria");
		RegistroProducto registro = new RegistroProducto(categoria, marca, nombre, precioBase);
		this.producto = new Producto(registro);	
	}
	
	@Test
	public void precioFinalSinDescuento() {
		assertEquals(producto.getPrecioBase(), producto.getPrecioFinal());
	}
	
	@Test
	public void precioFinalConUnDescuento() {
		Double precioInicial = producto.getPrecioBase();
		Double importeDescuento = precioInicial * producto.getPorcentajeDescuento() / 100;
		Double precioFinalEsperado = precioInicial - importeDescuento;
		
		assertEquals(precioFinalEsperado, producto.getPrecioFinal());
	}
	
	@Test
	public void precioFinalConDescuentoMaximo() {
		producto.addPorcentajeDescuento(100.0);
		Double precioFinalEsperado = 0.0;
		
		assertEquals(precioFinalEsperado, producto.getPrecioFinal());
	}
	
	@Test
	public void precioFinalConSumaDescuentoTotalSuperiorAlMaximo() {
		Double primerDescuento = 80.0;
		Double segundoDescuento = 30.0;
		Double precioFinalEsperado = producto.getPrecioBase() * (100.0 - primerDescuento) / 100.0;
		precioFinalEsperado = precioFinalEsperado * (100.0 - segundoDescuento) / 100.0;
		
		producto.addPorcentajeDescuento(primerDescuento);
		producto.addPorcentajeDescuento(segundoDescuento);
		
		assertEquals(precioFinalEsperado, producto.getPrecioFinal());
	}
	
	@Test
	public void precioFinalConMasDeUnDescuentoMenorAlMax() {
		Double primerDesc = 40.0;
		Double segundoDesc = 25.5;
		Double precioFinalEsperado  = producto.getPrecioBase() * (100.0 - primerDesc ) / 100;  
		precioFinalEsperado = precioFinalEsperado * (100.0 - segundoDesc ) / 100;
		
		
		producto.addPorcentajeDescuento(primerDesc);
		producto.addPorcentajeDescuento(segundoDesc);
		
		assertEquals(precioFinalEsperado , producto.getPrecioFinal());
	}
}


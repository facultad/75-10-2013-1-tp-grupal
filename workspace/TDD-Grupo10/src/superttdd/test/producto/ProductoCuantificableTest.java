package superttdd.test.producto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.ProductoCuantificable;
import superttdd.producto.RegistroProducto;

public class ProductoCuantificableTest {
	
	ProductoCuantificable producto;
	MarcaProducto marca;
	CategoriaProducto categoria;
	String NOMBRE_PROD = "Papa";
	Double cantidad = 1.5;
	
	@Before
	public void setUp() {
		Double precioBase = 10.0;
		marca = new MarcaProducto("Papa rica");
		categoria = new CategoriaProducto("Verdulería");
		RegistroProducto registro = new RegistroProducto(categoria, marca, NOMBRE_PROD, precioBase);
		producto = new ProductoCuantificable(registro, cantidad);	
	}
	
	@Test
	public void precioFinalSinDescuento() {
		Double precioEsperado = producto.getPrecioBase() * cantidad; 
		assertEquals(precioEsperado, producto.getPrecioFinal());
	}
	
	@Test
	public void precioFinalConUnDescuento() {
		Double precioInicial = producto.getPrecioBase();
		Double importeDescuento = precioInicial * producto.getPorcentajeDescuento() / 100;
		Double precioFinalEsperado = (precioInicial - importeDescuento) * cantidad;
		
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
		producto.addPorcentajeDescuento(80.0);
		producto.addPorcentajeDescuento(30.0);
		Double precioFinalEsperado = 0.0;
		
		assertEquals(precioFinalEsperado, producto.getPrecioFinal());
	}
	
	@Test
	public void precioFinalConMasDeUnDescuentoMenorAlMax() {
		Double primerDesc = 40.0;
		Double segundoDesc = 25.5;
		Double montoDescuento = producto.getPrecioBase() * (primerDesc + segundoDesc) / 100;  
		Double precioFinalEsperado = (producto.getPrecioBase() - montoDescuento) * cantidad;
		
		
		producto.addPorcentajeDescuento(primerDesc);
		producto.addPorcentajeDescuento(segundoDesc);
		
		assertEquals(precioFinalEsperado , producto.getPrecioFinal());
	}

}

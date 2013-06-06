package superttdd.test.integracion;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.Caja;
import superttdd.caja.MedioPago;
import superttdd.ofertas.OfertaConjuntoProds;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.CuponDescuento;
import superttdd.promociones.PromoCuponFuturo;

public class PruebaIntegracion6 {
	/*
	 * Cupón de descuento:
		El mismo se genera en un compra pero no descuenta en la misma sino que sirve para ser usada en una futura compra. 
		===================
		
		Dado que:
		- Existe descuento "futuro" (para la próxima venta) de 2x1 en cocas.
		- La coca vale $1.
		- El cupón de descuento no puede descontar mas del 20% de la venta.
		 
		Cuando: 
		- Se realiza una venta de: 10 cocas con un cupón de descuento de $10.
		
		Entonces:
		- El precio final de la venta debe ser: 
		($10 (10 cocas)) - $2 (cupón de $10 limitado al 20% de la venta)
		- Hay un descuento de $2 por limite del 20% de la venta del Cupón de $10.
		- Se genera un Bono de descuento para próxima compra de $5 (por el 2x1 de 10 cocas)
	 * */

	private CuponDescuento cuponDescuento;
	private PromoCuponFuturo cuponFuturo;
	private RegistroProducto regProd;
	private Caja caja;
	private List<PromoCuponFuturo> cuponesFuturos; 
	private Double precioFinalEsperado;
	private static Double LIMITE_MAX_DESCUENTO = 20.0;
	private static Double MONTO_CUPON = 10.0;
	private static Double PRECIO_PROD = 1.0;
	private static Double MONTO_ESPERADO_CUPON = 5.0;
	private static Integer PRODS_A_COMPRAR = 10;
	
	private static String CATEGORIA_PRODUCTO = "Almacen";
	private static String MARCA_PRODUCTO = "Coca Cola";
	
	@Before
	public void setUp() {
		caja = new Caja();
		cuponDescuento = new CuponDescuento(MONTO_CUPON, LIMITE_MAX_DESCUENTO);
		regProd = new RegistroProducto(new CategoriaProducto(CATEGORIA_PRODUCTO), new MarcaProducto(MARCA_PRODUCTO), "Coca cola", PRECIO_PROD);
		List<RegistroProducto> productosOferta = new ArrayList<RegistroProducto>(); 
		productosOferta.add(regProd);
		productosOferta.add(regProd);
		OfertaConjuntoProds ofertaCuponFuturo = new OfertaConjuntoProds(productosOferta, 50.0);
		cuponFuturo = new PromoCuponFuturo(ofertaCuponFuturo);
		cuponesFuturos = new ArrayList<PromoCuponFuturo>();
		cuponesFuturos.add(cuponFuturo);
	}
	
	@Test 
	public void pruebaCompraConCuponDescuneto() {
		//TODO: Falta agregar validación de generación de cupones futuros
		caja.abrirCaja();
		caja.cargarDescuentosCuponesFuturos(cuponesFuturos);
		
		caja.iniciarCompra();
		
		// Ingreso producto de la compra
		for(int i = 0; i < PRODS_A_COMPRAR; i++) {
			caja.agregarProducto(new Producto(regProd));
		}
		
		caja.agregarDescuentoFactura(cuponDescuento);
		caja.confirmarCompra(MedioPago.EFECTIVO);
		
		
		Double montoFinal = caja.obtenerTotalCompraConDescuentos();
		Double montoCupon = caja.obtenerMontoCuponDescuento();
		caja.cerrarCompra();
		
		caja.cerrarCaja();
		
		calcularPrecioFinalEsperadoConDescuento();
		assertEquals(precioFinalEsperado, montoFinal);
		assertEquals(MONTO_ESPERADO_CUPON , montoCupon );
		
	}
	
	private void calcularPrecioFinalEsperadoConDescuento() {
		Double descuentoMax = PRECIO_PROD * PRODS_A_COMPRAR * (LIMITE_MAX_DESCUENTO)/100.0;
		Double descAplicar =  (MONTO_CUPON <= descuentoMax)? MONTO_CUPON : descuentoMax;
		precioFinalEsperado = PRECIO_PROD * PRODS_A_COMPRAR - descAplicar;
	}
}

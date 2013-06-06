package superttdd.test.integracion;

import static org.junit.Assert.*;

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

public class PruebaIntegracion7 {


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
			//TODO: Falta agregar validaci��n de generaci��n de cupones futuros
			caja.abrirCaja();
			caja.cargarDescuentosCuponesFuturos(cuponesFuturos);
			
			caja.iniciarCompra();
			
			// Ingreso producto de la compra
			for(int i = 0; i < 9; i++) {
				caja.agregarProducto(new Producto(regProd));
			}
			
			caja.agregarDescuentoFactura(cuponDescuento);
			caja.confirmarCompra(MedioPago.EFECTIVO);
			
			
			Double montoFinal = caja.obtenerTotalCompraConDescuentos();
			Double montoCupon = caja.obtenerMontoCuponDescuento();
			caja.cerrarCompra();
			
			caja.cerrarCaja();
			
			calcularPrecioFinalEsperadoConDescuento();
			//assertEquals(precioFinalEsperado, montoFinal);
			assertEquals(MONTO_ESPERADO_CUPON , montoCupon );
			
		}
		
		private void calcularPrecioFinalEsperadoConDescuento() {
			Double descuentoMax = PRECIO_PROD * PRODS_A_COMPRAR * (LIMITE_MAX_DESCUENTO)/100.0;
			Double descAplicar =  (MONTO_CUPON <= descuentoMax)? MONTO_CUPON : descuentoMax;
			precioFinalEsperado = PRECIO_PROD * PRODS_A_COMPRAR - descAplicar;
		}
}

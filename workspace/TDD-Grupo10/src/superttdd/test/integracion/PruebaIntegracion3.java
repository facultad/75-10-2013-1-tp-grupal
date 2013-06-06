package superttdd.test.integracion;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.Caja;
import superttdd.caja.MedioPago;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaCompuestaNOT;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.PromoMedioPago;

public class PruebaIntegracion3 {

	/** ESCENARIO IMPLEMENTADO
	Dado que: 
		- Existe promo descuento 10% en vinos, excepto Marca XXZ y Marca LLL. 
		- El vino ELL sale $10, el XXZ sale $20, el LLL sale $30. 

		Cuando: 
		- Se realiza una venta de: 2 vinos ELL, 1 de XXZ y 2 de LLL. 
		Pagando en efectivo. 

		Entonces: 
		- El precio final de la venta debe ser: 
		($10 * 2 (2 ELL)) * 0.90 (descuento del 10) + $20 XXZ + $30 LLL. 
		- Los descuentos aplicados son: $2 por promo descuento del 10% en vinos.
	*/

	private String CATEGORIA_VINO = "Vinoteca";
	private String MARCA_1_EXCEPTO = "XXZ";
	private String MARCA_2_EXCEPTO = "LLL";
	private Double PRECIO_VINO_ELL = 10.0;
	private Double PRECIO_VINO_XXZ = 20.0;
	private Double PRECIO_VINO_LLL = 30.0;
	private Double DESCUENTO_OFERTA = 10.0;
	private MedioPago MEDIO_PAGO_PROMO = MedioPago.EFECTIVO;

	Caja caja;
	ArrayList<Oferta> ofertasDelDia;
	ArrayList<PromoMedioPago> promosDelDia;

	Double precioFinalEsperado = 0.0;

	RegistroProducto registroVino;
	RegistroProducto registroELL;
	RegistroProducto registroXXZ;
	RegistroProducto registroLLL;

	@Before
	public void setUp() {
		caja = new Caja();
		ofertasDelDia = new ArrayList<Oferta>();
		promosDelDia = new ArrayList<PromoMedioPago>();
		registroELL = new RegistroProducto(new CategoriaProducto(CATEGORIA_VINO), new MarcaProducto("ELL"), "VinoELL", PRECIO_VINO_ELL);
		registroXXZ = new RegistroProducto(new CategoriaProducto(CATEGORIA_VINO), new MarcaProducto("XXZ"), "VinoXXZ", PRECIO_VINO_XXZ);
		registroLLL = new RegistroProducto(new CategoriaProducto(CATEGORIA_VINO), new MarcaProducto("LLL"), "VinoLLL", PRECIO_VINO_LLL);
		calcularPrecioFinalEsperado();
	}

	@Test 
	public void pruebaIntegracion2() {
		caja.abrirCaja();

		crearOfertasDelDia();
		crearPromosMedioDePagoDelDia();

		caja.cargarOfertas(ofertasDelDia);
		caja.cargarPromocionesDeMedioDePago(promosDelDia);

		caja.iniciarCompra();

		realizarCompra();

		caja.confirmarCompra(MEDIO_PAGO_PROMO);

		Double montoFinal = caja.obtenerTotalCompraConDescuentos();
		
		caja.cerrarCompra();
		
		montoFinal = caja.visualizarMontoEnCajaPorMedioDePago(MEDIO_PAGO_PROMO);

		caja.cerrarCaja();

		assertEquals(precioFinalEsperado, montoFinal);
	}

	private void crearOfertasDelDia() {
		OfertaMarca ofertaMarca1 = new OfertaMarca(new MarcaProducto(MARCA_1_EXCEPTO), 0.0);
		OfertaMarca ofertaMarca2 = new OfertaMarca(new MarcaProducto(MARCA_2_EXCEPTO), 0.0);
		ArrayList<Oferta> ofertasNOT = new ArrayList<Oferta>();
		ofertasNOT.add(ofertaMarca1);
		ofertasNOT.add(ofertaMarca2);
		OfertaCompuestaNOT ofertaNOT = new OfertaCompuestaNOT(ofertasNOT, 10.0);

		ofertasDelDia.add(ofertaNOT);
	}

	private void crearPromosMedioDePagoDelDia() {
		// No hay promos en esta Prueba de Integración
	}

	private void realizarCompra() {
		caja.agregarProducto(new Producto(registroELL));
		caja.agregarProducto(new Producto(registroELL));
		caja.agregarProducto(new Producto(registroXXZ));
		caja.agregarProducto(new Producto(registroLLL));
		caja.agregarProducto(new Producto(registroLLL));

	}

	private void calcularPrecioFinalEsperado() {
		// ($10 * 2 (2 ELL)) * 0.90 (descuento del 10) + $20 XXZ + $30 LLL.  
		// Los descuentos aplicados son: $2 por promo descuento del 10% en vinos.

		Double descuentoVino = PRECIO_VINO_ELL * DESCUENTO_OFERTA / 100.0;

		precioFinalEsperado += ((PRECIO_VINO_ELL - descuentoVino))*2;
		precioFinalEsperado += PRECIO_VINO_XXZ;
		precioFinalEsperado += (PRECIO_VINO_LLL)*2;
	}
}

package superttdd.test.integracion;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.Caja;
import superttdd.caja.DiaSemana;
import superttdd.caja.MedioPago;
import superttdd.comprobante.Factura;
import superttdd.comprobante.OrdenDeCompra;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaConjuntoProds;
import superttdd.ofertas.OfertaDia;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.PromoMedioPago;
import superttdd.promociones.PromoMedioPagoCompuestaAND;

public class PruebaIntegracion1 {
	private static final double DESCUENTO_TARJETA = 10.0;
	private static final double PRECIO_COCA_COLA = 1.0;
	private static final double PRECIO_CEPILLO = 3.0;
	private static final double PRECIO_MACETA = 10.0;

	/*
	 * 1) Dado que: - es Jueves. - Existe promo Coca lleve 2 y pague 1. - La
	 * coca sale 1 peso. - Hay descuento 10% para Tarjeta XYZ los dias Jueves. -
	 * Cepillo dientes sale 3 pesos. - Maceta sale 10 pesos.
	 * 
	 * Cuando: - Se realiza una venta de 2 cocas, un cepillo de dientes y una
	 * maceta. Pagando con Tarjeta XYZ.
	 * 
	 * Entonces: - El precio final de la venta debe ser: (1 Peso (2 cocas) + 3
	 * pesos (cepillo) + 10 pesos (maceta)) * 0.90 (descuento tarjeta) - Los
	 * descuentos aplicados son: 1 peso por promo 2x1 coca, 10% total por pago
	 * con tarjeta XYZ.
	 */

	ArrayList<Producto> productos;
	ArrayList<PromoMedioPago> promos;
	ArrayList<Oferta> ofertas;
	
	
	
	@Before
	public void setUp() throws Exception {
		RegistroProducto registroCocaCola = crearRegistroCocaCola();
		RegistroProducto registroCepillo = crearRegistroCepillo();
		RegistroProducto registroMaceta = crearRegistroMaceta();
		
		//---ofertas---\\
		ofertas = new ArrayList<Oferta>();
		//oferta coca lleve 2 pague 1
		ArrayList<RegistroProducto> registro_productos = new ArrayList<RegistroProducto>();
		registro_productos.add(registroCocaCola);
		registro_productos.add(registroCocaCola);
		Double desc2x1 = 50.0;
		OfertaConjuntoProds oferta2x1Coca = new OfertaConjuntoProds(registro_productos, desc2x1);
		ofertas.add(oferta2x1Coca);
		
		//--productos--\\
		productos = new ArrayList<Producto>();
		
		Producto cocaCola1 = new Producto(registroCocaCola); 
		Producto cocaCola2 = new Producto(registroCocaCola); 
		productos.add(cocaCola1);
		productos.add(cocaCola2);
		Producto cepillo = new Producto(registroCepillo); 
		productos.add(cepillo);
		Producto Maceta = new Producto(registroMaceta); 
		productos.add(Maceta);
		
		//--promos medio pago--\\
		promos = new ArrayList<PromoMedioPago>();
		ArrayList<OfertaDia> ofertas_promo = new ArrayList<OfertaDia>();
		OfertaDia ofertaDia = crearOfertaDia();
		ofertas_promo.add(ofertaDia);
		PromoMedioPago promoXYZ = new PromoMedioPagoCompuestaAND(MedioPago.TARJETA_XYZ,ofertas_promo, DESCUENTO_TARJETA);
		promos.add(promoXYZ);
		
	}

	private OfertaDia crearOfertaDia() {
		ArrayList<DiaSemana> diasSemana = new ArrayList<DiaSemana>();
		diasSemana.add(DiaSemana.HOY);	
		OfertaDia ofertaDia = new OfertaDia(5.0,diasSemana);
		return ofertaDia;
	}
	
	@Test
	public void pruebaIntegracion1_totalDescuento() {
		Caja caja = new Caja();
		caja.abrirCaja();
		caja.cargarOfertas(ofertas);
		caja.cargarPromocionesDeMedioDePago(promos);
		caja.iniciarCompra();
	
		for(Producto producto: productos) {
			caja.agregarProducto(producto);
		}
		
		caja.confirmarCompra(MedioPago.TARJETA_XYZ);
		Double total_obtenido = caja.obtenerTotalCompraConDescuentos(); 
		
		
		caja.cerrarCompra();
		caja.cerrarCaja();
	
		Double aux=PRECIO_COCA_COLA+PRECIO_CEPILLO+PRECIO_MACETA;
		Double total_esperado = aux - (PRECIO_COCA_COLA+PRECIO_CEPILLO+PRECIO_MACETA)*(DESCUENTO_TARJETA)/100;
		Assert.assertEquals(total_esperado,total_obtenido);	
	}
	
	@Test
	public void pruebaIntegracion1_totalSinDescuento() {
		Caja caja = new Caja();
		caja.abrirCaja();
		caja.cargarPromocionesDeMedioDePago(promos);
		caja.iniciarCompra();
	
		for(Producto producto: productos) {
			caja.agregarProducto(producto);
		}
		
		caja.confirmarCompra(MedioPago.TARJETA_XYZ);
		Double total_obtenido = caja.obtenerTotalCompraSinDescuentos();
		caja.cerrarCompra();
		caja.cerrarCaja();
	
		Double total_sin_descuento=2*PRECIO_COCA_COLA+PRECIO_CEPILLO+PRECIO_MACETA;
		Assert.assertEquals(total_sin_descuento,total_obtenido);
		
	}
	
	Double total_sin_descuento=2*PRECIO_COCA_COLA+PRECIO_CEPILLO+PRECIO_MACETA;
	Double aux=PRECIO_COCA_COLA+PRECIO_CEPILLO+PRECIO_MACETA;

	private RegistroProducto crearRegistroMaceta() {
		CategoriaProducto categoriaMaceta = new CategoriaProducto("Jardin");
		MarcaProducto marcaMaceta = new MarcaProducto("Plantul");
		String nombreMaceta = "Maceta";
		Double precioMaceta = PRECIO_MACETA;
		RegistroProducto registroMaceta = new RegistroProducto(categoriaMaceta, marcaMaceta,nombreMaceta,precioMaceta);
		return registroMaceta;
	}

	private RegistroProducto crearRegistroCepillo() {
		CategoriaProducto categoriaCepillo = new CategoriaProducto("Farmacia");
		MarcaProducto marcaCepillo = new MarcaProducto("Colgate");
		String nombreCepillo = "cepillo dientes";
		Double precioCepillo = PRECIO_CEPILLO;
		RegistroProducto registroCepillo = new RegistroProducto(categoriaCepillo, marcaCepillo,nombreCepillo,precioCepillo);
		return registroCepillo;
	}
	
	private RegistroProducto crearRegistroCocaCola() {
		CategoriaProducto categoriaCoca = new CategoriaProducto("gaseosa");
		MarcaProducto marcaCoca = new MarcaProducto("Coca Cola");
		String nombreCoca = "coca cola";
		Double precioCoca = PRECIO_COCA_COLA;
		return new RegistroProducto(categoriaCoca, marcaCoca,nombreCoca,precioCoca);
	}



}

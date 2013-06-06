package superttdd.test.promociones;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.DiaSemana;
import superttdd.caja.MedioPago;
import superttdd.comprobante.Factura;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaDia;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.PromoMedioPagoCompuestaAND;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.anyDouble;

public class PromoMedioPagoCompuestaANDTest {
	private static final String NOMBRE_MARCA_TEST = "marcaTest";
	private static final String NOMBRE_MARCA_DISTINTA_TEST = "marcaDistintaTest";
	private static final double PRECIO_PRODUCTO = 10.0;
	private static final double DESCUENTO_OFERTA = 5.0;
	private static final MedioPago MEDIO_PAGO_PROMO = MedioPago.TARJETA_XYZ;
	private List<OfertaDia> ofertas;
	private List<IProducto> productos;
	private MarcaProducto marca;
	PromoMedioPagoCompuestaAND promoCompuestaAND;
	
	@Before
	public void setUp() throws Exception {
		ofertas=new ArrayList<OfertaDia>();
		marca = new MarcaProducto(NOMBRE_MARCA_TEST);
		
		
		productos=new ArrayList<IProducto>();
	}

	@Test
	public void PromoAplicaParaProductoQueAplicaASusOfertasYFacturaConMismoMedioPago() {
		List<DiaSemana> dias_semana = new ArrayList<DiaSemana>();
		dias_semana.add(DiaSemana.HOY);
		OfertaDia ofertaDia = new OfertaDia(0.0, dias_semana);
		ofertas.add(ofertaDia);
		
		RegistroProducto registro=new RegistroProducto(mock(CategoriaProducto.class), marca, "Producto", PRECIO_PRODUCTO);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);	
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas, DESCUENTO_OFERTA);
		
		Factura factura = spy(new Factura(1, MEDIO_PAGO_PROMO, productos));
		promoCompuestaAND.aplicarDescuento(factura);
		
		verify(factura, times(1)).descontarMonto(anyDouble());
	}
	
	@Test
	public void PromoNOAplicaParaProductoQueAplicaASusOfertasYNOFacturaConMismoMedioPago() {
		List<DiaSemana> dias_semana = new ArrayList<DiaSemana>();
		dias_semana.add(DiaSemana.HOY);
		OfertaDia ofertaDia = new OfertaDia(0.0, dias_semana);
		ofertas.add(ofertaDia);
		
		RegistroProducto registro=new RegistroProducto(mock(CategoriaProducto.class), marca, "Producto", PRECIO_PRODUCTO);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas, DESCUENTO_OFERTA);
		
		Factura factura = spy(new Factura(1, MedioPago.EFECTIVO, productos));
		promoCompuestaAND.aplicarDescuento(factura);
		
		verify(factura, times(0)).descontarMonto(anyDouble());
	}
	
	@Test
	public void PromoNOAplicaParaProductoQueNOAplicaASusOfertasYFacturaConMismoMedioPago() {
		List<DiaSemana> dias_semana = new ArrayList<DiaSemana>();
		DiaSemana distintoDeHoy = getDiaDistintoHoy();
		dias_semana.add(distintoDeHoy);
		OfertaDia ofertaDia = new OfertaDia(0.0, dias_semana);
		ofertas.add(ofertaDia);
		
		marca = new MarcaProducto(NOMBRE_MARCA_DISTINTA_TEST);
		
		RegistroProducto registro = new RegistroProducto(mock(CategoriaProducto.class), marca, "Producto", PRECIO_PRODUCTO);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		promoCompuestaAND = new PromoMedioPagoCompuestaAND(MEDIO_PAGO_PROMO, ofertas, DESCUENTO_OFERTA);
		Factura factura = spy(new Factura(1, MEDIO_PAGO_PROMO, productos));
		promoCompuestaAND.aplicarDescuento(factura);
		
		verify(factura, times(0)).descontarMonto(anyDouble());
	}
	
	public DiaSemana getDiaDistintoHoy(){
		if(DiaSemana.HOY == DiaSemana.LUNES){
			return DiaSemana.MARTES;
		} else {
			return DiaSemana.LUNES;
		}
	}

}

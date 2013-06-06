package superttdd.test.integracion;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.Caja;
import superttdd.caja.MedioPago;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaConjuntoProds;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.Inventario;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class PruebaIntegracion5 {
	
	/*Se requiere obtener un listado de todos los productos con el total de ventas del dia de toda la sucursal.
	==================================
	Dado que:
	- Existe promo Coca+Sprite 30% descuento en la Sprite. 

	Cuando: 
	- Se realiza una venta de: 3 cocas + 2 sprites + 1 maceta.
	- Se realiza otra venta de: 1 coca + 4 sprite + 4 macetas.

	Entonces:
	- El ranking de productos vendidos en la sucursal: es de 6 sprites, 5 macetas, 4 cocas.*/

	private static final Double DESCUENTO_PROMO = 30.0;
	private String CATEGORIA_GASEOSAS = "Gaseosa";
	private String CATEGORIA_JARDINERIA = "Jardineria";
	private Double PRECIO_SPRITE = 10.0;
	private Double PRECIO_COCA = 12.0;
	private Double PRECIO_MACETA = 10.0;
	private MedioPago MEDIO_PAGO_PROMO = MedioPago.EFECTIVO;

	Caja caja;
	ArrayList<Oferta> ofertasDelDia;

	Double precioFinalEsperado = 0.0;

	RegistroProducto registroSprite;
	RegistroProducto registroCoca;
	RegistroProducto registroMaceta;

	@Before
	public void setUp() {
		caja = new Caja();
		ofertasDelDia = new ArrayList<Oferta>();
		registroSprite = new RegistroProducto(new CategoriaProducto(CATEGORIA_GASEOSAS), new MarcaProducto("Sprite"), "Sprite", PRECIO_SPRITE);
		registroMaceta = new RegistroProducto(new CategoriaProducto(CATEGORIA_JARDINERIA), new MarcaProducto("Maceta"), "Maceta", PRECIO_MACETA);
		registroCoca = new RegistroProducto(new CategoriaProducto(CATEGORIA_GASEOSAS), new MarcaProducto("Coca"), "Coca", PRECIO_COCA);
		Inventario inventario = Inventario.getInstance();
		inventario.borrarInventario();
		inventario.agregarRegistroProducto(registroSprite);
		inventario.agregarRegistroProducto(registroMaceta);
		inventario.agregarRegistroProducto(registroCoca);
	}

	@Test 
	public void pruebaIntegracion5() {
	
		caja.abrirCaja();
		crearOfertasDelDia();
		caja.cargarOfertas(ofertasDelDia);
		caja.iniciarCompra();
		
		/* - Se realiza una venta de: 3 cocas + 2 sprites + 1 maceta. */
		caja.agregarProducto(new Producto(registroCoca));
		caja.agregarProducto(new Producto(registroCoca));
		caja.agregarProducto(new Producto(registroCoca));
		caja.agregarProducto(new Producto(registroSprite));
		caja.agregarProducto(new Producto(registroSprite));
		caja.agregarProducto(new Producto(registroMaceta));

		caja.confirmarCompra(MEDIO_PAGO_PROMO);	
		caja.cerrarCompra();
		
		ArrayList<RegistroProducto> ranking = Inventario.getInstance().obtenerRankingDeProductos();
		
		assertEquals(ranking.get(0).getNombre(), "Coca");
		assertEquals(ranking.get(0).getCantidadVendida(), 3);
		assertEquals(ranking.get(1).getNombre(), "Sprite");
		assertEquals(ranking.get(1).getCantidadVendida(), 2);
		assertEquals(ranking.get(2).getNombre(), "Maceta");
		assertEquals(ranking.get(2).getCantidadVendida(), 1);
		
		/*Se realiza otra venta de: 1 coca + 4 sprite + 4 macetas. */
		caja.iniciarCompra();
		
		caja.agregarProducto(new Producto(registroCoca));
		caja.agregarProducto(new Producto(registroSprite));
		caja.agregarProducto(new Producto(registroSprite));
		caja.agregarProducto(new Producto(registroSprite));
		caja.agregarProducto(new Producto(registroSprite));
		caja.agregarProducto(new Producto(registroMaceta));
		caja.agregarProducto(new Producto(registroMaceta));
		caja.agregarProducto(new Producto(registroMaceta));
		caja.agregarProducto(new Producto(registroMaceta));
		
		caja.confirmarCompra(MEDIO_PAGO_PROMO);
		caja.cerrarCompra();
		
		caja.cerrarCaja();
		ranking = Inventario.getInstance().obtenerRankingDeProductos();
		
		assertEquals(ranking.get(0).getNombre(), "Sprite");
		assertEquals(ranking.get(0).getCantidadVendida(), 6);
		assertEquals(ranking.get(1).getNombre(), "Maceta");
		assertEquals(ranking.get(1).getCantidadVendida(), 5);
		assertEquals(ranking.get(2).getNombre(), "Coca");
		assertEquals(ranking.get(2).getCantidadVendida(), 4);
	}

	private void crearOfertasDelDia() {
		ArrayList<RegistroProducto> registros_oferta = new ArrayList<RegistroProducto>();
		registros_oferta.add(registroCoca);
		registros_oferta.add(registroSprite);
		
		Double descuento = registroSprite.getPrecio() * DESCUENTO_PROMO / 100;
		descuento = descuento / (registroSprite.getPrecio() + registroCoca.getPrecio());
		OfertaConjuntoProds oferta = new OfertaConjuntoProds(registros_oferta, descuento*100);
		
		ofertasDelDia.add(oferta);
	}
}

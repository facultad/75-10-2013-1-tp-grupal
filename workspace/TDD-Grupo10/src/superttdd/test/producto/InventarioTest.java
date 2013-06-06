package superttdd.test.producto;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import superttdd.producto.CategoriaProducto;
import superttdd.producto.Inventario;
import superttdd.producto.MarcaProducto;
import superttdd.producto.RegistroProducto;

public class InventarioTest {
	
	
	private String CATEGORIA_GASEOSAS = "Gaseosa";
	private String MARCA_GASEOSA = "Coca-Cola";
	private Double PRECIO_SPRITE = 10.0;
	private Double PRECIO_COCA = 12.0;
	
	private RegistroProducto registroSprite;
	private RegistroProducto registroCoca;	
	

	@Before
	public void setUp() throws Exception {
		registroSprite = new RegistroProducto(new CategoriaProducto(CATEGORIA_GASEOSAS), new MarcaProducto(MARCA_GASEOSA), "Sprite", PRECIO_SPRITE);
		registroCoca = new RegistroProducto(new CategoriaProducto(CATEGORIA_GASEOSAS), new MarcaProducto(MARCA_GASEOSA), "Coca", PRECIO_COCA);
		
		Inventario.getInstance().borrarInventario();
		Inventario.getInstance().agregarRegistroProducto(registroSprite);
		Inventario.getInstance().agregarRegistroProducto(registroCoca);
	}

	@Test(expected=RuntimeException.class)
	public void AgregarRegistroRepetido() throws RuntimeException {
		RegistroProducto otroRegistroSprite = new RegistroProducto(new CategoriaProducto(CATEGORIA_GASEOSAS), new MarcaProducto(MARCA_GASEOSA), "Sprite", PRECIO_SPRITE);
		Inventario.getInstance().agregarRegistroProducto(otroRegistroSprite);
	}
	
	@Test 
	public void ObtenerRegistroDeUnProducto() {
		RegistroProducto unRegistro = Inventario.getInstance().obtenerRegistroProducto(
		new MarcaProducto(MARCA_GASEOSA), new CategoriaProducto(CATEGORIA_GASEOSAS), "Sprite");
		
		assertEquals(registroSprite, unRegistro);
	}
	
	@Test
	public void CreacionCorrectoDeRankingDeProductos() {
		RegistroProducto unRegistroSprite = Inventario.getInstance().obtenerRegistroProducto(
		new MarcaProducto(MARCA_GASEOSA), new CategoriaProducto(CATEGORIA_GASEOSAS), "Sprite");
		RegistroProducto unRegistroCoca= Inventario.getInstance().obtenerRegistroProducto(
		new MarcaProducto(MARCA_GASEOSA), new CategoriaProducto(CATEGORIA_GASEOSAS), "Sprite");
		
		unRegistroSprite.agregarVenta();
		unRegistroSprite.agregarVenta();
		unRegistroSprite.agregarVenta();
		
		unRegistroCoca.agregarVenta();
		unRegistroCoca.agregarVenta();
		
		ArrayList<RegistroProducto> ranking = Inventario.getInstance().obtenerRankingDeProductos();
		assertEquals("Sprite", ranking.get(0).getNombre());
		assertEquals("Coca", ranking.get(1).getNombre());
		
		
		
	}

}

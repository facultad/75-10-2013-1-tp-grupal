package superttdd.test.oferta;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaCategoria;
import superttdd.ofertas.OfertaCompuestaNOT;
import superttdd.ofertas.OfertaConjuntoProds;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaCompuestaNOTTest {
	private static final String MARCA_OFERTA = "Logitech";
	private static final String MARCA_PRODUCTO = "Genius";
	private OfertaCompuestaNOT ofertaCompuesta;
	private List<Oferta> ofertas;
	private List<IProducto> productos;
	private static final Double DESCUENTO_OFERTA = 10.0;

	@Before
	public void setUp() throws Exception {
		ofertas= new ArrayList<Oferta>();
		productos = new ArrayList<IProducto>();		
	}

	@Test
	public void AplicaAProductoQueNoAplicaAOferta_UnaOferta() {
		MarcaProducto marca = new MarcaProducto(MARCA_OFERTA);
		OfertaMarca ofertaMarca = new OfertaMarca(marca,20.0);
		ofertas.add(ofertaMarca);
		
		MarcaProducto marcaProducto = new MarcaProducto(MARCA_PRODUCTO);
		CategoriaProducto categoria = new CategoriaProducto("categoria");
		
		RegistroProducto registro = new RegistroProducto(categoria, marcaProducto, "mouse inalambrico", 10.0);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		ofertaCompuesta = new OfertaCompuestaNOT(ofertas, DESCUENTO_OFERTA);
		ofertaCompuesta.aplicarOferta(productos);
		
		verify(producto, times(1)).addPorcentajeDescuento(DESCUENTO_OFERTA);
	}
	
	@Test
	public void AplicaAProductoQueNoAplicaAOferta_VariasOfertas() {
		agregarOfertas();
		
		MarcaProducto marcaProducto = new MarcaProducto(MARCA_PRODUCTO);
		CategoriaProducto categoria = new CategoriaProducto("categoria");
		
		RegistroProducto registro = new RegistroProducto(categoria, marcaProducto, "mouse inalambrico", 10.0);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		ofertaCompuesta = new OfertaCompuestaNOT(ofertas, DESCUENTO_OFERTA);
		ofertaCompuesta.aplicarOferta(productos);
		
		verify(producto, times(1)).addPorcentajeDescuento(DESCUENTO_OFERTA);
	}
	
	@Test
	public void NoAplicaAProductoQueAplicaOferta_UnaOferta() {
		MarcaProducto marca = new MarcaProducto(MARCA_PRODUCTO);
		OfertaMarca ofertaMarca = new OfertaMarca(marca,20.0);
		ofertas.add(ofertaMarca);
		
		MarcaProducto marcaProducto = new MarcaProducto(MARCA_PRODUCTO);
		CategoriaProducto categoria = new CategoriaProducto("categoria");
		
		RegistroProducto registro = new RegistroProducto(categoria, marcaProducto, "mouse inalambrico", 10.0);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		ofertaCompuesta = new OfertaCompuestaNOT(ofertas, DESCUENTO_OFERTA);
		ofertaCompuesta.aplicarOferta(productos);
		
		verify(producto, times(0)).addPorcentajeDescuento(DESCUENTO_OFERTA);
	}
	
	@Test
	public void NoAplicaAProductoQueAplicaAlgunaOferta_VariasOfertas() {
		agregarOfertas();
		
		MarcaProducto marcaProducto = new MarcaProducto(MARCA_OFERTA);
		CategoriaProducto categoria = new CategoriaProducto("categoria");
		
		RegistroProducto registro = new RegistroProducto(categoria, marcaProducto, "mouse inalambrico", 10.0);
		Producto producto = spy(new Producto(registro));
		productos.add(producto);
		
		ofertaCompuesta = new OfertaCompuestaNOT(ofertas, DESCUENTO_OFERTA);
		ofertaCompuesta.aplicarOferta(productos);
		
		verify(producto, times(0)).addPorcentajeDescuento(DESCUENTO_OFERTA);
	}

	private void agregarOfertas() {
		MarcaProducto marca = new MarcaProducto(MARCA_OFERTA);
		OfertaMarca ofertaMarca = new OfertaMarca(marca,20.0);
		ofertas.add(ofertaMarca);
		
		CategoriaProducto categoriaOferta = new CategoriaProducto("Gaseosas");
		OfertaCategoria ofertaCategoria = new OfertaCategoria(categoriaOferta, 10.0);
		ofertas.add(ofertaCategoria);

		RegistroProducto registro1 = new RegistroProducto(new CategoriaProducto("Lacteos"),
				new MarcaProducto("LaSerenisima"), "leche descremada", 10.0);
		RegistroProducto registro2 = new RegistroProducto(new CategoriaProducto("Fideos"),
				new MarcaProducto("Matarazzo"), "Mostacholes", 10.0);
		ArrayList<RegistroProducto>registro_productos=new ArrayList<RegistroProducto>();
		registro_productos.add(registro1);
		registro_productos.add(registro2);
		
		OfertaConjuntoProds ofertaConjuntos = new OfertaConjuntoProds(registro_productos, 25.0);
		ofertas.add(ofertaConjuntos);
	}

}

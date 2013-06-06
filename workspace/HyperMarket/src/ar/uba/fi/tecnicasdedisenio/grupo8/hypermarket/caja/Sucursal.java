package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Sucursal implements ISucursal{

	private String nombre;
	private long id = IdGenerator.getInstance().getNewId();
	
	private Collection<Caja> cajas;
	
	public Sucursal(String n) {
		this.nombre=n;
		this.cajas = new ArrayList<Caja>();
	}

	public long getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void addCaja(Caja caja) {
		cajas.add(caja);
	}
	
	public TreeMap<IProducto,Integer> obtenerRankingProductosMasVendidos() {
		TreeMap<IProducto,Integer> rankingGeneral = new TreeMap<IProducto,Integer>();

		for (Caja caja : cajas) {
			TreeMap<IProducto,Integer> rankingCaja = caja.obtenerRankingProductosMasVendidos();
			
			// merge de productos de esta caja con el global
			
			for (IProducto producto : rankingCaja.keySet()) {
				
				Integer cantidadProductoBuscado = rankingGeneral.get(producto);
				
				
				if (cantidadProductoBuscado != null) {
					cantidadProductoBuscado += rankingCaja.get(producto);
				}
				
				rankingGeneral.put(producto, cantidadProductoBuscado);					
			}
		}		
		
		return rankingGeneral;
	}
}

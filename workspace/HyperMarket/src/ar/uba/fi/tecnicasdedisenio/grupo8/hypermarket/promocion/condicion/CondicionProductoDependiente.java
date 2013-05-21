package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.DependenciasCondicionNoDefinidas;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.ProductoNoPuedeDependerDeSiMismoException;

public class CondicionProductoDependiente extends CondicionItemVenta {

	private IProducto productoDependiente;
	private Collection<IProducto> dependencias=new ArrayList<IProducto>();
	

	public CondicionProductoDependiente(IProducto productoDependiente) {
		this.productoDependiente=productoDependiente;
	}

	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		if (this.dependencias.isEmpty())
			throw new DependenciasCondicionNoDefinidas();
		if (this.productoDependiente.getId()!=itemVenta.getProducto().getId())
			return false;
		IVenta venta=itemVenta.getVenta();
		Iterator<IProducto> iterProducto=this.dependencias.iterator();
		while (iterProducto.hasNext()){
			IProducto dependencia=iterProducto.next();
			if (venta.contieneProducto(dependencia))
				return true;
		}
		return false;
	}

	public void addDependencia(IProducto dependencia) {
		if (this.productoDependiente.getId()==dependencia.getId())
			throw new ProductoNoPuedeDependerDeSiMismoException();
		this.dependencias.add(dependencia);
	}

}

package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia;

import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CuponDescuento;

public interface IEstrategiaGeneracionCupones {

	Collection<CuponDescuento> generarCupones(IVenta venta);

}

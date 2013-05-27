package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ProductoSinRubroAsignado;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ProductoSinMarcaAsignada;

public interface IProducto extends Identificable{

	double getPrecioUnitario();
	
	IRubro getRubro() throws ProductoSinRubroAsignado;

	IMarca getMarca() throws ProductoSinMarcaAsignada;
}

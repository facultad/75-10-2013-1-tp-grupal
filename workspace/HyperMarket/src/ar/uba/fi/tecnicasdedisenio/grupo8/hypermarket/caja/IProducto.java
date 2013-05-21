package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public interface IProducto extends Identificable{

	double getPrecioUnitario();
	
	IRubro getRubro();

	IMarca getMarca();

}

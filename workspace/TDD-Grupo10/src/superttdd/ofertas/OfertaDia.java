package superttdd.ofertas;

import java.util.Calendar;
import java.util.List;

import superttdd.caja.DiaSemana;
import superttdd.producto.IProducto;

public class OfertaDia extends Oferta {

	List<DiaSemana> diasSemana;
	
	public OfertaDia(Double porcentajeDescuento, List<DiaSemana> diasSemana) {
		super(porcentajeDescuento);
		this.diasSemana = diasSemana;
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		List<IProducto> prodsAplican = this.filtrarProductos(productos);
		if(hoyEsDiaDePromo()) {
			for(IProducto producto: prodsAplican) {
				producto.addPorcentajeDescuento(porcentajeDescuento);
			}
		}
	}

	@Override
	public List<IProducto> filtrarProductos(List<IProducto> productos) {
		return productos;
	}

	public Boolean hoyEsDiaDePromo() {
		int nroDiaHoy = Calendar.DAY_OF_WEEK;
		Boolean enPromo = false;
		for(DiaSemana diaSem: diasSemana) {
			enPromo = (enPromo || (diaSem.mismoDia(nroDiaHoy)));
		}
		return enPromo;
	}
}

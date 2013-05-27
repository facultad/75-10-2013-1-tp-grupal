package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Producto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.ICondicionPromocion;

public class AceptacionTest {

	@Test
	public void testAceptacion1() {
		fail("Not yet implemented");
		
		

		/*
		Dado que: 
		- es Jueves. 
		- Existe promo Coca lleve 2 y pague 1. 
		- La coca sale 1 peso. 
		- Hay descuento 10% para Tarjeta XYZ los dias Jueves. 
		- Cepillo dientes sale 3 pesos. 
		- Maceta sale 10 pesos.

		Cuando: 
		- Se realiza una venta de 2 cocas, un cepillo de dientes y una maceta. 
		Pagando con Tarjeta XYZ. 

		Entonces: 
		- El precio final de la venta debe ser: 
		(1 Peso (2 cocas) + 3 pesos (cepillo) + 10 pesos (maceta)) * 0.90 (descuento tarjeta) 
		- Los descuentos aplicados son: 1 peso por promo 2x1 coca, 10% total por pago con tarjeta XYZ. 
		*/
		IVenta ventaJuevesConMedioPagoXYZ=crearVentaJuevesConMedioPagoXYZ();
		IProducto coca=new Producto(1,1);
		ICondicionPromocion condicionEsCoca=new CondicionProducto(coca);
		IPromocion promocionCoca2x1=new Promocion(condicionEsCoca, 2, 0.5);
		ICondicionPromocion condicionEsJuevesYMedioPagoTarjetaXYZ=
				this.crearCondicionDiaJuevesYMedioPagoTarjetaXYZ();
		IPromocion promocionTarjetaXYZ=new Promocion(condicionEsJuevesYMedioPagoTarjetaXYZ,1,0.1);
		IProducto cepilloDientes=new Producto(2,3);
		IProducto maceta=new Producto(3,10);
		
		RepositorioPromociones promociones=new RepositorioPromociones();
		promociones.add(promocionCoca2x1);
		promociones.add(promocionTarjetaXYZ);
		
		IItemVenta itemCoca=new ItemVenta(coca, 2);
		IItemVenta itemCepillo=new ItemVenta(cepilloDientes, 1);
		IItemVenta itemMaceta=new ItemVenta(maceta, 1);
		
		ventaJuevesConMedioPagoXYZ.addItem(itemCoca);
		ventaJuevesConMedioPagoXYZ.addItem(itemCepillo);
		ventaJuevesConMedioPagoXYZ.addItem(itemMaceta);
		
		assertEquals(15, ventaJuevesConMedioPagoXYZ.getImporteTotal(), 0);
		assertEquals(1+13*0.9, ventaJuevesConMedioPagoXYZ.getImporteTotalConDescuento(promociones), 0);

	}
	
	private IVenta crearVentaJuevesConMedioPagoXYZ() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	private ICondicionPromocion crearCondicionDiaJuevesYMedioPagoTarjetaXYZ() {
		throw new UnsupportedOperationException();
	}

	private ICondicionPromocion crearCondicionMedioPagoTarjetaXYZ() {
		throw new UnsupportedOperationException();
	}

	private ICondicionPromocion crearCondicionDiaJueves() {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testAceptacion2() {
		fail("Not yet implemented");

		/*
		2) 
		Dado que: 
		- es Lunes. 
		- Existe promo 2da unidad igual 75% desc en Vinoteca. (Excepto marcas Chandon) 
		- El vino X sale 100 pesos. El chandon sale $75 
		- Hay descuento 10% para Tarjeta de debito el Lunes 
		- Cepillo dientes sale 3 pesos. 
		- Maceta sale 10 pesos. 

		Cuando: 
		- Se realiza una venta de 2 vinos X y 2 Chandon, un cepillo de dientes y una maceta. 
		Pagando con Tarjeta Debito 

		Entonces: 
		- El precio final de la venta debe ser: 
		(100+25 Pesos (2 vinos X) + 75*2 pesos de Chandon 3 pesos (cepillo) + 10 pesos (maceta)) * 0.90 (descuento tarjeta)
		- Los descuentos aplicados son: 75 pesos por 2 vinos X promo vinoteca, 10% total por pago con tarjeta debito.
		*/ 

	}

	@Test
	public void testAceptacion3() {
		fail("Not yet implemented");

		/*
		3) 
		Dado que: 
		- Existe promo descuento 10% en vinos, excepto Marca XXZ y Marca LLL. 
		- El vino ELL sale $10, el XXZ sale $20, el LLL sale $30. 

		Cuando: 
		- Se realiza una venta de: 2 vinos ELL, 1 de XXZ y 2 de LLL. 
		Pagando en efectivo. 

		Entonces: 
		- El precio final de la venta debe ser: 
		($10 * 2 (2 ELL)) * 0.90 (descuento del 10) + $20 XXZ + $30 LLL. 
		- Los descuentos aplicados son: $2 por promo descuento del 10% en vinos.
		*/ 
	}

}

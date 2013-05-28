package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMarca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IRubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Marca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.MedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Producto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Rubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Sucursal;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Venta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionAND;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionDiaSemana;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionMarca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionMedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionRubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.ICondicionPromocion;

public class AceptacionTest {

	@Test
	public void testAceptacion1() {
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
		
		assertEquals(15, ventaJuevesConMedioPagoXYZ.getImporteTotalSinDescuento(), 0);
		assertEquals(1+13*0.9, ventaJuevesConMedioPagoXYZ.getImporteTotalConDescuento(promociones), 0);

	}
	
	private IVenta crearVentaJuevesConMedioPagoXYZ() {
		throw new UnsupportedOperationException();
	}

	private ICondicionPromocion crearCondicionDiaJuevesYMedioPagoTarjetaXYZ() {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testAceptacion2() {
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
		
		IMedioPago debito=new MedioPago(1);
		IVenta venta=new Venta(new Sucursal(1), debito);
		venta.setFechaVenta(new Date(2013,5,27));
		
		IRubro vinoteca=new Rubro("vinoteca", 1);
		ICondicionPromocion condicionEsDeVinoteca=new CondicionRubro(vinoteca);
		IPromocion promocionSegundaUnidadVinoteca75=new Promocion(condicionEsDeVinoteca, 2, .75/2);
		
		IProducto vino=new Producto(2,100,vinoteca);
		IProducto chandon=new Producto(3,75,vinoteca);
		IProducto cepilloDientes=new Producto(4,3);
		IProducto maceta=new Producto(5,10);
		
		CondicionAND condicionDebitoLunes=new CondicionAND();
		condicionDebitoLunes.agregarCondicion(new CondicionMedioPago(debito));
		condicionDebitoLunes.agregarCondicion(
				new CondicionDiaSemana(CondicionDiaSemana.DiaSemana.LUNES));
		IPromocion promocionDebitoLunes10Porciento=new Promocion(condicionDebitoLunes, 1, .1);
		
		venta.addItem(new ItemVenta(vino, 2));
		venta.addItem(new ItemVenta(chandon, 2));
		venta.addItem(new ItemVenta(cepilloDientes, 1));
		venta.addItem(new ItemVenta(maceta, 1));
		
		RepositorioPromociones promociones=new RepositorioPromociones();
		promociones.add(promocionDebitoLunes10Porciento);
		promociones.add(promocionSegundaUnidadVinoteca75);
		
		assertEquals(2*100+2*75+3+10, venta.getImporteTotalSinDescuento(),0);
		assertEquals(100+100*.25+75+75*.25+3*.9+10*.9, venta.getImporteTotalConDescuento(promociones),0);
	}

	@Test
	public void testAceptacion3() {
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
		IRubro rubroVinos=new Rubro(1);
		IMarca marcaXYZ=new Marca("XYZ", 1);
		IMarca marcaLLL=new Marca("LLL", 2);
		ICondicionPromocion condicionVino=new CondicionRubro(rubroVinos);
		ICondicionPromocion condicionNoXYZ=new CondicionMarca(marcaXYZ);
		condicionNoXYZ.negar();
		ICondicionPromocion condicionNoLLL=new CondicionMarca(marcaLLL);
		condicionNoLLL.negar();
		CondicionAND condicionVinoYNoXYZYNoLLL=new CondicionAND();
		condicionVinoYNoXYZYNoLLL.agregarCondicion(condicionVino);
		condicionVinoYNoXYZYNoLLL.agregarCondicion(condicionNoXYZ);
		condicionVinoYNoXYZYNoLLL.agregarCondicion(condicionNoLLL);
		
		IPromocion promocionVinoYNoXYZYNoLLL10=new Promocion(condicionVinoYNoXYZYNoLLL, 1, .1);
		
		IProducto vinoELL=new Producto(1,10,new Marca("ELL", 3),rubroVinos);
		IProducto vinoXYZ=new Producto(1,20,marcaXYZ,rubroVinos);
		IProducto vinoLLL=new Producto(1,30,marcaLLL,rubroVinos);
		
		IVenta venta=new Venta(new Sucursal(1), new MedioPago(1));
		venta.addItem(new ItemVenta(vinoELL, 2));
		venta.addItem(new ItemVenta(vinoXYZ, 1));
		venta.addItem(new ItemVenta(vinoLLL, 2));
		
		RepositorioPromociones promociones=new RepositorioPromociones();
		promociones.add(promocionVinoYNoXYZYNoLLL10);

		assertEquals(10*2+20+30*2, venta.getImporteTotalSinDescuento(),0);
		assertEquals(10*2*.9+20+30*2, venta.getImporteTotalConDescuento(promociones),0);
	}

}

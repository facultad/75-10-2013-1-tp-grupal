package superttdd.test.promociones;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;

import superttdd.comprobante.Factura;
import superttdd.promociones.CuponDescuento;

public class CuponDescuentoTest {
	
	private static Double MONTO_CUPON = 10.0;
	private static Double LIMITE_MAX_DESC = 25.0;

	
	private CuponDescuento cuponDescuento;
	private Factura factura;
	
	@Before
	public void setUp() {
		cuponDescuento = new CuponDescuento(MONTO_CUPON, LIMITE_MAX_DESC);
		factura = mock(Factura.class);
	}
	
	
	@Test
	public void seAplicaElMontoTotalDelCupon() {
		Double montoInicial = 100.0;
	
		when(factura.getMontoTotalConDescuentos()).thenReturn(montoInicial);	
		cuponDescuento.aplicarDescuento(factura);
		
		verify(factura, times(1)).descontarMonto(MONTO_CUPON);
	}
	
	@Test
	public void seAplicaElPorcentajaMaximoDelCupon() {
		Double montoInicial = 2.0 * MONTO_CUPON;
		Double montoADescontar = montoInicial * (LIMITE_MAX_DESC / 100.0);
		
		when(factura.getMontoTotalConDescuentos()).thenReturn(montoInicial);	
		cuponDescuento.aplicarDescuento(factura);
		
		verify(factura, times(1)).descontarMonto(montoADescontar);
	}

}


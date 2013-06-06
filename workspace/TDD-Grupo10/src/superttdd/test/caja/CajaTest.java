package superttdd.test.caja;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.Caja;

public class CajaTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=RuntimeException.class)
	public void AbrirCajaCuandoLaMismaYaFueAbiertaSinComprasIniciadas() throws RuntimeException {
		Caja caja = new Caja();
		caja.abrirCaja();
		caja.abrirCaja();
	}
	
	@Test(expected=RuntimeException.class)
	public void CerrarCajaCuandoLaMismaEstabaCerradaSinComprasIniciadas() throws RuntimeException {
		Caja caja = new Caja();
		caja.abrirCaja();
		caja.cerrarCaja();
		caja.cerrarCaja();
	}

}

package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.test;

import org.junit.Test;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.*;
import static org.junit.Assert.*;

public class IdGeneratorTest {

	@Test
	public void testGeneradorID() {
		Id id1 = new Id();
		Id id2 = new Id();
		assertFalse(id1==id2);
		assertEquals(id1,id1);
	}
	
}

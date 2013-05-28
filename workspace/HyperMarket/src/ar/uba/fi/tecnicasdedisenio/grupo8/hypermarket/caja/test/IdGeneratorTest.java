package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.test;

import org.junit.Test;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.*;
import static org.junit.Assert.*;

public class IdGeneratorTest {

	@Test
	public void testGeneradorID() {
		long id1 = IdGenerator.getInstance().getNewId();
		long id2 = IdGenerator.getInstance().getNewId();
		long id3 = IdGenerator.getInstance().getNewId();
		assertFalse(id1==id2);
		assertFalse(id1==id3);
		assertFalse(id2==id3);
		assertEquals(id1,id1);
	}
	
}

package ar.uba.fi.tecnicas.dojo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

public class DojoTest {

	@Test
	public void testNumeroUno() {
		int entero = 1;
		ConversorNumerico c = new ConversorNumerico();
		String roman = c.fromIntToRoman(entero);
		assertEquals(roman, "I");
	}

	@Test
	public void testNumeroCuatro() {
		int entero = 4;
		ConversorNumerico c = new ConversorNumerico();
		String roman = c.fromIntToRoman(entero);
		assertEquals("IV", roman);
	}
	
	@Test
	public void testNumeroCinco() {
		int entero = 5;
		ConversorNumerico c = new ConversorNumerico();
		String roman = c.fromIntToRoman(entero);
		assertEquals("V", roman);
	}
	
	
	@Test
	public void testNumeroOcho() {
		int entero = 8;
		ConversorNumerico c = new ConversorNumerico();
		String roman = c.fromIntToRoman(entero);
		assertEquals(roman, "VIII");
	}

	@Test
	public void testNumeroNueve() {
		int entero = 9;
		ConversorNumerico c = new ConversorNumerico();
		String roman = c.fromIntToRoman(entero);
		assertEquals(roman, "IX");
	}
	

	@Test
	public void testNumero45() {
		int entero = 45;
		ConversorNumerico c = new ConversorNumerico();
		String roman = c.fromIntToRoman(entero);
		assertEquals(roman, "XLV");
	}
	
	@Test
	public void testNumeroNumeroNegativo_retornaExcep() {
		try {
			int entero = -8;
			ConversorNumerico c = new ConversorNumerico();
			String roman = c.fromIntToRoman(entero);
			assertTrue(false);
			
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}

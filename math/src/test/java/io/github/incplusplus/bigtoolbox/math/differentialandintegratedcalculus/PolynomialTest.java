package io.github.incplusplus.bigtoolbox.math.differentialandintegratedcalculus;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest
{
	private final Polynomial Lily = new Polynomial(4, new Term[]{new X(new BigDecimal(4), 4), new X(new BigDecimal(30), 3), new X(new BigDecimal(12), 1), new Constant(new BigDecimal(6))}, 4);
	private final Polynomial Lily_original = new Polynomial(3, new Term[]{new X(new BigDecimal(4), 4), new X(new BigDecimal(30), 3), new X(new BigDecimal(12), 1)}, 4);
	private final Polynomial Melvin = new Polynomial(2, new Term[]{new X(new BigDecimal(3), 3), new Constant(new BigDecimal(8))}, 3);

	@Test
	void f()
	{
	}

	@Test
	void f_integrated()
	{
	}

	@Test
	void getFormula()
	{
		assertEquals("(4x^4) + (30x^3) + (12x^1) + 6", Lily.getFormula());
		assertEquals("(4x^4) + (30x^3) + (12x^1)", Lily_original.getFormula());
		assertEquals("(3x^3) + 8", Melvin.getFormula());
	}

	@Test
	void getIntegratedFormula()
	{
		assertEquals("(4/5x^5) + (30/4x^4) + (12/2x^2) + 6x", Lily.getIntegratedFormula());
		assertEquals("(4/5x^5) + (30/4x^4) + (12/2x^2)", Lily_original.getIntegratedFormula());
		assertEquals("(3/4x^4) + 8x", Melvin.getIntegratedFormula());
	}
}
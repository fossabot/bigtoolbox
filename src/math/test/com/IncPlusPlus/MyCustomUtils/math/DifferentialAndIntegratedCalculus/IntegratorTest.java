package com.IncPlusPlus.math.DifferentialAndIntegratedCalculus;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegratorTest
{
	private final Integrator Lily = new Integrator(new BigDecimal(- 50), new BigDecimal(50), 1000, new Polynomial(4, new Term[]{new X(new BigDecimal(4), 4), new X(new BigDecimal(30), 3), new X(new BigDecimal(12), 1), new Constant(new BigDecimal(6))}, 4));
	private final Integrator Lily_original = new Integrator(new BigDecimal(-50), new BigDecimal(50), 1000, new Polynomial(3, new Term[]{new X(new BigDecimal(4), 4), new X(new BigDecimal(30), 3), new X(new BigDecimal(12), 1)}, 4));
	private final Integrator Melvin = new Integrator(new BigDecimal(1), new BigDecimal(5), 20, new Polynomial(2, new Term[]{new X(new BigDecimal(3), 3), new Constant(new BigDecimal(8))}, 3));

	@Test
	void midpointRule()
	{
		assertEquals(Lily.midpointRule().intValue(), 499998933);
	}

	@Test
	void trapezoidRule()
	{
		assertEquals(Lily.trapezoidRule().intValue(), 500003933);
	}

	@Test
	void simpsonsRule()
	{
		assertEquals(Lily.simpsonsRule().intValue(), 500000600);
	}

	@Test
	void exactValue()
	{
		assertTrue(Lily.exactValue().compareTo(new BigDecimal("500.0006E+6")) == 0);
	}

	@Test
	void percentError()
	{
	}
}
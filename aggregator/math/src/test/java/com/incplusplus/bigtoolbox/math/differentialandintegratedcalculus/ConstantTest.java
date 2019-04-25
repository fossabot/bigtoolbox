package com.incplusplus.bigtoolbox.math.differentialandintegratedcalculus;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstantTest
{
	private final int posVal = 50;
	private final int negVal = -50;
	private final int zeroVal = 0;
	
	@Test
	void eval()
	{
		Constant testConstant = new Constant(new BigDecimal(posVal));
		assertEquals(testConstant.eval(0).compareTo(new BigDecimal(posVal)), zeroVal);

		testConstant = new Constant(new BigDecimal(negVal));
		assertEquals(testConstant.eval(0).compareTo(new BigDecimal(negVal)), zeroVal);

		testConstant = new Constant(new BigDecimal(zeroVal));
		assertEquals(testConstant.eval(0).compareTo(new BigDecimal(zeroVal)), zeroVal);
	}

	@Test
	void integration()
	{
		Constant testConstant = new Constant(new BigDecimal(posVal));
		assertEquals(testConstant.integration(posVal).compareTo(new BigDecimal(posVal).pow(2)), zeroVal);

		testConstant = new Constant(new BigDecimal(negVal));
		assertEquals(testConstant.integration(negVal).compareTo(new BigDecimal(negVal).pow(2)), zeroVal);

		testConstant = new Constant(new BigDecimal(zeroVal));
		assertEquals(testConstant.integration(zeroVal).compareTo(new BigDecimal(zeroVal).pow(2)), zeroVal);
	}

	@Test
	void getFormulaRepresentation()
	{
		Constant testConstant = new Constant(new BigDecimal(posVal));
		assertEquals("50", testConstant.getFormulaRepresentation());

		testConstant = new Constant(new BigDecimal(zeroVal));
		assertEquals("", testConstant.getFormulaRepresentation());

		testConstant = new Constant(new BigDecimal(negVal));
		assertEquals("(-50)", testConstant.getFormulaRepresentation());
	}

	@Test
	void getIntegratedRepresentation()
	{
		Constant testConstant = new Constant(new BigDecimal(posVal));
		assertEquals("50x", testConstant.getIntegratedRepresentation());

		testConstant = new Constant(new BigDecimal(zeroVal));
		assertEquals("", testConstant.getIntegratedRepresentation());

		testConstant = new Constant(new BigDecimal(negVal));
		assertEquals("(-50x)", testConstant.getIntegratedRepresentation());
	}
}
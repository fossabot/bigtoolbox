package io.github.incplusplus.bigtoolbox.math.differentialandintegratedcalculus;

import java.math.BigDecimal;

/**
 * @author Ryan Cloherty
 */
public interface Function
{
	/**
	 * Evaluation of the polynomial at a certain x value
	 *
	 * @param x the value to substitute for x in the polynomial
	 * @return the y value of the polynomial at x
	 */
	BigDecimal f(BigDecimal x);

	/**
	 * Evaluation of the derivative of the polynomial at a certain x value
	 *
	 * @param x the value to substitute for x in the polynomial's integration
	 * @return the y value of the integration of the polynomial at x
	 */
	BigDecimal f_derived(BigDecimal x);

	/**
	 * Evaluation of the integration of the polynomial at a certain x value
	 *
	 * @param x the value to substitute for x in the polynomial's integration
	 * @return the y value of the integration of the polynomial at x
	 */
	BigDecimal f_integrated(BigDecimal x);

	/**
	 * @return a String representing the original, unevaluated form of this polynomial
	 */
	String getFormula();

	/**
	 * @return a String representing the unevaluated integral form of this polynomial
	 */
	String getIntegratedFormula();
}

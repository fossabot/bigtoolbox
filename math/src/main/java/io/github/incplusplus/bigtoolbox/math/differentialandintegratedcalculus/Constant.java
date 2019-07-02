package io.github.incplusplus.bigtoolbox.math.differentialandintegratedcalculus;

import java.math.BigDecimal;

/**
 * @author Ryan Cloherty
 */
public class Constant extends Term
{
	BigDecimal value;

	public Constant(BigDecimal value)
	{
		this.value = value;
	}

	/**
	 * Returns a BigDecimal value containing the evaluation of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the evaluation of this term
	 */
	@Override
	public BigDecimal eval(BigDecimal in)
	{
		return value;
	}

	/**
	 * Returns a BigDecimal value containing the integration of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the integration of this term
	 */
	@Override
	public BigDecimal integration(BigDecimal in)
	{
		return in.multiply(value);
	}

	/**
	 * Returns a BigDecimal value containing the derivation of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the derivation of this term
	 */
	@Override
	public BigDecimal derivation(BigDecimal in)
	{
		return BigDecimal.ZERO;
	}

	/**
	 * @return a String representing the original, unevaluated form of this term
	 */
	@Override
	public String getFormulaRepresentation()
	{
		if(value.compareTo(BigDecimal.ZERO) > 0)
		{
			return value.toString();
		}
		else if(value.compareTo(BigDecimal.ZERO) == 0)
		{
			//There's no need to print a +0 at the end
			return "";
		}
		else
		{
			//Surround with parenthesis because there will be a "-" indicating a negative sign
			return "(" + value.toString() + ")";
		}
	}

	/**
	 * @return a String representing the integrated form of the term without any numerical changes
	 */
	@Override
	public String getIntegratedRepresentation()
	{
		if(value.compareTo(BigDecimal.ZERO) > 0)
		{
			return value.toString() + "x";
		}
		else if(value.compareTo(BigDecimal.ZERO) == 0)
		{
			//There's no need to print a +0 at the end
			return "";
		}
		else
		{
			//Surround with parenthesis because there will be a "-" indicating a negative sign
			return "(" + value.toString() + "x" + ")";
		}
	}
}

package com.IncPlusPlus.MyCustomUtils.math.DifferentialAndIntegratedCalculus;

import java.math.BigDecimal;

/**
 * @author Ryan Cloherty
 */
public class X extends Term
{
	/* TODO make sure that the case of the integration where n+1 would equal zero gets accounted for.
	 *  answer would be ln(abs(x)) or something like that
	 * Make the integral be ln(abs(x_var)) instead of (1/(n+1))(coef)x^(n+1)
	 */

	private BigDecimal coefficient;
	private int exponent;

	public X(BigDecimal coefficient, int exponent)
	{
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	/**
	 * @return this X's exponent
	 */
	public int getExponent()
	{
		return exponent;
	}

	/**
	 * @return this X's coefficient
	 */
	public BigDecimal getCoefficient()
	{
		return coefficient;
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
		return in.pow(exponent).multiply(coefficient);
	}

	/**
	 * Returns a BigDecimal value containing the integration of this term
	 * This looks messy and it sorta is. Basically, the integration formula of Ax^B
	 * is (A/(B+1))x^(B+1)
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the integration of this term
	 */
	@Override
	public BigDecimal integration(BigDecimal in)
	{
		return in.pow(exponent + 1).multiply(coefficient.divide(new BigDecimal(exponent + 1), Term.mc));
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
		return in.pow(exponent-1).multiply(coefficient).multiply(new BigDecimal(exponent-1));
	}

	/**
	 * @return a String representing the original, unevaluated form of this term
	 */
	@Override
	public String getFormulaRepresentation()
	{
		//Test if the coefficient is 0. If it is, then we don't need to mention this x term
		if(coefficient.compareTo(BigDecimal.ZERO) != 0)
		{
			if(exponent == 0)
			{
				return coefficient.toString() + "x";
			}
			else
			{
				return "(" + coefficient.toString() + "x^" + exponent + ")";
			}
		}
		return "";
	}

	/**
	 * @return the integrated form of this x term
	 */
	@Override
	public String getIntegratedRepresentation()
	{
		if(coefficient.compareTo(BigDecimal.ZERO) != 0)
		{
			return "(" + coefficient.toString() + "/" + (exponent + 1) + "x^" + (exponent + 1) + ")";
		}
		else return "";
	}
}
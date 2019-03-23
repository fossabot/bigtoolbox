package com.incplusplus.bigtoolbox.math.differentialandintegratedcalculus;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Ryan Cloherty
 */
public class Integrator
{
	private MathContext mc = Term.mc;
	private BigDecimal lowerLimit;
	private BigDecimal upperLimit;
	private int subintervals;
	private Function p;
	private BigDecimal deltaX;
	private BigDecimal midpointResult;
	private BigDecimal trapezoidResult;
	private BigDecimal simpsonsResult;
	private BigDecimal exactResult;

	public Integrator(BigDecimal lowerLimit, BigDecimal upperLimit, int subintervals, Function p)
	{
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.subintervals = subintervals;
		this.p = p;

		deltaX = upperLimit.subtract(lowerLimit).divide(new BigDecimal(subintervals), mc);
	}

	public BigDecimal midpointRule()
	{
		if(midpointResult != null)
		{
			return midpointResult;
		}

		//System.out.println("Calculating midpoint rule...");
		BigDecimal out = BigDecimal.ZERO;
		for(int i = 1; i <= subintervals; i++)
		{
			//Left side of the rectangle
			BigDecimal leftSide = lowerLimit.add(deltaX.multiply(new BigDecimal(i - 1)));
			//Right side of the rectangle
			BigDecimal rightSide = lowerLimit.add(deltaX.multiply(new BigDecimal(i)));
			//Add f(x) where x is the midpoint of each rectangle
			BigDecimal x = (leftSide).add(rightSide).divide(new BigDecimal(2), mc);
			out = out.add(p.f(x));
		}
		//Finally, multiply these heights by their width (deltaX)
		out = out.multiply(deltaX, mc);
		out = out.stripTrailingZeros();
		midpointResult = out;
		return out;
	}

	public BigDecimal trapezoidRule()
	{
		if(trapezoidResult != null)
		{
			return trapezoidResult;
		}

		//System.out.println("Calculating trapezoid rule...");
		BigDecimal out = BigDecimal.ZERO;
		for(int i = 1; i <= subintervals; i++)
		{
			//Top of the trapezoid
			BigDecimal base1 = lowerLimit.add(deltaX.multiply(new BigDecimal(i - 1)));
			//Bottom of the trapezoid
			BigDecimal base2 = lowerLimit.add(deltaX.multiply(new BigDecimal(i)));
			//Add f(b1) + f(b2) for trapezoid i to the sums
			out = out.add(p.f(base1).add(p.f(base2)));
		}
		//Multiply the base sums by (1/2)*height
		out = out.multiply(new BigDecimal("0.5").multiply(deltaX, mc), mc);
		out = out.stripTrailingZeros();
		trapezoidResult = out;
		return out;
	}

	public BigDecimal simpsonsRule()
	{
		if(simpsonsResult != null)
		{
			return simpsonsResult;
		}

		//System.out.println("Calculating Simpson's rule...");
		if(midpointResult == null)
		{
			midpointResult = midpointRule();
		}
		if(trapezoidResult == null)
		{
			trapezoidResult = trapezoidRule();
		}
		BigDecimal out = new BigDecimal(2).multiply(midpointResult);
		out = out.add(trapezoidResult);
		out = out.divide(new BigDecimal(3), mc);
		out = out.stripTrailingZeros();
		simpsonsResult = out;
		return out;
	}

	public BigDecimal exactValue()
	{
		if(exactResult != null)
		{
			return exactResult;
		}

		//System.out.println("Calculating exact value...");
		exactResult = p.f_integrated(upperLimit).subtract(p.f_integrated(lowerLimit)).stripTrailingZeros();
		return exactResult;
	}

	public BigDecimal percentError(BigDecimal acceptedValue, BigDecimal experimentalValue)
	{
		BigDecimal out = BigDecimal.ZERO;
		out = experimentalValue.subtract(acceptedValue);
		out = out.abs();
		out = out.divide(acceptedValue, mc);
		out = out.multiply(new BigDecimal(100));
		return out.stripTrailingZeros();
	}
}
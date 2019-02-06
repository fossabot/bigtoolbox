package com.IncPlusPlus.MyCustomUtils.math;

import java.math.BigDecimal;
import java.util.Scanner;
import com.IncPlusPlus.MyCustomUtils.math.DifferentialAndIntegratedCalculus.*;

/**
 * @author Ryan Cloherty
 */
public class Main
{
	//TODO add more functions and terms
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		BigDecimal lowerLimit;
		BigDecimal upperLimit;
		int subintervals;
		Polynomial myPolynomial;
		Integrator myIntegrator;

		lowerLimit = Term.getBigDecimalAnswer("lowerLimit", in);
		upperLimit = Term.getBigDecimalAnswer("upperLimit", in);
		if(upperLimit.compareTo(lowerLimit) < 0)
		{
			System.out.println("WARNING: lowerLimit is greater than upperLimit");
		}
		subintervals = Term.getIntAnswerGreaterThan("subintervals", in, 0);
		myPolynomial = new Polynomial(in);
		myIntegrator = new Integrator(lowerLimit, upperLimit, subintervals, myPolynomial);

		System.out.println("#########################################################");
		System.out.println("f(x) = " + myPolynomial.getFormula());
		System.out.println();
		System.out.println("\u222B" + "f(x) = " + myPolynomial.getIntegratedFormula());
		System.out.println("#########################################################");

		System.out.println("Midpoint rule: " + myIntegrator.midpointRule().toEngineeringString());
		System.out.println("Error: " + myIntegrator.percentError(myIntegrator.exactValue(), myIntegrator.midpointRule()).toEngineeringString() + "%");
		System.out.println();
		System.out.println("Trapezoid rule: " + myIntegrator.trapezoidRule().toEngineeringString());
		System.out.println("Error: " + myIntegrator.percentError(myIntegrator.exactValue(), myIntegrator.trapezoidRule()).toEngineeringString() + "%");
		System.out.println();
		System.out.println("Simpson's rule: " + myIntegrator.simpsonsRule().toEngineeringString());
		System.out.println("Error: " + myIntegrator.percentError(myIntegrator.exactValue(), myIntegrator.simpsonsRule()).toEngineeringString() + "%");
		System.out.println();
		System.out.println("Exact value: " + myIntegrator.exactValue().toEngineeringString());
	}
}
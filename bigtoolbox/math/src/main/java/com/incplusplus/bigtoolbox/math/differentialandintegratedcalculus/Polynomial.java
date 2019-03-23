package com.incplusplus.bigtoolbox.math.differentialandintegratedcalculus;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Ryan Cloherty
 */
public class Polynomial implements Function
{
	private int numTerms;
	private Term[] terms;
	private int degree;

	public Polynomial(Scanner in)
	{
		System.out.println("Beginning polynomial setup!");

		numTerms = Term.getIntAnswerGreaterThan("degree of polynomial", in, - 1) + 1;
		degree = numTerms - 1;
		terms = new Term[numTerms];
		//Start with the letter 'A'
		char letter = (char)65;
		for(int i = degree; i > 0; i--)
		{
			letter = (char)(65 + (degree - i));
			System.out.println(letter + "x^" + i);
			terms[degree - i] = new X(Term.getBigDecimalAnswer(String.valueOf(letter), in), i);
		}
		letter++;
		System.out.println(letter);
		//The last term should always be a constant
		terms[terms.length - 1] = new Constant(Term.getBigDecimalAnswer(String.valueOf(letter), in));

		System.out.println("Polynomial setup complete!");
	}

	public Polynomial(int numTerms, Term[] terms, int degree)
	{
		if(terms.length != numTerms)
		{
			System.out.println("numTerms != terms.length!!!!!");
			System.out.println("Length supposedly is " + numTerms);
			System.out.println("Length is actually " + terms.length);
			System.exit(0);
		}
		for(Term i : terms)
		{
			if(i instanceof X)
			{
				if(((X)i).getExponent() > degree)
				{
					System.out.println("A term in terms has a degree that exceeds the maximum expected degree!!!!!");
					System.exit(1);
				}
			}
		}
		this.numTerms = numTerms;
		this.terms = terms;
		this.degree = degree;
	}

	public BigDecimal f(BigDecimal x)
	{
		BigDecimal out = BigDecimal.ZERO;
		for(Term i : terms)
		{
			out = out.add(i.eval(x));
		}
		return out;
	}

	/**
	 * Evaluation of the derivative of the polynomial at a certain x value
	 *
	 * @param x the value to substitute for x in the polynomial's integration
	 * @return the y value of the integration of the polynomial at x
	 */
	@Override
	public BigDecimal f_derived(BigDecimal x)
	{
		BigDecimal out = BigDecimal.ZERO;
		for(Term i : terms)
		{
			out = out.add(i.derivation(x));
		}
		return out;
	}

	public BigDecimal f_integrated(BigDecimal x)
	{
		BigDecimal out = BigDecimal.ZERO;
		for(Term i : terms)
		{
			out = out.add(i.integration(x));
		}
		return out;
	}

	public String getFormula()
	{
		String out = "";
		for(int i = 0; i < terms.length; i++)
		{
			if(! terms[i].getFormulaRepresentation().equals(""))
			{
				out += terms[i].getFormulaRepresentation();
				if(i < terms.length - 1)
				{
					out += " + ";
				}
			}
		}
		//if(! terms[terms.length - 1].getFormulaRepresentation().equals(""))
		//{
		//	System.out.println(terms[terms.length - 1].getFormulaRepresentation());
		//	out += terms[terms.length - 1].getFormulaRepresentation();
		//}
		return out;
	}

	public String getIntegratedFormula()
	{
		String out = "";
		for(int i = 0; i < terms.length; i++)
		{
			if(! terms[i].getIntegratedRepresentation().equals(""))
			{
				out += terms[i].getIntegratedRepresentation();
				if(i < terms.length - 1)
				{
					out += " + ";
				}
			}
		}
		//if(! terms[terms.length - 1].getIntegratedRepresentation().equals(""))
		//{
		//	out += " + " + terms[terms.length - 1].getIntegratedRepresentation();
		//}
		return out;
	}
}
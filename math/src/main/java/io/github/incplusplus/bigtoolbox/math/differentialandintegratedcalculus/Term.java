package io.github.incplusplus.bigtoolbox.math.differentialandintegratedcalculus;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

/**
 * @author Ryan Cloherty
 */
public abstract class Term
{
	public static MathContext mc = MathContext.DECIMAL64;

	/**
	 * A method to reduce code duplication.
	 *
	 * @param variableName The name of the variable the user is providing a value for
	 * @param in           The Scanner to use for prompting
	 * @return a BigDecimal holding the value the user input
	 */
	public static BigDecimal getBigDecimalAnswer(String variableName, Scanner in)
	{
		System.out.print("Input val for " + variableName + ": ");
		BigDecimal out = BigDecimal.ZERO;
		try
		{
			out = new BigDecimal(in.nextLine());
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("A non-numeric string was provided for " + variableName);
			System.out.println("Quitting");
			System.exit(1);
		}
		return out;
	}

	/**
	 * A method to reduce code duplication.
	 *
	 * @param variableName The name of the variable the user is providing a value for
	 * @param in           The Scanner to use for prompting
	 * @return a BigDecimal holding the value the user input
	 */
	public static int getIntAnswer(String variableName, Scanner in)
	{
		System.out.print("Input val for " + variableName + ": ");
		int out = 0;
		try
		{
			out = in.nextInt();
			in.nextLine();
		}
		catch(Exception e)
		{
			System.out.println("A non-numeric string was provided for " + variableName);
			System.out.println("Quitting");
			System.exit(1);
		}
		return out;
	}

	public static int getIntAnswerGreaterThan(String variableName, Scanner in, int lowerBound)
	{
		int out = 0;
		out = getIntAnswer(variableName, in);
		while(out <= lowerBound)
		{
			System.out.println("Value must be greater than " + lowerBound);
			out = getIntAnswer(variableName, in);
		}
		return out;
	}

	public static boolean getYesOrNo(Scanner in)
	{
		boolean answer = false;
		System.out.print("[y]es/[n]o: ");
		switch(in.nextLine())
		{
			case "y":
				answer = true;
				break;
			case "Y":
				answer = true;
				break;
			case "n":
				answer = false;
				break;
			case "N":
				answer = false;
				break;
			default:
				answer = false;
				break;
		}
		return answer;
	}

	/**
	 * Returns a BigDecimal value containing the evaluation of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the evaluation of this term
	 */
	BigDecimal eval(int in)
	{
		return eval(new BigDecimal(in));
	}

	/**
	 * Returns a BigDecimal value containing the evaluation of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the evaluation of this term
	 */
	public abstract BigDecimal eval(BigDecimal in);

	/**
	 * Returns a BigDecimal value containing the integration of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the integration of this term
	 */
	BigDecimal integration(int in)
	{
		return integration(new BigDecimal(in));
	}

	/**
	 * Returns a BigDecimal value containing the integration of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the integration of this term
	 */
	public abstract BigDecimal integration(BigDecimal in);

	/**
	 * Returns a BigDecimal value containing the derivation of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the derivation of this term
	 */
	BigDecimal derivation(int in)
	{
		return derivation(new BigDecimal(in));
	}

	/**
	 * Returns a BigDecimal value containing the derivation of this term
	 *
	 * @param in the value to substitute for the variable present in this term.
	 * @return a BigDecimal value containing the derivation of this term
	 */
	public abstract BigDecimal derivation(BigDecimal in);

	/**
	 * Show a preview of what this term looks like before any mathematical representation
	 *
	 * @return a String representing the original, unevaluated form of this term
	 */
	public abstract String getFormulaRepresentation();

	/**
	 * Show a preview of what the integration of this term would look like before any mathematical evaluation
	 *
	 * @return a String representing the integrated form of the term without any numerical changes
	 */
	public abstract String getIntegratedRepresentation();
}

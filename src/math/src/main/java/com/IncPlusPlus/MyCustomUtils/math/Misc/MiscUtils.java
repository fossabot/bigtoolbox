package com.IncPlusPlus.MyCustomUtils.math.Misc;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

public class MiscUtils
{
	//BEGIN NUMBER MANIPULATION / READING UTILS
	@SuppressWarnings("unused")
	public static int length(int number)
	{
		return (int)(Math.log10(number) + 1);
	}

	@SuppressWarnings("unused")
	public static int length(long number)
	{
		return (int)(Math.log10(number) + 1);
	}

	public static int truncateLeftDigit(int n)
	{
		return n %= Math.pow(10, (int)Math.log10(n));
	}

	public static int truncateRightDigit(int n)
	{
		return n / 10;
	}

	public static int leftDigit(int n)
	{
		return (int)Math.floor(n / Math.pow(10, (int)Math.log10(n)));
	}

	public static int rightDigit(int n)
	{
		return n % 10;
	}

	public static boolean isPalindrome(int in)
	{
		int numberInQuestion = in;
		while(length(numberInQuestion) > 1)
		{
			if(leftDigit(numberInQuestion) != rightDigit(numberInQuestion))
			{
				return false;
			}
			else
			{
				if(length(truncateRightDigit(numberInQuestion)) - length(truncateLeftDigit(numberInQuestion)) != 0)
				{
					for(int i = 0; i < length(numberInQuestion) - length(truncateLeftDigit(numberInQuestion)); i++)
					{
						numberInQuestion = truncateRightDigit(numberInQuestion);
					}
					numberInQuestion = truncateLeftDigit(numberInQuestion);
				}
				else
				{
					numberInQuestion = truncateLeftDigit(numberInQuestion);
					numberInQuestion = truncateRightDigit(numberInQuestion);
				}
			}
		}
		return true;
	}
	//END NUMBER MANIPULATION / READING UTILS

	//BEGIN VARIOUS ALGEBRA FUNCTIONS
	public static int distance(int x1, int y1, int x2, int y2)
	{
		return distance(new BigDecimal(x1), new BigDecimal(y1), new BigDecimal(x2), new BigDecimal(y2));
	}

	public static int distance(BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2)
	{
		return (MiscUtils.sqrt((MiscUtils.pow(x2.subtract(x1), 2)).add(MiscUtils.pow(y2.subtract(y1), 2)))).intValue();
	}
	//END VARIOUS ALGEBRA FUNCTIONS

	//BEGIN EXPERIMENTAL BIGDECIMAL RELATED MATH FUNCTIONS
	//TODO Make a way for the implementing software to specify the MathContext
	private static MathContext mc = MathContext.DECIMAL128;

	/**
	 * The following functions are wrapper functions.
	 * I made them for the purpose of using easy function names
	 * such as sin(x) to work with BigDecimal objects.
	 * BigDecimal objects were used to lose as little precision as possible.
	 * Use the functions below with caution. They are experimental.
	 */

	public static BigDecimal sin(final BigDecimal in)
	{
		return BigDecimalMath.sin(in, mc);
	}

	public static BigDecimal cos(final BigDecimal in)
	{
		return BigDecimalMath.cos(in, mc);
	}

	public static BigDecimal tan(final BigDecimal in)
	{
		return BigDecimalMath.tan(in, mc);
	}

	public static BigDecimal cot(final BigDecimal in)
	{
		return BigDecimalMath.cot(in, mc);
	}

	public static BigDecimal exp(BigDecimal in)
	{
		return BigDecimalMath.exp(in, mc);
	}

	public static BigDecimal pow(BigDecimal in, long exponent)
	{
		return BigDecimalMath.pow(in, new BigDecimal(exponent), mc);
	}

	public static BigDecimal pow(BigDecimal in, BigDecimal exponent)
	{
		return BigDecimalMath.pow(in, exponent, mc);
	}

	public static BigDecimal sqrt(BigDecimal in)
	{
		return nRootBigDecimal(2, in);
	}

	public static BigDecimal cbrt(BigDecimal in)
	{
		return nRootBigDecimal(3, in);
	}

	public static BigDecimal nRootBigDecimal(final int n, BigDecimal in)
	{
		return BigDecimalMath.root(new BigDecimal(n), in, mc);
	}
	//END EXPERIMENTAL BIGDECIMAL RELATED MATH FUNCTIONS
}
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
		return n %= Math.pow(10, (int) Math.log10(n));
	}
	public static int truncateRightDigit(int n)
	{
		return n/10;
	}
	public static int leftDigit(int n)
	{
		return (int) Math.floor(n / Math.pow(10, (int) Math.log10(n)));
	}
	public static int rightDigit(int n)
	{
		return n%10;
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
		return (MiscUtils.sqrt((MiscUtils.pow(x2.subtract(x1),2)).add(MiscUtils.pow(y2.subtract(y1),2)))).intValue();
	}
	//END VARIOUS ALGEBRA FUNCTIONS

	//BEGIN EXPERIMENTAL BIGDECIMAL RELATED MATH FUNCTIONS
	private static MathContext mc = MathContext.DECIMAL128;
	/**
	 * The following functions are wrapper functions.
	 * I made them for the purpose of using easy function names
	 * such as sin(x) to work with BigDecimal objects.
	 * BigDecimal objects were used to lose as little precision as possible.
	 * Use the functions below with caution. They are experimental and usually fail.
	 */


	/**
	 * The following function is a wrapper function for a function created by Eric Obermühlner.
	 * The functions were created for the big-math repo
	 * The functions can be downloaded from https://github.com/eobermuhlner/big-math/raw/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigDecimalMath.java
	 * The repo can be viewed from https://github.com/eobermuhlner/big-math
	 */
	public static BigDecimal sin(final BigDecimal in)
	{
		return BigDecimalMath.sin(in, mc);
	}
	/**
	 * The following function is a wrapper function for a function created by Eric Obermühlner.
	 * The functions were created for the big-math repo
	 * The functions can be downloaded from https://github.com/eobermuhlner/big-math/raw/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigDecimalMath.java
	 * The repo can be viewed from https://github.com/eobermuhlner/big-math
	 */
	public static BigDecimal cos(final BigDecimal in)
	{
		return BigDecimalMath.cos(in, mc);
	}
	/**
	 * The following function is a wrapper function for a function created by Eric Obermühlner.
	 * The functions were created for the big-math repo
	 * The functions can be downloaded from https://github.com/eobermuhlner/big-math/raw/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigDecimalMath.java
	 * The repo can be viewed from https://github.com/eobermuhlner/big-math
	 */
	public static BigDecimal tan(final BigDecimal in)
	{
		return BigDecimalMath.tan(in, mc);
	}
	/**
	 * The following function is a wrapper function for a function created by Eric Obermühlner.
	 * The functions were created for the big-math repo
	 * The functions can be downloaded from https://github.com/eobermuhlner/big-math/raw/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigDecimalMath.java
	 * The repo can be viewed from https://github.com/eobermuhlner/big-math
	 */
	public static BigDecimal cot(final BigDecimal in)
	{
		return BigDecimalMath.cot(in, mc);
	}
	/**
	 * The following function is a wrapper function for a function created by Richard J. Mathar.
	 * The functions were created for the paper "A Java Math.BigDecimal Implementation of Core Mathematical Functions"
	 * The functions can be downloaded from https://arxiv.org/src/0908.3030v3/anc
	 * The paper can be viewed from https://arxiv.org/abs/0908.3030v3
	 */
	public static BigDecimal exp(BigDecimal in)
	{
		return org.nevec.rjm.BigDecimalMath.exp(in);
	}
	public static BigDecimal pow(BigDecimal in, int power)
	{
		return in.pow(power);
	}
	/**
	 * The following function is a wrapper function for a function created by Richard J. Mathar.
	 * The functions were created for the paper "A Java Math.BigDecimal Implementation of Core Mathematical Functions"
	 * The functions can be downloaded from https://arxiv.org/src/0908.3030v3/anc
	 * The paper can be viewed from https://arxiv.org/abs/0908.3030v3
	 */
	public static BigDecimal sqrt(BigDecimal in)
	{
		return org.nevec.rjm.BigDecimalMath.sqrt(in, mc);
	}
	/**
	 * The following function is a wrapper function for a function created by Richard J. Mathar.
	 * The functions were created for the paper "A Java Math.BigDecimal Implementation of Core Mathematical Functions"
	 * The functions can be downloaded from https://arxiv.org/src/0908.3030v3/anc
	 * The paper can be viewed from https://arxiv.org/abs/0908.3030v3
	 */
	public static BigDecimal cbrt(BigDecimal in)
	{
		return org.nevec.rjm.BigDecimalMath.cbrt(in);
	}
	/**
	 * The following function is a wrapper function for a function created by Richard J. Mathar.
	 * The functions were created for the paper "A Java Math.BigDecimal Implementation of Core Mathematical Functions"
	 * The functions can be downloaded from https://arxiv.org/src/0908.3030v3/anc
	 * The paper can be viewed from https://arxiv.org/abs/0908.3030v3
	 */
	public static BigDecimal nRootBigDecimal(final int n, BigDecimal in)
	{
		return org.nevec.rjm.BigDecimalMath.root(n, in);
	}
	//END EXPERIMENTAL BIGDECIMAL RELATED MATH FUNCTIONS
}
package io.github.incplusplus.bigtoolbox.math.misc;

public class AmicableMath
{
	/**
	 * a and b are an amicable pair if d(a) = b and d(b) = a, where a ≠ b
	 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
	 * @param a
	 * @param b
	 * @return true if a and b are an amicable pair
	 */
	public static boolean amicablePair(int a, int b)
	{
		return DivisorMath.sumProperDivisors(a) == b
				&& DivisorMath.sumProperDivisors(b) == a
				&& a != b;
	}

	/**
	 *
	 * @param a the number to make an amicable pair with
	 * @return a number that makes an amicable pair when paired with a. If none exists, returns 0
	 */
	public static int findAmicableNumber(int a)
	{
		int possibleAmicableHalf = DivisorMath.sumProperDivisors(a);
		return amicablePair(a, possibleAmicableHalf) ? possibleAmicableHalf : 0;
	}

	/**
	 *
	 * @param a the number to make an amicable pair with
	 * @param limit the number the other half of the pair can be no greater than
	 * @return a number that makes an amicable pair when paired with a.. If none exists or the number found is greater than the limit, returns 0
	 */
	public static int findAmicableNumber(int a, int limit)
	{
		int possiblePair = findAmicableNumber(a);
		return possiblePair < limit ? possiblePair : 0;
	}
	public static int sumOfAmicableNumbersUnder(int limit)
	{
		int upperBound = limit + 1;
		int[] arrayOfAmicables = new int[upperBound];
		int sumOfAmicables = 0;
		int temporaryAmicableHalf;
		for(int i = 0; i < upperBound; i++)
		{
			temporaryAmicableHalf = findAmicableNumber(i, upperBound);
			if(temporaryAmicableHalf > 0)
			{
				arrayOfAmicables[i] = i;
				arrayOfAmicables[temporaryAmicableHalf] = temporaryAmicableHalf;
				System.out.println("Added pair " + i + " and " + temporaryAmicableHalf);
			}
			i = temporaryAmicableHalf != 0 ? temporaryAmicableHalf : i; //This makes the assumption that it is safe to have the iterator skip to the other half of the amicable pair
		}
		for(int i : arrayOfAmicables)
		{
			sumOfAmicables += i;
		}
		return sumOfAmicables;
	}
}

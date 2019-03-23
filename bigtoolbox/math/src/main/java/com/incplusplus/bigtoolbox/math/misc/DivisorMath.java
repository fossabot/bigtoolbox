package com.incplusplus.bigtoolbox.math.misc;

import java.util.ArrayList;

import static com.incplusplus.bigtoolbox.math.misc.PrimeMath.distinctPrimeFactors;

public class DivisorMath
{
	/**
	 * The sum of proper divisors of n (numbers less than n which divide evenly into n)
	 * @param n The number to find divisors of
	 * @return The sum of proper divisors of n
	 */
	public static int sumProperDivisors(int n)
	{
		int sum = 0;
		int[] properDivisors = properDivisors(n);
		for(int i : properDivisors)
		{
			sum += i;
		}
		return sum;
	}
	public static int[] properDivisors(int n)
	{
		ArrayList<Integer> properDivisors = new ArrayList<Integer>();
		int currentNumDivisors = 0;
		final int actualNumProperDivisors = numProperDivisors(n);
		if(n == 1)
		{
			int[] out = new int[0];
			return out;
		}
		for(int i = 1; currentNumDivisors < actualNumProperDivisors && i < n; i++)
		{
			if(n % i == 0)
			{
				properDivisors.add(i);
				currentNumDivisors++;
			}
		}
		return properDivisors.stream().mapToInt(Integer::valueOf).toArray();
	}

	/**
	 * Returns the number of proper divisors of n. For an explanation on how this works,
	 * see https://math.stackexchange.com/questions/1118616/find-how-many-positive-divisors-a-number-has-what-would-you-do
	 * or, for a simpler explanation see https://www.math.upenn.edu/~deturck/m170/wk2/numdivisors.html
	 * To summarize, the product of all of the exponents of the prime factorization of a number increased by 1
	 * is the number of divisors of that number. 144 = 2^4 * 3^2
	 * 4 + 1 = 5 and 2 + 1 = 3. 5 * 3 = 15 and there are 15 divisors of 144.
	 * @param n The product of the proper divisors
	 * @return The number of proper divisors of n
	 */
	public static int numProperDivisors(int n)
	{
		int numProperDivisors = 1;
		int[][] distinctPrimeFactors = distinctPrimeFactors(n);
		for(int[] i : distinctPrimeFactors)
		{
			numProperDivisors *= i[1] + 1;
		}
		return numProperDivisors;
	}
}

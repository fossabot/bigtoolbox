package io.github.incplusplus.bigtoolbox.math.misc;

import java.util.ArrayList;

//todo Add prime number generators including Sieve of Eratosthenes
public class PrimeMath
{
	/**
	 * Generate an ArrayList of prime Integers below a certain number
	 * @param a the limit
	 * @return an ArrayList of prime Integers below a certain number (a)
	 */
	public static ArrayList<Integer> sieveOfEratosthenes(double a)
	{
		ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
		/*
		 * Init a local variable to be used as
		 * the number to find primes up to
		 */
		int n = (int)Math.sqrt(a);
		boolean[] prime = new boolean[n+1];	//Create boolean array
		for(int i=0; i<n; i++)
		{
			prime[i] = true;	//Set each boolean to true
		}
		for(int p = 2; p*p <=n; p++)
		{
			if(prime[p])
			{
				// Update all multiples of p
				for(int i = p*p; i <= n; i += p)
				{
					prime[i] = false;
				}
			}
		}
		for(int i = 2; i < prime.length; i++)
		{
			if(prime[i])
			{
				primeNumbers.add(i);
			}
		}
		return primeNumbers;
	}

	public static boolean isPrime(int n)
	{
		if(n == 1)
		{
			return false;
		}
		int i = 2;
		while(i < n)
		{
			if(n % i == 0)
			{
				return false;
			}
			i+= 1;
		}
		return true;
	}
	/**
	 * Returns the number of proper divisors of n. For an explanation on how this works,
	 * see https://math.stackexchange.com/questions/1118616/find-how-many-positive-divisors-a-number-has-what-would-you-do
	 * or, for a simpler explanation see https://www.math.upenn.edu/~deturck/m170/wk2/numdivisors.html
	 * @param n The product of the distinct prime factors
	 * @return A 2D array with the rows having the bases of each of the distinct prime factors and the corresponding column containing the exponents for those bases
	 */
	public static int[][] distinctPrimeFactors(int n) {
		int[][] output;
		ArrayList<Integer> bases = new ArrayList<Integer>();
		ArrayList<Integer> exponents = new ArrayList<Integer>();
		for (int factor = 2; factor <= n; factor++) {
			int exponent = 0;
			while (n % factor == 0) {
				n /= factor;
				exponent++;
			}
			if (exponent > 0) {
				bases.add(factor);
				exponents.add(exponent);
			}
		}
		output = new int[bases.size()][2];
		for(int i = 0; i < bases.size(); i++)
		{
			output[i][0] = bases.get(i);
			output[i][1] = exponents.get(i);
		}
		return output;
	}
}

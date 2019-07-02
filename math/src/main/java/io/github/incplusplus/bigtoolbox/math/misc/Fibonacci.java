package io.github.incplusplus.bigtoolbox.math.misc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
public class Fibonacci
{
	private boolean calcHasBeenDone;
	private ArrayList<BigInteger> sequence;
	public Fibonacci()
	{
		sequence = new ArrayList<BigInteger>(Arrays.asList(BigInteger.ZERO, BigInteger.ONE));
	}
	public Fibonacci(int index1, int index2)
	{
		sequence = new ArrayList<BigInteger>(Arrays.asList(BigInteger.ZERO, BigInteger.valueOf(index1), BigInteger.valueOf(index2)));
	}
	public BigInteger fibonacciAtIndex(int n)
	{
		// System.out.println("Sequence size is " + sequence.size());
		// if(sequence.size() > 10000)
		// {
			// System.out.println(this.toString());
			// return BigInteger.ZERO;
		// }
		for(int i = sequence.size()-1; i < n; i++)
		{
			sequence.add(sequence.get(i).add(sequence.get(i-1)));
		}
		return sequence.get(n);
	}
	public int firstIndexWithDigitCount(int digits)
	{
		int currentFibonacciIndex = 1;
		int digitCount = 0;
		while(digitCount < digits)
		{
			int startingDigitCount = digitCount;
			currentFibonacciIndex++;
			digitCount = bigIntLength(fibonacciAtIndex(currentFibonacciIndex));
			if(startingDigitCount != digitCount)
			{
				System.out.println("digitCount is now " + digitCount);
			}
		}
		return currentFibonacciIndex;
	}
	public int lastIndexWithTermsUnder(int limit)
	{
		int currentFibonacciIndex = 1;
		int currentHighest = 0;
		while(currentHighest < limit)
		{
			currentFibonacciIndex++;
			currentHighest = fibonacciAtIndex(currentFibonacciIndex).intValue();
		}
		return currentFibonacciIndex;
	}
	public ArrayList<BigInteger> fibonacciSeriesUpTo(int termNum)
	{
		ArrayList<BigInteger> out = new ArrayList<BigInteger>();
		fibonacciAtIndex(termNum);	//Ensure that we've calculated that far
		for(int i = 1; i < termNum; i++)
		{
			out.add(sequence.get(i));
		}
		return out;
	}
	public ArrayList<BigInteger> fibonacciSeriesWithTermsUnder(int limit)
	{
		return fibonacciSeriesUpTo(lastIndexWithTermsUnder(limit));
	}
	public int bigIntLength(BigInteger number)
	{
		return number.toString().length();
	}
	public String toString()
	{
		return sequence.toString();
	}
}
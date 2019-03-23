package com.incplusplus.bigtoolbox.os;

public class LikelyOutdatedMethodException extends RuntimeException
{
	private static String cause = "A method that was called is in an unexpected state. \n" +
			"This is likely because the environment the method expects is outdated. \n" +
			"Further details: ";
	public LikelyOutdatedMethodException(String outdatedMethodLocation)
	{
		super(cause + outdatedMethodLocation);
	}
}
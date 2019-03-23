package com.incplusplus.bigtoolbox.os;

public class LikelyOutdatedMethodException extends RuntimeException
{
	private static String cause = "A method that was called is in an unexpected state. \n" +
			"This is likely because the environment the method expects is outdated. \n" +
			"Thrown from method";

	/**
	 * A method that depends on very specific strings given from the OS may
	 * receive a different format than expected and would
	 * not know how to parse that string properly.
	 * This can and likely will be caused by the OS receiving an update that
	 * changes the expected string format.
	 * @param culpritPackage The package this was thrown from
	 * @param culpritClass The class this was thrown from
	 * @param culpritMethod The method this was thrown from
	 */
	public LikelyOutdatedMethodException(String culpritPackage, String culpritClass, String culpritMethod)
	{
		super(String.format("%s %s in class %s in package %s", cause, culpritMethod, culpritClass, culpritPackage));
	}
}
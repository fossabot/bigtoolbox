package io.github.incplusplus.bigtoolbox.os;

public class LikelyOutdatedMethodException extends RuntimeException
{
	private static String cause = "A method that was called is in an unexpected state. \n" +
			"This is likely because the environment the method expects is outdated. \n" +
			"Thrown from method";

	/**
	 * A method that depends on very specific strings given from the OSFamily may
	 * receive a different format than expected and would
	 * not know how to parse that string properly.
	 * This can and likely will be caused by the OSFamily receiving an update that
	 * changes the expected string format.
	 * @param throwingClass The class that threw this exception. Best practice is to send in getClass()
	 */
	public LikelyOutdatedMethodException(Class<?> throwingClass)
	{
		super(String.format("%s %s in class %s in package %s", cause, throwingClass.getEnclosingMethod().getName(), throwingClass.getName(), throwingClass.getPackage().toString()));
	}
}
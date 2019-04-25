package com.incplusplus.bigtoolbox.os;

public class NoSuchOSException extends RuntimeException
{
	private static String cause = "The OS was given invalid constructor arguments. \n" +
			"The version of this OS requested has never existed. \n" +
			"Thrown from";

	/**
	 * A method that depends on very specific strings given from the OSFamily may
	 * receive a different format than expected and would
	 * not know how to parse that string properly.
	 * This can and likely will be caused by the OSFamily receiving an update that
	 * changes the expected string format.
	 * @param throwingClass The class that threw this exception. Best practice is to send in getClass()
	 */
	public NoSuchOSException(Class<?> throwingClass)
	{
		super(String.format("%s %s in class %s in package %s", cause, throwingClass.getEnclosingMethod().getName(), throwingClass.getName(), throwingClass.getPackage().toString()));
	}
}
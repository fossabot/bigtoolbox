package io.github.incplusplus.bigtoolbox.os;

public class UnsupportedOSException extends RuntimeException
{
	private static String cause = "The OS of this system is not supported. \n" +
			"It could be supported in the future, though. \n" +
			"System version:";

	/**
	 * Thrown when the OS of the current system or the requested skeleton OS does not have a representative class
	 * or the representative class is not yet completed.
	 */
	public UnsupportedOSException()
	{
		super(String.format("%s %s", cause, System.getProperty("os.version")));
	}
}
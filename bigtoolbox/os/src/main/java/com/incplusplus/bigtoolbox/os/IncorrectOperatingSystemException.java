package com.incplusplus.bigtoolbox.os;

public class IncorrectOperatingSystemException extends RuntimeException
{
	/**
	 * This is thrown when a specific OS from the opsys package is instantiated and
	 * is not actually the OS of the host that is running this program.
	 */
	public IncorrectOperatingSystemException()
	{
		super("An opsys class was instantiated but does not match the host OS.");
	}
}
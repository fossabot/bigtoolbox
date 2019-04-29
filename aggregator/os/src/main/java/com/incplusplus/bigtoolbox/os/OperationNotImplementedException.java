package com.incplusplus.bigtoolbox.os;

public class OperationNotImplementedException extends RuntimeException
{
	/**
	 * This is thrown when a specific OSFamily from the opsys package is instantiated and
	 * is not actually the OSFamily of the host that is running this program.
	 */
	public OperationNotImplementedException()
	{
		super("The OperatingSystem for which a method was called on has not been implemented for the target OS.");
	}
}
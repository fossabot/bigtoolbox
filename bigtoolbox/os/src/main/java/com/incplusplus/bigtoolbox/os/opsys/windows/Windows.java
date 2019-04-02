package com.incplusplus.bigtoolbox.os.opsys.windows;

import com.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import com.incplusplus.bigtoolbox.os.OperationNotImplementedException;
import com.incplusplus.bigtoolbox.os.UnsupportedOSException;
import com.incplusplus.bigtoolbox.os.opsys.OperatingSystem;
import org.apache.commons.lang3.SystemUtils;

public abstract class Windows extends OperatingSystem
{
	protected String version;
	protected boolean isSkeleton;

	public Windows()
	{
		if(! SystemUtils.IS_OS_WINDOWS_10)
		{
			throw new IncorrectOperatingSystemException();
		}
		version = System.getProperty("os.version");
	}

	public static OperatingSystem getSkeletonOS(String majorVersion, String minorVersion, String buildVersion)
	{
		if(majorVersion.equals("Windows 10"))
		{
			return Windows_10.getSkeletonOS(minorVersion,buildVersion);
		}
		throw new UnsupportedOSException();
	}

	//protected OperatingSystem getSkeletonOS(String releaseName, String OSVersion)
	//{
	//	String osReleaseName = releaseName.replace(' ', '_');
	//	return new Windows_10("312.34123");
	//}

	public static Windows getInstance()
	{
		if(SystemUtils.IS_OS_WINDOWS_10)
			return new Windows_10();
		else throw new UnsupportedOSException();
	}

	//protected Windows getSkeletonOS(String majorVersion, String minorVersion)
	//{
	//	if(majorVersion.equals())
	//}

	@Override
	public String getVersion()
	{
		return version;
	}
}

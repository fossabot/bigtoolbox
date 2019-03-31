package com.incplusplus.bigtoolbox.os.opsys;

import com.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import org.apache.commons.lang3.SystemUtils;

public abstract class Windows extends OperatingSystem
{
	protected String version;
	protected boolean isSkeleton;

	public Windows()
	{
		if(! SystemUtils.IS_OS_WINDOWS)
		{
			throw new IncorrectOperatingSystemException();
		}
		version = System.getProperty("os.version");
	}

	private Windows(String OSVersion)
	{
		this.isSkeleton = true;
		version =OSVersion;
	}

	protected OperatingSystem getSkeletonOS(String releaseName, String OSVersion)
	{
		String osReleaseName = releaseName.replace(' ', '_');
		return new Windows_10("312.34123");
	}

	public OperatingSystem getInstance()
	{
		if(SystemUtils.IS_OS_WINDOWS_10)
			return new Windows_10();
		else return new Unknown();
	}

	@Override
	public String getVersion()
	{
		return version;
	}
}

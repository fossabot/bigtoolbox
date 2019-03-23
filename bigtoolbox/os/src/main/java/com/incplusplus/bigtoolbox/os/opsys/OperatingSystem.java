package com.incplusplus.bigtoolbox.os.opsys;

import org.apache.commons.lang3.SystemUtils;



//TODO Implement Comparable
public abstract class OperatingSystem
{
	public enum OSFamily
	{
		Windows,Linux,Mac
	}
	public static OperatingSystem getOS()
	{
		if(SystemUtils.IS_OS_WINDOWS)
		{
			//return new Windows();
		}
		else if(SystemUtils.IS_OS_LINUX)
		{
			return new Linux();
		}
		else if(SystemUtils.IS_OS_MAC)
		{
			return new Mac();
		}
		return new Unknown();
	}

	protected abstract OperatingSystem getInstance();

	public abstract String getVersion();

	public OperatingSystem getSkeleton(OSFamily osFamily, String releaseName, String OSVersion)
	{
		if(osFamily.equals("Windows"))
		{
			return Windows.getSkeletonOS(releaseName,OSVersion);
		}
		if(osFamily.equals("Linux"))
		{
			return Linux.getSkeletonOS(releaseName,OSVersion);
		}
		if(osFamily.equals("Mac"))
		{
			return Mac.getSkeletonOS(releaseName,OSVersion);
		}
		return new Unknown();
	}

	protected abstract OperatingSystem getSkeletonOS(String releaseName, String OSVersion);


}
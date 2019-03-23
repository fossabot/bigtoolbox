package com.incplusplus.bigtoolbox.os.opsys;

import org.apache.commons.lang3.SystemUtils;

public abstract class OperatingSystem
{
	public OperatingSystem getOS()
	{
		if(SystemUtils.IS_OS_WINDOWS)
		{
			return new Windows();
		}
		else if(SystemUtils.IS_OS_LINUX)
		{
			return new Linux();
		}
		else if(SystemUtils.IS_OS_MAC)
		{
			return new Mac();
		}
		else
		{
			return new Unknown();
		}
	}
}
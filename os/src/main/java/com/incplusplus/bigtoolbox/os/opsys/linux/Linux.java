package com.incplusplus.bigtoolbox.os.opsys.linux;

import com.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import com.incplusplus.bigtoolbox.os.UnsupportedOSException;
import com.incplusplus.bigtoolbox.os.opsys.OperatingSystem;
import org.apache.commons.lang3.SystemUtils;

public abstract class Linux extends OperatingSystem
{
	public Linux()
	{
		if(! SystemUtils.IS_OS_LINUX)
		{
			throw new IncorrectOperatingSystemException();
		}
	}
	public static Linux getInstance()
	{
		throw new UnsupportedOSException();
	}
}
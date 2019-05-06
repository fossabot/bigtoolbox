package io.github.incplusplus.bigtoolbox.os.opsys.linux;

import io.github.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import io.github.incplusplus.bigtoolbox.os.UnsupportedOSException;
import io.github.incplusplus.bigtoolbox.os.opsys.OperatingSystem;
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
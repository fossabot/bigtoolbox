package com.incplusplus.bigtoolbox.os.opsys;

import com.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import org.apache.commons.lang3.SystemUtils;

public class Windows extends OperatingSystem
{
	public Windows()
	{
		if(!SystemUtils.IS_OS_WINDOWS)
		{
			throw new IncorrectOperatingSystemException();
		}
	}
}
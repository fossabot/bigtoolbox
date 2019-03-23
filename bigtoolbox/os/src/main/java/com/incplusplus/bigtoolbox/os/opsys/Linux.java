package com.incplusplus.bigtoolbox.os.opsys;

import com.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import org.apache.commons.lang3.SystemUtils;

public class Linux extends OperatingSystem
{
	public Linux()
	{
		if(! SystemUtils.IS_OS_LINUX)
		{
			throw new IncorrectOperatingSystemException();
		}
	}
}
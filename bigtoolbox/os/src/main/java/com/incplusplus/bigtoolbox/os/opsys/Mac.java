package com.incplusplus.bigtoolbox.os.opsys;

import com.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import org.apache.commons.lang3.SystemUtils;

public class Mac extends OperatingSystem
{
	public Mac()
	{
		if(! SystemUtils.IS_OS_MAC)
		{
			throw new IncorrectOperatingSystemException();
		}
	}
}
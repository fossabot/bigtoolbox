package com.incplusplus.bigtoolbox.os.opsys;

import com.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import com.incplusplus.bigtoolbox.os.LikelyOutdatedMethodException;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Windows extends OperatingSystem
{
	String buildNumber;

	public Windows()
	{
		if(! SystemUtils.IS_OS_WINDOWS)
		{
			throw new IncorrectOperatingSystemException();
		}
		getBuildNumber();
	}

	public String getBuildNumber()
	{
		String build = "";
		try
		{
			Process p = Runtime.getRuntime().exec("cmd /c ver");
			p.waitFor();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(p.getInputStream())
			);
			String line;
			while((line = reader.readLine()) != null)
			{
				build += line;
			}
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		catch(InterruptedException e2)
		{
			e2.printStackTrace();
		}
		if(! build.matches("(Microsoft Windows \\[Version )(\\d+\\.\\d+\\.\\d+\\.\\d+\\])"))
		{
			throw new LikelyOutdatedMethodException(getClass().getPackage().toString(),getClass().toString(),getClass().getEnclosingMethod().getName());
		}
		String[] splitBuildText =build.split("(\\.)|(\\])");
		StringBuilder buildBuilder = new StringBuilder(splitBuildText[2].length()+splitBuildText[3].length());
		buildBuilder.append(splitBuildText[2]);
		buildBuilder.append(".");
		buildBuilder.append(splitBuildText[3]);
		build = buildBuilder.toString();
		buildNumber = build;
		return buildNumber;
	}
}
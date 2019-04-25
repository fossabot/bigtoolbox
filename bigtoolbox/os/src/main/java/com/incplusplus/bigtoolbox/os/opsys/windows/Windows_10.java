package com.incplusplus.bigtoolbox.os.opsys.windows;

import com.incplusplus.bigtoolbox.os.IncorrectOperatingSystemException;
import com.incplusplus.bigtoolbox.os.LikelyOutdatedMethodException;
import com.incplusplus.bigtoolbox.os.opsys.OperatingSystem;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Windows_10 extends Windows
{
	private int version;
	private double buildNumber;
	private boolean isSkeleton;
	public Windows_10()
	{
		super();
		if(! SystemUtils.IS_OS_WINDOWS_10)
		{
			throw new IncorrectOperatingSystemException();
		}
		grabBuildNumber();
		grabVersionNumber();
		isSkeleton = false;
	}

	private Windows_10(String minorVersion, String buildVersion)
	{
		//TODO Implement throwing NoSuchOSException
		super();
		try
		{
			this.version=Integer.parseInt(minorVersion);
			this.buildNumber=Double.parseDouble(buildVersion);
		}
		catch(NumberFormatException e)
		{
			try
			{
				throw new Exception("minorVersion for Windows_10 must be an integer!\nmajorVersion for Windows_10 must be an double!");
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}
		this.isSkeleton=true;
	}

	public static OperatingSystem getSkeletonOS(String minorVersion, String buildVersion)
	{
		return new Windows_10(minorVersion,buildVersion);
	}

	public String getMajorVersion()
	{
		return Integer.toString(version);
	}

	public String getMinorVersion()
	{
		return Double.toString(buildNumber);
	}

	private void grabVersionNumber()
	{
		{
			String version = "";
			try
			{
				Process p = Runtime.getRuntime().exec("cmd /c powershell -command \"(Get-ItemProperty 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion').ReleaseId\"");
				p.waitFor();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(p.getInputStream())
				);
				String line;
				while((line = reader.readLine()) != null)
				{
					version += line;
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
			this.version = Integer.parseInt(version);
		}
	}

	private void grabBuildNumber()
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
			throw new LikelyOutdatedMethodException(getClass());
		}
		String[] splitBuildText = build.split("(\\.)|(\\])");
		StringBuilder buildBuilder = new StringBuilder(splitBuildText[2].length() + splitBuildText[3].length());
		buildBuilder.append(splitBuildText[2]);
		buildBuilder.append(".");
		buildBuilder.append(splitBuildText[3]);
		build = buildBuilder.toString();
		buildNumber = Double.parseDouble(build);
	}
}

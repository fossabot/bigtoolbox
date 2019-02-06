package com.IncPlusPlus.MyCustomUtils.io;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class represents a file within a Directory.
 * This class is named Record to avoid conflicts with Java's own File class.
 */
public class Record implements Entry
{    // TODO Add a String getSuffix() to determine B, KB, MB, GB, etc
	// TODO Add a String that nicely formats the size with spaces and commas and adds suffix
	// TODO Add various hashing techniques
	// TODO Add a compare function that uses hashing

	private File thisFile;
	private boolean hasNoContents = false;

	public Record()
	{
		thisFile = null;
		hasNoContents = true;
	}

	public Record(String filename)
	{
		this(new File(filename));
	}

	public Record(File in)
	{
		if(in == null)
		{
			try
			{
				throw new FileNotFoundException("File doesn't exist!");
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else if(in.exists())
		{
			if(in.isFile())
			{
				thisFile = in;
			}
			else
			{
				try
				{
					throw new FileNotFoundException("File exists but isn't a file!");
				}
				catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public long getSize()
	{
		if(thisFile != null)
		{
			return thisFile.length();
		}
		else
		{
			return 0;
		}
	}

	/**
	 * @return a Record if this is a Record or an empty Record if this is not a Record
	 */
	@Override
	public Record asRecord()
	{
		return this;
	}

	@Override
	public Directory asDirectory()
	{
		return new Directory();
	}

	@Override
	public boolean hasNoContents()
	{
		return hasNoContents;
	}

	@Override
	public String getName()
	{
		return thisFile.getName();
	}
}

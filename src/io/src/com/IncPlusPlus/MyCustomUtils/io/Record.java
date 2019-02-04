package com.IncPlusPlus.MyCustomUtils.io;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class represents a file within a Directory.
 * This class is named Record to avoid conflicts with Java's own File class.
 */
public class Record
{
	private File thisFile;


	public Record(String filename) throws FileNotFoundException
	{
		new Record(new File(filename));
	}
	public Record(File in) throws FileNotFoundException
	{
		if(in.exists())
		{
			if(thisFile.isFile())
			{
				thisFile = in;
			}
			else{
				throw new FileNotFoundException("File exists but isn't a file!");
			}
		}
		else{
			throw new FileNotFoundException("File doesn't exist!");
		}
	}
	public long getSize(){

	}
	public String sizeString()
	{
		return sizeString(false,false);
	}
	public String sizeString(boolean createSpaces, boolean addPunctuation)
	{
		return sizeString(createSpaces,addPunctuation,",");
	}
	public String sizeString(boolean createSpaces, boolean addPunctuation, String punctuation)
	{
		//TODO add implementation
		return null;
	}
}

package com.IncPlusPlus.MyCustomUtils.io;

public interface Entry
{
	long getSize();

	/**
	 * @return a Record if this is a Record or an empty Record if this is not a Record
	 */
	Record asRecord();
	Directory asDirectory();
	boolean hasNoContents();
	String getName();
}

package com.flipturnapps.bukkitguigit;

import java.io.File;

import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.PropertyManager;

public class GitPropertyManager extends PropertyManager
{

	public static final String PROPKEY_CBUKKIT_PATH = "cbukkit-path";

	@Override
	public File getStorageDirectory() 
	{
		return new File(FileHelper.getAppDataDir("flipturnapps", "GitBukkit"));
	}

	@Override
	public String getFilename() 
	{
		return "properties.zzz";
	}

}
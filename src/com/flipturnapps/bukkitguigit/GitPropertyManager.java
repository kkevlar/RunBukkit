package com.flipturnapps.bukkitguigit;

import java.io.File;
import java.io.IOException;

import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.PropertyManager;

public class GitPropertyManager extends PropertyManager
{

	public static final String PROPKEY_CBUKKIT_PATH = "cbukkit-path";
	public static final String PROPKEY_REMOTE = "remote";
	public static final String PROPKEY_USERNAME = "username";
	public static final String PROPKEY_NAME = "real-name";
	public static final String PROPKEY_EMAIL = "git-email";

	public GitPropertyManager(String pathFound) throws IOException
	{
		super(new File(pathFound));
	}

	public GitPropertyManager() {
		super();
	}

	@Override
	public File getStorageDirectory() 
	{
		return getDataDir();
	}

	@Override
	public String getFilename() 
	{
		return "properties.zzz";
	}

	public static File getDataDir() {
		File file = new File(FileHelper.getAppDataDir("flipturnapps", "GitBukkit"));
		file.mkdirs();
		return file;
	}

}

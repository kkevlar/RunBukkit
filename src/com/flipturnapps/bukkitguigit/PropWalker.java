package com.flipturnapps.bukkitguigit;

import java.io.File;

import com.flipturnapps.kevinLibrary.helper.FileWalker;

public class PropWalker extends FileWalker {

	@Override
	public boolean isFileTheTarget(File f) 
	{
		if(f.getName().equals("properties.zzz"))
			return true;
		return false;
	}

}

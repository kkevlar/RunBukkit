package com.flipturnapps.runcbukkit.command;

import com.flipturnapps.kevinLibrary.command.SimpleCommand;

public abstract class CommandParent extends SimpleCommand
{
	public String getHelpText()
	{
		return null;
	}
	
	@Override
	public boolean objectDataOK(Object data) 
	{
		return true;
	}

	@Override
	public boolean permProtected() 
	{
		return true;
	}
}

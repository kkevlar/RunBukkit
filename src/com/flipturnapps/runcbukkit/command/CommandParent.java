package com.flipturnapps.runcbukkit.command;

import com.flipturnapps.kevinLibrary.command.CommandOutput;
import com.flipturnapps.kevinLibrary.command.OutputSimpleCommand;

public abstract class CommandParent extends OutputSimpleCommand
{
	public CommandParent(CommandOutput output) 
	{
		super(output);
	}

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

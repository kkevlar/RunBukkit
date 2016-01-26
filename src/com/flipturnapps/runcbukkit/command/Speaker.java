package com.flipturnapps.runcbukkit.command;

import com.flipturnapps.kevinLibrary.command.CommandSpeaker;
import com.flipturnapps.runcbukkit.ManagerThread;

public interface Speaker
{
	public void register(ManagerThread thread);

	public void unregister();
	
	public CommandSpeaker getCommandSpeaker();
	
}

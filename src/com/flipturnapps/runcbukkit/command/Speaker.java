package com.flipturnapps.runcbukkit.command;

import com.flipturnapps.kevinLibrary.command.CommandIO;
import com.flipturnapps.runcbukkit.ManagerThread;

public interface Speaker extends CommandIO
{
	public void register(ManagerThread thread);

	public void unregister();
	
}

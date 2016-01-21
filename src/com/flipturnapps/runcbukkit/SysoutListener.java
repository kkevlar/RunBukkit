package com.flipturnapps.runcbukkit;

public class SysoutListener implements CBukkitListener
{

	@Override
	public void postLine(String line) 
	{
		System.out.println(line);
	}

}

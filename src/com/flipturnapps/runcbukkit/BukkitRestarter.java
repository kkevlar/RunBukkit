package com.flipturnapps.runcbukkit;

public class BukkitRestarter implements Runnable {

	private BukkitInstance bukkit;

	public BukkitRestarter(BukkitInstance bukkitInstance) 
	{
		this.bukkit = bukkitInstance;
	}

	@Override
	public void run() 
	{
		bukkit.getWriter().println("say This is your console speaking. Server will restart in 10 seconds.");
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bukkit.getWriter().println("stop");
		try {
			Thread.sleep(4*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new Main()).start();
	}

}

package com.flipturnapps.runcbukkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import com.flipturnapps.kevinLibrary.helper.FlushWriter;

public class BukkitReader extends BufferedReader implements Runnable
{

	

	private boolean shouldRead=true;
	private boolean readNull = false;
	private BukkitInstance bukkitInstance;
	private boolean tatling;
	public BukkitReader(Reader in, BukkitInstance bukkitInstance)
	{
		super(in);
		new Thread(this).start();
		this.bukkitInstance = bukkitInstance;
		
	}

	@Override
	public void run() 
	{
		while(isShouldRead())
		{
			String line = null;
			
			try {
				line = this.readLine();
			
			if(line == null )
			{
				readNull = true;
				shouldRead = false;
				continue;
			}
			if(line.length() > 200)
				continue;
			doLineMagic(line);
			Main.println(" --- " + line);
			bukkitInstance.getServer().sendAll(line);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void doLineMagic(String line) 
	{
		if(isTatling() && line.contains("issued server command") && !(line.contains("[Server]")))
		{	
			bukkitInstance.getWriter().println("say " + line.split("]")[1].substring(2));
		}
		if(line.contains("/restart_zzz")&& !(line.contains("[Server]")))
		{
			//new Thread(new BukkitRestarter(bukkitInstance)).start();
		}
		if(line.contains("/tattling on")&& !(line.contains("[Server]")))
		{
			this.setTatling(true);
		}
		if(line.contains("/tattling off")&& !(line.contains("[Server]")))
		{
			this.setTatling(false);
		}
		
	}

	public boolean isShouldRead() {
		return shouldRead;
	}

	public void setShouldRead(boolean shouldRead) {
		this.shouldRead = shouldRead;
	}

	public boolean isReadNull() {
		return readNull;
	}

	public void setReadNull(boolean readNull) {
		this.readNull = readNull;
	}

	public boolean isTatling() {
		return tatling;
	}

	public void setTatling(boolean tatling) {
		this.tatling = tatling;
	}

	



}

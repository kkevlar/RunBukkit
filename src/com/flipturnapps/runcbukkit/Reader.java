package com.flipturnapps.runcbukkit;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Reader implements Runnable
{
	private BufferedReader stream;
	private ArrayList<CBukkitListener> listeners;
	public Reader(BufferedReader reader)
	{
		stream = reader;
		setListeners(new ArrayList<CBukkitListener>());
	}
	public void start()
	{
		new Thread(this).start();
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String line = stream.readLine();
			if(line != null)
			{
				for
			}
		}
		
	}
	public ArrayList<CBukkitListener> getListeners() {
		return listeners;
	}
	public void setListeners(ArrayList<CBukkitListener> listeners) {
		this.listeners = listeners;
	}
	
}

package com.flipturnapps.runcbukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Reader implements Runnable
{
	private BufferedReader stream;
	private ArrayList<CBukkitListener> listeners;
	private boolean shouldRun;
	public Reader(BufferedReader reader)
	{
		stream = reader;
		setListeners(new ArrayList<CBukkitListener>());
		getListeners().add(new SysoutListener());
		shouldRun = true;
	}
	public void start()
	{
		new Thread(this).start();
	}
	public void stop()
	{
		shouldRun = false;
	}
	@Override
	public void run() 
	{
		while(shouldRun)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String line = null;
			try {
				line = stream.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(line != null)
			{
				String lineCopy = line;
				for (Iterator iterator = listeners.iterator(); iterator.hasNext();)
				{
					CBukkitListener cBukkitListener = (CBukkitListener) iterator.next();
					cBukkitListener.postLine(lineCopy);
				}
			}
		}
		
	}
	public ArrayList<CBukkitListener> getListeners() {
		return listeners;
	}
	private void setListeners(ArrayList<CBukkitListener> listeners)
	{
		this.listeners = listeners;
	}
	
}

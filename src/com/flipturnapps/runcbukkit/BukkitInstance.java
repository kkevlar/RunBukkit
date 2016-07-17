package com.flipturnapps.runcbukkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.flipturnapps.kevinLibrary.helper.FlushWriter;

public class BukkitInstance
{
	private FlushWriter writer;
	private BukkitReader reader;
	private Server server;

	public BukkitInstance(File bukkitDir)
	{
		Runtime runtime = Runtime.getRuntime();		
		Process process;
		try {
			process = runtime.exec(new String ("java -jar craftbukkit.jar"), null, bukkitDir);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		setWriter(new FlushWriter(process.getOutputStream()));
		setReader(new BukkitReader(new InputStreamReader(process.getInputStream()),this));
		
	}

	public PrintWriter getWriter() 
	{
		return writer;
	}

	private void setWriter(FlushWriter writer) 
	{
		this.writer = writer;
	}

	public BukkitReader getReader() 
	{
		return reader;
	}

	private void setReader(BukkitReader reader) 
	{
		this.reader = reader;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
}

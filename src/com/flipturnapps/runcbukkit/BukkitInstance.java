package com.flipturnapps.runcbukkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BukkitInstance
{
	private PrintWriter writer;
	private BufferedReader reader;

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
		setWriter(new PrintWriter(process.getOutputStream()));
		setReader(new BufferedReader(new InputStreamReader(process.getInputStream())));
		
	}

	public PrintWriter getWriter() 
	{
		return writer;
	}

	private void setWriter(PrintWriter writer) 
	{
		this.writer = writer;
	}

	public BufferedReader getReader() 
	{
		return reader;
	}

	private void setReader(BufferedReader reader) 
	{
		this.reader = reader;
	}
}

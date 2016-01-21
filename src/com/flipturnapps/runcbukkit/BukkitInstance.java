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

	public BukkitInstance()
	{
		Runtime runtime = Runtime.getRuntime();		
		Process process;
		try {
			process = runtime.exec(new String ("java -jar craftbukkit.jar"), null, new File("C:/Users/Kevin/Documents/cbukkit2/"));
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		setWriter(new PrintWriter(process.getOutputStream()));
		setReader(new BufferedReader(new InputStreamReader(process.getInputStream())));
		
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
}

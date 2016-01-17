package com.flipturnapps.runcbukkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class RunMain
{

	public static void main(String[] args) throws IOException 
	{
		new RunMain().go();
	}

	private PrintWriter writer;
	private BufferedReader reader;

	private void go() throws IOException 
	{
		Runtime runtime = Runtime.getRuntime();		
		Process process = runtime.exec(new String ("java -jar craftbukkit.jar"), null, new File("C:/Users/Kevin/Documents/cbukkit2/"));
		writer = new PrintWriter(process.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
	}
}

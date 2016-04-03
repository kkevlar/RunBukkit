package com.flipturnapps.runcbukkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.FlushWriter;

public class Main implements Runnable
{
	private static FlushWriter writer;
	private static File cBukkitDir;
	
	public static void println(String string) 
	{
		System.out.println(string);
		if(writer == null)
		{
			String name = "consolelog.log";
			File logFile;
			if(cBukkitDir != null) 
				logFile = new File(FileHelper.fileInDir(cBukkitDir, name));
			else
				logFile = new File(name);
			try {
				writer = new FlushWriter(logFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.println(string);	
	}
	public static void main(String[] args)
	{
		BukkitFinder finder = new BukkitFinder();
		finder.walk();
		if(cBukkitDir == null)
		{
			System.out.println("dir = null");
			return;
		}
		new Thread(new Main()).start();
	}

	public static void setDir(File dir) 
	{
		cBukkitDir = dir;
		
	}
	@Override
	public void run() 
	{
		BukkitInstance instance = new BukkitInstance(cBukkitDir);
		Scanner scan = new Scanner(System.in);
		while(instance.getReader().isShouldRead())
		{
			instance.getWriter().println(scan.nextLine());	
		}
		scan.close();
		
	}
}

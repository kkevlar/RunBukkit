package com.flipturnapps.runcbukkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketImpl;
import java.util.Scanner;

import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.FlushWriter;

public class Main implements Runnable
{
	private static int PORT = 25570;
	private static FlushWriter writer;
	private static File cBukkitDir;
	private Server server;
	
	public static void println(String string) 
	{
		System.out.println(string);
		if(writer == null)
		{
			String name = "consolelog.log";
			File logFile;
			if(getcBukkitDir() != null) 
				logFile = new File(FileHelper.fileInDir(getcBukkitDir(), name));
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
		if(getcBukkitDir() == null)
		{
			System.out.println("dir = null");
			return;
		}
		new Thread(new Main()).start();
	}
	@Override
	public void run() 
	{
		try {
			server = new Server(PORT, this.getcBukkitDir());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		Scanner scan = new Scanner(System.in);
		while(instance.getReader().isShouldRead())
		{
			instance.getWriter().println(scan.nextLine());	
		}
		scan.close();
		*/
		
	}
	public static File getcBukkitDir() {
		return cBukkitDir;
	}
	public static void setcBukkitDir(File cBukkitDir) {
		Main.cBukkitDir = cBukkitDir;
	}
}

package com.flipturnapps.bukkitguigit.animation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.flipturnapps.bukkitguigit.GitPropertyManager;
import com.flipturnapps.kevinLibrary.helper.FileHelper;

public class Checker
{
	
	public void deleteAnimationDir()
	{
		try {
			delete(new File(FileHelper.fileInDir(GitPropertyManager.getDataDir(), "animation" )));
		} catch (IOException e) {
			
		}
	}
	private void delete(File f) throws IOException {
		  if (f.isDirectory()) {
		    for (File c : f.listFiles())
		      delete(c);
		  }
		  if (!f.delete())
		    throw new FileNotFoundException("Failed to delete file: " + f);
		}
	public boolean checkImages()
	{
		if(!Downloader.getListLocation().exists())
		{
			Downloader dl = new Downloader();
			dl.downList();
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(Downloader.getListLocation()));
		} catch (FileNotFoundException e1) {

		}
		File dir =new File(FileHelper.fileInDir(GitPropertyManager.getDataDir(), "animation" ));
		if(!dir.exists())
			return false;

		File[] fileList = dir.listFiles();
		for (int i = 0; i < fileList.length; i++) 
		{
			if(fileList[i].getName().endsWith(".png"))
			{
				String line = null;
				try {
					line = reader.readLine();

					String name = fileList[i].getName();
					if(!line.equals(name))
					{
						return false;
					}
				} catch (Exception e) {
					return false;
				}
			}

		}
		try {
			reader.close();
		} catch (IOException e) {

		}
		return true;
	}
}

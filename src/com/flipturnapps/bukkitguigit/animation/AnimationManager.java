package com.flipturnapps.bukkitguigit.animation;

import java.io.File;

import com.flipturnapps.bukkitguigit.GitPropertyManager;
import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.FlushWriter;

public class AnimationManager implements Runnable
{
	public AnimationManager()
	{
		new Thread(this).start();
	}

	
	public void run() 
	{
		Checker checker = new Checker();
		if(!checker.checkImages())
		{
			checker.deleteAnimationDir();
			
		Downloader downloader = new Downloader();
		downloader.down1();
		downloader.down2();
		downloader.down3();
		downloader.downList();
		
		 String zp1 = downloader.getZipSaveLocation(1).getAbsolutePath();
		 String zp2 = downloader.getZipSaveLocation(2).getAbsolutePath();
		 String zp3 = downloader.getZipSaveLocation(3).getAbsolutePath();
	     String tempPath = FileHelper.fileInDir(GitPropertyManager.getDataDir(), "animation" );
	     File tempFile = new File(tempPath);
	     tempFile.mkdirs();
	     String destDirectory = tempFile.getAbsolutePath();
	     Unzipper unzipper = new Unzipper();
	        
	     try {
	            unzipper.unzip(zp1, destDirectory);
	            unzipper.unzip(zp2, destDirectory);
	            unzipper.unzip(zp3, destDirectory);
	     } catch (Exception ex) {
	            // some errors occurred
	            ex.printStackTrace();
	     }
		}
		else
			System.out.println("passed the folder check");
	}


	
	public void run1() {
		try
		{
		File dir =new File(FileHelper.fileInDir(GitPropertyManager.getDataDir(), "animation" ));
		File listFile = new File(FileHelper.fileInDir(GitPropertyManager.getDataDir(), "animation-file-list.zzz" ));
		
		if(!listFile.exists())
			listFile.createNewFile();
		FlushWriter writer = new FlushWriter(listFile);
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) 
		{
			if(list[i].getName().endsWith(".png"))
				writer.println(list[i].getName());
		}
		writer.close();
		}
		catch(Exception ex)
		{
			
		}
		
	}
}

package com.flipturnapps.bukkitguigit.animation;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.flipturnapps.bukkitguigit.GitPropertyManager;
import com.flipturnapps.kevinLibrary.helper.FileHelper;

public class Downloader {

	private URL zip1;
	private URL zip2;
	private URL zip3;
	private URL list;
	public static final String DIR_ANIMATION = FileHelper.fileInDir(GitPropertyManager.getDataDir(), "animation" );
	public Downloader()
	{
		try
		{
			zip1 = new URL("https://dl.dropboxusercontent.com/s/40tjf3cobx95znl/mcgif-1.zip");
			zip2 = new URL("https://dl.dropboxusercontent.com/s/ormftii9nc3n9r0/mcgif-2.zip");
			zip3 = new URL("https://dl.dropboxusercontent.com/s/7peottei2w9nsn2/mcgif-3.zip");
			list = new URL("https://drive.google.com/uc?export=download&id=0B5_wYgbEk-GZSUZ2azZsRjhkMjg");
		}
		catch(Exception ex)
		{

		}
	}
	public void down1()
	{
		down(zip1,getZipSaveLocation(1));
	}
	public static File getZipSaveLocation(int i) {
		return new File(FileHelper.fileInDir(GitPropertyManager.getDataDir(), "animation-frames-"+i+".zip"));
	}
	public void down2()
	{
		down(zip2,getZipSaveLocation(2));
	}
	public void down3()
	{
		down(zip3,getZipSaveLocation(3));
	}
	public void downList()
	{
		down(list,getListLocation());
	}
	public static File getListLocation() {
		return new File(FileHelper.fileInDir(GitPropertyManager.getDataDir(), "animation-file-list.zzz" ));
	}
	private void down(URL url, File out)
	{
		try
		{


			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream(out.getAbsolutePath());
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();


		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

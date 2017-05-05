package com.flipturnapps.bukkitguigit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.FlushWriter;
import com.flipturnapps.kevinLibrary.helper.TextFileHelper;

public class Executor 
{

	private File workingDir;
	private SimpleReader execOutput;
	private FlushWriter execInput;
	private GitFrame gitFrame;

	public Executor(GitFrame gitFrame) {
		this.gitFrame = gitFrame;
	}

	public String getLastBukkitLocation() 
	{
		File datadir = new File(FileHelper.getAppDataDir("flipturnapps", "GitBukkit"));
		try {
			return TextFileHelper.getFirstTextLine(new File(FileHelper.fileInDir(datadir, "path.zzz")));
		} catch (IOException e) {
			
		}
		return null;
	}

	public void setDir(File file)
	{
		this.workingDir = file;
		File datadir = new File(FileHelper.getAppDataDir("flipturnapps", "GitBukkit"));
		datadir.mkdirs();
			try {
				File writeFile = new File(FileHelper.fileInDir(datadir, "path.zzz"));
				writeFile.createNewFile();
				TextFileHelper.writeTextToFile(writeFile,workingDir.getAbsolutePath());
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		this.execute("git status");
	}
	
	
	public void execute(String string) {
		execute(string,false);
		
	}

	public void execute(String command, boolean b)
	{
		terminate();
		this.getGitFrame().getTextArea().println("$ " + command);
		Runtime runtime = Runtime.getRuntime();		
		Process process;
		try {
			process = runtime.exec(command, null, workingDir);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		setWriter(new FlushWriter(process.getOutputStream()));
		setReader(new SimpleReader(new InputStreamReader(process.getInputStream()),this,b));
	}

	private void setReader(SimpleReader re) {
		this.execOutput = re;
		
	}

	private void setWriter(FlushWriter flushWriter) {
		this.execInput = flushWriter;
		
	}

	public GitFrame getGitFrame() {
		return gitFrame;
	}

	public void terminate() {
		try
		{
		this.execInput.close();
		try {
			this.execOutput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	catch(	Exception ex)
		{
		
		}
		
	}

}

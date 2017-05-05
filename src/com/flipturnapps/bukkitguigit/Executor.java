package com.flipturnapps.bukkitguigit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.FlushWriter;
import com.flipturnapps.kevinLibrary.helper.PropertyManager;
import com.flipturnapps.kevinLibrary.helper.TextFileHelper;

public class Executor 
{

	private File workingDir;
	private SimpleReader execOutput;
	private FlushWriter execInput;
	private GitFrame gitFrame;
	private PropertyManager properties;

	public Executor(GitFrame gitFrame) {
		this.gitFrame = gitFrame;
		properties = new GitPropertyManager();
		try {
			properties.read();
		} catch (IOException e) {
			
		}
	}

	public String getLastBukkitLocation() 
	{
		return properties.getProperty(GitPropertyManager.PROPKEY_CBUKKIT_PATH);
	}

	public void setDir(File file)
	{
		this.workingDir = file;
		properties.setProperty(GitPropertyManager.PROPKEY_CBUKKIT_PATH, file.getAbsolutePath());
		try {
			properties.write();
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

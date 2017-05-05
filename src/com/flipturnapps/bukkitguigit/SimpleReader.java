package com.flipturnapps.bukkitguigit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.flipturnapps.kevinLibrary.newgui.KJTextArea;

public class SimpleReader extends BufferedReader implements Runnable
{

	

	private boolean shouldRead=true;
	private boolean readNull = false;
	private Executor executor;
	private boolean toCombo;
	public SimpleReader(Reader in, Executor exe, boolean b)
	{
		super(in);
		new Thread(this).start();
		this.executor = exe;
		this.toCombo = b;
	}

	@Override
	public void run() 
	{
		while(isShouldRead())
		{
			String line = null;
			
			try {
				line = this.readLine();
			
			if(line == null )
			{
				readNull = true;
				shouldRead = false;
				continue;
			}
			
			if(toCombo)
			{
				executor.getGitFrame().addToCombo(line.substring(9));
			}
			executor.getGitFrame().getTextArea().println(line);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		executor.terminate();
		
	}


	public boolean isShouldRead() {
		return shouldRead;
	}

	public void setShouldRead(boolean shouldRead) {
		this.shouldRead = shouldRead;
	}

	public boolean isReadNull() {
		return readNull;
	}

	public void setReadNull(boolean readNull) {
		this.readNull = readNull;
	}
	public void close() throws IOException
	{
		this.shouldRead= false;
		this.toCombo= false;
		super.close();
	}


}

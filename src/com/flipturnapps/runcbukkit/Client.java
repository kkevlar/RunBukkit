package com.flipturnapps.runcbukkit;

import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.ClientData;
import com.flipturnapps.kevinLibrary.net.KServer;

public class Client extends ClientData
{
	private long verboseTime;
	public Client(Socket socket, KServer<?> server) throws IOException 
	{
		super(socket, server);
	}

	public void sendMessageToServer(String message)
	{
		if (message.toLowerCase().startsWith("verbose"))
		{
			String[] split = message.replace(" ", "=").replace("=","~").split("~");
			long add = Long.parseLong(split[1]);
			addVerboseTime(add);
		}
		else
		super.sendMessageToServer(message);
	}

	private void addVerboseTime(long add)
	{
		if(this.getVerboseTime() < System.currentTimeMillis())
			this.setVerboseTime(System.currentTimeMillis());
		this.setVerboseTime(this.getVerboseTime() + add);
	}

	public long getVerboseTime() {
		return verboseTime;
	}

	public void setVerboseTime(long verboseTime) {
		this.verboseTime = verboseTime;
	}
	public void sendDumpIfVerbose(String line)
	{
		if(this.getVerboseTime() >= System.currentTimeMillis())
			this.sendMessage(line);
	}
}

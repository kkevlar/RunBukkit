package com.flipturnapps.runcbukkit;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.KServer;

public class Server extends KServer<Client>
{
	private BukkitInstance bukkitInstance;
	private File cBukkitDir;
	public Server(int port, File dir) throws IOException 
	{
		super(port);
		this.setcBukkitDir(dir);
		startBukkit();
	}


	private void startBukkit() 
	{
		BukkitInstance instance = new BukkitInstance(getcBukkitDir());
		this.setBukkitInstance(instance);
	}

	
	@Override
	protected void newMessage(String message, Client client) 
	{
		if(message.toLowerCase().startsWith("sdown"))
		{
			long time = Long.parseLong(message.replace(' ', '~').split(" ")[1]);
			boolean restart;
			if(message.contains("r"))
				restart = true;
			else
				restart = false;
			stopBukkit(time,restart);
		}
		
		
		
		if(!this.getBukkitInstance().getReader().isReadNull())
			this.getBukkitInstance().getWriter().println(message);
		else if (message.equalsIgnoreCase("start"))
		{
			this.startBukkit();
		}
			
		
	}

	private void stopBukkit(long time, boolean restart) 
	{
		String actionName;
		if (restart)
			actionName = "restart";
		else
			actionName = "stop";
		//more code here		
	}


	@Override
	protected Client getNewClientData(Socket socket, KServer<Client> kServer) 
	{
		try {
			return new Client(socket, kServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void newClient(Client data)
	{
		System.out.println("new client");
		this.getBukkitInstance().getWriter().println("say A new remote client connected.");
	}

	public BukkitInstance getBukkitInstance() {
		return bukkitInstance;
	}

	public void setBukkitInstance(BukkitInstance bukkitInstance) {
		this.bukkitInstance = bukkitInstance;
		this.bukkitInstance.setServer(this);
	}


	public File getcBukkitDir() {
		return cBukkitDir;
	}


	public void setcBukkitDir(File cBukkitDir) {
		this.cBukkitDir = cBukkitDir;
	}

}

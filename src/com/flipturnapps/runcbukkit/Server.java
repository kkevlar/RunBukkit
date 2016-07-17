package com.flipturnapps.runcbukkit;

import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.KServer;

public class Server extends KServer<Client>
{
	private BukkitInstance bukkitInstance;
	public Server(int port) throws IOException 
	{
		super(port);
	}

	@Override
	protected void newMessage(String message, Client client) 
	{
		
	}

	@Override
	protected Client getNewClientData(Socket socket, KServer<Client> kServer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void newClient(Client data) {
		// TODO Auto-generated method stub
		
	}

	public BukkitInstance getBukkitInstance() {
		return bukkitInstance;
	}

	public void setBukkitInstance(BukkitInstance bukkitInstance) {
		this.bukkitInstance = bukkitInstance;
		this.bukkitInstance.setServer(this);
	}
	
	public void restartBukkit()
	{
		
	}
	
	public void stopBukkit(boolean restarting)
	{
		
	}

}

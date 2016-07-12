package com.flipturnapps.runcbukkit;

import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.KServer;

public class Server extends KServer<Client>
{

	public Server(int port) throws IOException {
		super(port);
		// TODO Auto-generated constructor stub
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

}

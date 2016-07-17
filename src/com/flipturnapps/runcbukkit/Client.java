package com.flipturnapps.runcbukkit;

import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.ClientData;
import com.flipturnapps.kevinLibrary.net.KServer;

public class Client extends ClientData
{
	public Client(Socket socket, KServer<?> server) throws IOException 
	{
		super(socket, server);
	}
}

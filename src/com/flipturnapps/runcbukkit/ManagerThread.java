package com.flipturnapps.runcbukkit;

import java.util.ArrayList;

import com.flipturnapps.kevinLibrary.command.BasicCommandParser;
import com.flipturnapps.kevinLibrary.command.Command;
import com.flipturnapps.kevinLibrary.command.CommandParseException;
import com.flipturnapps.kevinLibrary.command.IncorrectDataException;
import com.flipturnapps.kevinLibrary.command.NonExistentCommandException;
import com.flipturnapps.runcbukkit.command.Speaker;

public class ManagerThread
{
	private BasicCommandParser parser;
	private ArrayList<Speaker> inputs;
	private BukkitInstance cbukkit = null;
	public ManagerThread()
	{
		ArrayList<Command> managerCommands = new ArrayList<Command>();
		inputs = new ArrayList<Speaker>();
		parser = new BasicCommandParser(managerCommands);
	}
	public void runCommand(String command, Speaker speaker)
	{
		try 
		{
			parser.runCommand(command, speaker, this);
		} catch (IncorrectDataException e) 
		{			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommandParseException e) 
		{			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonExistentCommandException e)
		{			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void registerCommandInput(Speaker input)
	{
		inputs.add(input);
		input.register(this);
	}
	public void unregisterCommandInput(Speaker input)
	{
		inputs.remove(input);
		input.unregister();
	}
	public BukkitInstance getCbukkit()
	{
		return cbukkit;
	}
	public void setCbukkit(BukkitInstance cbukkit)
	{
		this.cbukkit = cbukkit;
	}
	
	
}

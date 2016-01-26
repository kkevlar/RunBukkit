package com.flipturnapps.runcbukkit;

import java.util.ArrayList;

import com.flipturnapps.kevinLibrary.command.BasicCommandParser;
import com.flipturnapps.kevinLibrary.command.Command;
import com.flipturnapps.kevinLibrary.command.CommandExecutor;
import com.flipturnapps.kevinLibrary.command.CommandParseException;
import com.flipturnapps.kevinLibrary.command.CommandSpeaker;
import com.flipturnapps.kevinLibrary.command.IncorrectDataException;
import com.flipturnapps.kevinLibrary.command.NonExistentCommandException;

public class ManagerThread 
{
	private BasicCommandParser parser;
	private ArrayList<CommandInput> inputs;
	public ManagerThread()
	{
		ArrayList<Command> managerCommands = new ArrayList<Command>();
		inputs = new ArrayList<CommandInput>();
		parser = new BasicCommandParser(managerCommands);
	}
	public void runCommand(String command, CommandSpeaker speaker)
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
	public void registerCommandInput(CommandInput input)
	{
		inputs.add(input);
		input.register(this);
	}
	public void unregisterCommandInput(CommandInput input)
	{
		inputs.remove(input);
		input.unregister();
	}
	
}

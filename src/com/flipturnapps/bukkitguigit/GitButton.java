package com.flipturnapps.bukkitguigit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class GitButton extends JButton implements ActionListener
{

	public static final String TXT_PULL = "Pull";
	public static final String TXT_CHECKOUT = "Checkout";
public static final String TXT_GET_CHOICES = "Branches";
	public static final String TXT_FIND_REPO = "Find Repo";
	private int stageId;
	private Executor executor;
	static final String TXT_RUN_THE_SERVER = "Run The Server";
	static final String TXT_STAGE_ALL_CHANGES = "Stage ALL Changes";
	static final String TXT_COMMIT_CHANGES = "Commit Changes";
	static final String TXT_PUSH = "Push";
	public static final String TXT_STOP = "stop";
	private static ArrayList<GitButton> list;
	private static int staticId;

	public GitButton(String string, int stageId, Executor exe) 
	{
		super(string);
		this.addActionListener(this);
		this.stageId = stageId;
		this.decideIfEnabled();
		if(list == null)
			list = new ArrayList<GitButton>();
		list.add(this);
		
		this.executor = exe;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(this.getText() == TXT_FIND_REPO)
		{
		boolean success = executor.findServerDir(this);
			if(success)
				staticId = stageId+1;
		
		}
		
		if(this.getText() == TXT_PULL)
		{
			executor.execute("git pull");
			staticId = stageId+1;
		}
		if(this.getText() == TXT_GET_CHOICES)
		{
			executor.execute("git branch -r", true);
			staticId = stageId+1;
		}
		if(this.getText() == TXT_CHECKOUT)
		{
			executor.execute("git checkout " + this.executor.getGitFrame().getComboChoice(), true);
			staticId = stageId+1;
		}
		tellOthersToDecide();
	}

	private void tellOthersToDecide() 
	{
		for(int i = 0; i < list.size(); i++)
		{
			list.get(i).repaint();
		}
	}

	public void repaint()
	{
		decideIfEnabled();
		super.repaint();
	}

	private void decideIfEnabled() {
		if(staticId != stageId)
			this.setEnabled(false);
		else
			this.setEnabled(true);
	}

}

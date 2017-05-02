package com.flipturnapps.bukkitguigit;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StepPanel extends JPanel 
{
	private int number;
	private String stepname;
	private JLabel label;
	public StepPanel(int number, String stepname)
	{
		FlowLayout flowLayout = (FlowLayout) this.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		
		label = new JLabel("Step " + number + ": " + stepname);
		this.add(label);
		
		this.number = number;
		this.stepname = stepname;
	}
}

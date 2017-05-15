package com.flipturnapps.bukkitguigit.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class AnimationPanel extends JPanel	
{
	private static final int IMG_BUFFER_SIZE = 50;
	private static final double FRAME_MILLIS_LENGTH = 100;
	private Image[] imageBuffer = new Image[IMG_BUFFER_SIZE];
	private long startTime = 0;
	private int indexCount = 0;
	private int lastLoadIndex = 0;
	public AnimationPanel()
	{
		this.setBackground(new Color(255,255,255,0));
		this.startTime = System.currentTimeMillis();
	}
	public void paintComponent(Graphics g)
	{
		Image toPaint = quickDecideOnImage();
		g.drawImage(toPaint, 100, 100, null);
	}
	private Image quickDecideOnImage() 
	{
		double timeDelta = System.currentTimeMillis() - startTime;
		indexCount = (int) (timeDelta / FRAME_MILLIS_LENGTH);
		return imageBuffer[indexCount % IMG_BUFFER_SIZE];
	}
	
	public void fillBuffer()
	{
		
	}
}

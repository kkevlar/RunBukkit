package com.flipturnapps.bukkitguigit.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.flipturnapps.kevinLibrary.helper.FileHelper;

public class AnimationPanel extends JPanel implements Runnable	
{
	private static final int IMG_BUFFER_SIZE = 50;
	private static final double FRAME_MILLIS_LENGTH = 100;
	private Image[] imageBuffer = new Image[IMG_BUFFER_SIZE];
	private long startTime = -1;
	private int indexCount = 0;
	private int lastLoadIndex = 0;
	private BufferedReader reader;

	public AnimationPanel()
	{
		this.setBackground(new Color(255,255,255,0));
		new Thread(this).start();

	}
	public void paintComponent(Graphics g)
	{
		Image toPaint = quickDecideOnImage();
		if(toPaint != null)
			g.drawImage(toPaint, -200, -200, null);
		else
		{
			g.setColor(Color.RED);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	private Image quickDecideOnImage() 
	{
		double timeDelta = System.currentTimeMillis() - startTime;
		indexCount = (int) (timeDelta / FRAME_MILLIS_LENGTH);
		return imageBuffer[indexCount % IMG_BUFFER_SIZE];
	}

	public void fillBuffer()
	{
		if(startTime < 0)
			startTime = System.currentTimeMillis();
		int newLoadIndex = indexCount + IMG_BUFFER_SIZE ;
		for(int i = lastLoadIndex; i < newLoadIndex; i++)
		{
			imageBuffer[i % IMG_BUFFER_SIZE] = loadNextImage();
		}
		lastLoadIndex = newLoadIndex;
	}
	private Image loadNextImage() 
	{
		if(reader == null)
			createNewReader();
		String fileName = null;
		try {
			fileName = reader.readLine();
		} catch (IOException e1) {

		}
		if(fileName == null)
		{
			createNewReader();
			try {
				fileName = reader.readLine();
			} catch (IOException e) {

			}
		}

		File imgFile = new File(FileHelper.fileInDir(Downloader.DIR_ANIMATION, fileName));
		BufferedImage image = null;
		try {
			image = ImageIO.read(imgFile);
		} catch (IOException e) {

		}
		return image;
	}
	private void createNewReader()
	{
		try {
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {

				}
			reader = new BufferedReader(new FileReader(Downloader.getListLocation()));
		} catch (FileNotFoundException e) {
		}
	}
	public boolean shouldFillBuffer()
	{
		int usableBufferCount = lastLoadIndex - indexCount;
		if(!(usableBufferCount < IMG_BUFFER_SIZE - 20))
			return false;
		return true;
	}
	
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			if(this.shouldFillBuffer())
				this.fillBuffer();
		}
	}
}

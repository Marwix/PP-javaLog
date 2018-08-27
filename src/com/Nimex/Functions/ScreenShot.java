package com.Nimex.Functions;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenShot // [Done]
{
	public static void main(String[] args) throws IOException, AWTException 
	{
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage capture = new Robot().createScreenCapture(screenRect);
		ImageIO.write(capture, "bmp", new File(System.getProperty("user.home") + "/Desktop/file.bmp"));
	}
}

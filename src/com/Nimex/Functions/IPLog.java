package com.Nimex.Functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JOptionPane;

public class IPLog // [Done]
{
	static String IP;
	public static void main(String[] args)
	{
		try
		{
			URL url = new URL("http://icanhazip.com/");
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

		    String s = null;
		    while ((s = reader.readLine()) != null)
		    {
		    	IP = s;
		    }
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, e.toString());		
		}	
		
		System.out.println(IP);
	}

}

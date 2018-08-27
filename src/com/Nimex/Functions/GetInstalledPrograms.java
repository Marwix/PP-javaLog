package com.Nimex.Functions;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class GetInstalledPrograms // [Done]
{
	
	PowerShell powerShell = null;	
	public static String main()
	{
		try
		{	
   		    PowerShell powerShell = null;
			powerShell = PowerShell.openSession();
			PowerShellResponse response = powerShell.executeCommand("Get-WmiObject -Class Win32_Product -ComputerName . | Format-Wide -Column 1");
			return response.getCommandOutput();
		} 	
		catch (PowerShellNotAvailableException e) 
		{
			e.printStackTrace();
			return "";
		}
	}

}

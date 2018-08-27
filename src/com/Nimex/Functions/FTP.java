package com.Nimex.Functions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import com.Nimex.Nimex_Form;

public class FTP 
{
	
	public void SendFTP(String host, String user, String pass)
	{
		FTPClient ftp = new FTPClient();
		FileInputStream sis = null;
		String filename = System.getProperty("java.io.tmpdir") + Randomize() + ".txt";
		
		try
		{
			Nimex_Form.setTextFTP("Connecting...");
			ftp.connect(host);
			ftp.login(user, pass);
			Nimex_Form.setTextFTP("Sending...");
			
			PrintWriter ofd = new PrintWriter(filename, "UTF-8");
			
			ofd.write("This is a test file - jLog");
			ofd.close();
			
			sis = new FileInputStream(filename);	
			ftp.storeFile("Testlog.txt", sis);
			ftp.logout();
			JOptionPane.showMessageDialog(null, "Successfully Sent!", "Success", JOptionPane.INFORMATION_MESSAGE);
			Nimex_Form.setTextFTP("Send Test File");	
		} 
		catch (IOException er)
		{
			JOptionPane.showMessageDialog(null, "Could not send test file, please check your FTP settings - " + er.toString());
		} 
		finally 
		{
			try
			{
				if (sis != null)
				{
					sis.close();
				}
				ftp.disconnect();
				Nimex_Form.setTextFTP("Send Test File");
				Files.deleteIfExists(Paths.get(filename));
			}
			catch (IOException er)
			{
				Nimex_Form.setTextFTP("Send Test File");
			}
		}

	}
	
	public static char Randomize() 
	{
	    Random r = new Random();
	    char random_3_Char = (char) (48 + r.nextInt(47));
	    return random_3_Char;
	}

}

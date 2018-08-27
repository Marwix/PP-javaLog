package com.Nimex.KeyHook;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class KeyHook_Old
{
	private static boolean run = true;
	public static void main(String[] args) 
	{
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
        System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown.");
        keyboardHook.addKeyListener(new GlobalKeyAdapter() 
        {     	
			@Override public void keyPressed(GlobalKeyEvent event) 
            {		
            	int i = event.getVirtualKeyCode();
            	if (i == 91 || i == 93)
            	{
            		System.out.print("[WINDOWSBUTTON]");
            	}
            	else if (i == 32)
            	{
            		System.out.print(" ");
            	}
            	else if (i == 160 || i == 161)
            	{
            		System.out.print("[SHIFT]");
            	}
            	else if (i == 219)
            	{
            		System.out.print("´");
            	}
            	else if (i == 8)
            	{
            		System.out.print("[BACK]");
            	}
            	else if (i == 162 || i == 163)
            	{
            		System.out.print("[CTRL]");
            	}
            	else if (i == 164)
            	{
            		System.out.print("[ALT]");
            	}
            	else if (i == 13)
            	{
            		System.out.print("[TAB]");
            	}
            	else if (i == 9)
            	{
            		System.out.print("[HORIZONTAL]");
            	}
            	
            	else if(i == 226)
                {
                	if (event.isShiftPressed())
                	{
                		System.out.print(">");
                	}
                	else
                	{
                		System.out.print("<");
                	}
                }
            	else if (i == 220)
            	{
            		if (event.isShiftPressed())
            		{
            			System.out.print("½");
            		}
            		else
            		{
            			System.out.print("§");
            		}
            	}
            	else if (i == 20)
            	{
            		System.out.print("[CAPSLOCK]");
            	}
            	else if (i == 27)
            	{
            		System.out.print("[ESC]");
            	}
            	if (i >= 65 && i <= 90)
            	{
            		if (event.isShiftPressed())
            		{
            			char ch = (char)i;
            			System.out.print(ch);
            		}
            		else
            		{
            			char ch1 = (char)i;
            			System.out.print(Character.toLowerCase(ch1));
            		}
            	}               
            	else if (i >= 48 && i <= 57)
            	{
            		if (event.isShiftPressed())
            		{
            			switch (i) {
            			case 48:
            				System.out.print("=");
            				break;
            			case 49:
            				System.out.print("!");
            				break;
            			case 50:
            				System.out.print("@");
            				break;
            			case 51:
            				System.out.print("#");
            				break;
            			case 52:          			   
            				System.out.print("¤");
            				break;
            			case 53: 
            				System.out.print("%");
            				break;
            			case 54:
            				System.out.print("&");
            				break;
            			case 55:
            				System.out.print("/");
            				break;
            			case 56:
            				System.out.print("(");
            				break;
            			case 57: 
            				System.out.print(")");
            				break;	
            			}
            		}
            		
            		else
            		{
            			switch (i) {
            			case 48:
            				System.out.print("0");
            				break;
            			case 49:
            				System.out.print("1");
            				break;
            			case 50:
            				System.out.print("2");
            				break;
            			case 51:
            				System.out.print("3");
            				break;
            			case 52:
            				System.out.print("4");
            				break;
            			case 53: 
            				System.out.print("5");
            				break;
            			case 54:
            				System.out.print("6");
            				break;
            			case 55:
               				System.out.print("7");
            				break;
            			case 56:
            				System.out.print("8");
            				break;
            			case 57: 
            				System.out.print("9");
            				break;   		
            			}
            	}
            		if (event.isControlPressed() && event.isShiftPressed())
            		{
            			switch (i)
            			{
            			case 52:
            				System.out.print("hello"); 
            				break;
            			}
            		}
            	}       	
            	if (i >= 112 && i <= 123)
            	{
            		switch (i)
            		{
            		case 112:
            			System.out.print("[F1]");
            			break;
            		case 113:
            			System.out.print("[F2]");
            			break;
            		case 114:
            			System.out.print("[F3]");
            			break;
            		case 115:
            			System.out.print("[F4]");
            			break;
            		case 116:
            			System.out.print("[F5]");
            			break;
            		case 117:
            			System.out.print("[F6]");
            			break;
            		case 118:
            			System.out.print("[F7]");
            			break;
            		case 119:
            			System.out.print("[F8]");
            			break;
            		case 120:
            			System.out.print("[F9]");
            			break;
            		case 121:
            			System.out.print("[F10]");
            			break;
            		case 122:
              			System.out.print("[F11]");
            			break;
            		case 123:
            			System.out.print("[F12]");
            			break;   		
            		}
            	}
            	
                if (i >= 96 && i <= 111)
                {
                	switch (i)
                	{
                	case 96:
                		System.out.print("[NumPad 0]");
                		break; 
                	case 97:
                		System.out.print("[NumPad 1]");
                		break; 
                	case 98:
                		System.out.print("[NumPad 2]");
                		break; 
                	case 99:
                		System.out.print("[NumPad 3]");
                		break; 
                	case 100:
                		System.out.print("[NumPad 4]");
                		break; 
                	case 101:
                		System.out.print("[NumPad 5]");
                		break; 
                	case 102:
                		System.out.print("[NumPad 6]");
                		break; 
                	case 103:
                		System.out.print("[NumPad 7]");
                		break; 
                	case 104:
                		System.out.print("[NumPad 8]");
                		break; 
                	case 105:
                		System.out.print("[NumPad 9]");
                		break; 
                	case 106:
                		System.out.print("[MULTIPLY]");
                		break; 
                	case 107:
                		System.out.print("[ADD]");
                		break; 
                	case 108:
                		System.out.print("[SEPARATOR]");
                		break; 
                	case 109:
                		System.out.print("[SUBSTRACT]");
                	break; 
                	case 110:
                		System.out.print("[COMMA]");
                		break; 
                	case 111:
                		System.out.print("[DIVIDE]");
                	}
                }   
                if (i >= 37 && i <= 40)
                {
                	switch (i)
                	{
                	case 37:
                		System.out.print("[LEFT]");
                		break;
                	case 38:
                		System.out.print("[UP]");
                		break;
                	case 39:
                		System.out.print("[RIGHT]");
                		break;
                	case 40:
                		System.out.print("[DOWN]");
                		break;
                	}
                }
                
                if (i >= 186 && i <= 191)
                {
                   
                	switch (i)
                	{
                	case 186:
                		if (event.isShiftPressed())
                		{
                			System.out.print("^");
                    		break;
                		}
                		else
                		{
                			System.out.print("¨");
                    		break;
                		}
                	case 187:
                		if (event.isShiftPressed())
                		{
                			System.out.print(GlobalKeyEvent.VK_LMENU);
                    		break;
                		}
                		else if (event.isShiftPressed())
                		{
                			System.out.print("?");
                			break;
                		}             		
                		else
                		{
                			System.out.print("+");
                			break;
                		}
                	case 188:
                		if (event.isShiftPressed())
                		{
                			System.out.print(";");
                    	    break;
                		}
                		else
                		{
                			System.out.print(",");
                    	    break;
                		}
                	case 189:
                		if (event.isShiftPressed())
                		{
                			System.out.print("_");
                    		break;
                		}
                		else
                		{
                			System.out.print("-");
                    		break;
                		}
                	case 190:
                		if (event.isShiftPressed())
                		{
                			System.out.print(":");
                    		break;
                		}
                		else
                		{
                			System.out.print(".");
                    		break;
                		}
                	case 191:
                		if (event.isShiftPressed())
                		{
                			System.out.print("'");
                    		break;
                		}
                		else
                		{
                			System.out.print("*");
                    		break;
                		}
                 	}           	
                } 
                
                if (i == 221 || i == 222 || i == 192)
                {
                	switch (i)
                	{
                	case 221:
                		if (event.isShiftPressed())
                		{
                			System.out.print("Å");
                			break;
                		}
                		else
                		{
                			System.out.print("å");
                			break;
                		}
                	case 222:
                		if (event.isShiftPressed())
                		{
                			System.out.print("Ä");
                			break;
                		}
                		else
                		{
                			System.out.print("ä");
                			break;
                		}
                	case 192:
                		if (event.isShiftPressed())
                		{
                			System.out.print("Ö");
                			break;
                		}
                		else
                		{
                			System.out.print("ö");
                			break;
                		}
                	}
                }
             
                if (i == 45)
                {
                	System.out.print("[INSERT]");
                }
                else if (i == 36)
                {
                	System.out.print("[HOME]");
                }
                else if (i == 33)
                {
                	System.out.print("[PGUP]");
                }
                else if (i == 46)
                {
                	System.out.print("[DELETE]");
                }
                else if (i == 35)
                {
                	System.out.print("[END]");
                }
                else if (i == 34)
                {
                	System.out.print("[PGDOWN]");
                }
                else if (i == 44)
                {
                	System.out.print("[PRINTSCREEN]");
                }
                else if (i == 145)
                {
                	System.out.print("[SCRLK]");
                }
                else if (i == 19)
                {
                	System.out.print("[PAUSE]");
                }
                else if (i == 144)
                {
                	System.out.print("[NUMLOCK]");
                }
                //System.out.println(i);
             
            	
                if(event.getVirtualKeyCode()==GlobalKeyEvent.VK_ESCAPE) 
                {
                	run = false;
                }
            }
//            @Override public void keyReleased(GlobalKeyEvent event) 
//            {
//                //System.out.println(event);   
//            }
        });

        try 
        {
            while(run);
            Thread.sleep(128);
        } 
        catch(InterruptedException e) 
        { }
          finally 
          { 
        	  keyboardHook.shutdownHook(); 
          }
	}
}
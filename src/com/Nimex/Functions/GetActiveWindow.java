package com.Nimex.Functions;

import com.sun.jna.*;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.*;

public class GetActiveWindow // [Done] - Windows Only
{
   public interface User32 extends StdCallLibrary 
   {
      User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
      HWND GetForegroundWindow();
      int GetWindowTextA(PointerType hWnd, byte[] lpString, int nMaxCount);
   }

   public static void main(String[] args) throws InterruptedException 
   {
      long lastSec = 0;
      while(true)
      {
          long sec = System.currentTimeMillis() / 1000;
          if (sec != lastSec) 
          {
        	 byte[] windowText = new byte[512];

             PointerType hwnd = User32.INSTANCE.GetForegroundWindow();
             User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);
             lastSec = sec;
        	 System.out.println(Native.toString(windowText));
             lastSec = sec;
          }
      }
   }
}
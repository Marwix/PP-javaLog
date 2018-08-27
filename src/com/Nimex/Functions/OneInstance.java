package com.Nimex.Functions;

import java.io.IOException;
import java.net.ServerSocket;

public class OneInstance 
{
    @SuppressWarnings("unused")
	private static ServerSocket SERVER_SOCKET;    
    public boolean GetInstance()
    {
    	try 
        {
            SERVER_SOCKET = new ServerSocket(9999);
            return true;
        } 
        catch (IOException x) 
        {
        	return false;
        }
    }
} 
package com.stackroute.bookapp.exception;
public class UserAlreadyExistException extends Exception
{
	public UserAlreadyExistException(String message)
	{
        super(message);
    }
}

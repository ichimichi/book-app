package com.stackroute.bookapp.UserAuthenticationService.exception;
public class UserAlreadyExistException extends Exception
{
	public UserAlreadyExistException(String message)
	{
        super(message);
    }
}

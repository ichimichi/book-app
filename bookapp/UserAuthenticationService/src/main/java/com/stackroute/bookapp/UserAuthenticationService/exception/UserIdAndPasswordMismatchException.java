package com.stackroute.bookapp.UserAuthenticationService.exception;
public class UserIdAndPasswordMismatchException extends Exception
{
	public UserIdAndPasswordMismatchException(String message)
	{
        super(message);
    }
}

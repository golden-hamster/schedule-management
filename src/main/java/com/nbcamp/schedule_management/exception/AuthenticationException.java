package com.nbcamp.schedule_management.exception;

public class AuthenticationException extends RuntimeException{

    private static final String MESSAGE = "인증에 실패했습니다.";

    public AuthenticationException() {super (MESSAGE);}

    public AuthenticationException(String message) {super(message);}
}

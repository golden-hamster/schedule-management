package com.nbcamp.schedule_management.exception;

public class ManagerNotFoundException extends RuntimeException{

    private static final String MESSAGE = "해당 담당자를 찾을 수 없습니다.";

    public ManagerNotFoundException() {super (MESSAGE);}

    public ManagerNotFoundException(String message) {super(message);}
}

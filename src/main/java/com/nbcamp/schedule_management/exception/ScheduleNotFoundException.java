package com.nbcamp.schedule_management.exception;

public class ScheduleNotFoundException extends RuntimeException{

    private static final String MESSAGE = "해당 일정을 찾을 수 없습니다.";

    public ScheduleNotFoundException() {super (MESSAGE);}

    public ScheduleNotFoundException(String message) {super(message);}
}

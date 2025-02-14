package com.tay.ContactMessageManagement.dto.messages;

public class ErrorMessages {

    private ErrorMessages() {
    }

    //user
    public static final String NOT_FOUND_BY_EMAIL = "No message found with email: %s";
    public static final String NOT_FOUND_BY_SUBJECT = "No message found with subject: %s";
    public static final String NOT_FOUND_BY_DATE = "No message found between dates: %s - %s";

}

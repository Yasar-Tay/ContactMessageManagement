package com.tay.ContactMessageManagement.dto.messages;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String NOT_FOUND_BY_EMAIL = "No message found with email: %s";
    public static final String USER_NOT_FOUND_BY_EMAIL = "No user found with email: %s";
    public static final String NOT_FOUND_BY_SUBJECT = "No message found with subject: %s";
    public static final String NOT_FOUND_BY_DATE = "No message found between dates: %s - %s";
    public static final String NOT_FOUND_BY_TIME = "No message found between times: %s - %s";
    public static final String NOT_FOUND_BY_ID = "No message found with ID: %s";
    public static final String BAD_REQUEST_DATE = "Invalid date! Please enter a valid date in yyyy-MM-dd.";
    public static final String BAD_REQUEST_TIME = "Invalid time! Please enter a valid time in HH:mm.";

}

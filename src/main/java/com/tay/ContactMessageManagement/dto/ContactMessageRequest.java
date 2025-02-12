package com.tay.ContactMessageManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactMessageRequest {

    @NotNull(message = "Please enter your name")
    private String name;

    @NotNull(message = "Please enter your email")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotNull(message = "Please enter the subject")
    private String subject;

    @NotNull(message = "Please enter your message")
    @Size(max = 180, message = "Your message should be consist of 180 characters max.")
    private String message;
}

package com.tay.ContactMessageManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactMessageRequest {

    @NotNull(message = "Please enter your name")
    @NotEmpty(message = "Please enter your name")
    @NotBlank(message = "Please enter your name")
    private String name;

    @NotNull(message = "Please enter your email")
    @NotEmpty(message = "Please enter your email")
    @NotBlank(message = "Please enter your email")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotNull(message = "Please enter the subject")
    @NotEmpty(message = "Please enter the subject")
    @NotBlank(message = "Please enter the subject")
    private String subject;

    @NotNull(message = "Please enter your message")
    @NotBlank(message = "Please enter your message")
    @NotEmpty(message = "Please enter your message")
    @Size(max = 180, message = "Your message should be consist of 180 characters max.")
    private String message;
}

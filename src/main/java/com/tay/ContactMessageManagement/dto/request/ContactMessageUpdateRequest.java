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
public class ContactMessageUpdateRequest {

    private String name;

    @Email(message = "Please provide a valid email")
    private String email;

    private String subject;

    private String message;
}

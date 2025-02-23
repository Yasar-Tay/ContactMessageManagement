package com.tay.ContactMessageManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "Enter a firstname")
    private String firstName;

    @NotBlank(message = "Enter a lastname")
    private String lastName;

    @Email(message = "Enter a valid email")
    private String email;
}

package com.tay.ContactMessageManagement.dto.request;

import com.tay.ContactMessageManagement.entity.User;
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

    private String subject;

    private String message;

    private User user;
}

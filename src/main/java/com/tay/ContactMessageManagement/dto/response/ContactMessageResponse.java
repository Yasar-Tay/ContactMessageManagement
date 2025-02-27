package com.tay.ContactMessageManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tay.ContactMessageManagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactMessageResponse {

    private Long id;

    private String subject;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", timezone = "US")
    private LocalDateTime creationDateTime;

    private User user;
}

package com.tay.ContactMessageManagement.mapper.business;

import com.tay.ContactMessageManagement.dto.request.ContactMessageRequest;
import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.entity.business.ContactMessage;
import org.springframework.stereotype.Component;

@Component
public class ContactMessageMapper {

    public ContactMessage mapContactMessageRequestToContactMessage(ContactMessageRequest contactMessageRequest) {
        return ContactMessage.builder()
                .name(contactMessageRequest.getName())
                .email(contactMessageRequest.getEmail())
                .subject(contactMessageRequest.getSubject())
                .message(contactMessageRequest.getMessage())
                .build();
    }

    public ContactMessageResponse mapContactMessageToContactMessageResponse(ContactMessage contactMessage){
        return ContactMessageResponse.builder()
                .id(contactMessage.getId())
                .name(contactMessage.getName())
                .email(contactMessage.getEmail())
                .subject(contactMessage.getSubject())
                .message(contactMessage.getMessage())
                .creationDateTime(contactMessage.getCreationDateTime())
                .build();
    }
}

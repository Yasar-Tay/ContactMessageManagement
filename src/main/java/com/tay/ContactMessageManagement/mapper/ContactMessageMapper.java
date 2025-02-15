package com.tay.ContactMessageManagement.mapper;

import com.tay.ContactMessageManagement.dto.request.ContactMessageRequest;
import com.tay.ContactMessageManagement.dto.request.ContactMessageUpdateRequest;
import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.entity.ContactMessage;
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

    public ContactMessage updateContactMessageWithContactMessageRequest(ContactMessageUpdateRequest contactMessageUpdateRequest, ContactMessage contactMessage){
        if (contactMessageUpdateRequest.getName() != null){
            contactMessage.setName(contactMessageUpdateRequest.getName());
        }
        if (contactMessageUpdateRequest.getEmail() != null){
            contactMessage.setEmail(contactMessageUpdateRequest.getEmail());
        }
        if (contactMessageUpdateRequest.getSubject() != null){
            contactMessage.setSubject(contactMessageUpdateRequest.getSubject());
        }
        if (contactMessageUpdateRequest.getMessage() != null){
            contactMessage.setMessage(contactMessageUpdateRequest.getMessage());
        }
        return contactMessage;
    }

}

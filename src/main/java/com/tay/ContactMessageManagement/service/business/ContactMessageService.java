package com.tay.ContactMessageManagement.service.business;

import com.tay.ContactMessageManagement.dto.request.ContactMessageRequest;
import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.entity.business.ContactMessage;
import com.tay.ContactMessageManagement.mapper.business.ContactMessageMapper;
import com.tay.ContactMessageManagement.repository.business.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMessageMapper contactMessageMapper;

    /**
     *
     * @param contactMessageRequest DTO from postman or FE
     * @return ResponseEntity within ContactMessageResponse DTO
     */
    public ResponseEntity<ContactMessageResponse> saveMessage(ContactMessageRequest contactMessageRequest) {
        //mapping request to entity
        ContactMessage messageToSave = contactMessageMapper.mapContactMessageRequestToContactMessage(contactMessageRequest);
        //save into DB
        ContactMessage savedMessage = contactMessageRepository.save(messageToSave);
        //mapping entity to response
        ContactMessageResponse response = contactMessageMapper.mapContactMessageToContactMessageResponse(savedMessage);
        //returning response
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Fetches all messages from DB
     * @return  ResponseEntity within ContactMessageResponse DTOs as List
     */
    public ResponseEntity<List<ContactMessageResponse>> getAll() {
        //Fetching messages from DB
        List<ContactMessage> messages = contactMessageRepository.findAll();
        //Mapping into Response DTO
        List<ContactMessageResponse> responseList = messages.stream()
                .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                .collect(Collectors.toList());
        //returning DTO list
        return ResponseEntity.ok(responseList);
    }
}

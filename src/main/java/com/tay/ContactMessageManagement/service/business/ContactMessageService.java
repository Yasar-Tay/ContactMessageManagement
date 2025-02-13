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

    //TODO Delete later. Added for test purposes
    public ResponseEntity<List<ContactMessageResponse>> getAll() {
        List<ContactMessageResponse> responseList = contactMessageRepository.findAll().stream()
                .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }
}

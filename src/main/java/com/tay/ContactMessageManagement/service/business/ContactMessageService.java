package com.tay.ContactMessageManagement.service.business;

import com.tay.ContactMessageManagement.dto.request.ContactMessageRequest;
import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.entity.business.ContactMessage;
import com.tay.ContactMessageManagement.mapper.business.ContactMessageMapper;
import com.tay.ContactMessageManagement.repository.business.ContactMessageRepository;
import com.tay.ContactMessageManagement.service.helper.PageableHelper;
import com.tay.ContactMessageManagement.service.validator.PropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final PageableHelper pageableHelper;
    private final PropertyValidator propertyValidator;

    /**
     *
     * @param contactMessageRequest DTO from postman or FE
     * @return ContactMessageResponse DTO within ResponseEntity
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
     * @return ContactMessageResponse DTOs as List within ResponseEntity
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

    /**
     * Parameters sent by ContactMessageController::getMessagesByPage()
     * @param page Index number of page
     * @param size How many items should be fetched per page
     * @param type Type of direction (ASC or DESC)
     * @param prop Which property will be used for sorting.
     * @return Page object consist of ContactMessageResponse DTOs within ResponseEntity object.
     */
    public ResponseEntity<Page<ContactMessageResponse>> getByPage(int page, int size, String type, String prop) {
        //creating pageable
        Pageable pageable = pageableHelper.createPageable(page, size, type, prop);
        //Fetch from DB
        Page<ContactMessage> messagePage = contactMessageRepository.findAll(pageable);
        //mapping entities into response DTO and returning them in a ResponseEntity
        return ResponseEntity.ok(messagePage.map(contactMessageMapper::mapContactMessageToContactMessageResponse));
    }

    /**
     * This method fetches messages belonged to the given email address.
     * @param email Email String sent by ContactMessageController::getMessagesByEmail()
     * @return ContactMessageResponse DTOS within a ResponseEntity object.
     */
    public ResponseEntity<List<ContactMessageResponse>> getByEmail(String email) {
        //validation
        propertyValidator.existsByEmail(email);
        //Fetch from DB
        List<ContactMessage> messagesByEmail = contactMessageRepository.findByEmail(email);
        //Mapping into DTO and returning in a ResponseEntity object
        return ResponseEntity.ok(
                messagesByEmail.stream()
                .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                .collect(Collectors.toList()));

    }
}

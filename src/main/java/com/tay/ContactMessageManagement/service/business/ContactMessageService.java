package com.tay.ContactMessageManagement.service.business;

import com.tay.ContactMessageManagement.dto.messages.ErrorMessages;
import com.tay.ContactMessageManagement.dto.request.ContactMessageRequest;
import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.entity.business.ContactMessage;
import com.tay.ContactMessageManagement.exceptions.ConflictException;
import com.tay.ContactMessageManagement.exceptions.ResourceNotFoundException;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
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
     *
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
     *
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
     * Fetches messages according to the subject param coming from Postman or FE
     *
     * @param searchParam String parameter sent by Controller method
     * @return ResponseEntitiy object within list of found messages
     * throws ResourceNotFoundException if no message is found.
     */
    public ResponseEntity<List<ContactMessageResponse>> searchBySubject(String searchParam) {
        List<ContactMessage> foundMessages = contactMessageRepository.findAllBySubjectLike(searchParam);
        if (foundMessages.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_BY_SUBJECT, searchParam));
        }
        return ResponseEntity.ok(
                foundMessages.stream()
                        .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                        .collect(Collectors.toList()));
    }

    /**
     * This method fetches messages belonged to the given email address.
     *
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

    /**
     * This method fetches the messages between two dates given as parameter.
     * @param startDate startDate in String type
     * @param endDate   endDate in String type
     * @return  ResponseEntity within a list of found messages
     */
    public ResponseEntity<List<ContactMessageResponse>> getByDate(String startDate, String endDate) {
        //parsing String dates into LocalDateTime
        LocalDateTime startDateTime = LocalDate.parse(startDate).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endDateTime = LocalDate.parse(endDate).atTime(LocalTime.MAX);
        //checking if the endDate is after startDate
        if (startDateTime.isAfter(endDateTime)) {
            throw new ConflictException("Start Date can not be after end date.");
        }
        //DB fetch
        List<ContactMessage> foundMessages = contactMessageRepository.findAllBetweenDates(startDateTime, endDateTime);
        //checking if it is empty
        if (foundMessages.isEmpty())
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_BY_DATE, startDate, endDate));

        return ResponseEntity.ok(
                foundMessages.stream()
                        .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                        .collect(Collectors.toList()));
    }

    /**
     * This method fetches the messages between two times given as parameter.
     * @param startTime startTime in String
     * @param endTime endTime in String
     * @return ResponseEntity within a list of found messages
     */
    public ResponseEntity<List<ContactMessageResponse>> getByTime(String startTime, String endTime) {
        //parsing String times into LocalTime. This is just for validation.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime firstTime = LocalTime.parse(startTime, formatter);
        LocalTime secondTime = LocalTime.parse(endTime, formatter);
        //DB fetch
        List<ContactMessage> foundMessages = contactMessageRepository.findAllBetweenTimes(firstTime.toString(), secondTime.toString());
        //checking if it is empty
        if (foundMessages.isEmpty())
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_BY_TIME, startTime, endTime));

        return ResponseEntity.ok(
                foundMessages.stream()
                        .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                        .collect(Collectors.toList()));
    }
}

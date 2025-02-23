package com.tay.ContactMessageManagement.controller;


import com.tay.ContactMessageManagement.dto.request.ContactMessageRequest;
import com.tay.ContactMessageManagement.dto.request.ContactMessageUpdateRequest;
import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    /**
     * @param contactMessageRequest ContactMessageRequest DTO from Postman or FE
     * @return  a ContactMessageResponse DTO mapped from saved Entity within ResponseEntity object.
     */
    @PostMapping("/save")
    public ResponseEntity<ContactMessageResponse> saveContactMessage(
            @RequestBody @Valid ContactMessageRequest contactMessageRequest){
        return contactMessageService.saveMessage(contactMessageRequest);
    }

    /**
     * @return a ContactMessageResponse DTO List object within ResponseEntity.
     */
    @GetMapping("/getall")
    public ResponseEntity<List<ContactMessageResponse>> getAllContactMessages(){
        return contactMessageService.getAll();
    }

    /**
     * This method fetches all messages by page
     * @param page Index number of page
     * @param size How many items should be fetched per page
     * @param type Type of direction (ASC or DESC)
     * @param prop Which property will be used for sorting.
     * @return a Page object consist of Response DTOs within a ResponseEntity object.
     */
    @GetMapping("/getbypage")
    public ResponseEntity<Page<ContactMessageResponse>> getMessagesByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String type,
            @RequestParam(defaultValue = "0") String prop
    ){
        return contactMessageService.getByPage(page, size, type, prop);
    }

    /**
     * Fetches messages according to the subject param coming from Postman or FE
     * @param searchParam String parameter
     * @return  ResponseEntitiy object within list of found messages
     */
    @GetMapping("/searchbysubject")
    public ResponseEntity<List<ContactMessageResponse>> searchMessagesBySubject(@RequestParam String searchParam){
        return contactMessageService.searchBySubject(searchParam);
    }

    /**
     * This method fetches messages belonged to the given email address.
     * @param email Email String sent by Postman or FE
     * @return ContactMessageResponse DTOS within a ResponseEntity object.
     */
    @GetMapping("/getbyemail")
    public ResponseEntity<List<ContactMessageResponse>> getMessagesByEmail(@RequestParam String email){
        return contactMessageService.getByEmail(email);
    }

    /**
     * This method fetches the messages between two dates given as parameter.
     * @param startDate startDate in String type
     * @param endDate   endDate in String type
     * @return  ResponseEntity within a list of found messages
     */
    //TODO - DateTimeParseException must be handled too.
    @GetMapping("/getbydates")
    public ResponseEntity<List<ContactMessageResponse>> getMessagesByDate(@RequestParam String startDate, @RequestParam String endDate){
        return contactMessageService.getByDate(startDate, endDate);
    }

    /**
     * This method fetches the messages between two times given as parameter.
     * @param startTime startTime in String
     * @param endTime endTime in String
     * @return ResponseEntity within a list of found messages
     */

    @GetMapping("/getbytimes")
    public ResponseEntity<List<ContactMessageResponse>> getMessagesByTime(@RequestParam String startTime, @RequestParam String endTime){
        return contactMessageService.getByTime(startTime, endTime);
    }

    /**
     * @param id id of the message to be deleted
     * @return ResponseEntity within a success message in String
     */
    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteMessageById(@PathVariable Long id){
        return contactMessageService.deleteById(id);
    }

    /**
     * @param id id of the message to be deleted
     * @return ResponseEntity within a success message in String
     */
    @DeleteMapping("/del")
    public ResponseEntity<String> deleteMessageByIdParam(@RequestParam Long id){
        return contactMessageService.deleteById(id);
    }

    /**
     * This method updates the message with given id and request body
     * @param id Id value of message to be updated
     * @param contactMessageUpdateRequest request body for update
     * @return ResponseEntity within the updated message.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ContactMessageResponse> updateMessageById(
            @PathVariable Long id,
            @RequestBody @Valid ContactMessageUpdateRequest contactMessageUpdateRequest){
        return contactMessageService.updateMessage(id, contactMessageUpdateRequest);
    }

    @GetMapping("/getByUserId/{userId}")
    public List<ContactMessageResponse> getMessagesByUserId(@PathVariable Long userId){
        return contactMessageService.getByUserId(userId);
    }


}

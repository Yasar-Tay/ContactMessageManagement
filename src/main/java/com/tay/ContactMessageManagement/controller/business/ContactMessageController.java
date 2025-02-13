package com.tay.ContactMessageManagement.controller.business;

import com.tay.ContactMessageManagement.dto.request.ContactMessageRequest;
import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.service.business.ContactMessageService;
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
     *
     * @param contactMessageRequest ContactMessageRequest DTO from Postman or FE
     * @return  a ContactMessageResponse DTO mapped from saved Entity within ResponseEntity object.
     */
    @PostMapping("/save")
    public ResponseEntity<ContactMessageResponse> saveContactMessage(
            @RequestBody @Valid ContactMessageRequest contactMessageRequest){
        return contactMessageService.saveMessage(contactMessageRequest);
    }

    /**
     *
     * @return a ContactMessageResponse DTO List object within ResponseEntity.
     */
    //TODO - Only Admin should be able to reach this endpoint
    @GetMapping("/getall")
    public ResponseEntity<List<ContactMessageResponse>> getAllMessages(){
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
    //TODO - Only Admin should be able to reach this endpoint
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
     * This method fetches messages belonged to the given email address.
     * @param email Email String sent by Postman or FE
     * @return ContactMessageResponse DTOS within a ResponseEntity object.
     */
    @GetMapping("/getbyemail")
    public ResponseEntity<List<ContactMessageResponse>> getMessagesByEmail(@RequestParam String email){
        return contactMessageService.getByEmail(email);
    }
}

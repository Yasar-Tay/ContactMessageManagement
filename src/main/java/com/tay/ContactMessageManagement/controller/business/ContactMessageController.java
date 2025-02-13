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
     * @return  ResponseEntity object within a ContactMessageResponse DTO mapped from saved Entity.
     */
    @PostMapping("/save")
    public ResponseEntity<ContactMessageResponse> saveContactMessage(
            @RequestBody @Valid ContactMessageRequest contactMessageRequest){
        return contactMessageService.saveMessage(contactMessageRequest);
    }

    /**
     *
     * @return ResponseEntity object within a ContactMessageResponse DTO List.
     */
    //TODO - Only Admin should be able to reach this endpoint
    @GetMapping("/getall")
    public ResponseEntity<List<ContactMessageResponse>> getAllMessages(){
        return contactMessageService.getAll();
    }

    /**
     * Parameters sent by Postman or FE
     * @param page Index number of page
     * @param size How many items should be fetched per page
     * @param type Type of direction (ASC or DESC)
     * @param prop Which property will be used for sorting.
     * @return ResponseEntity object within a Page object consist of ContactMessageResponse DTOs.
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

    @GetMapping("/getbyemail")
    public ResponseEntity<List<ContactMessageResponse>> getMessagesByEmail(@RequestParam String email){
        return contactMessageService.getByEmail(email);
    }
}

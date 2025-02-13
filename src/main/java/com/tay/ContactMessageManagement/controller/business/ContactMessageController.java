package com.tay.ContactMessageManagement.controller.business;

import com.tay.ContactMessageManagement.dto.request.ContactMessageRequest;
import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.service.business.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping
    public ResponseEntity<ContactMessageResponse> saveContactMessage(
            @RequestBody @Valid ContactMessageRequest contactMessageRequest){
        return contactMessageService.saveMessage(contactMessageRequest);
    }

    //TODO Delete later. Added for test purposes
    @GetMapping
    public ResponseEntity<List<ContactMessageResponse>> getAllMessages(){
        return contactMessageService.getAll();
    }
}

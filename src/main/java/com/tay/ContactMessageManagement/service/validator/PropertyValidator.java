package com.tay.ContactMessageManagement.service.validator;


import com.tay.ContactMessageManagement.dto.messages.ErrorMessages;
import com.tay.ContactMessageManagement.exceptions.ResourceNotFoundException;
import com.tay.ContactMessageManagement.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertyValidator {

    private final ContactMessageRepository contactMessageRepository;

    /**
     * Checks if given email exists in DB. If not, throws ResourceNotFoundException
     * @param email Email String to be checked
     */
    public void existsByEmail(String email){
        if (!contactMessageRepository.existsByEmail(email)){
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_BY_EMAIL, email));
        }
    }
}

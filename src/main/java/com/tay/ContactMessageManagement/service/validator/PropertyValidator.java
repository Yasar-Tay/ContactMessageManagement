package com.tay.ContactMessageManagement.service.validator;


import com.tay.ContactMessageManagement.dto.messages.ErrorMessages;
import com.tay.ContactMessageManagement.exceptions.ResourceNotFoundException;
import com.tay.ContactMessageManagement.repository.ContactMessageRepository;
import com.tay.ContactMessageManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertyValidator {

    private final ContactMessageRepository contactMessageRepository;
    private final UserRepository userRepository;

    /**
     * Checks if given email exists in DB. If not, throws ResourceNotFoundException
     * @param email Email String to be checked
     */
    public void contactMessageExistsByEmail(String email){
        if (!contactMessageRepository.existsByEmail(email)){
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_BY_EMAIL, email));
        }
    }

    public void userExistsByEmail(String email){
        if (userRepository.existsByEmail(email)){
            throw new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_BY_EMAIL, email));
        }
    }


}

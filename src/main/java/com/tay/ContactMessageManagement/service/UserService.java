package com.tay.ContactMessageManagement.service;

import com.tay.ContactMessageManagement.dto.request.UserRequest;
import com.tay.ContactMessageManagement.dto.response.UserResponse;
import com.tay.ContactMessageManagement.entity.User;
import com.tay.ContactMessageManagement.exceptions.ResourceNotFoundException;
import com.tay.ContactMessageManagement.mapper.UserMapper;
import com.tay.ContactMessageManagement.repository.UserRepository;
import com.tay.ContactMessageManagement.service.validator.PropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PropertyValidator propertyValidator;
    private final UserMapper userMapper;

    public ResponseEntity<UserResponse> saveUser(@Valid UserRequest userRequest) {
        propertyValidator.userExistsByEmail(userRequest.getEmail());
        User savedUser = userRepository.save(userMapper.mapUserRequestToUser(userRequest));
        return new ResponseEntity<>(userMapper.mapUserToUserResponse(savedUser), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteById(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("No user with this id!");
        }
        userRepository.deleteById(userId);
        return ResponseEntity.ok(String.format("User with Id: %s deleted successfully", userId));
    }

    public ResponseEntity<UserResponse> getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("No such user!"));
        return ResponseEntity.ok(userMapper.mapUserToUserResponse(user));
    }
}

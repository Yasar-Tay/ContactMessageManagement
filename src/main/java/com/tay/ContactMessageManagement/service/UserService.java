package com.tay.ContactMessageManagement.service;

import com.tay.ContactMessageManagement.dto.request.UserRequest;
import com.tay.ContactMessageManagement.dto.response.UserResponse;
import com.tay.ContactMessageManagement.entity.User;
import com.tay.ContactMessageManagement.exceptions.ResourceNotFoundException;
import com.tay.ContactMessageManagement.mapper.UserMapper;
import com.tay.ContactMessageManagement.repository.UserRepository;
import com.tay.ContactMessageManagement.service.helper.PageableHelper;
import com.tay.ContactMessageManagement.service.validator.PropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PropertyValidator propertyValidator;
    private final UserMapper userMapper;
    private final PageableHelper pageableHelper;

    public ResponseEntity<UserResponse> saveUser(@Valid UserRequest userRequest) {
        propertyValidator.userExistsByEmail(userRequest.getEmail());
        User savedUser = userRepository.save(userMapper.mapUserRequestToUser(userRequest));
        return new ResponseEntity<>(userMapper.mapUserToUserResponse(savedUser), HttpStatus.CREATED);
    }


    public Page<UserResponse> getAllByPage(int page, int size, String type, String prop) {
        Pageable pageable = pageableHelper.createPageable(page, size, type, prop);
        return userRepository.findAll(pageable).map(userMapper::mapUserToUserResponse);
    }

    public ResponseEntity<String> deleteById(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("No user with this id!");
        }
        userRepository.deleteById(userId);
        return ResponseEntity.ok(String.format("User with Id: %s deleted successfully", userId));
    }
}

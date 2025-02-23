package com.tay.ContactMessageManagement.mapper;

import com.tay.ContactMessageManagement.dto.request.UserRequest;
import com.tay.ContactMessageManagement.dto.response.UserResponse;
import com.tay.ContactMessageManagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserRequestToUser(UserRequest userRequest){
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .build();
    }

    public UserResponse mapUserToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .contactMessages(user.getContactMessages())
                .build();
    }
}

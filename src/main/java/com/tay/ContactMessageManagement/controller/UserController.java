package com.tay.ContactMessageManagement.controller;

import com.tay.ContactMessageManagement.dto.request.UserRequest;
import com.tay.ContactMessageManagement.dto.response.UserResponse;
import com.tay.ContactMessageManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

   @GetMapping("/{userId}")
   public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        return userService.getById(userId);
   }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        return userService.deleteById(userId);
    }
}

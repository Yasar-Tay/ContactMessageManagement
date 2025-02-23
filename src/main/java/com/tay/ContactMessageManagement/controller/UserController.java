package com.tay.ContactMessageManagement.controller;

import com.tay.ContactMessageManagement.dto.request.UserRequest;
import com.tay.ContactMessageManagement.dto.response.UserResponse;
import com.tay.ContactMessageManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping
    public Page<UserResponse> getAllUsersByPage(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "DESC") String type,
                                                @RequestParam(defaultValue = "0") String prop) {
        return userService.getAllByPage(page, size, type, prop);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        return userService.deleteById(userId);
    }
}

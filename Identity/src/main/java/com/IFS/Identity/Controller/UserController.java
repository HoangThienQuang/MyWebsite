package com.IFS.Identity.Controller;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Service.UserService;
import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.request.UserCreationRequest;
import com.IFS.Identity.dto.request.UserUpdateRequest;
import com.IFS.Identity.dto.response.ApiResponseSuccess;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping("/users")
    ApiResponseSuccess<User> createUser(@Valid @RequestBody UserCreationRequest request)
    {
        ApiResponseSuccess<User> apiResponseSuccess = new ApiResponseSuccess<>();

        apiResponseSuccess.setCode(ResponseCode.SUCCESS_STATUS.getCode());
        apiResponseSuccess.setMessage(ResponseCode.SUCCESS_STATUS.getMessage());
        apiResponseSuccess.setData(userService.createRequest(request));

        return apiResponseSuccess;
    }

    //read
    @GetMapping("/allUsers")
    List<User> getAllUser()
    {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") String userId)
    {
        return userService.getUserById(userId);
    }

    //update
    @PutMapping("/{userId}")
    User updateUserById(@Valid @RequestBody UserUpdateRequest request, @PathVariable("userId") String userId)
    {
        return userService.updateUserById(request,userId);
    }

    //delete
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId)
    {
        userService.deleteUserById(userId);
        return "user has been deleted";
    }

//    @DeleteMapping("/user/{userName}")
//    String deleteUserByName(@PathVariable("userName") String userName)
//    {
//        userService.deleteUserByUserName(userName);
//        return "user has been deleted";
//    }

}

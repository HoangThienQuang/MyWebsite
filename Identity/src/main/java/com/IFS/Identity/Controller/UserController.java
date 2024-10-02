package com.IFS.Identity.Controller;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Service.UserService;
import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.request.UserCreationRequest;
import com.IFS.Identity.dto.request.UserUpdateRequest;
import com.IFS.Identity.dto.response.ApiResponseSuccess;
import com.IFS.Identity.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping("/users")
    ApiResponseSuccess<User> createUser(@Valid @RequestBody UserCreationRequest request)
    {
//        ApiResponseSuccess<User> apiResponseSuccess = new ApiResponseSuccess<>();
//
//        apiResponseSuccess.setCode(ResponseCode.SUCCESS_STATUS.getCode());
//        apiResponseSuccess.setMessage(ResponseCode.SUCCESS_STATUS.getMessage());
//        apiResponseSuccess.setData(userService.createRequest(request));

        return ApiResponseSuccess.<User>builder()
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message(ResponseCode.SUCCESS_STATUS.getMessage())
                .data(userService.createRequest(request))
                .build();
        //return apiResponseSuccess;
    }

    //read
    @GetMapping("/allUsers")
    List<User> getAllUser()
    {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId)
    {
        return userService.getUserById(userId);
    }

    //update
    @PutMapping("/{userId}")
    UserResponse updateUserById(@Valid @RequestBody UserUpdateRequest request, @PathVariable("userId") String userId)
    {
        return userService.updateUserById(request,userId);
    }

    //delete
    @DeleteMapping("/{userId}")
    ApiResponseSuccess deleteUser(@PathVariable("userId") String userId)
    {
//        ApiResponseSuccess apiResponseSuccess = new ApiResponseSuccess<>();
//        apiResponseSuccess.setCode(ResponseCode.SUCCESS_STATUS.getCode());
//        apiResponseSuccess.setMessage("User has been delete");
//        userService.deleteUserById(userId);
        //return apiResponseSuccess;
        return ApiResponseSuccess.builder()
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message("User has been delete")
                .build();
    }

//    @DeleteMapping("/user/{userName}")
//    String deleteUserByName(@PathVariable("userName") String userName)
//    {
//        userService.deleteUserByUserName(userName);
//        return "user has been deleted";
//    }

}

package com.IFS.Identity.Controller;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Service.UserService;
import com.IFS.Identity.dto.request.UserCreationRequest;
import com.IFS.Identity.dto.request.UserUpdateRequest;
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
    User createUser(@Valid @RequestBody UserCreationRequest request)
    {
        return userService.createRequest(request);
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
    @DeleteMapping("{userId}")
    String deleteUser(@PathVariable("userId") String userId)
    {
        userService.deleteUserById(userId);
        return "user has been deleted";
    }

}

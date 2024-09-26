package com.IFS.Identity.Controller;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Service.UserService;
import com.IFS.Identity.dto.request.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    User createUser(@RequestBody UserCreationRequest request)
    {
        return userService.createRequest(request);
    }

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
//    @PutMapping("/{userName}")

}

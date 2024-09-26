package com.IFS.Identity.Service;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Repositoty.UserRepository;
import com.IFS.Identity.dto.request.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createRequest(UserCreationRequest request)
    {
        User user = new User();

        user.setUserName(request.getUserName());
        user.setPassWord(request.getPassWord());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }
}

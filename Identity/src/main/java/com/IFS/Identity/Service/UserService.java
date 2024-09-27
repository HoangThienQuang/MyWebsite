package com.IFS.Identity.Service;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Exception.AppException;
import com.IFS.Identity.Repositoty.UserRepository;
import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.request.UserCreationRequest;
import com.IFS.Identity.dto.request.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createRequest(UserCreationRequest request){
        User user = new User();

        if (userRepository.existsByUserName(request.getUserName()))
            throw new AppException(ResponseCode.USER_EXISTED);

        user.setUserName(request.getUserName());
        user.setPassWord(request.getPassWord());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        //user.setBirthDay(request.getBirthDay());

        return userRepository.save(user);
    }

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

    public User getUserById(String userId)
    {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUserById(UserUpdateRequest request, String userId)
    {
        User user = getUserById(userId);

        user.setPassWord(request.getPassWord());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        //user.setBirthDay(request.getBirthDay());
        user.setPhoneNumber(request.getPhoneNumber());

        return userRepository.save(user);
    }

    public void deleteUserById(String userId)
    {
        userRepository.deleteById(userId);
    }

    //public void deleteUserByUserName(String userName){ userRepository.deleteByuserName(userName);}
}

package com.IFS.Identity.Service;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.Exception.AppException;
import com.IFS.Identity.Mapper.UserMapper;
import com.IFS.Identity.Repositoty.UserRepository;
import com.IFS.Identity.dto.ResponseCode;
import com.IFS.Identity.dto.request.UserCreationRequest;
import com.IFS.Identity.dto.request.UserUpdateRequest;
import com.IFS.Identity.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.mapstruct.Mapper;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponse createRequest(UserCreationRequest request){
        if (userRepository.existsByUserName(request.getUserName()))
            throw new AppException(ResponseCode.USER_EXISTED);

//        User user = new User();
//
//        user.setUserName(request.getUserName());
//        user.setPassWord(request.getPassWord());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        //user.setBirthDay(request.getBirthDay());
//        user.setPhoneNumber(request.getPhoneNumber());
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassWord(passwordEncoder.encode(request.getPassWord()));

        //return userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

    public UserResponse getUserById(String userId)
    {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new AppException(ResponseCode.USER_ID_INVALID)));
        //return userRepository.findById(userId).orElseThrow(() -> new AppException(ResponseCode.USER_ID_INVALID));
    }

    public UserResponse updateUserById(UserUpdateRequest request, String userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ResponseCode.USER_ID_INVALID));
        userMapper.updateRequest(user, request);
//        user.setPassWord(request.getPassWord());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        //user.setBirthDay(request.getBirthDay());
//        user.setPhoneNumber(request.getPhoneNumber());
        UserResponse userResponse = userMapper.toUserResponse(userRepository.save(user));
        userResponse.setId(user.getId());
        return userResponse;
    }

    public void deleteUserById(String userId)
    {
        userRepository.deleteById(userId);
    }

    //public void deleteUserByUserName(String userName){ userRepository.deleteByuserName(userName);}
}

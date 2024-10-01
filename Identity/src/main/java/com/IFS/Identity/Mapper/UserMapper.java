package com.IFS.Identity.Mapper;

import com.IFS.Identity.Entity.User;
import com.IFS.Identity.dto.request.UserCreationRequest;
import com.IFS.Identity.dto.request.UserUpdateRequest;
import com.IFS.Identity.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateRequest(@MappingTarget User user, UserUpdateRequest request);
    UserResponse toUserResponse(User user);
}

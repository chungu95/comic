package com.comic.core.mappers;

import com.comic.core.domain.UserProfile;
import com.comic.core.entity.User;
import com.comic.users.domain.RegisterRequest;
import com.comic.users.domain.UpdateProfileRequest;
import com.comic.users.domain.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserProfile mapFromUserToUserProfile(User user);

    @Mapping(target = "username", source = "email")
    User mapFromRegisterRequestToUser(RegisterRequest registerRequest);

    UserDto mapFromUserToUserDto(User user);

    @Mapping(target = "id", ignore = true)
    void merge(@MappingTarget User user, UpdateProfileRequest updateProfileRequest);
}

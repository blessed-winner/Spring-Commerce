package com.xenon.store.mappers;

import com.xenon.store.dto.RegisterUserRequest;
import com.xenon.store.dto.UpdateUserRequest;
import com.xenon.store.dto.UserDto;
import com.xenon.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
  User toEntity(RegisterUserRequest request);
  void update(UpdateUserRequest request, @MappingTarget User user);
}

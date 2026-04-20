package com.xenon.luxe.mappers;

import com.xenon.luxe.dto.RegisterUserRequest;
import com.xenon.luxe.dto.UpdateUserRequest;
import com.xenon.luxe.dto.UserDto;
import com.xenon.luxe.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
  User toEntity(RegisterUserRequest request);
  void update(UpdateUserRequest request, @MappingTarget User user);
}

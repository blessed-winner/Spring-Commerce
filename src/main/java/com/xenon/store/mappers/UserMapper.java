package com.xenon.store.mappers;

import com.xenon.store.dto.UserDto;
import com.xenon.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
}

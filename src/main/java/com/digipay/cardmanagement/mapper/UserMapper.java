package com.digipay.cardmanagement.mapper;

import com.digipay.cardmanagement.dto.UserDto;
import com.digipay.cardmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {CardMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}

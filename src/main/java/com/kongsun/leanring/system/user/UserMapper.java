package com.kongsun.leanring.system.user;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                UserService.class
        }
)
public interface UserMapper {
     UserResponse toUserResponse(User user);
}

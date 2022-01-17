package com.user.service.userservice.service;

import com.user.service.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService  extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserDetailsByEmail(String email);
}

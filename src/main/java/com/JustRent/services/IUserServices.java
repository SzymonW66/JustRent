package com.JustRent.services;

import com.JustRent.dto.UserDto;
import com.JustRent.exceptions.UserAlreadyExistException;
import com.JustRent.models.User;

import java.util.List;

public interface IUserServices {
    User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();

}
